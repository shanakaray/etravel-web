/**
 * 
 */
package com.yd.etravel.persistence.dao.room.availability;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO;
import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;

@Repository
public class RoomAvailabilityDAO extends BaseDAO implements
	IRoomAvailabilityDAO {

    final static StringBuilder IS_DATE_RANGE_VALID = new StringBuilder(
	    "SELECT roomAvailability FROM com.yd.etravel.domain.room.availability.RoomAvailability as roomAvailability"
		    + " join roomAvailability.room as room where ((roomAvailability.fromDate <= ? And roomAvailability.toDate >= ? ) OR "
		    + "(roomAvailability.fromDate <= ? And roomAvailability.toDate >= ? )) And room.id= ?");

    final static StringBuilder FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ID = new StringBuilder(
	    "SELECT roomDailyAvailability FROM com.yd.etravel.domain.room.availability.RoomDailyAvailability as roomDailyAvailability "
		    + "join fetch  roomDailyAvailability.roomAvailability as roomAvailability  "
		    + "join fetch  roomAvailability.room as room join fetch  room.roomType as roomType where roomAvailability.id=?");

    final static StringBuilder FIND_ALL_DAILY_ROOM_AVAILABILITY = new StringBuilder(
	    "SELECT roomDailyAvailability FROM com.yd.etravel.domain.room.availability.RoomDailyAvailability as roomDailyAvailability "
		    + "join fetch  roomDailyAvailability.roomAvailability as roomAvailability  "
		    + "join fetch  roomAvailability.room as room join fetch  room.roomType as roomType order by roomDailyAvailability.createdDate");

    final static StringBuilder FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ROOM_AVAILABILITY_ID = new StringBuilder(
	    "SELECT roomDailyAvailability FROM com.yd.etravel.domain.room.availability.RoomDailyAvailability as roomDailyAvailability  "
		    + "join fetch  roomDailyAvailability.roomAvailability as roomAvailability  "
		    + "where roomAvailability.id=?");
    final static StringBuilder FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ROOM_AVAILABILITY_ID_AND_DATE_RANGE = new StringBuilder(
	    "SELECT roomDailyAvailability FROM com.yd.etravel.domain.room.availability.RoomDailyAvailability as roomDailyAvailability  "
		    + "join fetch  roomDailyAvailability.roomAvailability as roomAvailability  "
		    + "where roomAvailability.id=? And roomDailyAvailability.date>= ? And roomDailyAvailability.date<= ? ");

    final static StringBuilder FIND_ALL_ROOM_AVAILABILITY_WITH_ROOM_AND_ROOM_TYPE_BY_ID = new StringBuilder(
	    "SELECT roomAvailability FROM com.yd.etravel.domain.room.availability.RoomAvailability as roomAvailability "
		    + "join fetch  roomAvailability.room as room join fetch room.roomType as roomType where  roomAvailability.id=?");

    /**
	 * 
	 */
    public RoomAvailabilityDAO() {
    }

    @Override
    public boolean isDataRangeValid(final Long roomId, final Date fromDate,
	    final Date toDate) throws PersistenceException {
	try {

	    final Query query = getCurrentSession().createQuery(
		    IS_DATE_RANGE_VALID.toString());
	    query.setParameter(0, fromDate);
	    query.setParameter(1, fromDate);
	    query.setParameter(2, toDate);
	    query.setParameter(3, toDate);
	    query.setParameter(4, roomId);

	    return query.list().isEmpty();
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    @Deprecated
    /**
     * use List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(Long hotelId)
     * instead
     */
    public List<RoomAvailability> findAllRoomAvailabilityWithRoomAndOccu(
	    final Long hotelId) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder(

	    "SELECT roomAvailability FROM RoomAvailability as roomAvailability ")
		    .append("join fetch  roomAvailability.room as room join fetch room.roomType as roomType ")
		    .append("join fetch room.hotel as hot WHERE 1=1 ");

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

    /**
	 * 
	 * 
	 */
    @Override
    public List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(
	    final RoomAvailabilityDTO dto) throws PersistenceException {
	try {

	    final StringBuilder sb = new StringBuilder("SELECT new ")
		    .append(RoomAvailabilityDTO.class.getName()).append("( ")
		    .append("ra.id,room.id,type.id,hot.id,")
		    .append("hot.name,room.name,type.name,")
		    .append("ra.fromDate,ra.toDate,ra.unit,ra.active")
		    .append(")FROM RoomAvailability as ra ")
		    .append("join ra.room as room join room.roomType as type ")
		    .append("join room.hotel as hot WHERE 1=1 ");

	    if (dto.getHotelId() != null) {
		sb.append(" AND hot.id=:hid ");
	    }

	    if (dto.getFromDate() != null) {
		sb.append(" AND (ra.fromDate>=:fdate ");
	    }
	    if (dto.getToDate() != null) {
		sb.append(" OR ra.fromDate<=:tdate) ");
	    }

	    final Query query = getCurrentSession().createQuery(sb.toString());
	    if (dto.getHotelId() != null) {
		query.setParameter("hid", dto.getHotelId());
	    }
	    if (dto.getFromDate() != null) {
		query.setParameter("fdate", dto.getFromDate());
	    }
	    if (dto.getToDate() != null) {
		query.setParameter("tdate", dto.getToDate());
	    }

	    return query.list();
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    // Daily Availabilty

    @Override
    public List<RoomDailyAvailability> findAllRoomDailyAvailability(
	    final Long id) throws PersistenceException {
	try {
	    final Query query = getCurrentSession().createQuery(
		    FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ID.toString());
	    query.setParameter(0, id);

	    final List list = query.list();

	    return list;

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List<RoomDailyAvailability> findAllRoomDailyAvailability()
	    throws PersistenceException {
	try {
	    final Query query = getCurrentSession().createQuery(
		    FIND_ALL_DAILY_ROOM_AVAILABILITY.toString());

	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List<RoomDailyAvailability> findAllRoomDailyAvailabilityByRoomAvailabilityId(
	    final Long id) throws PersistenceException {
	try {
	    final Query query = getCurrentSession().createQuery(
		    FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ROOM_AVAILABILITY_ID
			    .toString());
	    query.setParameter(0, id);

	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public RoomAvailability findRoomAvailabilityById(final Long id)
	    throws ServiceException, PersistenceException {
	try {
	    final Query query = getCurrentSession().createQuery(
		    FIND_ALL_ROOM_AVAILABILITY_WITH_ROOM_AND_ROOM_TYPE_BY_ID
			    .toString());
	    query.setParameter(0, id);

	    final List<RoomAvailability> list = query.list();
	    return !list.isEmpty() ? (RoomAvailability) list.get(0)
		    : new RoomAvailability();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);

	}
    }

    @Override
    public List<RoomDailyAvailability> findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
	    final Long id, final Date checkIn, final Date checkOut)
	    throws PersistenceException {
	try {
	    final Query query = getCurrentSession().createQuery(
		    FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ROOM_AVAILABILITY_ID_AND_DATE_RANGE
			    .toString());
	    query.setParameter(0, id);
	    query.setParameter(1, checkIn);
	    query.setParameter(2, checkOut);

	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List<DailyAvailabilityDTO> findDailyAvailability(final Long id)
	    throws PersistenceException {
	final StringBuilder sb = new StringBuilder(
		"SELECT new com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO( "
			+ "rda.id,rda.date,rda.allocatedUnit,rda.availabalUnit,"
			+ "r.id,t.id,h.id,"
			+ "h.name,r.name,t.name,rda.active,ra.createdDate"
			+ ")"
			+ "FROM com.yd.etravel.domain.room.availability.RoomDailyAvailability as rda ")
		.append("join rda.roomAvailability as ra  ")
		.append("join  ra.room as r join r.roomType as t join r.hotel h where 1=1 ");

	if (id != null) {
	    sb.append(" AND ra.id=?");
	}

	sb.append(" order by ra.createdDate");

	try {
	    final Query query = getCurrentSession().createQuery(sb.toString());
	    if (id != null) {
		query.setParameter(0, id);
	    }

	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }
}
