/**
 * 
 */
package com.yd.etravel.service.pax;

import java.util.List;

import com.yd.etravel.domain.custom.pax.PaxSearchDTO;
import com.yd.etravel.domain.hotel.Pax;
import com.yd.etravel.service.exception.ServiceException;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Jan 26, 2009 : 9:19:29 PM Type :
 *         com.yd.etravel.service.pax.IPaxManager
 * 
 */
public interface IPaxManager {

    public List<Pax> findAllActivePax() throws ServiceException;

    public Pax savePax(final Pax pax) throws ServiceException;

    public List<Pax> findAllPax() throws ServiceException;

    public Pax findPaxById(Long id) throws ServiceException;

    public int deletePax(Long id) throws ServiceException;

    public List<Pax> findPaxByHotel(Long hotelid) throws ServiceException;

    public List<Pax> findPax(PaxSearchDTO paxSearchDTO) throws ServiceException;

}
