package com.yd.etravel.persistence.dao.search;

import java.util.List;

import com.yd.etravel.domain.custom.search.SearchRequestDTO;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author Dharsahana
 * 
 */
public interface ISearchDAO extends IBaseDAO<RoomAvailability> {

    public List<RoomAvailability> findRooms(
	    final SearchRequestDTO searchRequestDTO)
	    throws PersistenceException;

    public List<RoomAvailability> findRoomsByCheckOutDate(
	    final SearchRequestDTO searchRequestDTO)
	    throws PersistenceException;

    public List<RoomAvailability> findRoomsByCheckInDate(
	    final SearchRequestDTO searchRequestDTO)
	    throws PersistenceException;
}
