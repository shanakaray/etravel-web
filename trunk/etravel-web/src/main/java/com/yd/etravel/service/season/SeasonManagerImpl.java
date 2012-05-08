package com.yd.etravel.service.season;

import java.util.List;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.persistence.dao.hotel.IHotelDAO;
import com.yd.etravel.persistence.dao.season.ISeasonDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

/**
 * 
 * 
 * @author Dharsahana
 * 
 */
public class SeasonManagerImpl implements ISeasonManager {

    private ISeasonDAO seasonDAO;
    private IHotelDAO hotelDAO;

    public void setSeasonDAO(ISeasonDAO seasonDAO) {
	this.seasonDAO = seasonDAO;
    }

    public void setHotelDAO(IHotelDAO hotelDAO) {
	this.hotelDAO = hotelDAO;
    }

    public Season save(final Season season) throws ServiceException {
	try {
	    if (season.getId() == null) {
		if (seasonDAO.isSeasonNameExist(season.getName())) {
		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.seasonName.exist"));
		} else if (!seasonDAO.isDataRangeValid(season.getHotel()
			.getId(), season.getFromDate(), season.getToDate())) {

		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.season.dateRange.valid"));

		} else {

		    Hotel hotel = (Hotel) hotelDAO.findById(Hotel.class, season
			    .getHotel().getId());

		    season.setHotel(hotel);

		    seasonDAO.save(season);
		}

	    } else {
		// if (!seasonDAO.isDataRangeValid(season.getHotel().getId(),
		// season.getFromDate(),season.getToDate())) {
		//
		// throw new ServiceException(ValidationHelper
		// .getMessageHolder("etravel.season.dateRange.valid"));
		//
		// } else {
		Hotel hotel = (Hotel) hotelDAO.findById(Hotel.class, season
			.getHotel().getId());

		season.setHotel(hotel);
		seasonDAO.update(season);
		// }

	    }
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return season;
    }

    public Season findSeasonById(final Long id) throws ServiceException {
	Season season = null;
	try {
	    season = (Season) seasonDAO.findById(Season.class, id);
	    season.toString();
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return season;
    }

    public int deleteSeason(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = seasonDAO.deleteAny(Season.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    public List<Season> findAllSeason() throws ServiceException {
	try {
	    return seasonDAO.findAll(Season.class);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<Season> findAllSeasonWithHotel() throws ServiceException {
	try {
	    return seasonDAO.findAllSeasonWithHotel();
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.service.hotel.IHotelManager#findAllActiveHotels()
     */
    public List<Season> findAllActiveSeason() throws ServiceException {
	try {
	    return seasonDAO.findAllActive(Season.class);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    // / Seasonal Rate

    public RoomSeasonalRate save(final RoomSeasonalRate roomSeasonalRate)
	    throws ServiceException {
	try {
	    if (roomSeasonalRate.getId() == null) {
		if (!seasonDAO.isSeasonalRateExist(roomSeasonalRate)) {
		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.seasonalRate.exist"));
		} else {

		    Season season = (Season) seasonDAO.findById(Season.class,
			    roomSeasonalRate.getSeason().getId());
		    roomSeasonalRate.setSeason(season);

		    Room room = (Room) seasonDAO.findById(Room.class,
			    roomSeasonalRate.getRoom().getId());
		    roomSeasonalRate.setRoom(room);

		    seasonDAO.save(roomSeasonalRate);
		}
	    } else {

		Season season = (Season) seasonDAO.findById(Season.class,
			roomSeasonalRate.getSeason().getId());
		roomSeasonalRate.setSeason(season);

		Room room = (Room) seasonDAO.findById(Room.class,
			roomSeasonalRate.getRoom().getId());
		roomSeasonalRate.setRoom(room);
		seasonDAO.update(roomSeasonalRate);

	    }
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomSeasonalRate;
    }

    public RoomSeasonalRate findRoomSeasonalRateById(final Long id)
	    throws ServiceException {
	RoomSeasonalRate roomSeasonalRate = null;
	try {
	    roomSeasonalRate = seasonDAO.findById(id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomSeasonalRate;
    }

    public int deleteRoomSeasonalRate(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = seasonDAO.deleteAny(RoomSeasonalRate.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    public List<RoomSeasonalRate> findAllRoomSeasonalRate()
	    throws ServiceException {
	try {
	    return seasonDAO.findAll(RoomSeasonalRate.class);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<RoomSeasonalRate> findAllRoomSeasonalRateWithRoomAndSeason(
	    Long hotelId) throws ServiceException {
	try {
	    return seasonDAO.findAllRoomSeasonalRateWithRoomAndSeason(hotelId);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<Season> findSeasonByHotel(Long hotelId) throws ServiceException {
	try {
	    return seasonDAO.findSeasonByHotel(hotelId);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }
}
