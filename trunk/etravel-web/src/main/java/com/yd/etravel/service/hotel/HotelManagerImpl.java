/**
 * 
 */
package com.yd.etravel.service.hotel;

import java.util.List;

import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.persistence.dao.hotel.IHotelDAO;
import com.yd.etravel.persistence.dao.user.IUserDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 14, 2009 : 8:42:24 PM Type :
 *         com.yd.etravel.service.hotel.HotelManagerImpl
 * 
 */
public class HotelManagerImpl implements IHotelManager {

    private IHotelDAO hotelDAO;
    private IUserDAO userDAO;

    public void setHotelDAO(IHotelDAO hotelDAO) {
	this.hotelDAO = hotelDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
	this.userDAO = userDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.service.hotel.IHotelManager#deleteHotel(java.lang.Long)
     */
    public int deleteHotel(Long id) throws ServiceException {
	int val = 0;
	try {
	    val = hotelDAO.deleteHotel(id);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    public List<Hotel> findAllActiveHotels() throws ServiceException {
	try {
	    return hotelDAO.findAllActive(Hotel.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllHotel()
     */
    public List<Hotel> findAllHotel() throws ServiceException {
	try {
	    return hotelDAO.findAll(Hotel.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.service.hotel.IHotelManager#findRoleById(java.lang.Long)
     */
    public Hotel findHotelById(Long id) throws ServiceException {
	Hotel hotel = null;
	try {
	    hotel = (Hotel) hotelDAO.findById(Hotel.class, id);
	    hotel.getUserIds();
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return hotel;
    }

    public List<Hotel> findHotelsById(Long id[]) throws ServiceException {
	try {
	    return hotelDAO.findAll(Hotel.class, id);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.service.hotel.IHotelManager#saveHotel(com.yd.etravel.domain
     * .hotel.Hotel)
     */
    public Hotel saveHotel(Hotel hotel, final Long[] userids)
	    throws ServiceException {
	try {

	    if (hotelDAO.isHotelNameExist(hotel.getName(), hotel.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.hotelname.exist"));
	    }
	    UserSearchDTO userSearchDTO = new UserSearchDTO();
	    userSearchDTO.setIds(userids);
	    if (hotel.getSuperUser() != null && !hotel.getSuperUser().isEmpty())
		hotel.getSuperUser().clear();
	    hotel.setSuperUser(userDAO.findUsers(userSearchDTO));

	    if (hotel.getId() == null) {
		hotel = (Hotel) hotelDAO.save(hotel);
	    } else {
		hotel = (Hotel) hotelDAO.update(hotel);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return hotel;
    }

}
