package com.yd.etravel.service.extraitem;

import java.util.List;

import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.extraitem.ExtraItem;
import com.yd.etravel.persistence.dao.extraitem.IExtraItemDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 14, 2009 : 4:43:03 PM Type :
 *         com.yd.etravel.service.extraitem.ExtraItemManagerImpl
 * 
 */
public class ExtraItemManagerImpl implements IExtraItemManager {

    private IExtraItemDAO itemDAO;

    public IExtraItemDAO getItemDAO() {
	return itemDAO;
    }

    public void setItemDAO(IExtraItemDAO itemDAO) {
	this.itemDAO = itemDAO;
    }

    public int deleteExtraItem(Long id) throws ServiceException {
	int val = 0;
	try {
	    val = this.itemDAO.deleteAny(ExtraItem.class, id);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return val;
    }

    public List<ExtraItem> findAllActiveExtraItems() throws ServiceException {
	try {
	    return this.itemDAO.findAllActive(ExtraItem.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<ExtraItem> findAllExtraItem() throws ServiceException {
	try {
	    return this.itemDAO.findAll(ExtraItem.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public ExtraItem findExtraItemById(Long id) throws ServiceException {
	ExtraItem extraItem = null;
	try {

	    extraItem = (ExtraItem) itemDAO.findById(ExtraItem.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return extraItem;
    }

    public ExtraItem saveExtraItem(ExtraItem item) throws ServiceException {
	try {
	    if (itemDAO.isExist(item.getName(), item.getCode(), item.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.hotel.extraitem.exist"));
	    }
	    if (item.getId() == null) {
		item = (ExtraItem) itemDAO.save(item);
	    } else {
		item = (ExtraItem) itemDAO.update(item);
	    }
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return item;
    }

    public List<ExtraItemBooking> findByBookingId(Long bookingId)
	    throws ServiceException {
	try {
	    return this.itemDAO.findByBookingId(bookingId);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<ExtraItem> findExtraItemsByHotel(Long hotelId)
	    throws ServiceException {
	try {
	    return this.itemDAO.findAllByHotelId(hotelId);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

}
