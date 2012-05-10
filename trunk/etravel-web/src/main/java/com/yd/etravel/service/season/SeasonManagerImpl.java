package com.yd.etravel.service.season;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.persistence.dao.hotel.IHotelDAO;
import com.yd.etravel.persistence.dao.season.ISeasonDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

@Service(value = "seasonService")
@Transactional(propagation = Propagation.SUPPORTS)
public class SeasonManagerImpl implements ISeasonManager {
    @Autowired(required = true)
    private ISeasonDAO seasonDAO;
    @Autowired(required = true)
    private IHotelDAO hotelDAO;

    public void setSeasonDAO(final ISeasonDAO seasonDAO) {
	this.seasonDAO = seasonDAO;
    }

    public void setHotelDAO(final IHotelDAO hotelDAO) {
	this.hotelDAO = hotelDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Season save(final Season season) throws ServiceException {
	try {
	    if (season.getId() == null) {
		if (this.seasonDAO.isSeasonNameExist(season.getName())) {
		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.seasonName.exist"));
		} else if (!this.seasonDAO.isDataRangeValid(season.getHotel()
			.getId(), season.getFromDate(), season.getToDate())) {
		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.season.dateRange.valid"));

		} else {

		    final Hotel hotel = (Hotel) this.hotelDAO.findById(
			    Hotel.class, season.getHotel().getId());
		    season.setHotel(hotel);
		    this.seasonDAO.save(season);
		}

	    } else {
		final Hotel hotel = (Hotel) this.hotelDAO.findById(Hotel.class,
			season.getHotel().getId());

		season.setHotel(hotel);
		this.seasonDAO.update(season);
	    }
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return season;
    }

    @Override
    public Season findSeasonById(final Long id) throws ServiceException {
	Season season = null;
	try {
	    season = (Season) this.seasonDAO.findById(Season.class, id);
	    season.toString();
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return season;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int deleteSeason(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = this.seasonDAO.deleteAny(id, null);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    @Override
    public List<Season> findAllSeason() throws ServiceException {
	try {
	    return this.seasonDAO.findAll(Season.class);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<Season> findAllSeasonWithHotel() throws ServiceException {
	try {
	    return this.seasonDAO.findAllSeasonWithHotel();
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<Season> findAllActiveSeason() throws ServiceException {
	try {
	    return this.seasonDAO.findAllActive(Season.class);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public RoomSeasonalRate save(final RoomSeasonalRate roomSeasonalRate)
	    throws ServiceException {
	try {
	    if (roomSeasonalRate.getId() == null) {
		if (!this.seasonDAO.isSeasonalRateExist(roomSeasonalRate)) {
		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.seasonalRate.exist"));
		} else {

		    final Season season = (Season) this.seasonDAO.findById(
			    Season.class, roomSeasonalRate.getSeason().getId());
		    roomSeasonalRate.setSeason(season);
		    final Room room = (Room) this.seasonDAO.findById(
			    Room.class, roomSeasonalRate.getRoom().getId());
		    roomSeasonalRate.setRoom(room);
		    this.seasonDAO.save(roomSeasonalRate);
		}
	    } else {

		final Season season = (Season) this.seasonDAO.findById(
			Season.class, roomSeasonalRate.getSeason().getId());
		roomSeasonalRate.setSeason(season);
		final Room room = (Room) this.seasonDAO.findById(Room.class,
			roomSeasonalRate.getRoom().getId());
		roomSeasonalRate.setRoom(room);
		this.seasonDAO.update(roomSeasonalRate);

	    }
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomSeasonalRate;
    }

    @Override
    public RoomSeasonalRate findRoomSeasonalRateById(final Long id)
	    throws ServiceException {
	RoomSeasonalRate roomSeasonalRate = null;
	try {
	    roomSeasonalRate = this.seasonDAO.findById(id);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return roomSeasonalRate;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int deleteRoomSeasonalRate(final Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = this.seasonDAO.deleteAny(id, null);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    @Override
    public List<RoomSeasonalRate> findAllRoomSeasonalRate()
	    throws ServiceException {
	try {
	    return this.seasonDAO.findAll(RoomSeasonalRate.class);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<RoomSeasonalRate> findAllRoomSeasonalRateWithRoomAndSeason(
	    final Long hotelId) throws ServiceException {
	try {
	    return this.seasonDAO
		    .findAllRoomSeasonalRateWithRoomAndSeason(hotelId);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<Season> findSeasonByHotel(final Long hotelId)
	    throws ServiceException {
	try {
	    return this.seasonDAO.findSeasonByHotel(hotelId);

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }
}
