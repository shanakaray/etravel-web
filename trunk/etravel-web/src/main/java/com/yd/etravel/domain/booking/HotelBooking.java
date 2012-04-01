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
	return hotel;
    }

    public void setHotel(Hotel hotel) {
	this.hotel = hotel;
    }

    public Date getCheckInDate() {
	return checkInDate;
    }

    public String getCheckInDateString() {
	return DateUtil.format(checkInDate);
    }

    public void setCheckInDate(Date checkInDate) {
	this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
	return checkOutDate;
    }

    public String getCheckOutString() {
	return DateUtil.format(checkOutDate);
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

    public Booking getBooking() {
	return booking;
    }

    public void setBooking(Booking booking) {
	this.booking = booking;
    }

    public Long getRoomAvalabiltyId() {
	return roomAvalabiltyId;
    }

    public void setRoomAvalabiltyId(Long roomAvalabiltyId) {
	this.roomAvalabiltyId = roomAvalabiltyId;
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

    public Map<String, String> getParams() {
	Map<String, String> map = new HashMap<String, String>();
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
