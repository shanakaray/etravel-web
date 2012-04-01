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
	return bookingId;
    }

    public void setBookingId(Long bookingId) {
	this.bookingId = bookingId;
    }

    public List<ExtraItemBooking> getItemBookingList() {
	return itemBookingList;
    }

    public void setItemBookingList(List<ExtraItemBooking> itemBookingList) {
	itemBookingList = itemBookingList;
    }

    public BookingReportDTO getBookingReportDTO() {
	return bookingReportDTO;
    }

    public void setBookingReportDTO(BookingReportDTO bookingReportDTO) {
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
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	this.bookingId = -1L;
	this.itemBookingList = Collections.EMPTY_LIST;
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
    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	// TODO Auto-generated method stub
	return null;
    }

}
