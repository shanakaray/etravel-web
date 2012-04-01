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

    public void setSearchDAO(ISearchDAO searchDAO) {
	this.searchDAO = searchDAO;
    }

    public void setSeasonDAO(ISeasonDAO seasonDAO) {
	this.seasonDAO = seasonDAO;
    }

    public void setRoomAvailabilityDAO(IRoomAvailabilityDAO roomAvailabilityDAO) {
	this.roomAvailabilityDAO = roomAvailabilityDAO;
    }

    public SearchResultsDTO searchRoom(SearchRequestDTO searchRequestDTO)
	    throws ServiceException {
	SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
	try {
	    List<RoomAvailability> list = searchDAO.findRooms(searchRequestDTO);
	    List<RoomDTO> roomDTOList = new ArrayList<RoomDTO>();

	    if (list == null || list.isEmpty()) {

		roomDTOList = this
			.findRoomDateCrossAllocation(searchRequestDTO);
	    } else {

		for (RoomAvailability type : list) {
		    RoomDTO roomDTO = new RoomDTO();

		    RoomAvailability maxType =

		    this.findRoomAvailability(type,
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
		    rsrList = seasonDAO.findRoomSeasonalRateByRoomId(type
			    .getRoom().getId());
		    RoomSeasonalRate roomSeasonalRate = this
			    .findRoomSeasonalRate(searchRequestDTO, rsrList);

		    if (roomSeasonalRate.getSeason() == null) {
			roomSeasonalRate.setSeason(new Season());
			continue;
		    }
		    roomDTO.setRoomSeasonalRate(roomSeasonalRate);
		    roomDTOList.add(roomDTO);
		}
	    }
	    searchResultsDTO.setRoomDTO(roomDTOList);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return searchResultsDTO;
    }

    private RoomSeasonalRate findRoomSeasonalRate(
	    SearchRequestDTO searchRequestDTO, List<RoomSeasonalRate> rsrList) {

	RoomSeasonalRate rsr = new RoomSeasonalRate();

	boolean flag = false;

	Iterator<RoomSeasonalRate> it = rsrList.iterator();

	for (RoomSeasonalRate type : rsrList) {

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

	    for (RoomSeasonalRate type : rsrList) {
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
	    RoomAvailability roomAvailability, Date checkIn, Date checkOut)
	    throws ServiceException {

	try {
	    ArrayList<RoomDailyAvailability> roomDailyAvList = (ArrayList<RoomDailyAvailability>) roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    roomAvailability.getId(), checkIn, checkOut);

	    roomDailyAvList = (ArrayList<RoomDailyAvailability>) SortedCollection
		    .orderByField(roomDailyAvList, "availabalUnit", true);

	    if (!roomDailyAvList.isEmpty()) {

		roomAvailability
			.setAvailableUnit(((RoomDailyAvailability) roomDailyAvList
				.get(0)).getAvailabalUnit());
	    }

	    // should return here minimum available room
	} catch (PersistenceException e) {
	    // TODO Auto-generated catch block
	    throw new ServiceException(null, e);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    throw new ServiceException(null, e);
	}

	return roomAvailability;

    }

    private List<RoomDTO> findRoomDateCrossAllocation(
	    SearchRequestDTO searchRequestDTO) throws PersistenceException,
	    ServiceException {

	ArrayList listFound = new ArrayList();
	List<RoomAvailability> checkInList = searchDAO
		.findRoomsByCheckInDate(searchRequestDTO);
	List<RoomAvailability> checkOutList = searchDAO
		.findRoomsByCheckOutDate(searchRequestDTO);
	List<RoomDTO> roomDTOList = new ArrayList<RoomDTO>();

	Iterator<RoomAvailability> iterCheckIn = checkInList.iterator();
	while (iterCheckIn.hasNext()) {
	    RoomAvailability roomAvailabilityCheckIn = (RoomAvailability) iterCheckIn
		    .next();

	    Iterator<RoomAvailability> iterCheckOut = checkOutList.iterator();
	    while (iterCheckOut.hasNext()) {
		RoomAvailability roomAvailabilityCheckOut = (RoomAvailability) iterCheckOut
			.next();

		Calendar cal;
		cal = Calendar.getInstance();
		cal.setTime(roomAvailabilityCheckIn.getToDate());
		cal.add(cal.DATE, 1);

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

			RoomAvailability type = roomAvailabilityCheckIn;
			roomDTO.setRoomAvailability(new RoomAvailability());
			roomDTO.getRoomAvailability().setFromDate(
				searchRequestDTO.getCheckIn());
			roomDTO.getRoomAvailability().setToDate(
				searchRequestDTO.getCheckOut());
			roomDTO = this.findCombineRoomAvailability(roomDTO);
			roomDTO.setRoom(type.getRoom());
			roomDTO.setRoomType(type.getRoom().getRoomType());
			roomDTO.setHotel(type.getRoom().getHotel());

			roomDTO.setId(type.getId());
			// roomDTO.setOccupancy(type.getOccupancy());

			List<RoomSeasonalRate> rsrList = new ArrayList<RoomSeasonalRate>();
			rsrList = seasonDAO.findRoomSeasonalRateByRoomId(type
				.getRoom().getId());
			RoomSeasonalRate roomSeasonalRate = this
				.findRoomSeasonalRate(searchRequestDTO, rsrList);

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

    private RoomDTO findCombineRoomAvailability(RoomDTO roomDTO)
	    throws ServiceException {

	try {

	    RoomAvailability roomAvailabilityCheckIn = roomDTO
		    .getRoomAvailabilityCheckIn();
	    RoomAvailability roomAvailabilityCheckOut = roomDTO
		    .getRoomAvailabilityCheckOut();

	    ArrayList<RoomDailyAvailability> roomDailyAvListCheckIn = (ArrayList<RoomDailyAvailability>) roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    roomAvailabilityCheckIn.getId(), roomDTO
				    .getRoomAvailability().getFromDate(),
			    roomDTO.getRoomAvailability().getToDate());

	    roomDailyAvListCheckIn = (ArrayList<RoomDailyAvailability>) SortedCollection
		    .orderByField(roomDailyAvListCheckIn, "availabalUnit", true);

	    ArrayList<RoomDailyAvailability> roomDailyAvListCheckOut = (ArrayList<RoomDailyAvailability>) roomAvailabilityDAO
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

		avalUnitCheckIn = ((RoomDailyAvailability) roomDailyAvListCheckIn
			.get(0)).getAvailabalUnit();
	    }
	    if (!roomDailyAvListCheckOut.isEmpty()) {

		avalUnitCheckOut = ((RoomDailyAvailability) roomDailyAvListCheckOut
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
	} catch (PersistenceException e) {
	    // TODO Auto-generated catch block
	    throw new ServiceException(null, e);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    throw new ServiceException(null, e);
	}

	return roomDTO;

    }

}
