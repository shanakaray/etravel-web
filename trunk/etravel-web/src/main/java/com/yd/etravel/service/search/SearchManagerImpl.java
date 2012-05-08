package com.yd.etravel.service.search;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.yd.etravel.domain.custom.search.RoomDTO;
import com.yd.etravel.domain.custom.search.SearchRequestDTO;
import com.yd.etravel.domain.custom.search.SearchResultsDTO;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.persistence.dao.room.availability.IRoomAvailabilityDAO;
import com.yd.etravel.persistence.dao.search.ISearchDAO;
import com.yd.etravel.persistence.dao.season.ISeasonDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.util.SortedCollection;

/**
 * 
 * @author Dharsahana
 * 
 */
public class SearchManagerImpl implements ISearchManager {

    private ISearchDAO searchDAO;
    private ISeasonDAO seasonDAO;
    private IRoomAvailabilityDAO roomAvailabilityDAO;

    public void setSearchDAO(final ISearchDAO searchDAO) {
	this.searchDAO = searchDAO;
    }

    public void setSeasonDAO(final ISeasonDAO seasonDAO) {
	this.seasonDAO = seasonDAO;
    }

    public void setRoomAvailabilityDAO(final IRoomAvailabilityDAO roomAvailabilityDAO) {
	this.roomAvailabilityDAO = roomAvailabilityDAO;
    }

    @Override
    public SearchResultsDTO searchRoom(final SearchRequestDTO searchRequestDTO)
	    throws ServiceException {
	final SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
	try {
	    final List<RoomAvailability> list = this.searchDAO.findRooms(searchRequestDTO);
	    List<RoomDTO> roomDTOList = new ArrayList<RoomDTO>();

	    if (list == null || list.isEmpty()) {

		roomDTOList = findRoomDateCrossAllocation(searchRequestDTO);
	    } else {

		for (final RoomAvailability type : list) {
		    final RoomDTO roomDTO = new RoomDTO();

		    final RoomAvailability maxType =

		    findRoomAvailability(type,
			    searchRequestDTO.getCheckIn(),
			    searchRequestDTO.getCheckOut());
		    if (maxType.getAvailableUnit() <= 0) {
			continue;

		    }
		    roomDTO.setRoomAvailability(maxType);
		    roomDTO.setRoom(type.getRoom());
		    roomDTO.setRoomType(type.getRoom().getRoomType());
		    roomDTO.setHotel(type.getRoom().getHotel());
		    roomDTO.setId(type.getId());

		    List<RoomSeasonalRate> rsrList = new ArrayList<RoomSeasonalRate>();
		    rsrList = this.seasonDAO.findRoomSeasonalRateByRoomId(type
			    .getRoom().getId());
		    final RoomSeasonalRate roomSeasonalRate = findRoomSeasonalRate(searchRequestDTO, rsrList);

		    if (roomSeasonalRate.getSeason() == null) {
			roomSeasonalRate.setSeason(new Season());
			continue;
		    }
		    roomDTO.setRoomSeasonalRate(roomSeasonalRate);
		    roomDTOList.add(roomDTO);
		}
	    }
	    searchResultsDTO.setRoomDTO(roomDTOList);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return searchResultsDTO;
    }

    private RoomSeasonalRate findRoomSeasonalRate(
	    final SearchRequestDTO searchRequestDTO, final List<RoomSeasonalRate> rsrList) {

	RoomSeasonalRate rsr = new RoomSeasonalRate();

	boolean flag = false;

	rsrList.iterator();

	for (final RoomSeasonalRate type : rsrList) {

	    if (type.getSeason().getFromDate().getTime() <= searchRequestDTO
		    .getCheckIn().getTime()
		    && searchRequestDTO.getCheckOut().getTime() <= type
			    .getSeason().getToDate().getTime()) {
		rsr = type;
		flag = true;
		break;
	    }
	}
	if (!flag) {

	    for (final RoomSeasonalRate type : rsrList) {
		if (type.getSeason().getFromDate().getTime() <= searchRequestDTO
			.getCheckIn().getTime()
			&& searchRequestDTO.getCheckIn().getTime() <= type
				.getSeason().getToDate().getTime()) {
		    rsr = type;
		    flag = true;
		    break;
		}
	    }
	}
	return rsr;

    }

    private RoomAvailability findRoomAvailability(
	    final RoomAvailability roomAvailability, final Date checkIn, final Date checkOut)
	    throws ServiceException {

	try {
	    ArrayList<RoomDailyAvailability> roomDailyAvList = (ArrayList<RoomDailyAvailability>) this.roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    roomAvailability.getId(), checkIn, checkOut);

	    roomDailyAvList = (ArrayList<RoomDailyAvailability>) SortedCollection
		    .orderByField(roomDailyAvList, "availabalUnit", true);

	    if (!roomDailyAvList.isEmpty()) {

		roomAvailability
			.setAvailableUnit((roomDailyAvList
				.get(0)).getAvailabalUnit());
	    }

	    // should return here minimum available room
	} catch (final PersistenceException e) {
	    // TODO Auto-generated catch block
	    throw new ServiceException(null, e);
	} catch (final Exception e) {
	    // TODO Auto-generated catch block
	    throw new ServiceException(null, e);
	}

	return roomAvailability;

    }

    private List<RoomDTO> findRoomDateCrossAllocation(
	    final SearchRequestDTO searchRequestDTO) throws PersistenceException,
	    ServiceException {

	new ArrayList();
	final List<RoomAvailability> checkInList = this.searchDAO
		.findRoomsByCheckInDate(searchRequestDTO);
	final List<RoomAvailability> checkOutList = this.searchDAO
		.findRoomsByCheckOutDate(searchRequestDTO);
	final List<RoomDTO> roomDTOList = new ArrayList<RoomDTO>();

	final Iterator<RoomAvailability> iterCheckIn = checkInList.iterator();
	while (iterCheckIn.hasNext()) {
	    final RoomAvailability roomAvailabilityCheckIn = iterCheckIn
		    .next();

	    final Iterator<RoomAvailability> iterCheckOut = checkOutList.iterator();
	    while (iterCheckOut.hasNext()) {
		final RoomAvailability roomAvailabilityCheckOut = iterCheckOut
			.next();

		Calendar cal;
		cal = Calendar.getInstance();
		cal.setTime(roomAvailabilityCheckIn.getToDate());
		cal.add(Calendar.DATE, 1);

		if (roomAvailabilityCheckIn
			.getRoom()
			.getRoomType()
			.getName()
			.equals(roomAvailabilityCheckOut.getRoom()
				.getRoomType().getName())) {

		    if (cal.getTime().getTime() == roomAvailabilityCheckOut
			    .getFromDate().getTime()) {

			RoomDTO roomDTO = new RoomDTO();

			roomDTO.setRoomAvailabilityCheckIn(roomAvailabilityCheckIn);
			roomDTO.setRoomAvailabilityCheckOut(roomAvailabilityCheckOut);
			roomDTO.setCombineAvailability(true);

			final RoomAvailability type = roomAvailabilityCheckIn;
			roomDTO.setRoomAvailability(new RoomAvailability());
			roomDTO.getRoomAvailability().setFromDate(
				searchRequestDTO.getCheckIn());
			roomDTO.getRoomAvailability().setToDate(
				searchRequestDTO.getCheckOut());
			roomDTO = findCombineRoomAvailability(roomDTO);
			roomDTO.setRoom(type.getRoom());
			roomDTO.setRoomType(type.getRoom().getRoomType());
			roomDTO.setHotel(type.getRoom().getHotel());

			roomDTO.setId(type.getId());
			// roomDTO.setOccupancy(type.getOccupancy());

			List<RoomSeasonalRate> rsrList = new ArrayList<RoomSeasonalRate>();
			rsrList = this.seasonDAO.findRoomSeasonalRateByRoomId(type
				.getRoom().getId());
			final RoomSeasonalRate roomSeasonalRate = findRoomSeasonalRate(searchRequestDTO, rsrList);

			if (roomSeasonalRate.getSeason() == null) {
			    roomSeasonalRate.setSeason(new Season());
			    continue;
			}

			roomDTO.setRoomSeasonalRate(roomSeasonalRate);
			roomDTOList.add(roomDTO);

		    }
		}

	    }

	}

	return roomDTOList;

    }

    private RoomDTO findCombineRoomAvailability(final RoomDTO roomDTO)
	    throws ServiceException {

	try {

	    final RoomAvailability roomAvailabilityCheckIn = roomDTO
		    .getRoomAvailabilityCheckIn();
	    final RoomAvailability roomAvailabilityCheckOut = roomDTO
		    .getRoomAvailabilityCheckOut();

	    ArrayList<RoomDailyAvailability> roomDailyAvListCheckIn = (ArrayList<RoomDailyAvailability>) this.roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    roomAvailabilityCheckIn.getId(), roomDTO
				    .getRoomAvailability().getFromDate(),
			    roomDTO.getRoomAvailability().getToDate());

	    roomDailyAvListCheckIn = (ArrayList<RoomDailyAvailability>) SortedCollection
		    .orderByField(roomDailyAvListCheckIn, "availabalUnit", true);

	    ArrayList<RoomDailyAvailability> roomDailyAvListCheckOut = (ArrayList<RoomDailyAvailability>) this.roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    roomAvailabilityCheckOut.getId(), roomDTO
				    .getRoomAvailability().getFromDate(),
			    roomDTO.getRoomAvailability().getToDate());

	    roomDailyAvListCheckOut = (ArrayList<RoomDailyAvailability>) SortedCollection
		    .orderByField(roomDailyAvListCheckOut, "availabalUnit",
			    true);

	    int avalUnitCheckIn = 0;
	    int avalUnitCheckOut = 0;

	    if (!roomDailyAvListCheckIn.isEmpty()) {

		avalUnitCheckIn = (roomDailyAvListCheckIn
			.get(0)).getAvailabalUnit();
	    }
	    if (!roomDailyAvListCheckOut.isEmpty()) {

		avalUnitCheckOut = (roomDailyAvListCheckOut
			.get(0)).getAvailabalUnit();
	    }
	    if (avalUnitCheckIn > avalUnitCheckOut) {
		roomDTO.getRoomAvailability()
			.setAvailableUnit(avalUnitCheckOut);
	    } else {
		roomDTO.getRoomAvailability().setAvailableUnit(avalUnitCheckIn);
	    }

	    int allocatedUnitCheckIn = 0;
	    int allocatedUnitCheckOut = 0;

	    if (!roomDailyAvListCheckIn.isEmpty()) {

		allocatedUnitCheckIn = roomAvailabilityCheckIn.getUnit();
	    }
	    if (!roomDailyAvListCheckOut.isEmpty()) {

		allocatedUnitCheckOut = roomAvailabilityCheckOut.getUnit();
	    }
	    if (allocatedUnitCheckIn > allocatedUnitCheckOut) {
		roomDTO.getRoomAvailability().setUnit(allocatedUnitCheckOut);
	    } else {
		roomDTO.getRoomAvailability().setUnit(allocatedUnitCheckIn);
	    }
	    // should return here minimum avalable room
	} catch (final PersistenceException e) {
	    // TODO Auto-generated catch block
	    throw new ServiceException(null, e);
	} catch (final Exception e) {
	    // TODO Auto-generated catch block
	    throw new ServiceException(null, e);
	}

	return roomDTO;

    }

}
