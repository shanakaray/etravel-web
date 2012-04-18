/**
 * 
 */
package com.yd.etravel.persistence.dao.season;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author Dharsahana
 * 
 */
public class SeasonDAO extends BaseDAO implements ISeasonDAO {

    final static StringBuilder IS_SEASON_NAME_EXIST = new StringBuilder(
	    "SELECT season FROM com.yd.etravel.domain.season.Season as season where ")
	    .append(" UPPER(season.name)= UPPER(:name) ");

    final static StringBuilder IS_SEASON_CODE_EXIST = new StringBuilder(
	    "SELECT season FROM com.yd.etravel.domain.season.Season as season where ")
	    .append(" UPPER(season.code)= UPPER(:code) ");

    final static StringBuilder IS_DATE_RANGE_VALID = new StringBuilder(
	    "SELECT season FROM com.yd.etravel.domain.season.Season as season join season.hotel as hotel where"
		    + "((season.fromDate <= ? And season.toDate >= ? ) OR"
		    + "(season.fromDate <= ? And season.toDate >= ? )) And hotel.id= ?");

    final static StringBuilder FIND_ALL_SEASON_WITH_HOTEL = new StringBuilder(
	    "SELECT season FROM com.yd.etravel.domain.season.Season as season join fetch  season.hotel as hotel");

    // Seasonal Rate

    final static StringBuilder IS_SEASONAL_RATE_EXIST = new StringBuilder(
	    "SELECT roomSeasonalRate FROM com.yd.etravel.domain.season.RoomSeasonalRate as roomSeasonalRate"
		    + " join roomSeasonalRate.season as season join roomSeasonalRate.room as room "
		    + "where season.id = ? AND room.id= ?");

    final static StringBuilder FIND_ALL_ROOM_SEASONAL_RATE_BY_ROOM_ID = new StringBuilder(
	    "SELECT roomSeasonalRate FROM com.yd.etravel.domain.season.RoomSeasonalRate as roomSeasonalRate "
		    + "join fetch  roomSeasonalRate.season as season join fetch  roomSeasonalRate.room as room "
		    + " where room.id=?");

    /**
	 * 
	 */
    public SeasonDAO() {
	// TODO Auto-generated constructor stub
    }

    public boolean isSeasonNameExist(final String seasonName)
	    throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_SEASON_NAME_EXIST.toString());
	    query.setParameter("name", seasonName);
	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public boolean isSeasonCodeExist(final String seasonCode)
	    throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_SEASON_CODE_EXIST.toString());
	    query.setParameter("code", seasonCode);
	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public boolean isDataRangeValid(final Long hotelId, final Date fromDate,
	    final Date toDate) throws PersistenceException {
	try {

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_DATE_RANGE_VALID.toString());
	    query.setParameter(0, fromDate);
	    query.setParameter(1, fromDate);
	    query.setParameter(2, toDate);

	    query.setParameter(3, toDate);
	    query.setParameter(4, hotelId);
	    return (query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<Season> findAllSeasonWithHotel() throws PersistenceException {
	try {
	    return (List<Season>) getHibernateTemplate().find(
		    FIND_ALL_SEASON_WITH_HOTEL.toString());

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public boolean isSeasonalRateExist(final RoomSeasonalRate roomSeasonalRate)
	    throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session
		    .createQuery(IS_SEASONAL_RATE_EXIST.toString());
	    query.setParameter(0, roomSeasonalRate.getSeason().getId());
	    query.setParameter(1, roomSeasonalRate.getRoom().getId());
	    return (query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    // Seasonal rate
    public List<RoomSeasonalRate> findAllRoomSeasonalRateWithRoomAndSeason(
	    Long hotelid) throws PersistenceException {
	try {

	    final StringBuilder sb = new StringBuilder(
		    "SELECT roomSeasonalRate FROM com.yd.etravel.domain.season.RoomSeasonalRate as roomSeasonalRate "
			    + "join fetch  roomSeasonalRate.season as season join fetch roomSeasonalRate.room as room join "
			    + " fetch room.roomType as type join fetch room.hotel as hot "
			    + "where 1=1 ");

	    if (hotelid != null) {
		sb.append(" and hot.id=:id");
	    }
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());

	    if (hotelid != null) {
		query.setParameter("id", hotelid);
	    }

	    return query.list();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<RoomSeasonalRate> findRoomSeasonalRateByRoomId(Long roomId)
	    throws PersistenceException {
	try {

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session
		    .createQuery(FIND_ALL_ROOM_SEASONAL_RATE_BY_ROOM_ID
			    .toString());
	    query.setParameter(0, roomId);

	    return query.list();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<Season> findSeasonByHotel(Long hotelId)
	    throws PersistenceException {
	final StringBuilder sb = new StringBuilder(
		"SELECT season FROM com.yd.etravel.domain.season.Season as season join season.hotel as hot where ")
		.append(" 1=1 ");
	try {

	    if (hotelId != null) {
		sb.append(" AND hot.id=:hid");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());

	    if (hotelId != null) {
		query.setParameter("hid", hotelId);
	    }

	    return query.list();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public RoomSeasonalRate findById(Long id) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder(
		    "SELECT roomSeasonalRate FROM com.yd.etravel.domain.season.RoomSeasonalRate as roomSeasonalRate "
			    + "join fetch  roomSeasonalRate.season as season join fetch roomSeasonalRate.room as room join fetch room.hotel as hot "
			    + "where roomSeasonalRate.id=:id ");
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);
	    List<RoomSeasonalRate> list = query.list();
	    return list.isEmpty() ? null : (RoomSeasonalRate) list.get(0);

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }
}
