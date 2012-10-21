package com.yd.etravel.persistence.dao.pax;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yd.etravel.domain.custom.pax.PaxSearchDTO;
import com.yd.etravel.domain.hotel.Pax;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:38:41 PM Type :
 *         com.yd.etravel.persistence.dao.pax.PaxDAO
 * 
 */
@Repository
public class PaxDAO extends BaseDAO<Pax> implements IPaxDAO {

	@Override
	public boolean isPaxTypeExist(final Long hotelId, final Long id)
			throws PersistenceException {
		try {
			final StringBuilder IS_PAX_TYPE_EXIST = new StringBuilder(
					"SELECT pax FROM Pax as pax where ")
					.append("pax.hotel.id=:hid");

			if (id != null) {
				IS_PAX_TYPE_EXIST.append(" AND pax.id != :id");
			}

			final Query query = getCurrentSession().createQuery(
					IS_PAX_TYPE_EXIST.toString());
			query.setParameter("hid", hotelId);

			if (id != null) {
				query.setParameter("id", id);
			}
			return !query.list().isEmpty();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<Pax> findPax(final PaxSearchDTO paxSearchDTO)
			throws PersistenceException {
		try {
			final StringBuilder sb = new StringBuilder(
					"select pax from Pax as pax where 1=1 ");
			if (paxSearchDTO.getType() != null) {
				sb.append(" and pax.type=:type ");
			}

			if (paxSearchDTO.getHotelId() != null) {
				sb.append(" and pax.hotel.id=:hid ");
			}

			final Query query = getCurrentSession().createQuery(sb.toString());

			if (paxSearchDTO.getType() != null) {
				query.setParameter("type", paxSearchDTO.getType());
			}

			if (paxSearchDTO.getHotelId() != null) {
				query.setParameter("hid", paxSearchDTO.getHotelId());
			}

			return query.list();

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	protected Class getEntityClass() {
		return Pax.class;
	}

}
