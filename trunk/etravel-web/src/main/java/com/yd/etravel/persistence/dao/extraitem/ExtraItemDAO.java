/**
 * 
 */
package com.yd.etravel.persistence.dao.extraitem;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

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
@Repository
public class ExtraItemDAO extends BaseDAO implements IExtraItemDAO {

    @Override
    public List<ExtraItemBooking> findByBookingId(final Long bookingId)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(" ExtraItemBooking as obj join fetch obj.extraItem join obj.booking bkg WHERE bkg.id = :id ");

	    final Query query = getCurrentSession().createQuery(sb.toString());
	    query.setParameter("id", bookingId);
	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
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
    @Override
    public boolean isExist(final String name, final String code, final Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder(
		    "SELECT item FROM ExtraItem as item where ");

	    if (id != null) {
		sb.append(" item.id != :id ");
		sb.append(" AND (item.name= :name " + "OR item.code = :code)");
	    } else {
		sb.append(" UPPER(item.name)= UPPER(:name) "
			+ "OR UPPER(item.code)= UPPER(:code)");
	    }

	    final Query query = getCurrentSession().createQuery(sb.toString());
	    query.setParameter("name", name);
	    query.setParameter("code", code);
	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return !query.list().isEmpty();
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public Object findById(final Class cls, final Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(cls.getName())
		    .append(" as obj left join fetch obj.hotel WHERE obj.id = :id ");

	    final Query query = getCurrentSession().createQuery(sb.toString());
	    query.setParameter("id", id);
	    final List results = query.list();
	    return results.isEmpty() ? null : results.get(0);

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List<ExtraItem> findAllByHotelId(final Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(ExtraItem.class.getName()).append(
			    " as obj join fetch obj.hotel h WHERE h.id = :id ");

	    final Query query = getCurrentSession().createQuery(sb.toString());
	    query.setParameter("id", id);
	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

}
