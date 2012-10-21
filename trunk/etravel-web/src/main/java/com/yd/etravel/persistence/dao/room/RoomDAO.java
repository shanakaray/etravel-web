/**
 * 
 */
package com.yd.etravel.persistence.dao.room;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yd.etravel.domain.custom.room.RoomSearchDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 11:39:40 AM Type :
 *         com.yd.etravel.persistence.dao.room.RoomDAO
 * 
 */
@Repository
public class RoomDAO extends BaseDAO<Room> implements IRoomDAO {

	@Override
	public List<Room> findAllRoomWithRoomType(final Long hotelId)
			throws PersistenceException {
		final StringBuilder sb = new StringBuilder(
				"SELECT room FROM Room as room join fetch  room.roomType as roomType"
						+ " JOIN room.hotel as hot where room.active=1");
		try {

			if (hotelId != null) {
				sb.append(" AND hot.id=:hid ");
			}

			final Query query = getCurrentSession().createQuery(sb.toString());

			if (hotelId != null) {
				query.setParameter("hid", hotelId);
			}

			return query.list();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<Room> findRooms(final RoomSearchDTO roomSearchDTO)
			throws PersistenceException {
		try {
			final StringBuilder stringBuilder = new StringBuilder(
					" from Room as room where 1=1 ");
			if (roomSearchDTO.getHotelId() != null) {
				stringBuilder.append(" and room.hotel.id=:hid ");
			}

			if (roomSearchDTO.getRoomTypeId() != null) {
				stringBuilder.append(" and room.roomType.id=:tid ");
			}

			final Query query = getCurrentSession().createQuery(
					stringBuilder.toString());

			if (roomSearchDTO.getHotelId() != null) {
				query.setParameter("hid", roomSearchDTO.getHotelId());
			}

			if (roomSearchDTO.getRoomTypeId() != null) {
				query.setParameter("tid", roomSearchDTO.getRoomTypeId());
			}
			return query.list();

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	protected Class getEntityClass() {
		return Room.class;
	}

	@Override
	public boolean isExist(final Long hotelId, final Long roomTypeId,
			final Long id) throws PersistenceException {
		try {
			final StringBuilder IS_ROOM_EXIST = new StringBuilder(
					"SELECT room FROM Room as room where ")
					.append("room.hotel.id=:hid AND room.roomType.id=:typeid ");

			if (id != null) {
				IS_ROOM_EXIST.append(" AND room.id != :id");
			}

			final Query query = getCurrentSession().createQuery(
					IS_ROOM_EXIST.toString());
			query.setParameter("hid", hotelId);
			query.setParameter("typeid", roomTypeId);

			if (id != null) {
				query.setParameter("id", id);
			}

			return !query.list().isEmpty();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}
}
