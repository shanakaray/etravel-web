/**
 * 
 */
package com.yd.etravel.web.reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.booking.BookingReportDTO;
import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.util.DateUtil;
import com.yd.etravel.web.common.BaseForm;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Apr 4, 2009 : 3:57:07 PM Type :
 *         com.yd.etravel.web.reservation.ReservationSheetForm
 * 
 */

public class ReservationSheetForm extends BaseForm {
    private static Map<Integer, String> colurCodes = new HashMap<Integer, String>();

    static {
	colurCodes.put(0, "background-color: e1f1f9;");
	colurCodes.put(1, "background-color: f9e1e1;");
	colurCodes.put(2, "background-color: e1f9f2;");
	colurCodes.put(3, "background-color: f8ffaf;");
	colurCodes.put(4, "background-color: afffc6;");
	colurCodes.put(5, "background-color: affff7;");
	colurCodes.put(6, "background-color: afb5ff;");
	colurCodes.put(7, "background-color: f8afff;");
	colurCodes.put(8, "background-color: ffafaf;");
	colurCodes.put(9, "background-color: ffffff;");
    }

    private List<Hotel> hotelList;
    private Long hotelId;
    private String startDate;
    private String endDate;
    private Set<RoomAvailabilityDTO> roomAvailabilitySet;
    private List<BookingReportDTO> bookingList;

    public List<Hotel> getHotelList() {
	return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
	this.hotelList = hotelList;
    }

    public Long getHotelId() {
	return hotelId;
    }

    public void setHotelId(Long hotelId) {
	this.hotelId = hotelId;
    }

    public String getStartDate() {
	return startDate;
    }

    public Date getStartDateToDate() {
	return DateUtil.parse(startDate);
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public Date getEndDateToDate() {
	return DateUtil.parse(endDate);
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public Set<RoomAvailabilityDTO> getRoomAvailabilitySet() {
	return roomAvailabilitySet;
    }

    public void setRoomAvailabilitySet(
	    Set<RoomAvailabilityDTO> roomAvailabilitySet) {
	this.roomAvailabilitySet = roomAvailabilitySet;
    }

    public void setRoomAvailabilityList(
	    List<RoomAvailabilityDTO> roomAvailabilityList) {
	this.roomAvailabilitySet = new HashSet<RoomAvailabilityDTO>();
	this.roomAvailabilitySet.addAll(roomAvailabilityList);
    }

    public String getColur(String bno) {
	Integer intg = Integer
		.valueOf((String.valueOf((bno.charAt(bno.length() - 1)))));
	return colurCodes.get(intg);
    }

    public List<BookingReportDTO> getBookingList() {
	return bookingList;
    }

    public void setBookingList(List<BookingReportDTO> bookingList) {
	this.bookingList = bookingList;
    }

    @Override
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	this.hotelList = Collections.EMPTY_LIST;
	this.roomAvailabilitySet = Collections.EMPTY_SET;
	this.bookingList = Collections.EMPTY_LIST;
	this.hotelId = 1L;
	this.startDate = EMPTY_STRING;
	this.endDate = EMPTY_STRING;
    }

    @Override
    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	return null;
    }

    public Set<Long> getHotelIdSet() {
	// TODO Auto-generated method stub
	return null;
    }

    public void addHotelMap(Map<Long, String> hotelMap) {
	this.hotelList = new ArrayList<Hotel>();
	for (Map.Entry<Long, String> e : hotelMap.entrySet()) {
	    Hotel hotel = new Hotel(e.getKey(), e.getValue(), "");
	    this.hotelList.add(hotel);
	}

    }
}
