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
	return bookingId;
    }

    public void setBookingId(Long bookingId) {
	this.bookingId = bookingId;
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

    public Long getHotelId() {
	return hotelId;
    }

    public void setHotelId(Long hotelId) {
	this.hotelId = hotelId;
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    public Long getAgentId() {
	return agentId;
    }

    public void setAgentId(Long agentId) {
	this.agentId = agentId;
    }

    public String getBookingCode() {
	return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
	this.bookingCode = bookingCode;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getUserFName() {
	if (!StringUtils.isEmpty(userFName)) {
	    String str = new String(new char[] { userFName.trim().charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(userFName.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return agentName;
	}
    }

    public void setUserFName(String userFName) {
	this.userFName = userFName;
    }

    public String getUserLName() {
	if (!StringUtils.isEmpty(userLName)) {
	    String str = new String(new char[] { userLName.trim().charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(userLName.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return agentName;
	}

    }

    public void setUserLName(String userLName) {
	this.userLName = userLName;
    }

    public String getAgentName() {
	if (!StringUtils.isEmpty(agentName)) {
	    String str = new String(new char[] { agentName.trim().charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(agentName.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return agentName;
	}

    }

    public void setAgentName(String agentName) {
	this.agentName = agentName;
    }

    public String getHotelName() {
	return hotelName;
    }

    public void setHotelName(String hotelName) {
	this.hotelName = hotelName;
    }

    public String getRoomName() {
	return roomName;
    }

    public void setRoomName(String roomName) {
	this.roomName = roomName;
    }

    public String getRoomTypeName() {
	return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
	this.roomTypeName = roomTypeName;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Date getDepatureDate() {
	return depatureDate;
    }

    public void setDepatureDate(Date depatureDate) {
	this.depatureDate = depatureDate;
    }

    public Date getBookingDate() {
	return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
	this.bookingDate = bookingDate;
    }

    public BigDecimal getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
	this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalCost() {
	return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
	this.totalCost = totalCost;
    }

    public String getPaymentMethod() {
	return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
    }

    public Date getCheckInDate() {
	return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
	this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
	return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
	this.checkOutDate = checkOutDate;
    }

    public Integer getNoOfRoom() {
	return noOfRoom;
    }

    public void setNoOfRoom(Integer noOfRoom) {
	this.noOfRoom = noOfRoom;
    }

    public String getCheckInDateString() {
	return DateUtil.format(checkInDate);
    }

    public String getCheckOutDateString() {
	return DateUtil.format(checkOutDate);
    }

    public String getDepatureDateString() {
	return DateUtil.format(depatureDate);
    }

    public String getBookingDateString() {
	return DateUtil.format(bookingDate);
    }

    public BigDecimal getPaidAmount() {
	if (status.equals(IConstants.IBooking.BOOKING_SATATUS_ON_REQUEST))
	    return new BigDecimal(0.00);
	else
	    return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
	this.paidAmount = paidAmount;
    }

    public String getUserAddress() {
	return userAddress;
    }

    public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
    }

    public String getUserContact() {
	return userContact;
    }

    public void setUserContact(String userContact) {
	this.userContact = userContact;
    }

    public String getUserEmail() {
	return userEmail;
    }

    public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
    }

    public Date getCancelDate() {
	return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
	this.cancelDate = cancelDate;
    }

    public String getCancelDateString() {
	return DateUtil.format(cancelDate);
    }

    public int getNoOfNights() {
	Calendar calendar1 = Calendar.getInstance();
	calendar1.setTime(checkOutDate);
	calendar1.getTime();

	Calendar calendar2 = Calendar.getInstance();
	calendar2.setTime(checkInDate);
	calendar2.getTime();

	long diff = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();

	int noOfNights = Long.valueOf(diff / (24 * 60 * 60 * 1000)).intValue();

	return noOfNights;
    }

    public Date getExpireDate() {
	return expireDate;
    }

    public void setExpireDate(Date expireDate) {
	this.expireDate = expireDate;
    }

    public String getExpireDateString() {
	return DateUtil.format(expireDate, DateUtil.SIMPLE_DATE_TIME_FORMAT);
    }

    public BookingReportDTO() {
    }

    public BookingReportDTO(Long bookingId, Long roomId, Long roomTypeId,
	    Long hotelId, Long userId, String bookingCode, String userName,
	    String userFName, String userLName, String userAdress,
	    String userContact, String userEmail, String hotelName,
	    String roomName, String roomTypeName, String status,
	    Date depatureDate, Date bookingDate, Date cancelDate,
	    Date expireDate, BigDecimal totalPrice, BigDecimal totalCost,
	    String paymentMethod, BigDecimal paidAmount, Date checkInDate,
	    Date checkOutDate, Integer noOfRoom, Long agentId, String agentName) {
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
