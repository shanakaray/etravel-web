package com.yd.etravel.domain.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.domain.season.RoomSeasonalRate;

@Entity
@Table(name = "T_ROOM")
public class Room extends BaseObject {
    @Column
    private int noOfRoom;

    @ManyToOne
    private RoomType roomType;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private RoomSeasonalRate roomSeasonalRate;

    public RoomType getRoomType() {
	return roomType;
    }

    public void setRoomType(RoomType roomType) {
	this.roomType = roomType;
    }

    public int getNoOfRoom() {
	return noOfRoom;
    }

    public void setNoOfRoom(int noOfRoom) {
	this.noOfRoom = noOfRoom;
    }

    public Hotel getHotel() {
	return hotel;
    }

    public void setHotel(Hotel hotel) {
	this.hotel = hotel;
    }

    public RoomSeasonalRate getRoomSeasonalRate() {
	return roomSeasonalRate;
    }

    public void setRoomSeasonalRate(RoomSeasonalRate roomSeasonalRate) {
	this.roomSeasonalRate = roomSeasonalRate;
    }

}
