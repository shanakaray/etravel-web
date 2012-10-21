/**
 * 
 */
package com.yd.etravel.persistence.dao.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.persistence.exception.PersistenceException;

public abstract class BaseDAO<T extends BaseObject> extends HibernateDaoSupport
		implements IBaseDAO<T> {

	@Override
	public void delete(final T object) throws PersistenceException {
		try {
			getCurrentSession().delete(object);
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public int deleteAny(final Long id) throws PersistenceException {
		try {

			final StringBuilder sb = new StringBuilder("delete from ").append(
					getEntityClass().getName()).append(
					" as obj Where obj.id = (:id) ");
			final Session session = getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
			final Query query = session.createQuery(sb.toString());
			query.setParameter("id", id);

			return query.executeUpdate();

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public int deleteAny(final Long[] id) throws PersistenceException {
		try {
			final StringBuilder sb = new StringBuilder("delete from ").append(
					getEntityClass().getName()).append(
					" as obj Where obj.id IN (:ids) ");

			final Session session = getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
			final Query query = session.createQuery(sb.toString());
			query.setParameterList("ids", id);

			return query.executeUpdate();
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() throws PersistenceException {
		try {
			final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
					.append(getEntityClass().getName()).append(" as obj");
			return new ArrayList<T>(getHibernateTemplate().find(sb.toString()));
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<T> findAll(final Long[] id) throws PersistenceException {
		try {
			final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
					.append(getEntityClass().getName()).append(
							" as obj Where obj.id IN (:ids) AND obj.active=1 ");

			final Session session = getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
			final Query query = session.createQuery(sb.toString());
			query.setParameterList("ids", id);

			return query.list();

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllActive() throws PersistenceException {
		try {
			final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
					.append(getEntityClass().getName()).append(
							" as obj Where obj.active=1 ");
			return new ArrayList<T>(getHibernateTemplate().find(sb.toString()));
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public T findById(final Long id) throws PersistenceException {
		try {
			return (T) getCurrentSession().load(getEntityClass(), id);
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findBySample(final T object) throws PersistenceException {
		try {
			return new ArrayList<T>(getHibernateTemplate()
					.findByExample(object));
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	protected Session getCurrentSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("rawtypes")
	protected abstract Class getEntityClass();

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public T merge(final T object) throws PersistenceException {
		try {
			BaseObject baseObject = object;
			if (baseObject.getId() == null) {
				baseObject.setCreatedDate(new Date());
				baseObject.setCreatedBy(Thread.currentThread().getName());
			}

			baseObject = (BaseObject) getHibernateTemplate()
					.getSessionFactory().getCurrentSession().merge(object);
			baseObject.setModifiedDate(new Date());
			baseObject.setModifiedBy(Thread.currentThread().getName());
			getHibernateTemplate().saveOrUpdate(baseObject);

			return object;
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public T saveOrUpdate(final T obj) throws PersistenceException {
		try {
			final BaseObject object = obj;
			if (object.getId() == null) {
				object.setCreatedDate(new Date());
				object.setCreatedBy(Thread.currentThread().getName());
			} else {
				object.setModifiedDate(new Date());
				object.setModifiedBy(Thread.currentThread().getName());
			}
			getHibernateTemplate().saveOrUpdate(object);
			return obj;
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		} catch (final DataAccessException e) {
			throw new PersistenceException(e);
		}
	}
}
