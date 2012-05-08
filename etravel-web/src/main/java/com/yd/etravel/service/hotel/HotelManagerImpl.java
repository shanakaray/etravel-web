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

    public void setHotelDAO(final IHotelDAO hotelDAO) {
	this.hotelDAO = hotelDAO;
    }

    public void setUserDAO(final IUserDAO userDAO) {
	this.userDAO = userDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.service.hotel.IHotelManager#deleteHotel(java.lang.Long)
     */
    @Override
    public int deleteHotel(final Long id) throws ServiceException {
	int val = 0;
	try {
	    val = this.hotelDAO.deleteHotel(id);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    @Override
    public List<Hotel> findAllActiveHotels() throws ServiceException {
	try {
	    return this.hotelDAO.findAllActive(Hotel.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllHotel()
     */
    @Override
    public List<Hotel> findAllHotel() throws ServiceException {
	try {
	    return this.hotelDAO.findAll(Hotel.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.service.hotel.IHotelManager#findRoleById(java.lang.Long)
     */
    @Override
    public Hotel findHotelById(final Long id) throws ServiceException {
	Hotel hotel = null;
	try {
	    hotel = (Hotel) this.hotelDAO.findById(Hotel.class, id);
	    hotel.getUserIds();
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return hotel;
    }

    @Override
    public List<Hotel> findHotelsById(final Long id[]) throws ServiceException {
	try {
	    return this.hotelDAO.findAll(Hotel.class, id);
	} catch (final PersistenceException e) {
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
    @Override
    public Hotel saveHotel(Hotel hotel, final Long[] userids)
	    throws ServiceException {
	try {

	    if (this.hotelDAO.isHotelNameExist(hotel.getName(), hotel.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.hotelname.exist"));
	    }
	    final UserSearchDTO userSearchDTO = new UserSearchDTO();
	    userSearchDTO.setIds(userids);
	    if (hotel.getSuperUser() != null && !hotel.getSuperUser().isEmpty()) {
		hotel.getSuperUser().clear();
	    }
	    hotel.setSuperUser(this.userDAO.findUsers(userSearchDTO));

	    if (hotel.getId() == null) {
		hotel = (Hotel) this.hotelDAO.save(hotel);
	    } else {
		hotel = (Hotel) this.hotelDAO.update(hotel);
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return hotel;
    }

}
