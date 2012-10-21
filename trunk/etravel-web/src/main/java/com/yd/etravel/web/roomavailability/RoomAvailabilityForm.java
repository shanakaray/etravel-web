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

	/**
	 * 
	 */
	private static final long serialVersionUID = 4768392798260825668L;
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
	 * @return the active
	 */
	public boolean getActive() {
		return this.active;
	}

	/**
	 * @return the allRoom
	 */
	public List<Room> getAllRoom() {
		return this.allRoom;
	}

	/**
	 * @return the allRoomAvailability
	 */
	public List<RoomAvailabilityDTO> getAllRoomAvailability() {
		return this.allRoomAvailability;
	}

	/**
	 * @return the allRoomDailyAvailability
	 */
	public List<RoomDailyAvailability> getAllRoomDailyAvailability() {
		return this.allRoomDailyAvailability;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return this.fromDate;
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public List<Hotel> getHotelList() {
		return this.hotelList;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	public Long getOccupancyId() {
		return this.occupancyId;
	}

	public Long getRoomId() {
		return this.roomId;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return this.roomType;
	}

	public Long getRoomTypeId() {
		return this.roomTypeId;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return this.toDate;
	}

	/**
	 * @return the unit
	 */
	public int getUnit() {
		return this.unit;
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

	public boolean isEdit() {
		return this.edit;
	}

	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.allRoomAvailability = Collections.emptyList();
		this.allRoom = Collections.emptyList();
		// allOccupancy = Collections.emptyList();
		this.allRoomDailyAvailability = Collections.emptyList();
		this.fromDate = EMPTY_STRING;
		this.toDate = EMPTY_STRING;
		this.roomType = EMPTY_STRING;
		this.hotelId = -1l;
		this.roomId = -1l;
		this.occupancyId = -1l;
		this.roomTypeId = -1l;
		this.unit = 0;
		this.edit = false;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * @param allRoom
	 *            the allRoom to set
	 */
	public void setAllRoom(final List<Room> allRoom) {
		this.allRoom = allRoom;
	}

	/**
	 * @param allRoomAvailability
	 *            the allRoomAvailability to set
	 */
	public void setAllRoomAvailability(
			final List<RoomAvailabilityDTO> allRoomAvailability) {
		this.allRoomAvailability = allRoomAvailability;
	}

	/**
	 * @param allRoomDailyAvailability
	 *            the allRoomDailyAvailability to set
	 */
	public void setAllRoomDailyAvailability(
			final List<RoomDailyAvailability> allRoomDailyAvailability) {
		this.allRoomDailyAvailability = allRoomDailyAvailability;
	}

	public void setEdit(final boolean edit) {
		this.edit = edit;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(final String fromDate) {
		this.fromDate = fromDate;
	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelList(final List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	public void setOccupancyId(final Long occupancyId) {
		this.occupancyId = occupancyId;
	}

	public void setRoomId(final Long roomId) {
		this.roomId = roomId;
	}

	/**
	 * @param roomType
	 *            the roomType to set
	 */
	public void setRoomType(final String roomType) {
		this.roomType = roomType;
	}

	public void setRoomTypeId(final Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(final String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(final int unit) {
		this.unit = unit;
	}

	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		final ActionErrors errors = new ActionErrors();

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
