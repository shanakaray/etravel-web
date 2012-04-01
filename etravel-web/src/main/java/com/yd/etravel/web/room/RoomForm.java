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

    public int getNoOfRooms() {
	return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
	this.noOfRooms = noOfRooms;
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

    public List<Hotel> getHotelList() {
	return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
	this.hotelList = hotelList;
	this.hotelMap = new HashMap<Long, Hotel>();
	for (Hotel hotel : hotelList) {
	    this.hotelMap.put(hotel.getId(), hotel);
	}
    }

    public String getHotelName(Long id) {
	if (this.hotelMap != null && this.hotelMap.containsKey(id)) {
	    return this.hotelMap.get(id).getName();
	}
	return EMPTY_STRING;
    }

    public String getTypeName(Long id) {
	if (this.roomTypeMap != null && this.roomTypeMap.containsKey(id)) {
	    return this.roomTypeMap.get(id).getName();
	}
	return EMPTY_STRING;
    }

    public List<RoomType> getRoomTypeList() {
	return roomTypeList;
    }

    public void setRoomTypeList(List<RoomType> roomTypeList) {
	this.roomTypeList = roomTypeList;
	this.roomTypeMap = new HashMap<Long, RoomType>();
	for (RoomType roomType : roomTypeList) {
	    this.roomTypeMap.put(roomType.getId(), roomType);
	}

    }

    public RoomType getRoomType(Long id) {
	return this.roomTypeMap.get(id);
    }

    public Hotel getHotel(Long id) {
	return this.hotelMap.get(id);
    }

    public List<Room> getRoomList() {
	return roomList;
    }

    public void setRoomList(List<Room> roomList) {
	this.roomList = roomList;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	this.hotelList = Collections.EMPTY_LIST;
	this.roomTypeList = Collections.EMPTY_LIST;
	this.roomList = Collections.EMPTY_LIST;
	this.roomTypeId = 0L;
	this.hotelId = 0L;
	this.noOfRooms = 0;
	this.id = 0L;
	this.hotelMap = Collections.EMPTY_MAP;
	this.roomTypeMap = Collections.EMPTY_MAP;
	// this.name=EMPTY_STRING;

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
	ActionErrors errors = new ActionErrors();

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
