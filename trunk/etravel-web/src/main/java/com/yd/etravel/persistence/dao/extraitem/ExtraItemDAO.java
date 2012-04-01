/**
 * 
 */
package com.yd.etravel.persistence.dao.extraitem;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.extraitem.ExtraItem;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 14, 2009 : 4:55:49 PM Type :
 *         com.yd.etravel.persistence.dao.extraitem.ExtraItemDAO
 * 
 */
public class ExtraItemDAO extends BaseDAO implements IExtraItemDAO {

    public List<ExtraItemBooking> findByBookingId(Long bookingId)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(" ExtraItemBooking as obj join fetch obj.extraItem join obj.booking bkg WHERE bkg.id = :id ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", bookingId);
	    return (List<ExtraItemBooking>) query.list();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.persistence.dao.extraitem.IExtraItemDAO#isExtraItemExist
     * (java.lang.String, java.lang.Long)
     */
    public boolean isExist(String name, String code, Long id)
	    throws PersistenceException {
	try {
	    StringBuilder sb = new StringBuilder(
		    "SELECT item FROM ExtraItem as item where ");

	    if (id != null) {
		sb.append(" item.id != :id ");
		sb.append(" AND (item.name= :name " + "OR item.code = :code)");
	    } else {
		sb.append(" UPPER(item.name)= UPPER(:name) "
			+ "OR UPPER(item.code)= UPPER(:code)");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("name", name);
	    query.setParameter("code", code);
	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public Object findById(Class cls, Long id) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(cls.getName())
		    .append(" as obj left join fetch obj.hotel WHERE obj.id = :id ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);
	    List results = query.list();
	    return results.isEmpty() ? null : results.get(0);

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<ExtraItem> findAllByHotelId(Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(ExtraItem.class.getName()).append(
			    " as obj join fetch obj.hotel h WHERE h.id = :id ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);
	    return query.list();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public int deleteAny(Class cls, Long id) throws PersistenceException {
	try {

	    final StringBuilder sb = new StringBuilder("delete from ").append(
		    cls.getName()).append(" as obj Where obj.id = (:id) ");
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    Query sqlQuery = session
		    .createSQLQuery("delete from T_ITEM_HOTEL where FK_ITEM_ID=:id");
	    sqlQuery.setParameter("id", id);
	    sqlQuery.executeUpdate();

	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    return query.executeUpdate();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

}