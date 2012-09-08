package com.yd.etravel.domain.booking;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.util.DateUtil;

@Entity
@Table(name = "T_HOTEL_BOOKING")
public class HotelBooking extends BaseObject {

    private static final long serialVersionUID = 8353998802516217898L;

    @ManyToOne
    private Hotel hotel;

    @Column
    private Date checkInDate;

    @Column
    private Date checkOutDate;

    @Column
    private Integer noOfRoom;

    @ManyToOne
    private Booking booking;

    @Column
    private Long roomAvalabiltyId;

    public Hotel getHotel() {
	return this.hotel;
    }

    public void setHotel(final Hotel hotel) {
	this.hotel = hotel;
    }

    public Date getCheckInDate() {
	return this.checkInDate;
    }

    public String getCheckInDateString() {
	return DateUtil.format(this.checkInDate);
    }

    public void setCheckInDate(final Date checkInDate) {
	this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
	return this.checkOutDate;
    }

    public String getCheckOutString() {
	return DateUtil.format(this.checkOutDate);
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

    public Booking getBooking() {
	return this.booking;
    }

    public void setBooking(final Booking booking) {
	this.booking = booking;
    }

    public Long getRoomAvalabiltyId() {
	return this.roomAvalabiltyId;
    }

    public void setRoomAvalabiltyId(final Long roomAvalabiltyId) {
	this.roomAvalabiltyId = roomAvalabiltyId;
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

    public Map<String, String> getParams() {
	final Map<String, String> map = new HashMap<String, String>();
	map.put("hotel.name", getHotel().getName());
	map.put("booking.bookingId", getBooking().getCode());
	map.put("booking.checkin", DateUtil.format(getCheckInDate()));
	map.put("booking.checkout", DateUtil.format(getCheckOutDate()));
	// getBooking().
	map.put("booking.roomcost", getBooking().getRoomPrice().toPlainString());
	map.put("booking.totcost", getBooking().getTotalPrice().toPlainString());
	map.put("room.name", "xx");
	map.put("room.count", "xx");
	return map;
    }
}
