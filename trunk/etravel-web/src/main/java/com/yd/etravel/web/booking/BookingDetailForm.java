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

	private Long bookingId;
	private List<ExtraItemBooking> itemBookingList;
	private BookingReportDTO bookingReportDTO;

	/**
	 * 
	 */
	public BookingDetailForm() {
		// TODO Auto-generated constructor stub
	}

	public Long getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(final Long bookingId) {
		this.bookingId = bookingId;
	}

	public List<ExtraItemBooking> getItemBookingList() {
		return this.itemBookingList;
	}

	public void setItemBookingList(final List<ExtraItemBooking> itemBookingList) {
		this.itemBookingList = itemBookingList;
	}

	public BookingReportDTO getBookingReportDTO() {
		return this.bookingReportDTO;
	}

	public void setBookingReportDTO(final BookingReportDTO bookingReportDTO) {
		this.bookingReportDTO = bookingReportDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.bookingId = -1L;
		this.itemBookingList = Collections.emptyList();
		this.bookingReportDTO = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yd.etravel.web.common.BaseForm#validateBean(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
