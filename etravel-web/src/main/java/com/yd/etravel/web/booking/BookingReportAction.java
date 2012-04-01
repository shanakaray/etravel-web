/**
 * 
 */
package com.yd.etravel.web.booking;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.booking.BookingReportSearchDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.util.IUserProfile;
import com.yd.etravel.util.DateUtil;
import com.yd.etravel.util.IConstants.IUserFunctions;
import com.yd.etravel.util.IConstants.IUserRoles;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseAction;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Mar 7, 2009 : 1:21:44 PM Type :
 *         com.yd.etravel.web.booking.BookingReportAction
 * 
 */

public class BookingReportAction extends BaseAction {

    /**
	 * 
	 */
    public BookingReportAction() {
	// TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.web.common.BaseAction#add(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward add(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.web.common.BaseAction#back(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward back(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseAction#create(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward create(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseAction#delete(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward delete(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.web.common.BaseAction#edit(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward edit(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.web.common.BaseAction#find(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward find(ActionMapping mapping, ActionForm actionForm,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	final BookingReportForm reportForm = (BookingReportForm) actionForm;
	BookingReportSearchDTO bookingDto = new BookingReportSearchDTO();
	bookingDto
		.setRoomId(reportForm.getRoomId().longValue() > 0 ? reportForm
			.getRoomId() : null);
	bookingDto.setBookingId(reportForm.getBookingId());
	bookingDto.setBookedFrom(StringUtils.isEmpty(reportForm
		.getBookedFromString()) ? null : DateUtil.parse(reportForm
		.getBookedFromString()));
	bookingDto.setBookedTo(StringUtils.isEmpty(reportForm
		.getBookedToString()) ? null : DateUtil.parse(reportForm
		.getBookedToString()));
	bookingDto.setHotelId(reportForm.getHotelSet());
	IUserProfile profile = getUserProfile(request);
	filUpSelects(reportForm, profile);

	if (profile.getRoles().contains(IUserRoles.CUSTOMER_ROLE_NAME)) {
	    bookingDto.setBookedBy(profile.getId());
	}

	if (profile.hasFunction(IUserFunctions.BOOKINGREPORT_ASSIGNHOTELS_ONLY)) {
	    reportForm.addHotelMap(profile.getAssignedHotels());
	    if (!profile.getAssignedHotels().isEmpty()) {
		if (reportForm.getHotelSet().isEmpty()) {
		    bookingDto.setHotelId(profile.getAssignedHotels().keySet());
		}
		reportForm.setBookingList(getBookingManager()
			.findBookingDetail(bookingDto));
	    }
	} else {
	    reportForm.setBookingList(getBookingManager().findBookingDetail(
		    bookingDto));
	}

	if (reportForm.getHotelId() != null
		&& reportForm.getHotelId().longValue() > 0) {
	    List<Room> rooms = getRoomManager().findAllRoomWithRoomType(
		    reportForm.getHotelId());
	    reportForm.setAllRoom(rooms);
	}

	return mapping.findForward(SUCCESS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseAction#forward(org.apache.struts.action
     * .ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward forward(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.web.common.BaseAction#init(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward init(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	BookingReportForm reportForm = (BookingReportForm) form;
	IUserProfile profile = getUserProfile(request);

	filUpSelects(reportForm, profile);

	if (reportForm.getBookingList() == null
		|| reportForm.getBookingList().isEmpty()) {
	    BookingReportSearchDTO bookinDto = new BookingReportSearchDTO();

	    if (profile.getRoles().contains(IUserRoles.CUSTOMER_ROLE_NAME)) {
		bookinDto.setBookedBy(profile.getId());
	    }

	    if (profile
		    .hasFunction(IUserFunctions.BOOKINGREPORT_ASSIGNHOTELS_ONLY)) {
		bookinDto.setHotelId(profile.getAssignedHotels().keySet());
		if (!profile.getAssignedHotels().isEmpty())
		    reportForm.setBookingList(getBookingManager()
			    .findBookingDetail(bookinDto));
	    } else

		reportForm.setBookingList(getBookingManager()
			.findBookingDetail(bookinDto));
	}

	return mapping.findForward(SUCCESS);
    }

    public void filUpSelects(final BookingReportForm reportForm,
	    final IUserProfile profile) throws ServiceException {

	if (profile.hasFunction(IUserFunctions.BOOKINGREPORT_ASSIGNHOTELS_ONLY)) {
	    reportForm.addHotelMap(profile.getAssignedHotels());
	} else {
	    reportForm.setHotelList(getHotelManager().findAllActiveHotels());
	}

	if (reportForm.getHotelId() != null
		&& reportForm.getHotelId().longValue() > 0) {
	    List<Room> roomList = getRoomManager().findAllRoomWithRoomType(
		    reportForm.getHotelId());
	    reportForm.setAllRoom(roomList);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseAction#process(org.apache.struts.action
     * .ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward process(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.web.common.BaseAction#save(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward save(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.web.common.BaseAction#send(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward send(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.web.common.BaseAction#sort(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward sort(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	return null;
    }

    @Override
    public ActionForward search(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
