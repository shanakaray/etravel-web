/**
 * 
 */
package com.yd.etravel.persistence.dao.common;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:47:43 PM Type :
 *         com.yd.etravel.persistence.dao.common.BaseDAO
 * 
 */
public class BaseDAO extends HibernateDaoSupport implements IBaseDAO {

    @Override
    public void delete(final Object object) throws PersistenceException {
	try {

	    getHibernateTemplate().delete(object);

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public Object findById(final Class cls, final Long id) throws PersistenceException {
	try {
	    return getHibernateTemplate().load(cls, id);
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List findBySample(final Object object) throws PersistenceException {
	try {
	    final List list = getHibernateTemplate().findByExample(object);
	    return list;
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public Object save(final Object object) throws PersistenceException {
	try {
	    final BaseObject baseObject = (BaseObject) object;
	    baseObject.setCreatedDate(new Date());
	    baseObject.setCreatedBy(Thread.currentThread().getName());
	    getHibernateTemplate().saveOrUpdate(baseObject);
	    return object;
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public Object update(final Object object) throws PersistenceException {
	try {
	    final BaseObject baseObject = (BaseObject) object;
	    baseObject.setModifiedDate(new Date());
	    baseObject.setModifiedBy(Thread.currentThread().getName());
	    getHibernateTemplate().update(baseObject);
	    return object;
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public Object merge(final Object object) throws PersistenceException {
	try {
	    BaseObject baseObject = (BaseObject) object;
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
    public List findAll(final Class cls) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(cls.getName()).append(" as obj");
	    final List list = getHibernateTemplate().find(sb.toString());
	    return list;
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List findAllActive(final Class cls) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(cls.getName())
		    .append(" as obj Where obj.active=1 ");
	    final List list = getHibernateTemplate().find(sb.toString());
	    return list;
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List findAll(final Class cls, final Long[] id) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(cls.getName()).append(
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

    @Override
    public int deleteAny(final Class cls, final Long[] id) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("delete from ").append(
		    cls.getName()).append(" as obj Where obj.id IN (:ids) ");

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

    @Override
    public int deleteAny(final Class cls, final Long id) throws PersistenceException {
	try {

	    final StringBuilder sb = new StringBuilder("delete from ").append(
		    cls.getName()).append(" as obj Where obj.id = (:id) ");
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
}
