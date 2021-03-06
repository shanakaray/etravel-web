package com.yd.etravel.service.roomtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.persistence.dao.roomtype.IRoomTypeDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

@Service(value = "roomTypeService")
@Transactional(propagation = Propagation.SUPPORTS)
public class RoomTypeManagerImpl implements IRoomTypeManager {
	@Autowired(required = true)
	private IRoomTypeDAO roomTypeDAO;

	@Transactional
	@Override
	public int deleteRoomType(final Long id) throws ServiceException {
		try {
			return this.roomTypeDAO.deleteAny(id);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<RoomType> findAllActiveRoomType() throws ServiceException {
		try {
			return this.roomTypeDAO.findAllActive();
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<RoomType> findAllRoomType() throws ServiceException {
		try {
			return this.roomTypeDAO.findAll();
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public RoomType findRoomTypeById(final Long id) throws ServiceException {
		try {
			final RoomType roomType = this.roomTypeDAO.findById(id);
			roomType.toString();
			return roomType;
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Transactional
	@Override
	public RoomType save(final RoomType roomType) throws ServiceException {
		try {
			if (this.roomTypeDAO.isRoomTypeNameExist(roomType.getName(),
					roomType.getId())) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.roomTypeName.exist"));
			}
			return this.roomTypeDAO.saveOrUpdate(roomType);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	public void setRoomTypeDAO(final IRoomTypeDAO roomTypeDAO) {
		this.roomTypeDAO = roomTypeDAO;
	}
}
