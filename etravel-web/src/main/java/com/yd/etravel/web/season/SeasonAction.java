/**
 * 
 */
package com.yd.etravel.web.season;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;
import com.yd.etravel.util.DateUtil;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author Dharshana
 * 
 */
public class SeasonAction extends BaseAction {

    /**
	 * 
	 */
    public SeasonAction() {
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
    protected ActionForward add(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
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
    protected ActionForward back(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
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
    public ActionForward create(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub

	final SeasonForm seasonForm = (SeasonForm) form;
	boolean isnew = true;
	final Season season = new Season();

	season.setActive(seasonForm.getActive());
	season.setName(seasonForm.getName());
	season.setComments(seasonForm.getComments());

	season.setFromDate(convertStringToDate(seasonForm.getFromDate()));
	season.setToDate(convertStringToDate(seasonForm.getToDate()));

	if (season.getFromDate().getTime() >= season.getToDate().getTime()) {

	    throw new ServiceException(
		    ValidationHelper
			    .getMessageHolder("etravel.season.fromAndTo.date.valid"));

	}

	if (seasonForm.getId() != null && seasonForm.getId() > 0) {
	    season.setId(seasonForm.getId());
	    Date fd = new Date();
	    Date td = new Date();
	    if (request.getSession().getAttribute("fromDate") != null) {
		fd = (Date) request.getSession().getAttribute("fromDate");
	    }
	    if (request.getSession().getAttribute("toDate") != null) {
		td = (Date) request.getSession().getAttribute("toDate");
	    }

	    if (season.getFromDate().getTime() != fd.getTime()) {

		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.season.fromDate.changed"));
	    }

	    if (season.getToDate().getTime() != td.getTime()) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.season.toDate.changed"));

	    }
	    isnew = false;
	}

	if (seasonForm.getHotelIds() != null
		&& seasonForm.getHotelIds().length > 0) {
	    final Hotel hotel = new Hotel();
	    for (int i = 0; i < seasonForm.getHotelIds().length; i++) {

		hotel.setId(seasonForm.getHotelIds()[i]);

	    }
	    season.setHotel(hotel);
	}

	getSeasonManager().save(season);
	if (isnew) {
	    addInfoMessages(COM_SAVE_MSG);
	} else {
	    addInfoMessages(COM_UPDATE_MSG);
	}
	return mapping.findForward(SUCCESS);
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
    protected ActionForward delete(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	final SeasonForm seasonForm = (SeasonForm) form;
	getSeasonManager().deleteSeason(seasonForm.getId());
	addInfoMessages(COM_DELETE_MSG);
	return mapping.findForward(SUCCESS);
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
    protected ActionForward edit(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	final SeasonForm seasonForm = (SeasonForm) form;
	final Season season = getSeasonManager().findSeasonById(
		seasonForm.getId());
	seasonForm.setName(season.getName());
	seasonForm.setComments(season.getComments());
	seasonForm.setActive(season.isActive());
	seasonForm.setFromDate(season.getFromDateString());
	seasonForm.setToDate(season.getToDateString());
	request.getSession().setAttribute("fromDate", season.getFromDate());
	request.getSession().setAttribute("toDate", season.getToDate());

	seasonForm.setAllSeason(getSeasonManager().findAllSeasonWithHotel());
	List<Hotel> allHotel = new ArrayList();
	request.getSession().setAttribute("hotelId", season.getHotel().getId());

	allHotel = getHotelManager().findAllActiveHotels();

	seasonForm.setAllHotel(allHotel);
	return mapping.findForward(SUCCESS);
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
    protected ActionForward find(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	return null;
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
    protected ActionForward forward(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	final SeasonForm seasonForm = (SeasonForm) form;

	seasonForm.setAllSeason(getSeasonManager().findAllSeasonWithHotel());
	List<Hotel> allHotel = new ArrayList();
	allHotel = getHotelManager().findAllActiveHotels();
	seasonForm.setAllHotel(allHotel);
	request.getSession().setAttribute("hotelId", "");

	return mapping.findForward(SUCCESS);
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
    protected ActionForward init(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	final SeasonForm seasonForm = (SeasonForm) form;
	seasonForm.reset(mapping, request);
	seasonForm.setAllSeason(getSeasonManager().findAllSeasonWithHotel());

	List<Hotel> allHotel = new ArrayList();

	allHotel = getHotelManager().findAllActiveHotels();

	getSeasonManager().findAllSeasonWithHotel();
	seasonForm.setAllSeason(getSeasonManager().findAllSeasonWithHotel());
	seasonForm.setAllHotel(allHotel);
	request.getSession().setAttribute("hotelId", "");

	return mapping.findForward(SUCCESS);
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
    public ActionForward process(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
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
    protected ActionForward save(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
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
    protected ActionForward send(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
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
    protected ActionForward sort(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    public Date convertStringToDate(final String dateVal) {
	return DateUtil.parse(dateVal);
    }

    @Override
    public ActionForward search(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
