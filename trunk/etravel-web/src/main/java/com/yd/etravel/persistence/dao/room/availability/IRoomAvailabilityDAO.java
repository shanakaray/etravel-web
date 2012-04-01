package com.yd.etravel.persistence.dao.room.availability;

import java.util.Date;
import java.util.List;

import com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO;
import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;

/**
 * @author Dharsahana
 * 
 */
public interface IRoomAvailabilityDAO extends IBaseDAO {

    public boolean isDataRangeValid(final Long hotelId, Date fromDate,
	    Date toDate) throws PersistenceException;

    @Deprecated
    /**
     * use List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(Long hotelId)
     * instead
     */
    public List<RoomAvailability> findAllRoomAvailabilityWithRoomAndOccu(
	    Long hotelId) throws PersistenceException;

    public List<RoomDailyAvailability> findAllRoomDailyAvailability(Long id)
	    throws PersistenceException;

    public List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(
	    RoomAvailabilityDTO dto) throws PersistenceException;

    public List<RoomDailyAvailability> findAllRoomDailyAvailability()
	    throws PersistenceException;

    public RoomAvailability findRoomAvailabilityById(final Long id)
	    throws ServiceException, PersistenceException;

    public List<RoomDailyAvailability> findAllRoomDailyAvailabilityByRoomAvailabilityId(
	    Long id) throws PersistenceException;

    public List<RoomDailyAvailability> findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
	    Long id, Date checkIn, Date checkOut) throws PersistenceException;

    public List<DailyAvailabilityDTO> findDailyAvailability(Long id)
	    throws PersistenceException;

}
