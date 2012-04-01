/**
 * 
 */
package com.yd.etravel.service.room.availability;

import java.util.List;

import com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO;
import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.service.exception.ServiceException;

/**
 * @author Dharsahana
 * 
 */
public interface IRoomAvailabilityManager {

    public RoomAvailability save(final RoomAvailability roomAvailability)
	    throws ServiceException;

    public RoomAvailability findRoomAvailabilityById(Long id)
	    throws ServiceException;

    public int deleteRoomAvailability(Long id) throws ServiceException;

    public List<RoomAvailability> findAllRoomAvailability()
	    throws ServiceException;

    @Deprecated
    /**
     * @see List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(Long hotelId)
     *      instead
     */
    public List<RoomAvailability> findAllRoomAvailabilityWithRoomAndOccu(
	    Long hotelid) throws ServiceException;

    public List<RoomDailyAvailability> findAllRoomDailyAvailability(Long id)
	    throws ServiceException;

    public List<RoomDailyAvailability> findAllRoomDailyAvailability()
	    throws ServiceException;

    public RoomAvailability findRoomAvailabilityWithRoomInfoById(Long id)
	    throws ServiceException;

    public List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(
	    RoomAvailabilityDTO dto) throws ServiceException;

    public List<DailyAvailabilityDTO> findDailyAvailability(Long id)
	    throws ServiceException;
}
