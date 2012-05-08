package com.yd.etravel.service.room.availability;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO;
import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.persistence.dao.occupancy.IOccupancyDAO;
import com.yd.etravel.persistence.dao.room.IRoomDAO;
import com.yd.etravel.persistence.dao.room.availability.IRoomAvailabilityDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

/**
 * 
 * 
 * @author Dharsahana
 * 
 */
public class RoomAvailabilityManagerImpl implements IRoomAvailabilityManager {

    private IRoomAvailabilityDAO roomAvailabilityDAO;
    private IRoomDAO roomDAO;
    private IOccupancyDAO occupancyDAO;

    public void setRoomAvailabilityDAO(IRoomAvailabilityDAO roomAvailabilityDAO) {
	this.roomAvailabilityDAO = roomAvailabilityDAO;
    }

    public void setRoomDAO(IRoomDAO roomDAO) {
	this.roomDAO = roomDAO;
    }

    public void setOccupancyDAO(IOccupancyDAO occupancyDAO) {
	this.occupancyDAO = occupancyDAO;
    }

    public RoomAvailability save(RoomAvailability roomAvail)
	    throws ServiceException {
	try {
	    if (roomAvail.getId() == null) {
		if (!roomAvailabilityDAO.isDataRangeValid(roomAvail.getRoom()
			.getId(), roomAvail.getFromDate(), roomAvail
			.getToDate())) {

		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.roomAvailability.dateRange.valid"));

		} else {

		    Room room = (Room) roomDAO.findById(Room.class, roomAvail
			    .getRoom().getId());
		    // Occupancy occ = (Occupancy) occupancyDAO.findById(
		    // Occupancy.class, roomAvail.getOccupancy()
		    // .getId());
		    if (room.getNoOfRoom() < roomAvail.getUnit()) {

			throw new ServiceException(
				ValidationHelper
					.getMessageHolder("etravel.roomAvailability.max.allocated.room"));

		    }

		    roomAvail.setRoom(room);
		    // roomAvail.setOccupancy(occ);

		    roomAvail = (RoomAvailability) roomAvailabilityDAO
			    .save(roomAvail);

		    boolean flag = true;

		    Date from = roomAvail.getFromDate();
		    Date to = roomAvail.getToDate();

		    while (flag) {

			RoomDailyAvailability rda = new RoomDailyAvailability();
			rda.setRoomAvailability(roomAvail);
			rda.setAllocatedUnit(roomAvail.getUnit());
			rda.setAvailabalUnit(roomAvail.getUnit());
			rda.setActive(true);

			rda.setDate(from);
			roomAvailabilityDAO.save(rda);

			Calendar cal = Calendar.getInstance();
			cal.setTime(from);
			cal.add(cal.DATE, 1);
			from = cal.getTime();
			if (from.getTime() > to.getTime()) {

			    flag = false;
			}
		    }

		}

	    } else {
		// if (!roomAvailDAO.isDataRangeValid(roomAvail
		// .getRoom().getId(), roomAvail.getFromDate())) {
		//
		// throw new ServiceException(
		// ValidationHelper
		// .getMessageHolder("etravel.roomAvailability.dateRange.valid"));
		//
		// } else {
		Room room = (Room) roomDAO.findById(Room.class, roomAvail
			.getRoom().getId());
		// Occupancy occ = (Occupancy) occupancyDAO.findById(
		// Occupancy.class, roomAvailability.getOccupancy()
		// .getId());

		// roomAvailability.setOccupancy(occ);
		roomAvail.setRoom(room);
		roomAvailabilityDAO.update(roomAvail);

		List<RoomDailyAvailability> availabilityList = roomAvailabilityDAO
			.findAllRoomDailyAvailabilityByRoomAvailabilityId(roomAvail
				.getId());

		for (RoomDailyAvailability dailyRoomAv : availabilityList) {

		    dailyRoomAv.setAllocatedUnit(roomAvail.getUnit());

		    int avaunit = 0;
		    if (roomAvail.getUnit() < dailyRoomAv.getAvailabalUnit()) {
			avaunit = roomAvail.getUnit();
			dailyRoomAv.setAvailabalUnit(avaunit);
		    }

		    roomAvailabilityDAO.update(dailyRoomAv);

		}

		// }

	    }
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomAvail;
    }

    public RoomAvailability findRoomAvailabilityById(final Long id)
	    throws ServiceException {
	RoomAvailability roomAvailability = null;
	try {
	    roomAvailability = (RoomAvailability) roomAvailabilityDAO.findById(
		    RoomAvailability.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomAvailability;
    }

    public int deleteRoomAvailability(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = roomAvailabilityDAO.deleteAny(RoomAvailability.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    public List<RoomAvailability> findAllRoomAvailability()
	    throws ServiceException {
	try {
	    return roomAvailabilityDAO.findAll(RoomAvailability.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Deprecated
    /**
     * @see List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(Long hotelId)
     *      instead
     */
    public List<RoomAvailability> findAllRoomAvailabilityWithRoomAndOccu(
	    Long hotelId) throws ServiceException {
	try {
	    return roomAvailabilityDAO
		    .findAllRoomAvailabilityWithRoomAndOccu(hotelId);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<RoomDailyAvailability> findAllRoomDailyAvailability(Long id)
	    throws ServiceException {
	try {
	    return roomAvailabilityDAO.findAllRoomDailyAvailability(id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<RoomDailyAvailability> findAllRoomDailyAvailability()
	    throws ServiceException {
	try {
	    return roomAvailabilityDAO.findAllRoomDailyAvailability();

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public RoomAvailability findRoomAvailabilityWithRoomInfoById(Long id)
	    throws ServiceException {
	try {
	    return roomAvailabilityDAO.findRoomAvailabilityById(id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    public List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(
	    RoomAvailabilityDTO dto) throws ServiceException {
	try {
	    return roomAvailabilityDAO.findAllRoomAvailabilityDTO(dto);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<DailyAvailabilityDTO> findDailyAvailability(Long id)
	    throws ServiceException {
	try {
	    return roomAvailabilityDAO.findDailyAvailability(id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

}
