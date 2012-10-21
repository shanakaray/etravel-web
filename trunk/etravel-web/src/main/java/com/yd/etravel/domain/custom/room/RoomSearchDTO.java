/**
 * 
 */
package com.yd.etravel.domain.custom.room;

import java.io.Serializable;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 11:45:40 AM Type :
 *         com.yd.etravel.domain.custom.room.RoomSearchDTO
 * 
 */
public class RoomSearchDTO implements Serializable {

	private Integer noOfRoom;
	private Long roomTypeId;
	private Long hotelId;

	public RoomSearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNoOfRoom() {
		return this.noOfRoom;
	}

	public void setNoOfRoom(final Integer noOfRoom) {
		this.noOfRoom = noOfRoom;
	}

	public Long getRoomTypeId() {
		return this.roomTypeId;
	}

	public void setRoomTypeId(final Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

}
