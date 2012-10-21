/**
 * 
 */
package com.yd.etravel.service.room;

import java.util.List;

import com.yd.etravel.domain.custom.room.RoomSearchDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.service.exception.ServiceException;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 11:15:09 AM Type :
 *         com.yd.etravel.service.room.IRoomManager
 * 
 */
public interface IRoomManager {

	public int deleteRoom(Long id) throws ServiceException;

	public List<Room> findAllActiveRoom() throws ServiceException;

	public List<Room> findAllRooms() throws ServiceException;

	public List<Room> findAllRoomWithRoomType(Long hotelid)
			throws ServiceException;

	public Room findRoomById(Long id) throws ServiceException;

	public List<Room> findRooms(RoomSearchDTO roomSearchDTO)
			throws ServiceException;

	public Room saveRoom(final Room Room) throws ServiceException;

}
