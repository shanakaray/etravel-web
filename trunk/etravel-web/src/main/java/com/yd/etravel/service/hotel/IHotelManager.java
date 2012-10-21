/**
 * 
 */
package com.yd.etravel.service.hotel;

import java.util.List;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.service.exception.ServiceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 14, 2009 : 8:39:56 PM Type :
 *         com.yd.etravel.service.hotel.IHotelManager
 * 
 */
public interface IHotelManager {

	public List<Hotel> findAllActiveHotels() throws ServiceException;

	public Hotel saveHotel(final Hotel hotel, final Long[] userids)
			throws ServiceException;

	public List<Hotel> findAllHotel() throws ServiceException;

	public Hotel findHotelById(Long id) throws ServiceException;

	public List<Hotel> findHotelsById(Long id[]) throws ServiceException;

	public int deleteHotel(Long id) throws ServiceException;
}
