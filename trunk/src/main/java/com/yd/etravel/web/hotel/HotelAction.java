/**
 * 
 */
package com.yd.etravel.web.hotel;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.util.IConstants.IUserRoles;
import com.yd.etravel.web.common.BaseAction;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 1, 2009 : 1:52:26 PM Type :
 *         com.yd.etravel.web.hotel.HotelAction
 * 
 */
public class HotelAction extends BaseAction {

    final static Set<Long> ADMIN_ROLES = new HashSet<Long>();

    /**
	 * 
	 */
    public HotelAction() {
	ADMIN_ROLES.add(IUserRoles.HOTEL_ADMIN_ROLE_ID);
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
	final HotelForm hotelForm = (HotelForm) form;
	boolean isnew = false;
	Hotel hotel = new Hotel();

	if (hotelForm.getId() != null && hotelForm.getId().longValue() > 0)
	    hotel.setId(hotelForm.getId());
	else {
	    hotel.setId(null);
	    isnew = true;
	}
	hotel.setName(hotelForm.getName());
	hotel.setAddress(hotelForm.getAddress());
	hotel.setEmail(hotelForm.getEmail());
	hotel.setCity(hotelForm.getCity().toLowerCase());
	hotel.setCountry(hotelForm.getCountry().toLowerCase());
	hotel.setRating(hotelForm.getRating());
	hotel.setContact(hotelForm.getContact());
	hotel.setActive(hotelForm.isActive());
	getHotelManager().saveHotel(hotel, hotelForm.getSuperUserId());
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
    protected ActionForward delete(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	final HotelForm hotelForm = (HotelForm) form;
	getHotelManager().deleteHotel(hotelForm.getId());
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
    protected ActionForward edit(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	final HotelForm hotelForm = (HotelForm) form;

	Hotel hotel = getHotelManager().findHotelById(hotelForm.getId());
	hotelForm.setId(hotel.getId());
	hotelForm.setName(hotel.getName());
	hotelForm.setAddress(hotel.getAddress());
	hotelForm.setContact(hotel.getContact());
	hotelForm.setEmail(hotel.getEmail());
	hotelForm.setActive(hotel.isActive());
	hotelForm.setSuperUserId(hotel.getUserIds());
	hotelForm.setRating(hotel.getRating());
	hotelForm.setCity(hotel.getCity());
	hotelForm.setCountry(hotel.getCountry());
	UserSearchDTO dto = new UserSearchDTO();
	dto.setRoleIds(ADMIN_ROLES);
	dto.setActive(Boolean.TRUE);
	hotelForm.setAdminList(getUserManager().findUsers(dto));
	hotelForm.setHotelList(getHotelManager().findAllHotel());
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
    protected ActionForward find(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
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
    protected ActionForward forward(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	return init(mapping, form, request, response);
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
	final HotelForm hotelForm = (HotelForm) form;
	UserSearchDTO dto = new UserSearchDTO();
	dto.setRoleIds(ADMIN_ROLES);
	dto.setActive(Boolean.TRUE);
	hotelForm.setAdminList(getUserManager().findUsers(dto));
	hotelForm.setHotelList(getHotelManager().findAllHotel());
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
    protected ActionForward sort(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
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
