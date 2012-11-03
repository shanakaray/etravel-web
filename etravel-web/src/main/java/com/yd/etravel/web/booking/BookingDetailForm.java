/**
 * 
 */
package com.yd.etravel.web.booking;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.custom.booking.BookingReportDTO;
import com.yd.etravel.web.common.BaseForm;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : May 20, 2009 : 9:07:28 AM Type :
 *         com.yd.etravel.web.booking.BookingDetailForm
 * 
 */

public class BookingDetailForm extends BaseForm {

	private static final long serialVersionUID = 4495071934442319318L;
	private Long bookingId;
	private List<ExtraItemBooking> itemBookingList;
	private BookingReportDTO bookingReportDTO;

	public BookingDetailForm() {
	}

	public Long getBookingId() {
		return this.bookingId;
	}

	public BookingReportDTO getBookingReportDTO() {
		return this.bookingReportDTO;
	}

	public List<ExtraItemBooking> getItemBookingList() {
		return this.itemBookingList;
	}

	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.bookingId = -1L;
		this.itemBookingList = Collections.emptyList();
		this.bookingReportDTO = null;
	}

	public void setBookingId(final Long bookingId) {
		this.bookingId = bookingId;
	}

	public void setBookingReportDTO(final BookingReportDTO bookingReportDTO) {
		this.bookingReportDTO = bookingReportDTO;
	}

	public void setItemBookingList(final List<ExtraItemBooking> itemBookingList) {
		this.itemBookingList = itemBookingList;
	}

	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		return null;
	}

}
