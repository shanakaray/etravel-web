/**
 * 
 */
package com.yd.etravel.domain.custom.search;

import java.io.Serializable;

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
public class RoomDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1859777270142908528L;
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
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return this.hotel;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return this.room;
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
		return this.roomAvailability;
	}

	/**
	 * @return the roomAvailabilityCheckIn
	 */
	public RoomAvailability getRoomAvailabilityCheckIn() {
		return this.roomAvailabilityCheckIn;
	}

	/**
	 * @return the roomAvailabilityCheckOut
	 */
	public RoomAvailability getRoomAvailabilityCheckOut() {
		return this.roomAvailabilityCheckOut;
	}

	/**
	 * @return the roomSeasonalRate
	 */
	public RoomSeasonalRate getRoomSeasonalRate() {
		return this.roomSeasonalRate;
	}

	/**
	 * @return the roomType
	 */
	public RoomType getRoomType() {
		return this.roomType;
	}

	/**
	 * @return the combineAvailability
	 */
	public boolean isCombineAvailability() {
		return this.combineAvailability;
	}

	/**
	 * @param combineAvailability
	 *            the combineAvailability to set
	 */
	public void setCombineAvailability(final boolean combineAvailability) {
		this.combineAvailability = combineAvailability;
	}

	/**
	 * @param hotel
	 *            the hotel to set
	 */
	public void setHotel(final Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(final Room room) {
		this.room = room;
	}

	/**
	 * @param roomAvailability
	 *            the roomAvailability to set
	 */
	public void setRoomAvailability(final RoomAvailability roomAvailability) {
		this.roomAvailability = roomAvailability;
	}

	/**
	 * @param roomAvailabilityCheckIn
	 *            the roomAvailabilityCheckIn to set
	 */
	public void setRoomAvailabilityCheckIn(
			final RoomAvailability roomAvailabilityCheckIn) {
		this.roomAvailabilityCheckIn = roomAvailabilityCheckIn;
	}

	/**
	 * @param roomAvailabilityCheckOut
	 *            the roomAvailabilityCheckOut to set
	 */
	public void setRoomAvailabilityCheckOut(
			final RoomAvailability roomAvailabilityCheckOut) {
		this.roomAvailabilityCheckOut = roomAvailabilityCheckOut;
	}

	/**
	 * @param roomSeasonalRate
	 *            the roomSeasonalRate to set
	 */
	public void setRoomSeasonalRate(final RoomSeasonalRate roomSeasonalRate) {
		this.roomSeasonalRate = roomSeasonalRate;
	}

	/**
	 * @param roomType
	 *            the roomType to set
	 */
	public void setRoomType(final RoomType roomType) {
		this.roomType = roomType;
	}

}
