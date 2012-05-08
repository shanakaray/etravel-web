package com.yd.etravel.service.pax;

import java.util.List;

import com.yd.etravel.domain.custom.pax.PaxSearchDTO;
import com.yd.etravel.domain.hotel.Pax;
import com.yd.etravel.persistence.dao.pax.IPaxDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

/**
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:24:19 PM Type :
 *         com.yd.etravel.service.pax.PaxManagerImpl
 * 
 */
public class PaxManagerImpl implements IPaxManager {

    private IPaxDAO paxDAO;

    public void setPaxDAO(final IPaxDAO paxDAO) {
	this.paxDAO = paxDAO;
    }

    @Override
    public int deletePax(final Long id) throws ServiceException {
	int count = 0;
	try {
	    count = this.paxDAO.deleteAny(Pax.class, id);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return count;
    }

    @Override
    public List<Pax> findAllActivePax() throws ServiceException {
	try {
	    return this.paxDAO.findAllActive(Pax.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<Pax> findAllPax() throws ServiceException {
	try {
	    return this.paxDAO.findAll(Pax.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<Pax> findPaxByHotel(final Long hotelId) throws ServiceException {
	try {
	    final PaxSearchDTO searchDTO = new PaxSearchDTO();
	    searchDTO.setHotelId(hotelId);
	    return this.paxDAO.findPax(searchDTO);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public Pax findPaxById(final Long id) throws ServiceException {
	Pax pax = null;
	try {

	    pax = (Pax) this.paxDAO.findById(Pax.class, id);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return pax;

    }

    @Override
    public Pax savePax(Pax pax) throws ServiceException {
	try {

	    if (this.paxDAO.isPaxTypeExist(pax.getHotel().getId(), pax.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.pax.type.exist"));
	    }

	    if (pax.getId() == null) {
		pax = (Pax) this.paxDAO.save(pax);
	    } else {
		pax = (Pax) this.paxDAO.update(pax);
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return pax;
    }

    @Override
    public List<Pax> findPax(final PaxSearchDTO paxSearchDTO) throws ServiceException {
	try {
	    return this.paxDAO.findPax(paxSearchDTO);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

}
