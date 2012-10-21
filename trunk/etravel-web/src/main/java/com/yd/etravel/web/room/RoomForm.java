/**
 * 
 */
package com.yd.etravel.web.room;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 11:19:30 AM Type :
 *         com.yd.etravel.web.room.RoomForm
 * 
 */
public class RoomForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8267573289442318573L;
	private int noOfRooms;
	// private String name;
	private Long roomTypeId;
	private Long hotelId;
	private Long id;
	private List<Hotel> hotelList;
	private List<Room> roomList;
	private List<RoomType> roomTypeList;
	private java.util.Map<Long, Hotel> hotelMap;
	private java.util.Map<Long, RoomType> roomTypeMap;

	/**
	 * 
	 */
	public RoomForm() {
		// TODO Auto-generated constructor stub
	}

	public Hotel getHotel(final Long id) {
		return this.hotelMap.get(id);
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public List<Hotel> getHotelList() {
		return this.hotelList;
	}

	public String getHotelName(final Long id) {
		if ((this.hotelMap != null) && this.hotelMap.containsKey(id)) {
			return this.hotelMap.get(id).getName();
		}
		return EMPTY_STRING;
	}

	public Long getId() {
		return this.id;
	}

	public int getNoOfRooms() {
		return this.noOfRooms;
	}

	public List<Room> getRoomList() {
		return this.roomList;
	}

	public RoomType getRoomType(final Long id) {
		return this.roomTypeMap.get(id);
	}

	public Long getRoomTypeId() {
		return this.roomTypeId;
	}

	public List<RoomType> getRoomTypeList() {
		return this.roomTypeList;
	}

	public String getTypeName(final Long id) {
		if ((this.roomTypeMap != null) && this.roomTypeMap.containsKey(id)) {
			return this.roomTypeMap.get(id).getName();
		}
		return EMPTY_STRING;
	}

	/*
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.hotelList = Collections.emptyList();
		this.roomTypeList = Collections.emptyList();
		this.roomList = Collections.emptyList();
		this.roomTypeId = 0L;
		this.hotelId = 0L;
		this.noOfRooms = 0;
		this.id = 0L;
		this.hotelMap = Collections.emptyMap();
		this.roomTypeMap = Collections.emptyMap();
		// this.name=EMPTY_STRING;

	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelList(final List<Hotel> hotelList) {
		this.hotelList = hotelList;
		this.hotelMap = new HashMap<Long, Hotel>();
		for (final Hotel hotel : hotelList) {
			this.hotelMap.put(hotel.getId(), hotel);
		}
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setNoOfRooms(final int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public void setRoomList(final List<Room> roomList) {
		this.roomList = roomList;
	}

	public void setRoomTypeId(final Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public void setRoomTypeList(final List<RoomType> roomTypeList) {
		this.roomTypeList = roomTypeList;
		this.roomTypeMap = new HashMap<Long, RoomType>();
		for (final RoomType roomType : roomTypeList) {
			this.roomTypeMap.put(roomType.getId(), roomType);
		}

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
		final ActionErrors errors = new ActionErrors();

		if (StringUtils.isEmpty(this.hotelId)) {
			addErrors(errors, "etravel.error.pax.hotelid.required");
		}
		if (StringUtils.isEmpty(this.roomTypeId)) {
			addErrors(errors, "etravel.error.roomtype.required");
		}
		if (this.noOfRooms < 1) {
			addErrors(errors, "etravel.error.roomcount.required");
		}

		return errors;
	}

}
