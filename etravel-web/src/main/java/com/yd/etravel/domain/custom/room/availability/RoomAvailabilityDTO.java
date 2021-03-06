/**
 * 
 */
package com.yd.etravel.domain.custom.room.availability;

import java.io.Serializable;
import java.util.Date;

import com.yd.etravel.util.DateUtil;

/**
 * 
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 28, 2009 : 5:18:39 AM Type :
 *         com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO
 * 
 */
public class RoomAvailabilityDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7585417514098525045L;
	private Long id;
	private Long roomId;
	private Long roomTypeId;
	private Long hotelId;

	private String hotelName;
	private String roomName;
	private String roomTypeName;

	private Date fromDate;
	private Date toDate;
	private int unit;
	private int availableUnit;

	private Boolean active;

	public RoomAvailabilityDTO() {
	}

	public RoomAvailabilityDTO(final Long id, final Long roomId,
			final Long roomTypeId, final Long hotelId, final String hotelName,
			final String roomName, final String roomTypeName,
			final Date fromDate, final Date toDate, final int unit,
			// int availableUnit
			final Boolean active) {
		this.id = id;
		this.roomId = roomId;
		this.roomTypeId = roomTypeId;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.roomName = roomName;
		this.roomTypeName = roomTypeName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.unit = unit;
		// this.availableUnit = availableUnit;
		this.active = active;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RoomAvailabilityDTO other = (RoomAvailabilityDTO) obj;
		if (this.hotelId == null) {
			if (other.hotelId != null) {
				return false;
			}
		} else if (!this.hotelId.equals(other.hotelId)) {
			return false;
		}
		if (this.roomTypeId == null) {
			if (other.roomTypeId != null) {
				return false;
			}
		} else if (!this.roomTypeId.equals(other.roomTypeId)) {
			return false;
		}
		return true;
	}

	public Boolean getActive() {
		return this.active;
	}

	public int getAvailableUnit() {
		return this.availableUnit;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public String getFromDateString() {
		return DateUtil.format(this.fromDate);
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public Long getId() {
		return this.id;
	}

	public Long getRoomId() {
		return this.roomId;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public Long getRoomTypeId() {
		return this.roomTypeId;
	}

	public String getRoomTypeName() {
		return this.roomTypeName;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public String getToDateString() {
		return DateUtil.format(this.toDate);
	}

	public int getUnit() {
		return this.unit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ (this.hotelId == null ? 0 : this.hotelId.hashCode());
		result = (prime * result)
				+ (this.roomTypeId == null ? 0 : this.roomTypeId.hashCode());
		return result;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public void setAvailableUnit(final int availableUnit) {
		this.availableUnit = availableUnit;
	}

	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelName(final String hotelName) {
		this.hotelName = hotelName;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setRoomId(final Long roomId) {
		this.roomId = roomId;
	}

	public void setRoomName(final String roomName) {
		this.roomName = roomName;
	}

	public void setRoomTypeId(final Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public void setRoomTypeName(final String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

	public void setUnit(final int unit) {
		this.unit = unit;
	}

}
