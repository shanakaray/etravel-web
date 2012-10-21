/**
 * 
 */
package com.yd.etravel.persistence.dao.hotel;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:48:33 PM Type :
 *         com.yd.etravel.persistence.dao.hotel.IHotelDAO
 * 
 */
public interface IHotelDAO extends IBaseDAO<Hotel> {

	public boolean isHotelNameExist(final String name, Long id)
			throws PersistenceException;

	public Hotel findHotelWithUser(Long id) throws PersistenceException;

	public int deleteHotel(Long id) throws PersistenceException;
}
