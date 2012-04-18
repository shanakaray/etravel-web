/**
 * 
 */
package com.yd.etravel.persistence.dao.hotel;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 9:34:05 PM Type :
 *         com.yd.etravel.persistence.dao.hotel.HotelDAO
 * 
 */
public class HotelDAO extends BaseDAO implements IHotelDAO {

    final static StringBuilder FIND_HOTEL_WITH_USER = new StringBuilder(
	    "SELECT hot FROM ").append(Hotel.class.getName()).append(
	    " as hot inner join fetch hot.superUser WHERE hot.id=:pk");

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.persistence.dao.hotel.IHotelDAO#isHotelNameExist(java.
     * lang.String)
     */
    public boolean isHotelNameExist(String name, Long id)
	    throws PersistenceException {
	try {
	    StringBuilder IS_HOTEL_NAME_EXIST = new StringBuilder(
		    "SELECT hotel FROM Hotel as hotel where ")
		    .append(" UPPER(hotel.name)= UPPER(:name)");

	    if (id != null) {
		IS_HOTEL_NAME_EXIST.append(" AND hotel.id != :id ");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_HOTEL_NAME_EXIST.toString());
	    query.setParameter("name", name);
	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public Hotel findHotelWithUser(Long id) throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(FIND_HOTEL_WITH_USER.toString());
	    query.setParameter("pk", id);
	    List<Hotel> list = query.list();
	    return list.isEmpty() ? null : list.get(0);
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public int deleteHotel(Long id) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("delete from ").append(
		    Hotel.class.getName()).append(
		    " as obj Where obj.id = (:id) ");
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    Query sqlQuery = session
		    .createSQLQuery("delete from T_HOTEL_USER where FK_HOTEL_ID=:id");
	    sqlQuery.setParameter("id", id);
	    sqlQuery.executeUpdate();

	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    return query.executeUpdate();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

}
