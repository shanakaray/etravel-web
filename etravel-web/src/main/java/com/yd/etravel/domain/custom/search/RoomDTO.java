/**
 * 
 */
package com.yd.etravel.domain.custom.search;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.domain.season.RoomSeasonalRate;

/**
 * @author : Dharshana. Created Date : Feb 1, 2009 : 09:50:40 PM Type :
 * 
 *         com.yd.etravel.domain.custom.search.RoomDTO
 */
public class RoomDTO {
    private Long id; // This is equal to roomAvailability id
    private Room room;
    private RoomType roomType;
    private RoomSeasonalRate roomSeasonalRate;
    // private Occupancy occupancy;
    private RoomAvailability roomAvailability;
    private Hotel hotel;
    private boolean combineAvailability;
    private RoomAvailability roomAvailabilityCheckIn;
    private RoomAvailability roomAvailabilityCheckOut;

    /**
     * @return the room
     */
    public Room getRoom() {
	return room;
    }

    /**
     * @param room
     *            the room to set
     */
    public void setRoom(Room room) {
	this.room = room;
    }

    /**
     * @return the roomType
     */
    public RoomType getRoomType() {
	return roomType;
    }

    /**
     * @param roomType
     *            the roomType to set
     */
    public void setRoomType(RoomType roomType) {
	this.roomType = roomType;
    }

    /**
     * @return the roomSeasonalRate
     */
    public RoomSeasonalRate getRoomSeasonalRate() {
	return roomSeasonalRate;
    }

    /**
     * @param roomSeasonalRate
     *            the roomSeasonalRate to set
     */
    public void setRoomSeasonalRate(RoomSeasonalRate roomSeasonalRate) {
	this.roomSeasonalRate = roomSeasonalRate;
    }

    // /**
    // * @return the occupancy
    // */
    // public Occupancy getOccupancy() {
    // return occupancy;
    // }
    // /**
    // * @param occupancy the occupancy to set
    // */
    // public void setOccupancy(Occupancy occupancy) {
    // this.occupancy = occupancy;
    // }
    /**
     * @return the roomAvailability
     */
    public RoomAvailability getRoomAvailability() {
	return roomAvailability;
    }

    /**
     * @param roomAvailability
     *            the roomAvailability to set
     */
    public void setRoomAvailability(RoomAvailability roomAvailability) {
	this.roomAvailability = roomAvailability;
    }

    /**
     * @return the hotel
     */
    public Hotel getHotel() {
	return hotel;
    }

    /**
     * @param hotel
     *            the hotel to set
     */
    public void setHotel(Hotel hotel) {
	this.hotel = hotel;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the combineAvailability
     */
    public boolean isCombineAvailability() {
	return combineAvailability;
    }

    /**
     * @param combineAvailability
     *            the combineAvailability to set
     */
    public void setCombineAvailability(boolean combineAvailability) {
	this.combineAvailability = combineAvailability;
    }

    /**
     * @return the roomAvailabilityCheckIn
     */
    public RoomAvailability getRoomAvailabilityCheckIn() {
	return roomAvailabilityCheckIn;
    }

    /**
     * @param roomAvailabilityCheckIn
     *            the roomAvailabilityCheckIn to set
     */
    public void setRoomAvailabilityCheckIn(
	    RoomAvailability roomAvailabilityCheckIn) {
	this.roomAvailabilityCheckIn = roomAvailabilityCheckIn;
    }

    /**
     * @return the roomAvailabilityCheckOut
     */
    public RoomAvailability getRoomAvailabilityCheckOut() {
	return roomAvailabilityCheckOut;
    }

    /**
     * @param roomAvailabilityCheckOut
     *            the roomAvailabilityCheckOut to set
     */
    public void setRoomAvailabilityCheckOut(
	    RoomAvailability roomAvailabilityCheckOut) {
	this.roomAvailabilityCheckOut = roomAvailabilityCheckOut;
    }

}
