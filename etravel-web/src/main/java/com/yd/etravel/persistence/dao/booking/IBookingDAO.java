/**
 * 
 */
package com.yd.etravel.persistence.dao.booking;

import java.util.Date;
import java.util.List;

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
public interface IBookingDAO extends IBaseDAO {
    public List<RoomBooking> findAllBooking() throws PersistenceException;

    public List<BookingReportDTO> findBookingDetail(BookingReportSearchDTO dto)
	    throws PersistenceException;

    public RoomBooking findRoomBooking(String bookingid)
	    throws PersistenceException;

    public List<RoomBooking> findExpiredBookings(final Date date,
	    final String status, final String paymentMethod)
	    throws PersistenceException;

}
