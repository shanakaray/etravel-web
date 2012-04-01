package com.yd.etravel.persistence.dao.pax;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yd.etravel.domain.custom.pax.PaxSearchDTO;
import com.yd.etravel.domain.hotel.Pax;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:38:41 PM Type :
 *         com.yd.etravel.persistence.dao.pax.PaxDAO
 * 
 */
public class PaxDAO extends BaseDAO implements IPaxDAO {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.persistence.dao.pax.IPaxDAO.isPaxTypeExist(java.lang.Long
     * , java.lang.String)
     */
    public boolean isPaxTypeExist(Long hotelId, Long id)
	    throws PersistenceException {
	try {
	    StringBuilder IS_PAX_TYPE_EXIST = new StringBuilder(
		    "SELECT pax FROM Pax as pax where ")
		    .append("pax.hotel.id=:hid");

	    if (id != null) {
		IS_PAX_TYPE_EXIST.append(" AND pax.id != :id");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_PAX_TYPE_EXIST.toString());
	    query.setParameter("hid", hotelId);

	    if (id != null) {
		query.setParameter("id", id);
	    }
	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<Pax> findPax(PaxSearchDTO paxSearchDTO)
	    throws PersistenceException {
	try {
	    StringBuilder sb = new StringBuilder(
		    "select pax from Pax as pax where 1=1 ");
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    if (paxSearchDTO.getType() != null) {
		sb.append(" and pax.type=:type ");
	    }

	    if (paxSearchDTO.getHotelId() != null) {
		sb.append(" and pax.hotel.id=:hid ");
	    }

	    Query query = session.createQuery(sb.toString());

	    if (paxSearchDTO.getType() != null) {
		query.setParameter("type", paxSearchDTO.getType());
	    }

	    if (paxSearchDTO.getHotelId() != null) {
		query.setParameter("hid", paxSearchDTO.getHotelId());
	    }

	    return query.list();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

}
