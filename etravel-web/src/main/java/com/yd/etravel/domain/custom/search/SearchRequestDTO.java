package com.yd.etravel.domain.custom.search;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.yd.etravel.util.DateUtil;

/**
 * @author Dharshana com.yd.etravel.domain.custom.search.SearchRequestDTO
 */
public class SearchRequestDTO implements Serializable {

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
     * @return the hotelId
     */
    public Long getHotelId() {
	return this.hotelId;
    }

    /**
     * @param hotelId
     *            the hotelId to set
     */
    public void setHotelId(final Long hotelId) {
	this.hotelId = hotelId;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
	return this.hotelName;
    }

    /**
     * @param hotelName
     *            the hotelName to set
     */
    public void setHotelName(final String hotelName) {
	this.hotelName = hotelName;
    }

    /**
     * @return the roomTypeId
     */
    public Long getRoomTypeId() {
	return this.roomTypeId;
    }

    /**
     * @param roomTypeId
     *            the roomTypeId to set
     */
    public void setRoomTypeId(final Long Long) {
	this.roomTypeId = this.roomTypeId;
    }

    /**
     * @return the roomTypeName
     */
    public String getRoomTypeName() {
	return this.roomTypeName;
    }

    /**
     * @param roomTypeName
     *            the roomTypeName to set
     */
    public void setRoomTypeName(final String roomTypeName) {
	this.roomTypeName = roomTypeName;
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
     * @param checkIn
     *            the checkIn to set
     */
    public void setCheckIn(final Date checkIn) {
	this.checkIn = checkIn;
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

    public int getNoOfNights() {
	final Calendar calendar1 = Calendar.getInstance();
	calendar1.setTime(getCheckOut());
	calendar1.getTime();

	final Calendar calendar2 = Calendar.getInstance();
	calendar2.setTime(getCheckIn());
	calendar2.getTime();

	final long diff = calendar1.getTimeInMillis()
		- calendar2.getTimeInMillis();
	final int noOfNights = new Long(diff / (24 * 60 * 60 * 1000))
		.intValue();
	return noOfNights;
    }

    /**
     * @param checkOut
     *            the checkOut to set
     */
    public void setCheckOut(final Date checkOut) {
	this.checkOut = checkOut;
    }

    /**
     * @return the adult
     */
    public Integer getAdult() {
	return this.adult;
    }

    /**
     * @param adult
     *            the adult to set
     */
    public void setAdult(final Integer adult) {
	this.adult = adult;
    }

    /**
     * @return the child
     */
    public Integer getChild() {
	return this.child;
    }

    /**
     * @param child
     *            the child to set
     */
    public void setChild(final Integer child) {
	this.child = child;
    }

    /**
     * @return the infant
     */
    public Integer getInfant() {
	return this.infant;
    }

    /**
     * @param infant
     *            the infant to set
     */
    public void setInfant(final Integer infant) {
	this.infant = infant;
    }

    /**
     * @return the childAge
     */
    public Integer getChildAge() {
	return this.childAge;
    }

    /**
     * @param childAge
     *            the childAge to set
     */
    public void setChildAge(final Integer childAge) {
	this.childAge = childAge;
    }

    /**
     * @return the infantAge
     */
    public Integer getInfantAge() {
	return this.infantAge;
    }

    /**
     * @param infantAge
     *            the infantAge to set
     */
    public void setInfantAge(final Integer infantAge) {
	this.infantAge = infantAge;
    }

}
