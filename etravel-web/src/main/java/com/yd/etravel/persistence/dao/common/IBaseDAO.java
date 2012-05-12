/**
 * 
 */
package com.yd.etravel.persistence.dao.common;

import java.util.List;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:48:03 PM Type :
 *         com.yd.etravel.persistence.dao.common.IBaseDAO
 * 
 */
public interface IBaseDAO<T extends BaseObject> {

    public T findById(final Long id) throws PersistenceException;

    public List<T> findBySample(final T object) throws PersistenceException;

    public List<T> findAll() throws PersistenceException;

    public T save(final T object) throws PersistenceException;

    public T update(final T object) throws PersistenceException;

    public T merge(final T object) throws PersistenceException;

    public void delete(final T object) throws PersistenceException;

    public List<T> findAllActive() throws PersistenceException;

    public List<T> findAll(Long[] id) throws PersistenceException;

    public int deleteAny(Long id) throws PersistenceException;

    public int deleteAny(Long[] id) throws PersistenceException;

}
