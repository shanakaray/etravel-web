package com.yd.etravel.service.roomtype;

import java.util.List;

import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.persistence.dao.roomtype.IRoomTypeDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

/**
 * 
 * 
 * @author Dharshana
 * 
 */
public class RoomTypeManagerImpl implements IRoomTypeManager {

    private IRoomTypeDAO roomTypeDAO;

    public void setRoomTypeDAO(IRoomTypeDAO roomTypeDAO) {
	this.roomTypeDAO = roomTypeDAO;
    }

    public RoomType save(final RoomType roomType) throws ServiceException {
	try {

	    if (roomTypeDAO.isRoomTypeNameExist(roomType.getName(),
		    roomType.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.roomTypeName.exist"));
	    }

	    if (roomType.getId() == null) {
		roomTypeDAO.save(roomType);

	    } else {
		roomTypeDAO.update(roomType);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomType;
    }

    public RoomType findRoomTypeById(final Long id) throws ServiceException {
	RoomType roomType = null;
	try {
	    roomType = (RoomType) roomTypeDAO.findById(RoomType.class, id);
	    roomType.toString();
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomType;
    }

    public int deleteRoomType(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = roomTypeDAO.deleteAny(RoomType.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    public List<RoomType> findAllRoomType() throws ServiceException {
	try {
	    return roomTypeDAO.findAll(RoomType.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    public List<RoomType> findAllActiveRoomType() throws ServiceException {
	try {
	    return roomTypeDAO.findAllActive(RoomType.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }
}
