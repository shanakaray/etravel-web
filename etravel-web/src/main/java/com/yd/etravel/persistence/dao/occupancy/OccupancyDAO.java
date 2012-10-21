/**
 * 
 */
package com.yd.etravel.persistence.dao.occupancy;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yd.etravel.domain.occupancy.Occupancy;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.util.IConstants.IOccupancy;

@Repository
public class OccupancyDAO extends BaseDAO<Occupancy> implements IOccupancyDAO {

	final static StringBuilder IS_OCCUPANCY_NAME_EXIST = new StringBuilder(
			"SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy where ")
			.append(" UPPER(occupancy.name)= UPPER(:name) ");

	final static StringBuilder FIND_ALL_OCCUPANCY_WITH_ROOM_TYPE = new StringBuilder(
			"SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy join fetch  occupancy.roomType as roomType");

	final static StringBuilder FIND_ALL_OCCUPANCY_BY_PAX_INFO = new StringBuilder(
			"SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy  "
					+ "where occupancy.adult=? And occupancy.child=? And occupancy.infant=?");

	final static StringBuilder FIND_ALL_OCCUPANCY_BY_ROOM_TYPE = new StringBuilder(
			"SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy join fetch  occupancy.roomType as roomType  "
					+ "where roomType.id=? ");

	final static StringBuilder FIND_ALL_OCCUPANCY_BY_OCC_NAME = new StringBuilder(
			"SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy "
					+ "where occupancy.name=? ");

	public OccupancyDAO() {
	}

	@Override
	public boolean isOccupancyNameExist(final String occupancyName,
			final Long id) throws PersistenceException {
		try {

			final StringBuilder IS_OCCUPANCY_NAME_EXIST = new StringBuilder(
					"SELECT occupancy FROM com.yd.etravel.domain.occupancy.Occupancy as occupancy where ")
					.append(" UPPER(occupancy.name)= UPPER(:name) ");

			if (id != null) {
				IS_OCCUPANCY_NAME_EXIST.append(" AND occupancy.id != :id");
			}

			final Query query = getCurrentSession().createQuery(
					IS_OCCUPANCY_NAME_EXIST.toString());
			query.setParameter("name", occupancyName);
			if (id != null) {
				query.setParameter("id", id);
			}

			return !query.list().isEmpty();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Occupancy> findAllOccupancyWithRoomType()
			throws PersistenceException {
		try {
			return getHibernateTemplate().find(
					FIND_ALL_OCCUPANCY_WITH_ROOM_TYPE.toString());

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public boolean findAllOccupancyByPaxInfo(final Occupancy occupancy)
			throws PersistenceException {

		try {
			final Query query = getCurrentSession().createQuery(
					FIND_ALL_OCCUPANCY_BY_PAX_INFO.toString());
			query.setParameter(0, occupancy.getAdult());
			query.setParameter(1, occupancy.getChild());
			query.setParameter(2, occupancy.getInfant());

			return query.list().isEmpty();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<Occupancy> findAllOccupancyByRoomType(final Long roomTypeId)
			throws PersistenceException {
		try {
			final List<Occupancy> results = new ArrayList<Occupancy>();
			final Query queryOccupancyByName = getCurrentSession().createQuery(
					FIND_ALL_OCCUPANCY_BY_OCC_NAME.toString());
			queryOccupancyByName.setParameter(0,
					IOccupancy.COMMON_OCCUPANCY_NAME);
			results.addAll(queryOccupancyByName.list());

			final Query queryOccupancyByType = getCurrentSession().createQuery(
					FIND_ALL_OCCUPANCY_BY_ROOM_TYPE.toString());
			queryOccupancyByType.setParameter(0, roomTypeId);

			results.addAll(queryOccupancyByType.list());

			return results;
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	protected Class getEntityClass() {
		return Occupancy.class;
	}
}
