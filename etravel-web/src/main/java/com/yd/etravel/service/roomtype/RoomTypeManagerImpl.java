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

    public void setRoomTypeDAO(final IRoomTypeDAO roomTypeDAO) {
	this.roomTypeDAO = roomTypeDAO;
    }

    @Override
    public RoomType save(final RoomType roomType) throws ServiceException {
	try {

	    if (this.roomTypeDAO.isRoomTypeNameExist(roomType.getName(),
		    roomType.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.roomTypeName.exist"));
	    }

	    if (roomType.getId() == null) {
		this.roomTypeDAO.save(roomType);

	    } else {
		this.roomTypeDAO.update(roomType);
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomType;
    }

    @Override
    public RoomType findRoomTypeById(final Long id) throws ServiceException {
	RoomType roomType = null;
	try {
	    roomType = (RoomType) this.roomTypeDAO.findById(RoomType.class, id);
	    roomType.toString();
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomType;
    }

    @Override
    public int deleteRoomType(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = this.roomTypeDAO.deleteAny(RoomType.class, id);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    @Override
    public List<RoomType> findAllRoomType() throws ServiceException {
	try {
	    return this.roomTypeDAO.findAll(RoomType.class);
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
    public List<RoomType> findAllActiveRoomType() throws ServiceException {
	try {
	    return this.roomTypeDAO.findAllActive(RoomType.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }
}
