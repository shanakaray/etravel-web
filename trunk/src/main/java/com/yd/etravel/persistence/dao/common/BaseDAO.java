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

    public void delete(Object object) throws PersistenceException {
	try {

	    getHibernateTemplate().delete(object);

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public Object findById(Class cls, Long id) throws PersistenceException {
	try {
	    return getHibernateTemplate().load(cls, id);
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public List findBySample(Object object) throws PersistenceException {
	try {
	    List list = getHibernateTemplate().findByExample(object);
	    return list;
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public Object save(Object object) throws PersistenceException {
	try {
	    BaseObject baseObject = (BaseObject) object;
	    baseObject.setCreatedDate(new Date());
	    baseObject.setCreatedBy(Thread.currentThread().getName());
	    getHibernateTemplate().saveOrUpdate(baseObject);
	    return object;
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public Object update(Object object) throws PersistenceException {
	try {
	    BaseObject baseObject = (BaseObject) object;
	    baseObject.setModifiedDate(new Date());
	    baseObject.setModifiedBy(Thread.currentThread().getName());
	    getHibernateTemplate().update(baseObject);
	    return object;
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public Object merge(Object object) throws PersistenceException {
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
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public List findAll(Class cls) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(cls.getName()).append(" as obj");
	    List list = getHibernateTemplate().find(sb.toString());
	    return list;
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public List findAllActive(Class cls) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(cls.getName())
		    .append(" as obj Where obj.active=1 ");
	    List list = getHibernateTemplate().find(sb.toString());
	    return list;
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public List findAll(Class cls, Long[] id) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT obj FROM ")
		    .append(cls.getName()).append(
			    " as obj Where obj.id IN (:ids) AND obj.active=1 ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameterList("ids", id);

	    return query.list();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}

    }

    public int deleteAny(Class cls, Long[] id) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("delete from ").append(
		    cls.getName()).append(" as obj Where obj.id IN (:ids) ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameterList("ids", id);

	    return query.executeUpdate();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }

    public int deleteAny(Class cls, Long id) throws PersistenceException {
	try {

	    final StringBuilder sb = new StringBuilder("delete from ").append(
		    cls.getName()).append(" as obj Where obj.id = (:id) ");
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    return query.executeUpdate();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}
    }
}
