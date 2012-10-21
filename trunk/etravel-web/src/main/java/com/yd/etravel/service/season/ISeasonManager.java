/**
 * 
 */
package com.yd.etravel.service.season;

import java.util.List;

import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.service.exception.ServiceException;

/**
 * @author Dharsahana
 * 
 */
public interface ISeasonManager {

	public Season save(final Season season) throws ServiceException;

	public Season findSeasonById(Long id) throws ServiceException;

	public int deleteSeason(Long id) throws ServiceException;

	public List<Season> findAllSeason() throws ServiceException;

	public List<Season> findAllSeasonWithHotel() throws ServiceException;

	public List<Season> findAllActiveSeason() throws ServiceException;

	public List<Season> findSeasonByHotel(Long hotelId) throws ServiceException;

	public RoomSeasonalRate save(final RoomSeasonalRate roomSeasonalRate)
			throws ServiceException;

	public RoomSeasonalRate findRoomSeasonalRateById(final Long id)
			throws ServiceException;

	public int deleteRoomSeasonalRate(final Long id) throws ServiceException;

	public List<RoomSeasonalRate> findAllRoomSeasonalRate()
			throws ServiceException;

	public List<RoomSeasonalRate> findAllRoomSeasonalRateWithRoomAndSeason(
			Long hotelid) throws ServiceException;

}
