/**
 * 
 */
package com.yd.etravel.persistence.dao.room;

import java.util.List;

import com.yd.etravel.domain.custom.room.RoomSearchDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 11:32:15 AM Type :
 *         com.yd.etravel.persistence.dao.room.IRoomDAO
 * 
 */
public interface IRoomDAO extends IBaseDAO<Room> {

	public List<Room> findAllRoomWithRoomType(Long hotelId)
			throws PersistenceException;

	public List<Room> findRooms(RoomSearchDTO roomSearchDTO)
			throws PersistenceException;

	public boolean isExist(Long hotelId, Long roomTypeId, Long id)
			throws PersistenceException;

}
