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

    public List<BookingReportDTO> getBookingList() {
	return bookingList;
    }

    public void setBookingList(List<BookingReportDTO> bookingList) {
	this.bookingList = bookingList;
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

    public Set<Long> getHotelSet() {
	Set<Long> set = new HashSet<Long>();
	if (hotelId != null && hotelId.longValue() > 0)
	    set.add(hotelId);
	return set;
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

    public List<Hotel> getHotelList() {
	return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
	this.hotelList = hotelList;
    }

    public void addHotelMap(Map<Long, String> hotelMap) {
	this.hotelList = new ArrayList<Hotel>();
	for (Map.Entry<Long, String> e : hotelMap.entrySet()) {
	    Hotel hotel = new Hotel(e.getKey(), e.getValue(), "");
	    this.hotelList.add(hotel);
	}

    }

    public List<Room> getAllRoom() {
	return allRoom;
    }

    public void setAllRoom(List<Room> allRoom) {
	this.allRoom = allRoom;
    }

    public Long getRoomId() {
	return roomId;
    }

    public void setRoomId(Long roomId) {
	this.roomId = roomId;
    }

    public String getBookingId() {
	return bookingId;
    }

    public void setBookingId(String bookingId) {
	this.bookingId = bookingId;
    }

    public String getBookedFromString() {
	return bookedFromString;
    }

    public void setBookedFromString(String bookedFromString) {
	this.bookedFromString = bookedFromString;
    }

    public String getBookedToString() {
	return bookedToString;
    }

    public void setBookedToString(String bookedToString) {
	this.bookedToString = bookedToString;
    }

    @Override
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	this.bookingList = Collections.EMPTY_LIST;
	this.hotelList = Collections.EMPTY_LIST;
	this.allRoom = Collections.EMPTY_LIST;
	this.bookingId = EMPTY_STRING;
	this.userName = EMPTY_STRING;
	this.userCode = EMPTY_STRING;
	this.bookedFromString = EMPTY_STRING;
	this.bookedToString = EMPTY_STRING;
	this.roomTypeId = -1L;
	this.hotelId = -1L;
	this.roomId = -1L;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#validateBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	// TODO Auto-generated method stub
	return null;
    }

}
