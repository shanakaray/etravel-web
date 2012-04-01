/**
 * 
 */
package com.yd.etravel.persistence.dao.search;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yd.etravel.domain.custom.search.SearchRequestDTO;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author Dharsahana
 * 
 */
public class SearchDAO extends BaseDAO implements ISearchDAO {

    // final static StringBuilder FIND_ROOMS = new StringBuilder(
    // "SELECT roomAvailability FROM
    // com.yd.etravel.domain.room.availability.RoomAvailability as
    // roomAvailability "
    // + "join fetch roomAvailability.room as room "
    // + "join fetch room.roomType as roomType "
    // + "join fetch roomAvailability.occupancy as occupancy "
    // + "join fetch room.hotel as hotel "
    // + "where hotel.id= ? And "
    // + "(roomAvailability.fromDate <= ? And roomAvailability.toDate >= ? ) ");
    //
    // final static StringBuilder FIND_ROOMS_BY_CHECK_IN_DATE = new
    // StringBuilder(
    // "SELECT roomAvailability FROM
    // com.yd.etravel.domain.room.availability.RoomAvailability as
    // roomAvailability "
    // + "join fetch roomAvailability.room as room "
    // + "join fetch room.roomType as roomType "
    // + "join fetch roomAvailability.occupancy as occupancy "
    // + "join fetch room.hotel as hotel "
    // + "where hotel.id= ? And "
    // + "(roomAvailability.fromDate <= ? And roomAvailability.toDate >= ? ) ");
    //
    // final static StringBuilder FIND_ROOMS_BY_CHECK_OUT_DATE = new
    // StringBuilder(
    // "SELECT roomAvailability FROM
    // com.yd.etravel.domain.room.availability.RoomAvailability as
    // roomAvailability "
    // + "join fetch roomAvailability.room as room "
    // + "join fetch room.roomType as roomType "
    // + "join fetch roomAvailability.occupancy as occupancy "
    // + "join fetch room.hotel as hotel "
    // + "where hotel.id= ? And "
    // + "(roomAvailability.fromDate <= ? And roomAvailability.toDate >= ? ) ");

    final static StringBuilder FIND_ROOMS_BY_CHECK_IN_DATE = new StringBuilder(
	    "SELECT roomAvailability FROM com.yd.etravel.domain.room.availability.RoomAvailability as roomAvailability "
		    + "join fetch roomAvailability.room as room "
		    + "join fetch room.roomType as roomType "
		    + "join fetch room.hotel as hotel "
		    + "where hotel.id= ? And "
		    + "(roomAvailability.fromDate <= ? And roomAvailability.toDate >= ? ) ");

    final static StringBuilder FIND_ROOMS_BY_CHECK_OUT_DATE = new StringBuilder(
	    "SELECT roomAvailability FROM com.yd.etravel.domain.room.availability.RoomAvailability as roomAvailability "
		    + "join fetch roomAvailability.room as room "
		    + "join fetch room.roomType as roomType "
		    + "join fetch room.hotel as hotel "
		    + "where hotel.id= ? And "
		    + "(roomAvailability.fromDate <= ? And roomAvailability.toDate >= ? ) ");

    /**
	 * 
	 */
    public SearchDAO() {
	// TODO Auto-generated constructor stub
    }

    public List<RoomAvailability> findRooms(
	    final SearchRequestDTO searchRequestDTO)
	    throws PersistenceException {
	try {

	    final StringBuilder FIND_ROOMS = new StringBuilder(
		    "SELECT roomAvailability FROM com.yd.etravel.domain.room.availability.RoomAvailability as roomAvailability ")
		    .append("join fetch roomAvailability.room as room ")
		    .append("join fetch room.roomType as roomType ")
		    .append("join fetch room.hotel as hotel ")
		    .append("where hotel.id= ? And ")
		    .append("(roomAvailability.fromDate <= ? And roomAvailability.toDate >= ? ) ");
	    if (searchRequestDTO.getRoomTypeId() != null) {
		FIND_ROOMS.append(" AND roomType.id=:rid ");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(FIND_ROOMS.toString());
	    query.setParameter(0, searchRequestDTO.getHotelId());
	    query.setParameter(1, searchRequestDTO.getCheckIn());
	    query.setParameter(2, searchRequestDTO.getCheckOut());
	    if (searchRequestDTO.getRoomTypeId() != null) {
		query.setParameter("rid", searchRequestDTO.getRoomTypeId());
	    }
	    return query.list();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<RoomAvailability> findRoomsByCheckInDate(
	    final SearchRequestDTO searchRequestDTO)
	    throws PersistenceException {
	try {

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(FIND_ROOMS_BY_CHECK_IN_DATE
		    .toString());
	    query.setParameter(0, searchRequestDTO.getHotelId());
	    query.setParameter(1, searchRequestDTO.getCheckIn());
	    query.setParameter(2, searchRequestDTO.getCheckIn());
	    return query.list();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<RoomAvailability> findRoomsByCheckOutDate(
	    final SearchRequestDTO searchRequestDTO)
	    throws PersistenceException {
	try {

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(FIND_ROOMS_BY_CHECK_OUT_DATE
		    .toString());
	    query.setParameter(0, searchRequestDTO.getHotelId());
	    query.setParameter(1, searchRequestDTO.getCheckOut());
	    query.setParameter(2, searchRequestDTO.getCheckOut());
	    return query.list();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }
}
