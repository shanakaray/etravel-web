/**
 * 
 */
package com.yd.etravel.persistence.dao.hotel;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 9:34:05 PM Type :
 *         com.yd.etravel.persistence.dao.hotel.HotelDAO
 * 
 */
@Repository
public class HotelDAO extends BaseDAO implements IHotelDAO {

    final static StringBuilder FIND_HOTEL_WITH_USER = new StringBuilder(
	    "SELECT hot FROM ").append(Hotel.class.getName()).append(
	    " as hot inner join fetch hot.superUser WHERE hot.id=:pk");

    @Override
    public boolean isHotelNameExist(final String name, final Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder IS_HOTEL_NAME_EXIST = new StringBuilder(
		    "SELECT hotel FROM Hotel as hotel where ")
		    .append(" UPPER(hotel.name)= UPPER(:name)");

	    if (id != null) {
		IS_HOTEL_NAME_EXIST.append(" AND hotel.id != :id ");
	    }

	    final Query query = getCurrentSession().createQuery(
		    IS_HOTEL_NAME_EXIST.toString());
	    query.setParameter("name", name);
	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return !query.list().isEmpty();
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public Hotel findHotelWithUser(final Long id) throws PersistenceException {
	try {
	    final Query query = getCurrentSession().createQuery(
		    FIND_HOTEL_WITH_USER.toString());
	    query.setParameter("pk", id);
	    final List<Hotel> list = query.list();
	    return list.isEmpty() ? null : list.get(0);
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public int deleteHotel(final Long id) throws PersistenceException {
	try {
	    final Hotel hotel = (Hotel) getCurrentSession()
		    .get(Hotel.class, id);
	    hotel.getSuperUser().clear();
	    getCurrentSession().delete(hotel);
	    return 1;
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

}
