/**
 * 
 */
package com.yd.etravel.persistence.dao.season;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

@Repository
public class SeasonDAO extends BaseDAO<Season> implements ISeasonDAO {

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

	final static StringBuilder IS_SEASONAL_RATE_EXIST = new StringBuilder(
			"SELECT roomSeasonalRate FROM com.yd.etravel.domain.season.RoomSeasonalRate as roomSeasonalRate"
					+ " join roomSeasonalRate.season as season join roomSeasonalRate.room as room "
					+ "where season.id = ? AND room.id= ?");

	final static StringBuilder FIND_ALL_ROOM_SEASONAL_RATE_BY_ROOM_ID = new StringBuilder(
			"SELECT roomSeasonalRate FROM com.yd.etravel.domain.season.RoomSeasonalRate as roomSeasonalRate "
					+ "join fetch  roomSeasonalRate.season as season join fetch  roomSeasonalRate.room as room "
					+ " where room.id=?");

	public SeasonDAO() {
	}

	@Override
	public List<RoomSeasonalRate> findAllRoomSeasonalRateList()
			throws PersistenceException {
		try {
			final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
					.append(RoomSeasonalRate.class.getName()).append(" as obj");
			return new ArrayList<RoomSeasonalRate>(getHibernateTemplate().find(
					sb.toString()));
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<RoomSeasonalRate> findAllRoomSeasonalRateWithRoomAndSeason(
			final Long hotelid) throws PersistenceException {
		try {

			final StringBuilder sb = new StringBuilder(
					"SELECT roomSeasonalRate FROM com.yd.etravel.domain.season.RoomSeasonalRate as roomSeasonalRate "
							+ "join fetch  roomSeasonalRate.season as season join fetch roomSeasonalRate.room as room join "
							+ " fetch room.roomType as type join fetch room.hotel as hot "
							+ "where 1=1 ");

			if (hotelid != null) {
				sb.append(" and hot.id=:id");
			}
			final Query query = getCurrentSession().createQuery(sb.toString());

			if (hotelid != null) {
				query.setParameter("id", hotelid);
			}

			return query.list();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<Season> findAllSeasonWithHotel() throws PersistenceException {
		try {
			return getHibernateTemplate().find(
					FIND_ALL_SEASON_WITH_HOTEL.toString());

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<RoomSeasonalRate> findRoomSeasonalRateByRoomId(final Long roomId)
			throws PersistenceException {
		try {

			final Query query = getCurrentSession().createQuery(
					FIND_ALL_ROOM_SEASONAL_RATE_BY_ROOM_ID.toString());
			query.setParameter(0, roomId);

			return query.list();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<Season> findSeasonByHotel(final Long hotelId)
			throws PersistenceException {
		final StringBuilder sb = new StringBuilder(
				"SELECT season FROM com.yd.etravel.domain.season.Season as season join season.hotel as hot where ")
				.append(" 1=1 ");
		try {

			if (hotelId != null) {
				sb.append(" AND hot.id=:hid");
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
	public RoomSeasonalRate findSeasonRate(final Long id)
			throws PersistenceException {
		try {
			final StringBuilder sb = new StringBuilder(
					"SELECT roomSeasonalRate FROM com.yd.etravel.domain.season.RoomSeasonalRate as roomSeasonalRate "
							+ "join fetch  roomSeasonalRate.season as season join fetch roomSeasonalRate.room as room join fetch room.hotel as hot "
							+ "where roomSeasonalRate.id=:id ");
			final Query query = getCurrentSession().createQuery(sb.toString());
			query.setParameter("id", id);
			final List<RoomSeasonalRate> list = query.list();
			return list.isEmpty() ? null : list.get(0);

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	protected Class getEntityClass() {
		return Season.class;
	}

	@Override
	public boolean isDataRangeValid(final Long hotelId, final Date fromDate,
			final Date toDate) throws PersistenceException {
		try {

			final Query query = getCurrentSession().createQuery(
					IS_DATE_RANGE_VALID.toString());
			query.setParameter(0, fromDate);
			query.setParameter(1, fromDate);
			query.setParameter(2, toDate);

			query.setParameter(3, toDate);
			query.setParameter(4, hotelId);
			return query.list().isEmpty();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public boolean isSeasonalRateExist(final RoomSeasonalRate roomSeasonalRate)
			throws PersistenceException {
		try {
			final Query query = getCurrentSession().createQuery(
					IS_SEASONAL_RATE_EXIST.toString());
			query.setParameter(0, roomSeasonalRate.getSeason().getId());
			query.setParameter(1, roomSeasonalRate.getRoom().getId());
			return query.list().isEmpty();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public boolean isSeasonCodeExist(final String seasonCode)
			throws PersistenceException {
		try {
			final Query query = getCurrentSession().createQuery(
					IS_SEASON_CODE_EXIST.toString());
			query.setParameter("code", seasonCode);
			return !query.list().isEmpty();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public boolean isSeasonNameExist(final String seasonName)
			throws PersistenceException {
		try {
			final Query query = getCurrentSession().createQuery(
					IS_SEASON_NAME_EXIST.toString());
			query.setParameter("name", seasonName);
			return !query.list().isEmpty();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void save(final RoomSeasonalRate roomSeasonalRate)
			throws PersistenceException {
		try {
			getCurrentSession().save(roomSeasonalRate);
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void update(final RoomSeasonalRate roomSeasonalRate)
			throws PersistenceException {
		try {
			getCurrentSession().update(roomSeasonalRate);
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}

	}
}
