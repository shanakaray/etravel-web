/**
 * 
 */
package com.yd.etravel.domain.custom.booking;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Mar 6, 2009 : 10:13:45 PM Type :
 *         com.yd.etravel.domain.custom.booking.BookingReportSearchDTO
 * 
 */

public class BookingReportSearchDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8938965251228660904L;
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

	public Long getBookedBy() {
		return this.bookedBy;
	}

	public Date getBookedFrom() {
		return this.bookedFrom;
	}

	public Date getBookedTo() {
		return this.bookedTo;
	}

	public String getBookingId() {
		return this.bookingId;
	}

	public Date getCheckInFrom() {
		return this.checkInFrom;
	}

	public Date getCheckInTo() {
		return this.checkInTo;
	}

	public Collection<Long> getHotelId() {
		return this.hotelId;
	}

	public Long getId() {
		return this.id;
	}

	public Long getRoomId() {
		return this.roomId;
	}

	public Long getRoomTypeId() {
		return this.roomTypeId;
	}

	public List<String> getStatusList() {
		return this.statusList;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setBookedBy(final Long bookedBy) {
		this.bookedBy = bookedBy;
	}

	public void setBookedFrom(final Date bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public void setBookedTo(final Date bookedTo) {
		this.bookedTo = bookedTo;
	}

	public void setBookingId(final String bookingId) {
		this.bookingId = bookingId;
	}

	public void setCheckInFrom(final Date checkInFrom) {
		this.checkInFrom = checkInFrom;
	}

	public void setCheckInTo(final Date checkInTo) {
		this.checkInTo = checkInTo;
	}

	public void setHotelId(final Collection<Long> hotelId) {
		this.hotelId = hotelId;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setRoomId(final Long roomId) {
		this.roomId = roomId;
	}

	public void setRoomTypeId(final Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public void setStatusList(final List<String> statusList) {
		this.statusList = statusList;
	}

	public void setUserCode(final String userCode) {
		this.userCode = userCode;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

}
