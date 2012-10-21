package com.yd.etravel.service.room.availability;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO;
import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.persistence.dao.room.IRoomDAO;
import com.yd.etravel.persistence.dao.room.availability.IRoomAvailabilityDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

@Service(value = "roomAvailabilityService")
@Transactional(propagation = Propagation.SUPPORTS)
public class RoomAvailabilityManagerImpl implements IRoomAvailabilityManager {
	@Autowired(required = true)
	private IRoomAvailabilityDAO roomAvailabilityDAO;
	@Autowired(required = true)
	private IRoomDAO roomDAO;

	public void setRoomAvailabilityDAO(
			final IRoomAvailabilityDAO roomAvailabilityDAO) {
		this.roomAvailabilityDAO = roomAvailabilityDAO;
	}

	public void setRoomDAO(final IRoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	@Transactional
	@Override
	public RoomAvailability save(RoomAvailability roomAvail)
			throws ServiceException {
		try {
			if (roomAvail.getId() == null) {
				if (!this.roomAvailabilityDAO.isDataRangeValid(roomAvail
						.getRoom().getId(), roomAvail.getFromDate(), roomAvail
						.getToDate())) {

					throw new ServiceException(
							ValidationHelper
									.getMessageHolder("etravel.roomAvailability.dateRange.valid"));

				} else {

					final Room room = this.roomDAO.findById(roomAvail.getRoom()
							.getId());
					if (room.getNoOfRoom() < roomAvail.getUnit()) {

						throw new ServiceException(
								ValidationHelper
										.getMessageHolder("etravel.roomAvailability.max.allocated.room"));

					}

					roomAvail.setRoom(room);
					roomAvail = this.roomAvailabilityDAO
							.saveOrUpdate(roomAvail);

					boolean flag = true;

					Date from = roomAvail.getFromDate();
					final Date to = roomAvail.getToDate();

					while (flag) {

						final RoomDailyAvailability rda = new RoomDailyAvailability();
						rda.setRoomAvailability(roomAvail);
						rda.setAllocatedUnit(roomAvail.getUnit());
						rda.setAvailableUnit(roomAvail.getUnit());
						rda.setActive(true);
						rda.setDate(from);
						this.roomAvailabilityDAO.save(rda);
						final Calendar cal = Calendar.getInstance();
						cal.setTime(from);
						cal.add(Calendar.DATE, 1);
						from = cal.getTime();
						if (from.getTime() > to.getTime()) {

							flag = false;
						}
					}

				}

			} else {
				final Room room = this.roomDAO.findById(roomAvail.getRoom()
						.getId());
				roomAvail.setRoom(room);
				this.roomAvailabilityDAO.saveOrUpdate(roomAvail);

				final List<RoomDailyAvailability> availabilityList = this.roomAvailabilityDAO
						.findAllRoomDailyAvailabilityByRoomAvailabilityId(roomAvail
								.getId());

				for (final RoomDailyAvailability dailyRoomAv : availabilityList) {

					dailyRoomAv.setAllocatedUnit(roomAvail.getUnit());

					int avaunit = 0;
					if (roomAvail.getUnit() < dailyRoomAv.getAvailableUnit()) {
						avaunit = roomAvail.getUnit();
						dailyRoomAv.setAvailableUnit(avaunit);
					}

					this.roomAvailabilityDAO.update(dailyRoomAv);

				}
			}
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return roomAvail;
	}

	@Override
	public RoomAvailability findRoomAvailabilityById(final Long id)
			throws ServiceException {
		RoomAvailability roomAvailability = null;
		try {
			roomAvailability = this.roomAvailabilityDAO.findById(id);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return roomAvailability;
	}

	@Transactional
	@Override
	public int deleteRoomAvailability(final Long id) throws ServiceException {
		int flag = 0;
		try {
			flag = this.roomAvailabilityDAO.deleteAny(id);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return flag;
	}

	@Override
	public List<RoomAvailability> findAllRoomAvailability()
			throws ServiceException {
		try {
			return this.roomAvailabilityDAO.findAll();
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	@Deprecated
	/**
	 * @see List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(Long hotelId)
	 *      instead
	 */
	public List<RoomAvailability> findAllRoomAvailabilityWithRoomAndOccu(
			final Long hotelId) throws ServiceException {
		try {
			return this.roomAvailabilityDAO
					.findAllRoomAvailabilityWithRoomAndOccu(hotelId);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<RoomDailyAvailability> findAllRoomDailyAvailability(
			final Long id) throws ServiceException {
		try {
			return this.roomAvailabilityDAO.findAllRoomDailyAvailability(id);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<RoomDailyAvailability> findAllRoomDailyAvailability()
			throws ServiceException {
		try {
			return this.roomAvailabilityDAO.findAllRoomDailyAvailability();

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public RoomAvailability findRoomAvailabilityWithRoomInfoById(final Long id)
			throws ServiceException {
		try {
			return this.roomAvailabilityDAO.findRoomAvailabilityById(id);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}

	}

	@Override
	public List<RoomAvailabilityDTO> findAllRoomAvailabilityDTO(
			final RoomAvailabilityDTO dto) throws ServiceException {
		try {
			return this.roomAvailabilityDAO.findAllRoomAvailabilityDTO(dto);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<DailyAvailabilityDTO> findDailyAvailability(final Long id)
			throws ServiceException {
		try {
			return this.roomAvailabilityDAO.findDailyAvailability(id);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

}
