/**
 * 
 */
package com.yd.etravel.domain.custom.booking;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.yd.etravel.domain.booking.Booking;
import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.booking.HotelBooking;
import com.yd.etravel.domain.booking.Payment;
import com.yd.etravel.domain.booking.RoomBooking;
import com.yd.etravel.domain.common.BaseObject;

/**
 * @author Dharshana
 * 
 */
public class BookingDTO extends BaseObject {

    private String bookingNumber;
    private BigDecimal totalPrice;
    private Long roomAvalabiltyId;
    private Booking booking;
    private HotelBooking hotelBooking;
    private RoomBooking roomBooking;

    private ArrayList<ExtraItemBooking> extraItemBookingList;
    private Payment payment;

    public BigDecimal getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
	this.totalPrice = totalPrice;
    }

    /**
     * @return the booking
     */
    public Booking getBooking() {
	return booking;
    }

    /**
     * @param booking
     *            the booking to set
     */
    public void setBooking(Booking booking) {
	this.booking = booking;
    }

    /**
     * @return the hotelBooking
     */
    public HotelBooking getHotelBooking() {
	return hotelBooking;
    }

    /**
     * @param hotelBooking
     *            the hotelBooking to set
     */
    public void setHotelBooking(HotelBooking hotelBooking) {
	this.hotelBooking = hotelBooking;
    }

    /**
     * @return the roomBooking
     */
    public RoomBooking getRoomBooking() {
	return roomBooking;
    }

    /**
     * @param roomBooking
     *            the roomBooking to set
     */
    public void setRoomBooking(RoomBooking roomBooking) {
	this.roomBooking = roomBooking;
    }

    /**
     * @return the bookingNumber
     */
    public String getBookingNumber() {
	return bookingNumber;
    }

    /**
     * @param bookingNumber
     *            the bookingNumber to set
     */
    public void setBookingNumber(String bookingNumber) {
	this.bookingNumber = bookingNumber;
    }

    /**
     * @return the roomAvalabiltyId
     */
    public Long getRoomAvalabiltyId() {
	return roomAvalabiltyId;
    }

    /**
     * @param roomAvalabiltyId
     *            the roomAvalabiltyId to set
     */
    public void setRoomAvalabiltyId(Long roomAvalabiltyId) {
	this.roomAvalabiltyId = roomAvalabiltyId;
    }

    /**
     * @return the payment
     */
    public Payment getPayment() {
	return payment;
    }

    /**
     * @param payment
     *            the payment to set
     */
    public void setPayment(Payment payment) {
	this.payment = payment;
    }

    /**
     * @return the extraItemBookingList
     */
    public ArrayList<ExtraItemBooking> getExtraItemBookingList() {
	return extraItemBookingList;
    }

    /**
     * @param extraItemBookingList
     *            the extraItemBookingList to set
     */
    public void setExtraItemBookingList(
	    ArrayList<ExtraItemBooking> extraItemBookingList) {
	this.extraItemBookingList = extraItemBookingList;
    }

}
