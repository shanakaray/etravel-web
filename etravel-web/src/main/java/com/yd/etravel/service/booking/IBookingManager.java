/**
 * 
 */
package com.yd.etravel.service.booking;

import java.math.BigDecimal;
import java.util.List;

import com.yd.etravel.domain.booking.RoomBooking;
import com.yd.etravel.domain.custom.booking.BookingDTO;
import com.yd.etravel.domain.custom.booking.BookingReportDTO;
import com.yd.etravel.domain.custom.booking.BookingReportSearchDTO;
import com.yd.etravel.service.exception.ServiceException;

public interface IBookingManager {

	public BookingDTO save(BookingDTO bookingDTO) throws ServiceException;

	public List<RoomBooking> findAllBooking() throws ServiceException;

	public List<BookingReportDTO> findBookingDetail(BookingReportSearchDTO dto)
			throws ServiceException;

	public void saveFailedOnRequestBookings() throws ServiceException;

	public void saveFailedOnlineBookings() throws ServiceException;

	public void saveCancelBooking(List<RoomBooking> bookingList)
			throws ServiceException;

	public RoomBooking saveBookingConfirm(String bookingid, BigDecimal ammount)
			throws ServiceException;

	public RoomBooking findRoomBooking(final String bookingid)
			throws ServiceException;
}
