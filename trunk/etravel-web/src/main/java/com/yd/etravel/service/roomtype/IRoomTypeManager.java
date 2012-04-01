/**
 * 
 */
package com.yd.etravel.service.roomtype;

import java.util.List;

import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.service.exception.ServiceException;

/**
 * @author XPPRESP3
 * 
 */
public interface IRoomTypeManager {

    public RoomType save(final RoomType roomType) throws ServiceException;

    public RoomType findRoomTypeById(Long id) throws ServiceException;

    public List<RoomType> findAllRoomType() throws ServiceException;

    public int deleteRoomType(Long id) throws ServiceException;

    public List<RoomType> findAllActiveRoomType() throws ServiceException;

}
