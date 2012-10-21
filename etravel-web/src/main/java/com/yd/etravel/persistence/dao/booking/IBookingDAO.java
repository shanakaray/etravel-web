/**
 * 
 */
package com.yd.etravel.persistence.dao.booking;

import java.util.Date;
import java.util.List;

import com.yd.etravel.domain.booking.Booking;
import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.booking.HotelBooking;
import com.yd.etravel.domain.booking.Payment;
import com.yd.etravel.domain.booking.RoomBooking;
import com.yd.etravel.domain.custom.booking.BookingReportDTO;
import com.yd.etravel.domain.custom.booking.BookingReportSearchDTO;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

/**
 * @author : Dharshana.
 * 
 * 
 */
public interface IBookingDAO extends IBaseDAO<Booking> {
	public List<RoomBooking> findAllBooking() throws PersistenceException;

	public List<BookingReportDTO> findBookingDetail(BookingReportSearchDTO dto)
			throws PersistenceException;

	public RoomBooking findRoomBooking(String bookingid)
			throws PersistenceException;

	public List<RoomBooking> findExpiredBookings(final Date date,
			final String status, final String paymentMethod)
			throws PersistenceException;

	public HotelBooking save(HotelBooking hotelBooking)
			throws PersistenceException;

	public RoomBooking save(RoomBooking roomBooking)
			throws PersistenceException;

	public Payment save(Payment payment) throws PersistenceException;

	public ExtraItemBooking merge(ExtraItemBooking extraItemBooking)
			throws PersistenceException;

}
