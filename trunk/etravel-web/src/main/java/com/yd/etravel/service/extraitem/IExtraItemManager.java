/**
 * 
 */
package com.yd.etravel.service.extraitem;

import java.util.List;

import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.extraitem.ExtraItem;
import com.yd.etravel.service.exception.ServiceException;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 14, 2009 : 4:37:45 PM Type :
 *         com.yd.etravel.service.extraitem.IExtraItemManager
 * 
 */
public interface IExtraItemManager {

    public List<ExtraItem> findAllActiveExtraItems() throws ServiceException;

    public List<ExtraItem> findExtraItemsByHotel(Long hotelId)
	    throws ServiceException;

    public ExtraItem saveExtraItem(final ExtraItem item)
	    throws ServiceException;

    public List<ExtraItem> findAllExtraItem() throws ServiceException;

    public ExtraItem findExtraItemById(Long id) throws ServiceException;

    public int deleteExtraItem(Long id) throws ServiceException;

    public List<ExtraItemBooking> findByBookingId(Long bookingId)
	    throws ServiceException;

}
