/**
 * 
 */
package com.yd.etravel.service.hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service(value = "hotelService")
@Transactional(propagation = Propagation.SUPPORTS)
public class HotelManagerImpl implements IHotelManager {

	@Autowired(required = true)
	private IHotelDAO hotelDAO;

	@Autowired(required = true)
	private IUserDAO userDAO;

	@Transactional
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

	@Override
	public List<Hotel> findAllActiveHotels() throws ServiceException {
		try {
			return this.hotelDAO.findAllActive();
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<Hotel> findAllHotel() throws ServiceException {
		try {
			return this.hotelDAO.findAll();
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public Hotel findHotelById(final Long id) throws ServiceException {
		Hotel hotel = null;
		try {
			hotel = this.hotelDAO.findById(id);
			hotel.getUserIds();
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return hotel;
	}

	@Override
	public List<Hotel> findHotelsById(final Long id[]) throws ServiceException {
		try {
			return this.hotelDAO.findAll(id);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Transactional
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
			if ((hotel.getSuperUser() != null)
					&& !hotel.getSuperUser().isEmpty()) {
				hotel.getSuperUser().clear();
			}
			hotel.setSuperUser(this.userDAO.findUsers(userSearchDTO));
			hotel = this.hotelDAO.saveOrUpdate(hotel);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return hotel;
	}

	public void setHotelDAO(final IHotelDAO hotelDAO) {
		this.hotelDAO = hotelDAO;
	}

	public void setUserDAO(final IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
