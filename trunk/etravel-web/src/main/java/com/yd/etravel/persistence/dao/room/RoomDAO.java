/**
 * 
 */
package com.yd.etravel.persistence.dao.room;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yd.etravel.domain.custom.room.RoomSearchDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 11:39:40 AM Type :
 *         com.yd.etravel.persistence.dao.room.RoomDAO
 * 
 */
public class RoomDAO extends BaseDAO implements IRoomDAO {

    public boolean isExist(Long hotelId, Long roomTypeId, Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder IS_ROOM_EXIST = new StringBuilder(
		    "SELECT room FROM Room as room where ")
		    .append("room.hotel.id=:hid AND room.roomType.id=:typeid ");

	    if (id != null) {
		IS_ROOM_EXIST.append(" AND room.id != :id");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_ROOM_EXIST.toString());
	    query.setParameter("hid", hotelId);
	    query.setParameter("typeid", roomTypeId);

	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<Room> findRooms(RoomSearchDTO roomSearchDTO)
	    throws PersistenceException {
	try {
	    StringBuilder stringBuilder = new StringBuilder(
		    " from Room as room where 1=1 ");
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    if (roomSearchDTO.getHotelId() != null) {
		stringBuilder.append(" and room.hotel.id=:hid ");
	    }

	    if (roomSearchDTO.getRoomTypeId() != null) {
		stringBuilder.append(" and room.roomType.id=:tid ");
	    }

	    Query query = session.createQuery(stringBuilder.toString());

	    if (roomSearchDTO.getHotelId() != null) {
		query.setParameter("hid", roomSearchDTO.getHotelId());
	    }

	    if (roomSearchDTO.getRoomTypeId() != null) {
		query.setParameter("tid", roomSearchDTO.getRoomTypeId());
	    }

	    return query.list();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<Room> findAllRoomWithRoomType(Long hotelId)
	    throws PersistenceException {
	final StringBuilder sb = new StringBuilder(
		"SELECT room FROM Room as room join fetch  room.roomType as roomType"
			+ " JOIN room.hotel as hot where room.active=1");

	try {

	    if (hotelId != null) {
		sb.append(" AND hot.id=:hid ");
	    }

	    Query query = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession().createQuery(sb.toString());

	    if (hotelId != null) {
		query.setParameter("hid", hotelId);
	    }

	    return query.list();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }
}
