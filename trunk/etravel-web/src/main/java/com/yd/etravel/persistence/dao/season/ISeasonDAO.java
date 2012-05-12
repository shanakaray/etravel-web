package com.yd.etravel.persistence.dao.season;

import java.util.Date;
import java.util.List;

import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author Dharsahana
 * 
 */
public interface ISeasonDAO extends IBaseDAO<Season> {

    public boolean isSeasonNameExist(final String seasonName)
	    throws PersistenceException;

    public boolean isSeasonCodeExist(final String seasonCode)
	    throws PersistenceException;

    public boolean isDataRangeValid(final Long hotelId, Date fromDate,
	    Date toDate) throws PersistenceException;

    public List<Season> findAllSeasonWithHotel() throws PersistenceException;

    public List<RoomSeasonalRate> findAllRoomSeasonalRateWithRoomAndSeason(
	    Long hotelId) throws PersistenceException;

    public boolean isSeasonalRateExist(final RoomSeasonalRate roomSeasonalRate)
	    throws PersistenceException;

    public List<RoomSeasonalRate> findRoomSeasonalRateByRoomId(Long roomId)
	    throws PersistenceException;

    public List<Season> findSeasonByHotel(final Long hotelId)
	    throws PersistenceException;

    public RoomSeasonalRate findSeasonRate(Long id) throws PersistenceException;

    public void save(RoomSeasonalRate roomSeasonalRate)
	    throws PersistenceException;

    public void update(RoomSeasonalRate roomSeasonalRate)
	    throws PersistenceException;

    public List<RoomSeasonalRate> findAllRoomSeasonalRateList()
	    throws PersistenceException;
}
