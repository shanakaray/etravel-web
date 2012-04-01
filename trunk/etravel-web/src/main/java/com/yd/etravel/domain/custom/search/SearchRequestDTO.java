package com.yd.etravel.domain.custom.search;

import java.util.Calendar;
import java.util.Date;

import com.yd.etravel.util.DateUtil;

/**
 * @author Dharshana com.yd.etravel.domain.custom.search.SearchRequestDTO
 */
public class SearchRequestDTO {

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
	return hotelId;
    }

    /**
     * @param hotelId
     *            the hotelId to set
     */
    public void setHotelId(Long hotelId) {
	this.hotelId = hotelId;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
	return hotelName;
    }

    /**
     * @param hotelName
     *            the hotelName to set
     */
    public void setHotelName(String hotelName) {
	this.hotelName = hotelName;
    }

    /**
     * @return the roomTypeId
     */
    public Long getRoomTypeId() {
	return roomTypeId;
    }

    /**
     * @param roomTypeId
     *            the roomTypeId to set
     */
    public void setRoomTypeId(Long Long) {
	this.roomTypeId = roomTypeId;
    }

    /**
     * @return the roomTypeName
     */
    public String getRoomTypeName() {
	return roomTypeName;
    }

    /**
     * @param roomTypeName
     *            the roomTypeName to set
     */
    public void setRoomTypeName(String roomTypeName) {
	this.roomTypeName = roomTypeName;
    }

    /**
     * @return the checkIn
     */
    public Date getCheckIn() {
	return checkIn;
    }

    public String getCheckInString() {
	return DateUtil.format(checkIn);
    }

    /**
     * @param checkIn
     *            the checkIn to set
     */
    public void setCheckIn(Date checkIn) {
	this.checkIn = checkIn;
    }

    /**
     * @return the checkOut
     */
    public Date getCheckOut() {
	return checkOut;
    }

    public String getCheckOutString() {
	return DateUtil.format(checkOut);
    }

    public int getNoOfNights() {
	Calendar calendar1 = Calendar.getInstance();
	calendar1.setTime(getCheckOut());
	calendar1.getTime();

	Calendar calendar2 = Calendar.getInstance();
	calendar2.setTime(getCheckIn());
	calendar2.getTime();

	long diff = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
	int noOfNights = new Long(diff / (24 * 60 * 60 * 1000)).intValue();
	return noOfNights;
    }

    /**
     * @param checkOut
     *            the checkOut to set
     */
    public void setCheckOut(Date checkOut) {
	this.checkOut = checkOut;
    }

    /**
     * @return the adult
     */
    public Integer getAdult() {
	return adult;
    }

    /**
     * @param adult
     *            the adult to set
     */
    public void setAdult(Integer adult) {
	this.adult = adult;
    }

    /**
     * @return the child
     */
    public Integer getChild() {
	return child;
    }

    /**
     * @param child
     *            the child to set
     */
    public void setChild(Integer child) {
	this.child = child;
    }

    /**
     * @return the infant
     */
    public Integer getInfant() {
	return infant;
    }

    /**
     * @param infant
     *            the infant to set
     */
    public void setInfant(Integer infant) {
	this.infant = infant;
    }

    /**
     * @return the childAge
     */
    public Integer getChildAge() {
	return childAge;
    }

    /**
     * @param childAge
     *            the childAge to set
     */
    public void setChildAge(Integer childAge) {
	this.childAge = childAge;
    }

    /**
     * @return the infantAge
     */
    public Integer getInfantAge() {
	return infantAge;
    }

    /**
     * @param infantAge
     *            the infantAge to set
     */
    public void setInfantAge(Integer infantAge) {
	this.infantAge = infantAge;
    }

}
