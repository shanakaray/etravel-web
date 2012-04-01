/**
 * 
 */
package com.yd.etravel.domain.custom.room.availability;

import java.util.Date;

import com.yd.etravel.util.DateUtil;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Mar 1, 2009 : 8:14:53 AM Type :
 *         com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO
 * 
 */

public class DailyAvailabilityDTO {
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

    public DailyAvailabilityDTO(Long id, Date date, Integer allocatedUnit,
	    Integer availableUnit, Long roomId, Long roomTypeId, Long hotelId,
	    String hotelName, String roomName, String roomTypeName,
	    Boolean active, Date createdDate) {
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
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Date getDate() {
	return date;
    }

    public String getDateString() {
	return DateUtil.format(date);
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public Integer getAllocatedUnit() {
	return allocatedUnit;
    }

    public void setAllocatedUnit(Integer allocatedUnit) {
	this.allocatedUnit = allocatedUnit;
    }

    public Integer getAvailableUnit() {
	return availableUnit;
    }

    public void setAvailableUnit(Integer availableUnit) {
	this.availableUnit = availableUnit;
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

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public String getCreatedDateString() {
	return DateUtil.format(createdDate);
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

}
