/**
 * 
 */
package com.yd.etravel.web.booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.booking.BookingReportDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.web.common.BaseForm;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Mar 7, 2009 : 1:22:14 PM Type :
 *         com.yd.etravel.web.booking.BookingReportForm
 * 
 */

public class BookingReportForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6153737269499189809L;
	private List<Hotel> hotelList;
	private List<BookingReportDTO> bookingList;
	private List<Room> allRoom;
	private Long roomTypeId;
	private Long hotelId;
	private Long roomId;
	private String userName;
	private String userCode;
	private String bookingId;
	private String bookedFromString;
	private String bookedToString;

	/**
	 * 
	 */
	public BookingReportForm() {
		// TODO Auto-generated constructor stub
	}

	public void addHotelMap(final Map<Long, String> hotelMap) {
		this.hotelList = new ArrayList<Hotel>();
		for (final Map.Entry<Long, String> e : hotelMap.entrySet()) {
			final Hotel hotel = new Hotel(e.getKey(), e.getValue(), "");
			this.hotelList.add(hotel);
		}

	}

	public List<Room> getAllRoom() {
		return this.allRoom;
	}

	public String getBookedFromString() {
		return this.bookedFromString;
	}

	public String getBookedToString() {
		return this.bookedToString;
	}

	public String getBookingId() {
		return this.bookingId;
	}

	public List<BookingReportDTO> getBookingList() {
		return this.bookingList;
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public List<Hotel> getHotelList() {
		return this.hotelList;
	}

	public Set<Long> getHotelSet() {
		final Set<Long> set = new HashSet<Long>();
		if ((this.hotelId != null) && (this.hotelId.longValue() > 0)) {
			set.add(this.hotelId);
		}
		return set;
	}

	public Long getRoomId() {
		return this.roomId;
	}

	public Long getRoomTypeId() {
		return this.roomTypeId;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.bookingList = Collections.emptyList();
		this.hotelList = Collections.emptyList();
		this.allRoom = Collections.emptyList();
		this.bookingId = EMPTYSTRING;
		this.userName = EMPTYSTRING;
		this.userCode = EMPTYSTRING;
		this.bookedFromString = EMPTYSTRING;
		this.bookedToString = EMPTYSTRING;
		this.roomTypeId = -1L;
		this.hotelId = -1L;
		this.roomId = -1L;
	}

	public void setAllRoom(final List<Room> allRoom) {
		this.allRoom = allRoom;
	}

	public void setBookedFromString(final String bookedFromString) {
		this.bookedFromString = bookedFromString;
	}

	public void setBookedToString(final String bookedToString) {
		this.bookedToString = bookedToString;
	}

	public void setBookingId(final String bookingId) {
		this.bookingId = bookingId;
	}

	public void setBookingList(final List<BookingReportDTO> bookingList) {
		this.bookingList = bookingList;
	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelList(final List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public void setRoomId(final Long roomId) {
		this.roomId = roomId;
	}

	public void setRoomTypeId(final Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public void setUserCode(final String userCode) {
		this.userCode = userCode;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yd.etravel.web.common.BaseForm#validateBean(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
