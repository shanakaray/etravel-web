package com.yd.etravel.persistence.dao.occupancy;

import java.util.List;

import com.yd.etravel.domain.occupancy.Occupancy;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

public interface IOccupancyDAO extends IBaseDAO<Occupancy> {

	public boolean isOccupancyNameExist(final String occupancyName, Long id)
			throws PersistenceException;

	public List<Occupancy> findAllOccupancyWithRoomType()
			throws PersistenceException;

	public boolean findAllOccupancyByPaxInfo(final Occupancy occupancy)
			throws PersistenceException;

	public List<Occupancy> findAllOccupancyByRoomType(Long roomTypeId)
			throws PersistenceException;
}
