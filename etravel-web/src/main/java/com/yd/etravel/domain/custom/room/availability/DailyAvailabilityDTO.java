/**
 * 
 */
package com.yd.etravel.domain.custom.room.availability;

import java.io.Serializable;
import java.util.Date;

import com.yd.etravel.util.DateUtil;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Mar 1, 2009 : 8:14:53 AM Type :
 *         com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO
 * 
 */

public class DailyAvailabilityDTO implements Serializable {
	private Long id;
	private Date date;
	private Date createdDate;
	private Integer allocatedUnit;
	private Integer availableUnit;

	private Long roomId;
	private Long roomTypeId;
	private Long hotelId;

	private String hotelName;
	private String roomName;
	private String roomTypeName;

	private Boolean active;

	public DailyAvailabilityDTO() {
	}

	public DailyAvailabilityDTO(final Long id, final Date date,
			final Integer allocatedUnit, final Integer availableUnit,
			final Long roomId, final Long roomTypeId, final Long hotelId,
			final String hotelName, final String roomName,
			final String roomTypeName, final Boolean active,
			final Date createdDate) {
		super();
		this.id = id;
		this.date = date;
		this.allocatedUnit = allocatedUnit;
		this.availableUnit = availableUnit;
		this.roomId = roomId;
		this.roomTypeId = roomTypeId;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.roomName = roomName;
		this.roomTypeName = roomTypeName;
		this.active = active;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public String getDateString() {
		return DateUtil.format(this.date);
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Integer getAllocatedUnit() {
		return this.allocatedUnit;
	}

	public void setAllocatedUnit(final Integer allocatedUnit) {
		this.allocatedUnit = allocatedUnit;
	}

	public Integer getAvailableUnit() {
		return this.availableUnit;
	}

	public void setAvailableUnit(final Integer availableUnit) {
		this.availableUnit = availableUnit;
	}

	public Long getRoomId() {
		return this.roomId;
	}

	public void setRoomId(final Long roomId) {
		this.roomId = roomId;
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

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(final String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(final String roomName) {
		this.roomName = roomName;
	}

	public String getRoomTypeName() {
		return this.roomTypeName;
	}

	public void setRoomTypeName(final String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public String getCreatedDateString() {
		return DateUtil.format(this.createdDate);
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

}
