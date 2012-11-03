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
	/**
	 * 
	 */
	private static final long serialVersionUID = 4301208483391889574L;
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

	public void addHotelMap(final Map<Long, String> hotelMap) {
		this.hotelList = new ArrayList<Hotel>();
		for (final Map.Entry<Long, String> e : hotelMap.entrySet()) {
			final Hotel hotel = new Hotel(e.getKey(), e.getValue(), "");
			this.hotelList.add(hotel);
		}

	}

	public List<BookingReportDTO> getBookingList() {
		return this.bookingList;
	}

	public String getColur(final String bno) {
		final Integer intg = Integer.valueOf(String.valueOf(bno.charAt(bno
				.length() - 1)));
		return colurCodes.get(intg);
	}

	public String getEndDate() {
		return this.endDate;
	}

	public Date getEndDateToDate() {
		return DateUtil.parse(this.endDate);
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public Set<Long> getHotelIdSet() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Hotel> getHotelList() {
		return this.hotelList;
	}

	public Set<RoomAvailabilityDTO> getRoomAvailabilitySet() {
		return this.roomAvailabilitySet;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public Date getStartDateToDate() {
		return DateUtil.parse(this.startDate);
	}

	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.hotelList = Collections.emptyList();
		this.roomAvailabilitySet = Collections.emptySet();
		this.bookingList = Collections.emptyList();
		this.hotelId = 1L;
		this.startDate = EMPTYSTRING;
		this.endDate = EMPTYSTRING;
	}

	public void setBookingList(final List<BookingReportDTO> bookingList) {
		this.bookingList = bookingList;
	}

	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelList(final List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public void setRoomAvailabilityList(
			final List<RoomAvailabilityDTO> roomAvailabilityList) {
		this.roomAvailabilitySet = new HashSet<RoomAvailabilityDTO>();
		this.roomAvailabilitySet.addAll(roomAvailabilityList);
	}

	public void setRoomAvailabilitySet(
			final Set<RoomAvailabilityDTO> roomAvailabilitySet) {
		this.roomAvailabilitySet = roomAvailabilitySet;
	}

	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		return null;
	}
}
