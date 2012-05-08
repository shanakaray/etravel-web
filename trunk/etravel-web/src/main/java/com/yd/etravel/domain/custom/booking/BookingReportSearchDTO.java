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

    public Collection<Long> getHotelId() {
	return this.hotelId;
    }

    public void setHotelId(final Collection<Long> hotelId) {
	this.hotelId = hotelId;
    }

    public String getUserName() {
	return this.userName;
    }

    public void setUserName(final String userName) {
	this.userName = userName;
    }

    public String getUserCode() {
	return this.userCode;
    }

    public void setUserCode(final String userCode) {
	this.userCode = userCode;
    }

    public String getBookingId() {
	return this.bookingId;
    }

    public void setBookingId(final String bookingId) {
	this.bookingId = bookingId;
    }

    public Date getBookedFrom() {
	return this.bookedFrom;
    }

    public void setBookedFrom(final Date bookedFrom) {
	this.bookedFrom = bookedFrom;
    }

    public Date getBookedTo() {
	return this.bookedTo;
    }

    public void setBookedTo(final Date bookedTo) {
	this.bookedTo = bookedTo;
    }

    public Date getCheckInFrom() {
	return this.checkInFrom;
    }

    public void setCheckInFrom(final Date checkInFrom) {
	this.checkInFrom = checkInFrom;
    }

    public Date getCheckInTo() {
	return this.checkInTo;
    }

    public void setCheckInTo(final Date checkInTo) {
	this.checkInTo = checkInTo;
    }

    public Long getId() {
	return this.id;
    }

    public void setId(final Long id) {
	this.id = id;
    }

    public List<String> getStatusList() {
	return this.statusList;
    }

    public void setStatusList(final List<String> statusList) {
	this.statusList = statusList;
    }

    public Long getBookedBy() {
	return this.bookedBy;
    }

    public void setBookedBy(final Long bookedBy) {
	this.bookedBy = bookedBy;
    }

}
