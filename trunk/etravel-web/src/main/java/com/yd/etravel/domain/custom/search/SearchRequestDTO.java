package com.yd.etravel.domain.custom.search;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.yd.etravel.util.DateUtil;

/**
 * @author Dharshana com.yd.etravel.domain.custom.search.SearchRequestDTO
 */
public class SearchRequestDTO implements Serializable {

	private static final long serialVersionUID = -8337895053648763479L;
	private Long hotelId;
	private String hotelName;
	private Long roomTypeId;
	private String roomTypeName;

	private Date checkIn;
	private Date checkOut;

	private Integer adult;
	private Integer child;
	private Integer infant;

	private Integer childAge; // Years
	private Integer infantAge; // Month

	/**
	 * @return the adult
	 */
	public Integer getAdult() {
		return this.adult;
	}

	/**
	 * @return the checkIn
	 */
	public Date getCheckIn() {
		return this.checkIn;
	}

	public String getCheckInString() {
		return DateUtil.format(this.checkIn);
	}

	/**
	 * @return the checkOut
	 */
	public Date getCheckOut() {
		return this.checkOut;
	}

	public String getCheckOutString() {
		return DateUtil.format(this.checkOut);
	}

	/**
	 * @return the child
	 */
	public Integer getChild() {
		return this.child;
	}

	/**
	 * @return the childAge
	 */
	public Integer getChildAge() {
		return this.childAge;
	}

	/**
	 * @return the hotelId
	 */
	public Long getHotelId() {
		return this.hotelId;
	}

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return this.hotelName;
	}

	/**
	 * @return the infant
	 */
	public Integer getInfant() {
		return this.infant;
	}

	/**
	 * @return the infantAge
	 */
	public Integer getInfantAge() {
		return this.infantAge;
	}

	public int getNoOfNights() {
		final Calendar startDate = Calendar.getInstance();
		startDate.setTime(getCheckOut());
		startDate.getTime();

		final Calendar endDate = Calendar.getInstance();
		endDate.setTime(getCheckIn());
		endDate.getTime();

		final long diff = startDate.getTimeInMillis()
				- endDate.getTimeInMillis();
		return Long.valueOf(diff / (24 * 60 * 60 * 1000)).intValue(); // NOPMD
																		// by
																		// yohan
																		// on
																		// 10/22/12
																		// 12:03
																		// AM
	}

	/**
	 * @return the roomTypeId
	 */
	public Long getRoomTypeId() {
		return this.roomTypeId;
	}

	/**
	 * @return the roomTypeName
	 */
	public String getRoomTypeName() {
		return this.roomTypeName;
	}

	/**
	 * @param adult
	 *            the adult to set
	 */
	public void setAdult(final Integer adult) {
		this.adult = adult;
	}

	/**
	 * @param checkIn
	 *            the checkIn to set
	 */
	public void setCheckIn(final Date checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * @param checkOut
	 *            the checkOut to set
	 */
	public void setCheckOut(final Date checkOut) {
		this.checkOut = checkOut;
	}

	/**
	 * @param child
	 *            the child to set
	 */
	public void setChild(final Integer child) {
		this.child = child;
	}

	/**
	 * @param childAge
	 *            the childAge to set
	 */
	public void setChildAge(final Integer childAge) {
		this.childAge = childAge;
	}

	/**
	 * @param hotelId
	 *            the hotelId to set
	 */
	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * @param hotelName
	 *            the hotelName to set
	 */
	public void setHotelName(final String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * @param infant
	 *            the infant to set
	 */
	public void setInfant(final Integer infant) {
		this.infant = infant;
	}

	/**
	 * @param infantAge
	 *            the infantAge to set
	 */
	public void setInfantAge(final Integer infantAge) {
		this.infantAge = infantAge;
	}

	/**
	 * @param roomTypeId
	 *            the roomTypeId to set
	 */
	public void setRoomTypeId(final Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	/**
	 * @param roomTypeName
	 *            the roomTypeName to set
	 */
	public void setRoomTypeName(final String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

}
