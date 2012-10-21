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

	private static final long serialVersionUID = 773272035813874261L;
	private String bookingNumber;
	private BigDecimal totalPrice;
	private Long roomAvalabiltyId;
	private Booking booking;
	private HotelBooking hotelBooking;
	private RoomBooking roomBooking;

	private ArrayList<ExtraItemBooking> extraItemBookingList;
	private Payment payment;

	/**
	 * @return the booking
	 */
	public Booking getBooking() {
		return this.booking;
	}

	/**
	 * @return the bookingNumber
	 */
	public String getBookingNumber() {
		return this.bookingNumber;
	}

	/**
	 * @return the extraItemBookingList
	 */
	public ArrayList<ExtraItemBooking> getExtraItemBookingList() {
		return this.extraItemBookingList;
	}

	/**
	 * @return the hotelBooking
	 */
	public HotelBooking getHotelBooking() {
		return this.hotelBooking;
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return this.payment;
	}

	/**
	 * @return the roomAvalabiltyId
	 */
	public Long getRoomAvalabiltyId() {
		return this.roomAvalabiltyId;
	}

	/**
	 * @return the roomBooking
	 */
	public RoomBooking getRoomBooking() {
		return this.roomBooking;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * @param booking
	 *            the booking to set
	 */
	public void setBooking(final Booking booking) {
		this.booking = booking;
	}

	/**
	 * @param bookingNumber
	 *            the bookingNumber to set
	 */
	public void setBookingNumber(final String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	/**
	 * @param extraItemBookingList
	 *            the extraItemBookingList to set
	 */
	public void setExtraItemBookingList(
			final ArrayList<ExtraItemBooking> extraItemBookingList) {
		this.extraItemBookingList = extraItemBookingList;
	}

	/**
	 * @param hotelBooking
	 *            the hotelBooking to set
	 */
	public void setHotelBooking(final HotelBooking hotelBooking) {
		this.hotelBooking = hotelBooking;
	}

	/**
	 * @param payment
	 *            the payment to set
	 */
	public void setPayment(final Payment payment) {
		this.payment = payment;
	}

	/**
	 * @param roomAvalabiltyId
	 *            the roomAvalabiltyId to set
	 */
	public void setRoomAvalabiltyId(final Long roomAvalabiltyId) {
		this.roomAvalabiltyId = roomAvalabiltyId;
	}

	/**
	 * @param roomBooking
	 *            the roomBooking to set
	 */
	public void setRoomBooking(final RoomBooking roomBooking) {
		this.roomBooking = roomBooking;
	}

	public void setTotalPrice(final BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
