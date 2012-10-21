package com.yd.etravel.persistence.dao.roomtype;

import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

public interface IRoomTypeDAO extends IBaseDAO<RoomType> {

	public boolean isRoomTypeNameExist(final String seasonName, Long id)
			throws PersistenceException;

}
