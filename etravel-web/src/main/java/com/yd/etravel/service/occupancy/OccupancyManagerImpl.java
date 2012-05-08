package com.yd.etravel.service.occupancy;

import java.util.List;

import com.yd.etravel.domain.occupancy.Occupancy;
import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.persistence.dao.occupancy.IOccupancyDAO;
import com.yd.etravel.persistence.dao.roomtype.IRoomTypeDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

/**
 * 
 * @author Dharshana
 * 
 */
public class OccupancyManagerImpl implements IOccupancyManager {

    private IOccupancyDAO occupancyDAO;
    private IRoomTypeDAO roomTypeDAO;

    public void setOccupancyDAO(final IOccupancyDAO occupancyDAO) {
	this.occupancyDAO = occupancyDAO;
    }

    public void setRoomTypeDAO(final IRoomTypeDAO roomTypeDAO) {
	this.roomTypeDAO = roomTypeDAO;
    }

    @Override
    public Occupancy save(final Occupancy occupancy) throws ServiceException {
	try {

	    if (this.occupancyDAO.isOccupancyNameExist(occupancy.getName(),
		    occupancy.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.occupancyName.exist"));
	    }

	    if (occupancy.getId() == null) {
		if (!this.occupancyDAO.findAllOccupancyByPaxInfo(occupancy)) {

		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.occupancy.paxCombination.exist"));

		} else {

		    final RoomType roomType = (RoomType) this.roomTypeDAO.findById(
			    RoomType.class, occupancy.getRoomType().getId());

		    occupancy.setRoomType(roomType);

		    this.occupancyDAO.save(occupancy);
		}

	    } else {

		final RoomType roomType = (RoomType) this.roomTypeDAO.findById(
			RoomType.class, occupancy.getRoomType().getId());

		occupancy.setRoomType(roomType);
		this.occupancyDAO.update(occupancy);
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return occupancy;
    }

    @Override
    public Occupancy findOccupancyById(final Long id) throws ServiceException {
	Occupancy occupancy = null;
	try {
	    occupancy = (Occupancy) this.occupancyDAO.findById(Occupancy.class, id);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return occupancy;
    }

    @Override
    public int deleteOccupancy(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = this.occupancyDAO.deleteAny(Occupancy.class, id);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    @Override
    public List<Occupancy> findAllOccupancy() throws ServiceException {
	try {
	    return this.occupancyDAO.findAll(Occupancy.class);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<Occupancy> findAllOccupancyWithRoomType()
	    throws ServiceException {
	try {
	    return this.occupancyDAO
		    .findAllOccupancyWithRoomType();

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    @Override
    public List<Occupancy> findAllActiveOccupancy() throws ServiceException {
	try {
	    return this.occupancyDAO.findAllActive(Occupancy.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    @Override
    public List<Occupancy> findAllOccupancyByRoomType(final Long roomTypeId)
	    throws ServiceException {
	try {

	    return this.occupancyDAO.findAllOccupancyByRoomType(roomTypeId);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }
}
