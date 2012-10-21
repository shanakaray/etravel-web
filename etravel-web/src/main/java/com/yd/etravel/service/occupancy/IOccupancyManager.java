/**
 * 
 */
package com.yd.etravel.service.occupancy;

import java.util.List;

import com.yd.etravel.domain.occupancy.Occupancy;
import com.yd.etravel.service.exception.ServiceException;

public interface IOccupancyManager {

	public Occupancy save(final Occupancy occupancy) throws ServiceException;

	public Occupancy findOccupancyById(Long id) throws ServiceException;

	public int deleteOccupancy(Long id) throws ServiceException;

	public List<Occupancy> findAllOccupancy() throws ServiceException;

	public List<Occupancy> findAllOccupancyWithRoomType()
			throws ServiceException;

	public List<Occupancy> findAllActiveOccupancy() throws ServiceException;

	public List<Occupancy> findAllOccupancyByRoomType(Long roomTypeId)
			throws ServiceException;

}
