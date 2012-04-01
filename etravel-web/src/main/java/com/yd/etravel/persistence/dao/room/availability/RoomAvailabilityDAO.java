/**
 * 
 */
package com.yd.etravel.persistence.dao.room.availability;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO;
import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;

/**
 * @author Dharsahana
 * 
 */
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

    public boolean isDataRangeValid(final Long roomId, final Date fromDate,
	    final Date toDate) throws PersistenceException {
	try {
	    ArrayList results = new ArrayList();

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_DATE_RANGE_VALID.toString());
	    query.setParameter(0, fromDate);
	    query.setParameter(1, fromDate);
	    query.setParameter(2, toDate);
	    query.setParameter(3, toDate);
	    query.setParameter(4, roomId);
	    List list = query.list();

	    return (list.isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Deprecated
    /**
     * use List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(Long hotelId)
     * instead
     */
    public List<RoomAvailability> findAllRoomAvailabilityWithRoomAndOccu(
	    Long hotelId) throws PersistenceException {
	try {
	    // final StringBuilder sb = new StringBuilder(
	    // "SELECT roomAvailability FROM RoomAvailability as
	    // roomAvailability ")
	    // .append("join fetch roomAvailability.room as room join fetch
	    // room.roomType as roomType " )
	    // .append("join fetch roomAvailability.occupancy as occupancy join
	    // room.hotel as hot WHERE 1=1 ");

	    final StringBuilder sb = new StringBuilder(

	    "SELECT roomAvailability FROM RoomAvailability as roomAvailability ")
		    .append("join fetch  roomAvailability.room as room join fetch room.roomType as roomType ")
		    .append("join fetch room.hotel as hot WHERE 1=1 ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    if (hotelId != null) {
		sb.append(" AND hot.id=:hid ");
	    }
	    Query query = session.createQuery(sb.toString());
	    if (hotelId != null) {
		query.setParameter("hid", hotelId);
	    }

	    return query.list();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    /**
	 * 
	 * 
	 */
    public List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(
	    RoomAvailabilityDTO dto) throws PersistenceException {
	try {

	    final StringBuilder sb = new StringBuilder("SELECT new ")
		    .append(RoomAvailabilityDTO.class.getName()).append("( ")
		    .append("ra.id,room.id,type.id,hot.id,")
		    .append("hot.name,room.name,type.name,")
		    .append("ra.fromDate,ra.toDate,ra.unit,ra.active")
		    .append(")FROM RoomAvailability as ra ")
		    .append("join ra.room as room join room.roomType as type ")
		    .append("join room.hotel as hot WHERE 1=1 ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    if (dto.getHotelId() != null) {
		sb.append(" AND hot.id=:hid ");
	    }

	    if (dto.getFromDate() != null) {
		sb.append(" AND (ra.fromDate>=:fdate ");
	    }
	    if (dto.getToDate() != null) {
		sb.append(" OR ra.fromDate<=:tdate) ");
	    }

	    Query query = session.createQuery(sb.toString());
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
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    // Daily Availabilty

    public List<RoomDailyAvailability> findAllRoomDailyAvailability(Long id)
	    throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session
		    .createQuery(FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ID
			    .toString());
	    query.setParameter(0, id);

	    List list = query.list();

	    return list;

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<RoomDailyAvailability> findAllRoomDailyAvailability()
	    throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(FIND_ALL_DAILY_ROOM_AVAILABILITY
		    .toString());

	    List list = query.list();

	    return list;

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<RoomDailyAvailability> findAllRoomDailyAvailabilityByRoomAvailabilityId(
	    Long id) throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session
		    .createQuery(FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ROOM_AVAILABILITY_ID
			    .toString());
	    query.setParameter(0, id);

	    List list = query.list();

	    return list;

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public RoomAvailability findRoomAvailabilityById(final Long id)
	    throws ServiceException, PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session
		    .createQuery(FIND_ALL_ROOM_AVAILABILITY_WITH_ROOM_AND_ROOM_TYPE_BY_ID
			    .toString());
	    query.setParameter(0, id);

	    List list = query.list();
	    if (!list.isEmpty()) {

		return (RoomAvailability) list.get(0);
	    } else {

		return new RoomAvailability();
	    }

	} catch (HibernateException e) {
	    throw new PersistenceException(e);

	}
    }

    public List<RoomDailyAvailability> findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
	    Long id, Date checkIn, Date checkOut) throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session
		    .createQuery(FIND_ALL_DAILY_ROOM_AVAILABILITY_BY_ROOM_AVAILABILITY_ID_AND_DATE_RANGE
			    .toString());
	    query.setParameter(0, id);
	    query.setParameter(1, checkIn);
	    query.setParameter(2, checkOut);

	    List list = query.list();

	    return list;

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<DailyAvailabilityDTO> findDailyAvailability(Long id)
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
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    if (id != null) {
		query.setParameter(0, id);
	    }
	    List list = query.list();

	    return list;

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }
}
