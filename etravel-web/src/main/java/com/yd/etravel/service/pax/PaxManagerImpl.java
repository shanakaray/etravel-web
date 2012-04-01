package com.yd.etravel.service.pax;

import java.util.Collections;
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

    public void setPaxDAO(IPaxDAO paxDAO) {
	this.paxDAO = paxDAO;
    }

    public int deletePax(Long id) throws ServiceException {
	int count = 0;
	try {
	    count = paxDAO.deleteAny(Pax.class, id);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return count;
    }

    public List<Pax> findAllActivePax() throws ServiceException {
	List<Pax> list = Collections.EMPTY_LIST;
	try {
	    list = paxDAO.findAllActive(Pax.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return list;
    }

    public List<Pax> findAllPax() throws ServiceException {
	List<Pax> list = Collections.EMPTY_LIST;
	try {
	    list = paxDAO.findAll(Pax.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return list;
    }

    public List<Pax> findPaxByHotel(Long hotelId) throws ServiceException {
	List<Pax> list = Collections.EMPTY_LIST;
	try {
	    PaxSearchDTO searchDTO = new PaxSearchDTO();
	    searchDTO.setHotelId(hotelId);
	    list = paxDAO.findPax(searchDTO);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return list;
    }

    public Pax findPaxById(Long id) throws ServiceException {
	Pax pax = null;
	try {

	    pax = (Pax) paxDAO.findById(Pax.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return pax;

    }

    public Pax savePax(Pax pax) throws ServiceException {
	try {

	    if (paxDAO.isPaxTypeExist(pax.getHotel().getId(), pax.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.pax.type.exist"));
	    }

	    if (pax.getId() == null) {
		pax = (Pax) paxDAO.save(pax);
	    } else {
		pax = (Pax) paxDAO.update(pax);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return pax;
    }

    public List<Pax> findPax(PaxSearchDTO paxSearchDTO) throws ServiceException {
	List<Pax> list = Collections.EMPTY_LIST;
	try {
	    list = paxDAO.findPax(paxSearchDTO);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return list;
    }

}
