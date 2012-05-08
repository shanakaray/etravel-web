package com.yd.etravel.service.room;

import java.util.List;

import com.yd.etravel.domain.custom.room.RoomSearchDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.persistence.dao.room.IRoomDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 11:56:42 AM Type :
 *         com.yd.etravel.service.room.RoomManagerImpl
 * 
 */
public class RoomManagerImpl implements IRoomManager {

    private IRoomDAO roomDAO;

    public IRoomDAO getRoomDAO() {
	return this.roomDAO;
    }

    public void setRoomDAO(final IRoomDAO roomDAO) {
	this.roomDAO = roomDAO;
    }

    @Override
    public int deleteRoom(final Long id) throws ServiceException {
	int val = 0;
	try {
	    val = this.roomDAO.deleteAny(Room.class, id);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return val;
    }

    @Override
    public List<Room> findAllRooms() throws ServiceException {
	try {
	    return this.roomDAO.findAll(Room.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    @Override
    public Room findRoomById(final Long id) throws ServiceException {
	Room room;
	try {
	    room = (Room) this.roomDAO.findById(Room.class, id);
	    room.toString();
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return room;
    }

    @Override
    public List<Room> findRooms(final RoomSearchDTO roomSearchDTO)
	    throws ServiceException {
	try {
	    return this.roomDAO.findRooms(roomSearchDTO);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    @Override
    public Room saveRoom(Room room) throws ServiceException {
	try {

	    if (this.roomDAO.isExist(room.getHotel().getId(), room.getRoomType()
		    .getId(), room.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.hotel.room.exist"));
	    }

	    if (room.getId() == null) {
		room = (Room) this.roomDAO.save(room);
	    } else {
		room = (Room) this.roomDAO.update(room);
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}

	return room;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    @Override
    public List<Room> findAllActiveRoom() throws ServiceException {
	try {
	    return this.roomDAO.findAllActive(Room.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<Room> findAllRoomWithRoomType(final Long hotelid)
	    throws ServiceException {
	try {
	    return this.roomDAO.findAllRoomWithRoomType(hotelid);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

}
