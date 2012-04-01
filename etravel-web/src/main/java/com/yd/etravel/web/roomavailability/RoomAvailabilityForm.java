/**
 * 
 */
package com.yd.etravel.web.roomavailability;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author Dharshana
 * 
 */
public class RoomAvailabilityForm extends BaseForm {

    private String fromDate;
    private String toDate;
    private String roomType;
    private boolean active;
    private boolean edit;
    private int unit;

    private Long hotelId;
    private Long id;
    private List<Hotel> hotelList;
    private List<RoomDailyAvailability> allRoomDailyAvailability;
    private List<RoomAvailabilityDTO> allRoomAvailability;
    // private List<Occupancy> allOccupancy;
    private List<Room> allRoom;
    private Long roomId;
    private Long roomTypeId;
    private Long occupancyId;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
	return fromDate;
    }

    /**
     * @param fromDate
     *            the fromDate to set
     */
    public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
	return toDate;
    }

    /**
     * @param toDate
     *            the toDate to set
     */
    public void setToDate(String toDate) {
	this.toDate = toDate;
    }

    /**
     * @return the active
     */
    public boolean getActive() {
	return active;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(boolean active) {
	this.active = active;
    }

    /**
     * @return the unit
     */
    public int getUnit() {
	return unit;
    }

    /**
     * @param unit
     *            the unit to set
     */
    public void setUnit(int unit) {
	this.unit = unit;
    }

    /**
     * @return the allRoomAvailability
     */
    public List<RoomAvailabilityDTO> getAllRoomAvailability() {
	return allRoomAvailability;
    }

    /**
     * @param allRoomAvailability
     *            the allRoomAvailability to set
     */
    public void setAllRoomAvailability(
	    List<RoomAvailabilityDTO> allRoomAvailability) {
	this.allRoomAvailability = allRoomAvailability;
    }

    /**
     * @return the allRoom
     */
    public List<Room> getAllRoom() {
	return allRoom;
    }

    /**
     * @param allRoom
     *            the allRoom to set
     */
    public void setAllRoom(List<Room> allRoom) {
	this.allRoom = allRoom;
    }

    // /**
    // * @return the allOccupancy
    // */
    // public List<Occupancy> getAllOccupancy() {
    // return allOccupancy;
    // }
    //
    // /**
    // * @param allOccupancy
    // * the allOccupancy to set
    // */
    // public void setAllOccupancy(List<Occupancy> allOccupancy) {
    // this.allOccupancy = allOccupancy;
    // }

    /**
     * @return the allRoomDailyAvailability
     */
    public List<RoomDailyAvailability> getAllRoomDailyAvailability() {
	return allRoomDailyAvailability;
    }

    /**
     * @param allRoomDailyAvailability
     *            the allRoomDailyAvailability to set
     */
    public void setAllRoomDailyAvailability(
	    List<RoomDailyAvailability> allRoomDailyAvailability) {
	this.allRoomDailyAvailability = allRoomDailyAvailability;
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
	return roomType;
    }

    /**
     * @param roomType
     *            the roomType to set
     */
    public void setRoomType(String roomType) {
	this.roomType = roomType;
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
    }

    public Long getOccupancyId() {
	return occupancyId;
    }

    public void setOccupancyId(Long occupancyId) {
	this.occupancyId = occupancyId;
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

    public boolean isEdit() {
	return edit;
    }

    public void setEdit(boolean edit) {
	this.edit = edit;
    }

    @Override
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	allRoomAvailability = Collections.EMPTY_LIST;
	allRoom = Collections.EMPTY_LIST;
	// allOccupancy = Collections.EMPTY_LIST;
	allRoomDailyAvailability = Collections.EMPTY_LIST;
	fromDate = EMPTY_STRING;
	toDate = EMPTY_STRING;
	roomType = EMPTY_STRING;
	hotelId = -1l;
	roomId = -1l;
	occupancyId = -1l;
	roomTypeId = -1l;
	unit = 0;
	edit = false;
    }

    @Override
    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();

	if (StringUtils.isEmpty(this.fromDate)) {
	    addErrors(errors, "etravel.roomAvailability.fromDate.required");
	}
	if (StringUtils.isEmpty(this.toDate)) {
	    addErrors(errors, "etravel.roomAvailability.toDate.required");
	}
	if (StringUtils.isEmpty(this.roomId)) {
	    addErrors(errors, "etravel.roomAvailability.hotel.required");
	}

	return errors;
    }

}
