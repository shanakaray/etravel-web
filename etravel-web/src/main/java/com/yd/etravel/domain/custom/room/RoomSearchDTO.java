/**
 * 
 */
package com.yd.etravel.domain.custom.room;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 11:45:40 AM Type :
 *         com.yd.etravel.domain.custom.room.RoomSearchDTO
 * 
 */
public class RoomSearchDTO {

    private Integer noOfRoom;
    private Long roomTypeId;
    private Long hotelId;

    public RoomSearchDTO() {
	// TODO Auto-generated constructor stub
    }

    public Integer getNoOfRoom() {
	return noOfRoom;
    }

    public void setNoOfRoom(Integer noOfRoom) {
	this.noOfRoom = noOfRoom;
    }

    public Long getRoomTypeId() {
	return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
	this.roomTypeId = roomTypeId;
    }

    public Long getHotelId() {
	return hotelId;
    }

    public void setHotelId(Long hotelId) {
	this.hotelId = hotelId;
    }

}
