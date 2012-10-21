/**
 * 
 */
package com.yd.etravel.persistence.dao.common;

import java.util.List;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.persistence.exception.PersistenceException;

public interface IBaseDAO<T extends BaseObject> {

	public void delete(final T object) throws PersistenceException;

	public int deleteAny(Long id) throws PersistenceException;

	public int deleteAny(Long[] id) throws PersistenceException;

	public List<T> findAll() throws PersistenceException;

	public List<T> findAll(Long[] id) throws PersistenceException;

	public List<T> findAllActive() throws PersistenceException;

	public T findById(final Long id) throws PersistenceException;

	public List<T> findBySample(final T object) throws PersistenceException;

	public T merge(final T object) throws PersistenceException;

	public T saveOrUpdate(final T object) throws PersistenceException;

}
