package com.yd.etravel.service.search;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

@Service(value = "searchService")
@Transactional(propagation = Propagation.SUPPORTS)
public class SearchManagerImpl implements ISearchManager {
    @Autowired(required = true)
    private ISearchDAO searchDAO;
    @Autowired(required = true)
    private ISeasonDAO seasonDAO;
    @Autowired(required = true)
    private IRoomAvailabilityDAO roomAvailabilityDAO;

    public void setSearchDAO(final ISearchDAO searchDAO) {
	this.searchDAO = searchDAO;
    }

    public void setSeasonDAO(final ISeasonDAO seasonDAO) {
	this.seasonDAO = seasonDAO;
    }

    public void setRoomAvailabilityDAO(
	    final IRoomAvailabilityDAO roomAvailabilityDAO) {
	this.roomAvailabilityDAO = roomAvailabilityDAO;
    }

    @Override
    public SearchResultsDTO searchRoom(final SearchRequestDTO searchRequestDTO)
	    throws ServiceException {
	final SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
	try {
	    final List<RoomAvailability> roomList = this.searchDAO
		    .findRooms(searchRequestDTO);
	    List<RoomDTO> roomDTOList = new ArrayList<RoomDTO>();

	    if (roomList == null || roomList.isEmpty()) {

		roomDTOList = findRoomDateCrossAllocation(searchRequestDTO);
	    } else {

		for (final RoomAvailability type : roomList) {
		    final RoomDTO room = new RoomDTO();

		    final RoomAvailability maxType =

		    findRoomAvailability(type, searchRequestDTO.getCheckIn(),
			    searchRequestDTO.getCheckOut());
		    if (maxType.getAvailableUnit() <= 0) {
			continue;

		    }
		    room.setRoomAvailability(maxType);
		    room.setRoom(type.getRoom());
		    room.setRoomType(type.getRoom().getRoomType());
		    room.setHotel(type.getRoom().getHotel());
		    room.setId(type.getId());

		    List<RoomSeasonalRate> rsrList = new ArrayList<RoomSeasonalRate>();
		    rsrList = this.seasonDAO.findRoomSeasonalRateByRoomId(type
			    .getRoom().getId());
		    final RoomSeasonalRate roomSeasonalRate = findRoomSeasonalRate(
			    searchRequestDTO, rsrList);

		    if (roomSeasonalRate.getSeason() == null) {
			roomSeasonalRate.setSeason(new Season());
			continue;
		    }
		    room.setRoomSeasonalRate(roomSeasonalRate);
		    roomDTOList.add(room);
		}
	    }
	    searchResultsDTO.setRoomDTO(roomDTOList);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return searchResultsDTO;
    }

    private RoomSeasonalRate findRoomSeasonalRate(
	    final SearchRequestDTO searchRequestDTO,
	    final List<RoomSeasonalRate> rsrList) {

	RoomSeasonalRate seasonrate = new RoomSeasonalRate();

	boolean flag = false;

	rsrList.iterator();

	for (final RoomSeasonalRate type : rsrList) {

	    if (type.getSeason().getFromDate().getTime() <= searchRequestDTO
		    .getCheckIn().getTime()
		    && searchRequestDTO.getCheckOut().getTime() <= type
			    .getSeason().getToDate().getTime()) {
		seasonrate = type;
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
		    seasonrate = type;
		    flag = true;
		    break;
		}
	    }
	}
	return seasonrate;

    }

    private RoomAvailability findRoomAvailability(
	    final RoomAvailability roomAvailability, final Date checkIn,
	    final Date checkOut) throws ServiceException {

	try {
	    final ArrayList<RoomDailyAvailability> roomDailyAvList = (ArrayList<RoomDailyAvailability>) this.roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    roomAvailability.getId(), checkIn, checkOut);

	    Collections.sort(roomDailyAvList,
		    new RoomDailyAvailabilityComparatorAsc());

	    if (!roomDailyAvList.isEmpty()) {

		roomAvailability.setAvailableUnit(roomDailyAvList.get(0)
			.getAvailableUnit());
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	} catch (final Exception e) {
	    throw new ServiceException(null, e);
	}

	return roomAvailability;

    }

    private List<RoomDTO> findRoomDateCrossAllocation(
	    final SearchRequestDTO searchRequestDTO)
	    throws PersistenceException, ServiceException {

	final List<RoomAvailability> checkInList = this.searchDAO
		.findRoomsByCheckInDate(searchRequestDTO);
	final List<RoomAvailability> checkOutList = this.searchDAO
		.findRoomsByCheckOutDate(searchRequestDTO);
	final List<RoomDTO> roomDTOList = new ArrayList<RoomDTO>();

	final Iterator<RoomAvailability> iterCheckIn = checkInList.iterator();
	while (iterCheckIn.hasNext()) {
	    final RoomAvailability roomAvailabilityCheckIn = iterCheckIn.next();

	    final Iterator<RoomAvailability> iterCheckOut = checkOutList
		    .iterator();
	    while (iterCheckOut.hasNext()) {
		final RoomAvailability roomAvailabilityCheckOut = iterCheckOut
			.next();

		final Calendar cal = Calendar.getInstance();
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

			List<RoomSeasonalRate> rsrList = new ArrayList<RoomSeasonalRate>();
			rsrList = this.seasonDAO
				.findRoomSeasonalRateByRoomId(type.getRoom()
					.getId());
			final RoomSeasonalRate roomSeasonalRate = findRoomSeasonalRate(
				searchRequestDTO, rsrList);

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

	    final ArrayList<RoomDailyAvailability> roomDailyAvListCheckIn = (ArrayList<RoomDailyAvailability>) this.roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    roomAvailabilityCheckIn.getId(), roomDTO
				    .getRoomAvailability().getFromDate(),
			    roomDTO.getRoomAvailability().getToDate());

	    Collections.sort(roomDailyAvListCheckIn,
		    new RoomDailyAvailabilityComparatorAsc());

	    final ArrayList<RoomDailyAvailability> roomDailyAvListCheckOut = (ArrayList<RoomDailyAvailability>) this.roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    roomAvailabilityCheckOut.getId(), roomDTO
				    .getRoomAvailability().getFromDate(),
			    roomDTO.getRoomAvailability().getToDate());

	    Collections.sort(roomDailyAvListCheckOut,
		    new RoomDailyAvailabilityComparatorAsc());

	    int avalUnitCheckIn = 0;
	    int avalUnitCheckOut = 0;

	    if (!roomDailyAvListCheckIn.isEmpty()) {

		avalUnitCheckIn = roomDailyAvListCheckIn.get(0)
			.getAvailableUnit();
	    }
	    if (!roomDailyAvListCheckOut.isEmpty()) {

		avalUnitCheckOut = roomDailyAvListCheckOut.get(0)
			.getAvailableUnit();
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
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	} catch (final Exception e) {
	    throw new ServiceException(null, e);
	}

	return roomDTO;

    }

    class RoomDailyAvailabilityComparatorAsc implements
	    Comparator<RoomDailyAvailability> {
	@Override
	public int compare(final RoomDailyAvailability o1,
		final RoomDailyAvailability o2) {
	    return o1.getAvailableUnit().compareTo(o2.getAvailableUnit());
	}
    }

    class RoomDailyAvailabilityComparatorDscs implements
	    Comparator<RoomDailyAvailability> {
	@Override
	public int compare(final RoomDailyAvailability o1,
		final RoomDailyAvailability o2) {
	    return o2.getAvailableUnit().compareTo(o1.getAvailableUnit());
	}
    }

}
