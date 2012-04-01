/**
 * 
 */
package com.yd.etravel.persistence.dao.common;

import java.util.List;

import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:48:03 PM Type :
 *         com.yd.etravel.persistence.dao.common.IBaseDAO
 * 
 */
public interface IBaseDAO {

    public Object findById(Class cls, final Long id)
	    throws PersistenceException;

    public List findBySample(final Object object) throws PersistenceException;

    public List findAll(Class cls) throws PersistenceException;

    public Object save(final Object object) throws PersistenceException;

    public Object update(final Object object) throws PersistenceException;

    public Object merge(final Object object) throws PersistenceException;

    public void delete(final Object object) throws PersistenceException;

    public List findAllActive(Class cls) throws PersistenceException;

    public List findAll(Class cls, Long[] id) throws PersistenceException;

    public int deleteAny(Class cls, Long id) throws PersistenceException;

    public int deleteAny(Class cls, Long[] id) throws PersistenceException;
}
