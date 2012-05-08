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
	return this.roomType;
    }

    public void setRoomType(final RoomType roomType) {
	this.roomType = roomType;
    }

    public int getNoOfRoom() {
	return this.noOfRoom;
    }

    public void setNoOfRoom(final int noOfRoom) {
	this.noOfRoom = noOfRoom;
    }

    public Hotel getHotel() {
	return this.hotel;
    }

    public void setHotel(final Hotel hotel) {
	this.hotel = hotel;
    }

    public RoomSeasonalRate getRoomSeasonalRate() {
	return this.roomSeasonalRate;
    }

    public void setRoomSeasonalRate(final RoomSeasonalRate roomSeasonalRate) {
	this.roomSeasonalRate = roomSeasonalRate;
    }

}
