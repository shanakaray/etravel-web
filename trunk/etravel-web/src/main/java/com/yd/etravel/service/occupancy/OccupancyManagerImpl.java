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

    public void setOccupancyDAO(IOccupancyDAO occupancyDAO) {
	this.occupancyDAO = occupancyDAO;
    }

    public void setRoomTypeDAO(IRoomTypeDAO roomTypeDAO) {
	this.roomTypeDAO = roomTypeDAO;
    }

    public Occupancy save(final Occupancy occupancy) throws ServiceException {
	try {

	    if (occupancyDAO.isOccupancyNameExist(occupancy.getName(),
		    occupancy.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.occupancyName.exist"));
	    }

	    if (occupancy.getId() == null) {
		if (!occupancyDAO.findAllOccupancyByPaxInfo(occupancy)) {

		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.occupancy.paxCombination.exist"));

		} else {

		    RoomType roomType = (RoomType) roomTypeDAO.findById(
			    RoomType.class, occupancy.getRoomType().getId());

		    occupancy.setRoomType(roomType);

		    occupancyDAO.save(occupancy);
		}

	    } else {

		RoomType roomType = (RoomType) roomTypeDAO.findById(
			RoomType.class, occupancy.getRoomType().getId());

		occupancy.setRoomType(roomType);
		occupancyDAO.update(occupancy);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return occupancy;
    }

    public Occupancy findOccupancyById(final Long id) throws ServiceException {
	Occupancy occupancy = null;
	try {
	    occupancy = (Occupancy) occupancyDAO.findById(Occupancy.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return occupancy;
    }

    public int deleteOccupancy(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = occupancyDAO.deleteAny(Occupancy.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    public List<Occupancy> findAllOccupancy() throws ServiceException {
	try {
	    return occupancyDAO.findAll(Occupancy.class);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<Occupancy> findAllOccupancyWithRoomType()
	    throws ServiceException {
	try {
	    return (List<Occupancy>) occupancyDAO
		    .findAllOccupancyWithRoomType();

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    public List<Occupancy> findAllActiveOccupancy() throws ServiceException {
	try {
	    return occupancyDAO.findAllActive(Occupancy.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    public List<Occupancy> findAllOccupancyByRoomType(Long roomTypeId)
	    throws ServiceException {
	try {

	    return occupancyDAO.findAllOccupancyByRoomType(roomTypeId);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }
}
