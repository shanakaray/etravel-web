package com.yd.etravel.domain.booking;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.util.DateUtil;

@Entity
@Table(name = "T_ROOM_BOOKING")
public class RoomBooking extends BaseObject {
    private static final long serialVersionUID = -4428559965266056423L;

    @ManyToOne
    private HotelBooking hotelBooking;

    @ManyToOne
    private Room room;

    @Column
    private BigDecimal price;

    @Column
    private Integer roomNumber;

    @Column
    private Integer totalPax;

    public HotelBooking getHotelBooking() {
	return this.hotelBooking;
    }

    public void setHotelBooking(final HotelBooking hotelBooking) {
	this.hotelBooking = hotelBooking;
    }

    public Room getRoom() {
	return this.room;
    }

    public void setRoom(final Room room) {
	this.room = room;
    }

    public BigDecimal getPrice() {
	return this.price;
    }

    public void setPrice(final BigDecimal price) {
	this.price = price;
    }

    public Integer getTotalPax() {
	return this.totalPax;
    }

    public void setTotalPax(final Integer totalPax) {
	this.totalPax = totalPax;
    }

    public Integer getRoomNumber() {
	return this.roomNumber;
    }

    public void setRoomNumber(final Integer roomNumber) {
	this.roomNumber = roomNumber;
    }

    public Map<String, String> getParams() {
	final Map<String, String> map = new HashMap<String, String>();
	map.put("hotel.name", getHotelBooking().getHotel().getName());
	map.put("booking.bookingId", getHotelBooking().getBooking().getCode());
	map.put("booking.checkin",
		DateUtil.format(getHotelBooking().getCheckInDate()));
	map.put("booking.checkout",
		DateUtil.format(getHotelBooking().getCheckOutDate()));
	// getBooking().
	map.put("booking.roomcost", getHotelBooking().getBooking()
		.getRoomPrice().toPlainString());
	map.put("booking.totcost", getHotelBooking().getBooking()
		.getTotalPrice().toPlainString());
	map.put("booking.paid", getHotelBooking().getBooking().getPaidAmount()
		.toPlainString());
	map.put("room.name", getRoom().getRoomType().getName());
	map.put("room.count", getHotelBooking().getNoOfRoom().toString());
	return map;
    }

}
