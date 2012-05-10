/**
 * 
 */
package com.yd.etravel.domain.custom.booking;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.yd.etravel.util.DateUtil;
import com.yd.etravel.util.IConstants;
import com.yd.etravel.util.StringUtils;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Mar 1, 2009 : 4:17:22 PM Type :
 *         com.yd.etravel.domain.custom.booking.BookingReportDTO
 * 
 */

public class BookingReportDTO {
    private Long bookingId;
    private Long roomId;
    private Long roomTypeId;
    private Long hotelId;
    private Long userId;
    private Long agentId;

    private String bookingCode;
    private String userName;
    private String userFName;
    private String userLName;
    private String userAddress;
    private String userContact;
    private String userEmail;
    private String agentName;
    private String hotelName;
    private String roomName;
    private String roomTypeName;

    private String status;
    private Date depatureDate;
    private Date bookingDate;
    private Date cancelDate;
    private Date expireDate;

    private BigDecimal totalPrice;
    private BigDecimal totalCost;
    private BigDecimal paidAmount;

    private String paymentMethod;

    private Date checkInDate;
    private Date checkOutDate;
    private Integer noOfRoom;

    public Long getBookingId() {
	return this.bookingId;
    }

    public void setBookingId(final Long bookingId) {
	this.bookingId = bookingId;
    }

    public Long getRoomId() {
	return this.roomId;
    }

    public void setRoomId(final Long roomId) {
	this.roomId = roomId;
    }

    public Long getRoomTypeId() {
	return this.roomTypeId;
    }

    public void setRoomTypeId(final Long roomTypeId) {
	this.roomTypeId = roomTypeId;
    }

    public Long getHotelId() {
	return this.hotelId;
    }

    public void setHotelId(final Long hotelId) {
	this.hotelId = hotelId;
    }

    public Long getUserId() {
	return this.userId;
    }

    public void setUserId(final Long userId) {
	this.userId = userId;
    }

    public Long getAgentId() {
	return this.agentId;
    }

    public void setAgentId(final Long agentId) {
	this.agentId = agentId;
    }

    public String getBookingCode() {
	return this.bookingCode;
    }

    public void setBookingCode(final String bookingCode) {
	this.bookingCode = bookingCode;
    }

    public String getUserName() {
	return this.userName;
    }

    public void setUserName(final String userName) {
	this.userName = userName;
    }

    public String getUserFName() {
	if (!StringUtils.isEmpty(this.userFName)) {
	    String str = new String(new char[] { this.userFName.trim()
		    .charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(this.userFName.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return this.agentName;
	}
    }

    public void setUserFName(final String userFName) {
	this.userFName = userFName;
    }

    public String getUserLName() {
	if (!StringUtils.isEmpty(this.userLName)) {
	    String str = new String(new char[] { this.userLName.trim()
		    .charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(this.userLName.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return this.agentName;
	}

    }

    public void setUserLName(final String userLName) {
	this.userLName = userLName;
    }

    public String getAgentName() {
	if (!StringUtils.isEmpty(this.agentName)) {
	    String str = new String(new char[] { this.agentName.trim()
		    .charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(this.agentName.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return this.agentName;
	}

    }

    public void setAgentName(final String agentName) {
	this.agentName = agentName;
    }

    public String getHotelName() {
	return this.hotelName;
    }

    public void setHotelName(final String hotelName) {
	this.hotelName = hotelName;
    }

    public String getRoomName() {
	return this.roomName;
    }

    public void setRoomName(final String roomName) {
	this.roomName = roomName;
    }

    public String getRoomTypeName() {
	return this.roomTypeName;
    }

    public void setRoomTypeName(final String roomTypeName) {
	this.roomTypeName = roomTypeName;
    }

    public String getStatus() {
	return this.status;
    }

    public void setStatus(final String status) {
	this.status = status;
    }

    public Date getDepatureDate() {
	return this.depatureDate;
    }

    public void setDepatureDate(final Date depatureDate) {
	this.depatureDate = depatureDate;
    }

    public Date getBookingDate() {
	return this.bookingDate;
    }

    public void setBookingDate(final Date bookingDate) {
	this.bookingDate = bookingDate;
    }

    public BigDecimal getTotalPrice() {
	return this.totalPrice;
    }

    public void setTotalPrice(final BigDecimal totalPrice) {
	this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalCost() {
	return this.totalCost;
    }

    public void setTotalCost(final BigDecimal totalCost) {
	this.totalCost = totalCost;
    }

    public String getPaymentMethod() {
	return this.paymentMethod;
    }

    public void setPaymentMethod(final String paymentMethod) {
	this.paymentMethod = paymentMethod;
    }

    public Date getCheckInDate() {
	return this.checkInDate;
    }

    public void setCheckInDate(final Date checkInDate) {
	this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
	return this.checkOutDate;
    }

    public void setCheckOutDate(final Date checkOutDate) {
	this.checkOutDate = checkOutDate;
    }

    public Integer getNoOfRoom() {
	return this.noOfRoom;
    }

    public void setNoOfRoom(final Integer noOfRoom) {
	this.noOfRoom = noOfRoom;
    }

    public String getCheckInDateString() {
	return DateUtil.format(this.checkInDate);
    }

    public String getCheckOutDateString() {
	return DateUtil.format(this.checkOutDate);
    }

    public String getDepatureDateString() {
	return DateUtil.format(this.depatureDate);
    }

    public String getBookingDateString() {
	return DateUtil.format(this.bookingDate);
    }

    public BigDecimal getPaidAmount() {
	if (this.status.equals(IConstants.IBooking.BOOKING_SATATUS_ON_REQUEST)) {
	    return new BigDecimal(0.00);
	} else {
	    return this.paidAmount;
	}
    }

    public void setPaidAmount(final BigDecimal paidAmount) {
	this.paidAmount = paidAmount;
    }

    public String getUserAddress() {
	return this.userAddress;
    }

    public void setUserAddress(final String userAddress) {
	this.userAddress = userAddress;
    }

    public String getUserContact() {
	return this.userContact;
    }

    public void setUserContact(final String userContact) {
	this.userContact = userContact;
    }

    public String getUserEmail() {
	return this.userEmail;
    }

    public void setUserEmail(final String userEmail) {
	this.userEmail = userEmail;
    }

    public Date getCancelDate() {
	return this.cancelDate;
    }

    public void setCancelDate(final Date cancelDate) {
	this.cancelDate = cancelDate;
    }

    public String getCancelDateString() {
	return DateUtil.format(this.cancelDate);
    }

    public int getNoOfNights() {
	final Calendar calendar1 = Calendar.getInstance();
	calendar1.setTime(this.checkOutDate);
	calendar1.getTime();

	final Calendar calendar2 = Calendar.getInstance();
	calendar2.setTime(this.checkInDate);
	calendar2.getTime();

	final long diff = calendar1.getTimeInMillis()
		- calendar2.getTimeInMillis();

	final int noOfNights = Long.valueOf(diff / (24 * 60 * 60 * 1000))
		.intValue();

	return noOfNights;
    }

    public Date getExpireDate() {
	return this.expireDate;
    }

    public void setExpireDate(final Date expireDate) {
	this.expireDate = expireDate;
    }

    public String getExpireDateString() {
	return DateUtil.format(this.expireDate,
		DateUtil.SIMPLE_DATE_TIME_FORMAT);
    }

    public BookingReportDTO() {
    }

    public BookingReportDTO(final Long bookingId, final Long roomId,
	    final Long roomTypeId, final Long hotelId, final Long userId,
	    final String bookingCode, final String userName,
	    final String userFName, final String userLName,
	    final String userAdress, final String userContact,
	    final String userEmail, final String hotelName,
	    final String roomName, final String roomTypeName,
	    final String status, final Date depatureDate,
	    final Date bookingDate, final Date cancelDate,
	    final Date expireDate, final BigDecimal totalPrice,
	    final BigDecimal totalCost, final String paymentMethod,
	    final BigDecimal paidAmount, final Date checkInDate,
	    final Date checkOutDate, final Integer noOfRoom,
	    final Long agentId, final String agentName) {
	super();
	this.bookingId = bookingId;
	this.roomId = roomId;
	this.roomTypeId = roomTypeId;
	this.hotelId = hotelId;
	this.userId = userId;
	this.bookingCode = bookingCode;
	this.userName = userName;
	this.userFName = userFName;
	this.userLName = userLName;
	this.userAddress = userAdress;
	this.userContact = userContact;
	this.userEmail = userEmail;
	this.hotelName = hotelName;
	this.roomName = roomName;
	this.roomTypeName = roomTypeName;
	this.status = status;
	this.depatureDate = depatureDate;
	this.bookingDate = bookingDate;
	this.totalPrice = totalPrice;
	this.totalCost = totalCost;
	this.paymentMethod = paymentMethod;
	this.checkInDate = checkInDate;
	this.checkOutDate = checkOutDate;
	this.noOfRoom = noOfRoom;
	this.agentId = agentId;
	this.agentName = agentName;
	this.paidAmount = paidAmount;
	this.cancelDate = cancelDate;
	this.expireDate = expireDate;
    }

}
