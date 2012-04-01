/**
 * 
 */
package com.yd.etravel.web.pax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.pax.PaxSearchDTO;
import com.yd.etravel.domain.hotel.Pax;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseAction;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Jan 29, 2009 : 8:12:45 AM Type :
 *         com.yd.etravel.web.pax.PaxAction
 * 
 */
public class PaxAction extends BaseAction {

    /**
	 * 
	 */
    public PaxAction() {
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
	final PaxFrom paxFrom = (PaxFrom) form;
	paxFrom.setHotelList(getHotelManager().findAllActiveHotels());

	Pax pax = new Pax();
	pax.setId(StringUtils.isEmpty(paxFrom.getId()) ? null : paxFrom.getId());

	pax.setInfantMin(paxFrom.getInfantMin());
	pax.setInfantMax(paxFrom.getInfantMax());

	pax.setChildMin(paxFrom.getChildMin());
	pax.setChildMax(paxFrom.getChildMax());

	pax.setAdultMin(paxFrom.getAdultMin());
	pax.setAdultMax(paxFrom.getAdultMax());

	pax.setHotel(paxFrom.getHotel(paxFrom.getHotelId()));
	pax.setActive(true);
	getPaxManager().savePax(pax);

	PaxSearchDTO searchDTO = new PaxSearchDTO();
	searchDTO.setHotelId(StringUtils.isEmpty(paxFrom.getHotelId()) ? null
		: paxFrom.getHotelId());
	paxFrom.setPaxList(getPaxManager().findPax(searchDTO));

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
	final PaxFrom paxFrom = (PaxFrom) form;
	getPaxManager().deletePax(paxFrom.getId());
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
	final PaxFrom paxFrom = (PaxFrom) form;
	Pax pax = getPaxManager().findPaxById(paxFrom.getId());

	paxFrom.setInfantMin(pax.getInfantMin());
	paxFrom.setInfantMax(pax.getInfantMax());

	paxFrom.setChildMin(pax.getChildMin());
	paxFrom.setChildMax(pax.getChildMax());

	paxFrom.setAdultMin(pax.getAdultMin());
	paxFrom.setAdultMax(pax.getAdultMax());

	paxFrom.setHotelId(pax.getHotel().getId());

	paxFrom.setHotelList(getHotelManager().findAllActiveHotels());

	PaxSearchDTO searchDTO = new PaxSearchDTO();
	searchDTO.setHotelId(StringUtils.isEmpty(paxFrom.getHotelId()) ? null
		: paxFrom.getHotelId());
	paxFrom.setPaxList(getPaxManager().findPax(searchDTO));

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
	final PaxFrom paxFrom = (PaxFrom) form;

	PaxSearchDTO searchDTO = new PaxSearchDTO();
	searchDTO.setHotelId(StringUtils.isEmpty(paxFrom.getHotelId()) ? null
		: paxFrom.getHotelId());
	paxFrom.setPaxList(getPaxManager().findPax(searchDTO));

	searchDTO.setHotelId(StringUtils.isEmpty(paxFrom.getHotelId()) ? null
		: paxFrom.getHotelId());

	paxFrom.setHotelList(getHotelManager().findAllActiveHotels());

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
    protected ActionForward init(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	final PaxFrom paxFrom = (PaxFrom) form;
	PaxSearchDTO searchDTO = new PaxSearchDTO();
	searchDTO.setHotelId(StringUtils.isEmpty(paxFrom.getHotelId()) ? null
		: paxFrom.getHotelId());
	paxFrom.setPaxList(getPaxManager().findPax(searchDTO));

	paxFrom.setHotelList(getHotelManager().findAllActiveHotels());
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

	final PaxFrom paxFrom = (PaxFrom) form;
	PaxSearchDTO searchDTO = new PaxSearchDTO();
	searchDTO.setHotelId(StringUtils.isEmpty(paxFrom.getHotelId()) ? null
		: paxFrom.getHotelId());
	paxFrom.setPaxList(getPaxManager().findPax(searchDTO));

	paxFrom.setHotelList(getHotelManager().findAllActiveHotels());

	return mapping.findForward(SUCCESS);
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

	return mapping.findForward(SUCCESS);
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