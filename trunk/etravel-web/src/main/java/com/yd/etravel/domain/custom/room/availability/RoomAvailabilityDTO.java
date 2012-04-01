/**
 * 
 */
package com.yd.etravel.domain.custom.room.availability;

import java.util.Date;

import com.yd.etravel.util.DateUtil;

/**
 * 
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 28, 2009 : 5:18:39 AM Type :
 *         com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO
 * 
 */
public class RoomAvailabilityDTO {

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

    public RoomAvailabilityDTO(Long id, Long roomId, Long roomTypeId,
	    Long hotelId, String hotelName, String roomName,
	    String roomTypeName, Date fromDate, Date toDate, int unit,
	    // int availableUnit
	    Boolean active) {
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

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getRoomId() {
	return roomId;
    }

    public void setRoomId(Long roomId) {
	this.roomId = roomId;
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

    public String getHotelName() {
	return hotelName;
    }

    public void setHotelName(String hotelName) {
	this.hotelName = hotelName;
    }

    public String getRoomName() {
	return roomName;
    }

    public void setRoomName(String roomName) {
	this.roomName = roomName;
    }

    public String getRoomTypeName() {
	return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
	this.roomTypeName = roomTypeName;
    }

    public Date getFromDate() {
	return fromDate;
    }

    public String getFromDateString() {
	return DateUtil.format(fromDate);
    }

    public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
    }

    public Date getToDate() {
	return toDate;
    }

    public String getToDateString() {
	return DateUtil.format(toDate);
    }

    public void setToDate(Date toDate) {
	this.toDate = toDate;
    }

    public int getUnit() {
	return unit;
    }

    public void setUnit(int unit) {
	this.unit = unit;
    }

    public int getAvailableUnit() {
	return availableUnit;
    }

    public void setAvailableUnit(int availableUnit) {
	this.availableUnit = availableUnit;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((hotelId == null) ? 0 : hotelId.hashCode());
	result = prime * result
		+ ((roomTypeId == null) ? 0 : roomTypeId.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final RoomAvailabilityDTO other = (RoomAvailabilityDTO) obj;
	if (hotelId == null) {
	    if (other.hotelId != null)
		return false;
	} else if (!hotelId.equals(other.hotelId))
	    return false;
	if (roomTypeId == null) {
	    if (other.roomTypeId != null)
		return false;
	} else if (!roomTypeId.equals(other.roomTypeId))
	    return false;
	return true;
    }

}
