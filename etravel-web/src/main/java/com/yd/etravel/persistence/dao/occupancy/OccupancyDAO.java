/**
 * 
 */
package com.yd.etravel.persistence.dao.occupancy;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yd.etravel.domain.occupancy.Occupancy;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.util.IConstants.IOccupancy;

public class OccupancyDAO extends BaseDAO implements IOccupancyDAO {

    final static StringBuilder IS_OCCUPANCY_NAME_EXIST = new StringBuilder(
	    "SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy where ")
	    .append(" UPPER(occupancy.name)= UPPER(:name) ");

    final static StringBuilder FIND_ALL_OCCUPANCY_WITH_ROOM_TYPE = new StringBuilder(
	    "SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy join fetch  occupancy.roomType as roomType");

    final static StringBuilder FIND_ALL_OCCUPANCY_BY_PAX_INFO = new StringBuilder(
	    "SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy  "
		    + "where occupancy.adult=? And occupancy.child=? And occupancy.infant=?");

    final static StringBuilder FIND_ALL_OCCUPANCY_BY_ROOM_TYPE = new StringBuilder(
	    "SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy join fetch  occupancy.roomType as roomType  "
		    + "where roomType.id=? ");

    final static StringBuilder FIND_ALL_OCCUPANCY_BY_OCC_NAME = new StringBuilder(
	    "SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy "
		    + "where occupancy.name=? ");

    /**
	 * 
	 */
    public OccupancyDAO() {
    }

    public boolean isOccupancyNameExist(final String occupancyName, Long id)
	    throws PersistenceException {
	try {

	    StringBuilder IS_OCCUPANCY_NAME_EXIST = new StringBuilder(
		    "SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy where ")
		    .append(" UPPER(occupancy.name)= UPPER(:name) ");

	    if (id != null) {
		IS_OCCUPANCY_NAME_EXIST.append(" AND occupancy.id != :id");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_OCCUPANCY_NAME_EXIST
		    .toString());
	    query.setParameter("name", occupancyName);
	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @SuppressWarnings("unchecked")
    public List<Occupancy> findAllOccupancyWithRoomType()
	    throws PersistenceException {
	try {
	    return (List<Occupancy>) getHibernateTemplate().find(
		    FIND_ALL_OCCUPANCY_WITH_ROOM_TYPE.toString());

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public boolean findAllOccupancyByPaxInfo(final Occupancy occupancy)
	    throws PersistenceException {

	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(FIND_ALL_OCCUPANCY_BY_PAX_INFO
		    .toString());
	    query.setParameter(0, occupancy.getAdult());
	    query.setParameter(1, occupancy.getChild());
	    query.setParameter(2, occupancy.getInfant());

	    return (query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<Occupancy> findAllOccupancyByRoomType(Long roomTypeId)
	    throws PersistenceException {
	try {
	    List<Occupancy> results = new ArrayList<Occupancy>();
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query1 = session.createQuery(FIND_ALL_OCCUPANCY_BY_OCC_NAME
		    .toString());
	    query1.setParameter(0, IOccupancy.COMMON_OCCUPANCY_NAME);
	    results.addAll(query1.list());

	    Query query = session.createQuery(FIND_ALL_OCCUPANCY_BY_ROOM_TYPE
		    .toString());
	    query.setParameter(0, roomTypeId);

	    results.addAll(query.list());

	    return results;
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }
}
