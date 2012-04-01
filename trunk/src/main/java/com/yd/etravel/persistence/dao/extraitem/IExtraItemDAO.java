/**
 * 
 */
package com.yd.etravel.persistence.dao.extraitem;

import java.util.List;

import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.extraitem.ExtraItem;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 14, 2009 : 4:53:05 PM Type :
 *         com.yd.etravel.persistence.dao.extraitem.IExtraItemDAO
 * 
 */
public interface IExtraItemDAO extends IBaseDAO {

    public boolean isExist(String name, String code, Long id)
	    throws PersistenceException;

    public List<ExtraItemBooking> findByBookingId(Long bookingId)
	    throws PersistenceException;

    public List<ExtraItem> findAllByHotelId(Long id)
	    throws PersistenceException;

}
