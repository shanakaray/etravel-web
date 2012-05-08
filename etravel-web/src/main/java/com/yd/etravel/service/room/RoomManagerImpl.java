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
	return roomDAO;
    }

    public void setRoomDAO(IRoomDAO roomDAO) {
	this.roomDAO = roomDAO;
    }

    public int deleteRoom(Long id) throws ServiceException {
	int val = 0;
	try {
	    val = roomDAO.deleteAny(Room.class, id);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return val;
    }

    public List<Room> findAllRooms() throws ServiceException {
	try {
	    return roomDAO.findAll(Room.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    public Room findRoomById(Long id) throws ServiceException {
	Room room;
	try {
	    room = (Room) roomDAO.findById(Room.class, id);
	    room.toString();
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return room;
    }

    public List<Room> findRooms(RoomSearchDTO roomSearchDTO)
	    throws ServiceException {
	try {
	    return roomDAO.findRooms(roomSearchDTO);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    public Room saveRoom(Room room) throws ServiceException {
	try {

	    if (roomDAO.isExist(room.getHotel().getId(), room.getRoomType()
		    .getId(), room.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.hotel.room.exist"));
	    }

	    if (room.getId() == null) {
		room = (Room) roomDAO.save(room);
	    } else {
		room = (Room) roomDAO.update(room);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

	return room;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    public List<Room> findAllActiveRoom() throws ServiceException {
	try {
	    return roomDAO.findAllActive(Room.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<Room> findAllRoomWithRoomType(Long hotelid)
	    throws ServiceException {
	try {
	    return roomDAO.findAllRoomWithRoomType(hotelid);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

}
