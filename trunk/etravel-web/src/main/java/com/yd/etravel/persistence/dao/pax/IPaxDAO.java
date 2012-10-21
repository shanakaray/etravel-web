/**
 * 
 */
package com.yd.etravel.persistence.dao.pax;

import java.util.List;

import com.yd.etravel.domain.custom.pax.PaxSearchDTO;
import com.yd.etravel.domain.hotel.Pax;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:25:59 PM Type :
 *         com.yd.etravel.persistence.dao.IPaxDAO
 * 
 */
public interface IPaxDAO extends IBaseDAO<Pax> {

	public List<Pax> findPax(PaxSearchDTO paxSearchDTO)
			throws PersistenceException;

	public boolean isPaxTypeExist(Long hotelId, Long id)
			throws PersistenceException;

}
