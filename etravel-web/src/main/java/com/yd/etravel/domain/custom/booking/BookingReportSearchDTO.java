/**
 * 
 */
package com.yd.etravel.domain.custom.booking;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Mar 6, 2009 : 10:13:45 PM Type :
 *         com.yd.etravel.domain.custom.booking.BookingReportSearchDTO
 * 
 */

public class BookingReportSearchDTO {

    private Long roomId;
    private Long roomTypeId;
    private Collection<Long> hotelId;
    private Long id;

    private String userName;
    private String userCode;
    private String bookingId;
    private Long bookedBy;

    private Date bookedFrom;
    private Date bookedTo;
    private Date checkInFrom;
    private Date checkInTo;

    private List<String> statusList;

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

    public Collection<Long> getHotelId() {
	return hotelId;
    }

    public void setHotelId(Collection<Long> hotelId) {
	this.hotelId = hotelId;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getUserCode() {
	return userCode;
    }

    public void setUserCode(String userCode) {
	this.userCode = userCode;
    }

    public String getBookingId() {
	return bookingId;
    }

    public void setBookingId(String bookingId) {
	this.bookingId = bookingId;
    }

    public Date getBookedFrom() {
	return bookedFrom;
    }

    public void setBookedFrom(Date bookedFrom) {
	this.bookedFrom = bookedFrom;
    }

    public Date getBookedTo() {
	return bookedTo;
    }

    public void setBookedTo(Date bookedTo) {
	this.bookedTo = bookedTo;
    }

    public Date getCheckInFrom() {
	return checkInFrom;
    }

    public void setCheckInFrom(Date checkInFrom) {
	this.checkInFrom = checkInFrom;
    }

    public Date getCheckInTo() {
	return checkInTo;
    }

    public void setCheckInTo(Date checkInTo) {
	this.checkInTo = checkInTo;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public List<String> getStatusList() {
	return statusList;
    }

    public void setStatusList(List<String> statusList) {
	this.statusList = statusList;
    }

    public Long getBookedBy() {
	return bookedBy;
    }

    public void setBookedBy(Long bookedBy) {
	this.bookedBy = bookedBy;
    }

}
