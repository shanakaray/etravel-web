/**
 * 
 */
package com.yd.etravel.web.occupancy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.occupancy.Occupancy;
import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author Dharshana
 * 
 */
public class OccupancyAction extends BaseAction {

	/**
	 * 
	 */
	public OccupancyAction() {
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

		final OccupancyForm occupancyForm = (OccupancyForm) form;
		final Occupancy occupancy = new Occupancy();
		boolean isnew = true;

		occupancy.setComments(occupancyForm.getComments());
		occupancy.setActive(occupancyForm.getActive());
		occupancy.setName(occupancyForm.getName());
		occupancy.setAdult(occupancyForm.getAdult());
		occupancy.setChild(occupancyForm.getAdult());
		occupancy.setInfant(occupancyForm.getAdult());
		occupancy.setTotalPax(occupancyForm.getAdult()
				+ occupancyForm.getAdult() + occupancyForm.getAdult());

		if ((occupancyForm.getId() != null) && (occupancyForm.getId() > 0)) {
			occupancy.setId(occupancyForm.getId());
			isnew = false;
		}

		if ((occupancyForm.getRoomTypeids() != null)
				&& (occupancyForm.getRoomTypeids().length > 0)) {
			final RoomType roomType = new RoomType();
			for (int i = 0; i < occupancyForm.getRoomTypeids().length; i++) {

				roomType.setId(occupancyForm.getRoomTypeids()[i]);

			}
			occupancy.setRoomType(roomType);
		}
		getOccupancyManager().save(occupancy);

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
		final OccupancyForm occupancyForm = (OccupancyForm) form;
		getOccupancyManager().deleteOccupancy(occupancyForm.getId());
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
		final OccupancyForm occupancyForm = (OccupancyForm) form;
		final Occupancy occupancy = getOccupancyManager().findOccupancyById(
				occupancyForm.getId());
		occupancyForm.setName(occupancy.getName());
		occupancyForm.setComments(occupancy.getComments());
		occupancyForm.setActive(occupancy.isActive());
		occupancyForm.setAdult(occupancy.getAdult());
		occupancyForm.setChild(occupancy.getChild());
		occupancyForm.setInfant(occupancy.getInfant());

		occupancyForm.setAllOccupancy(getOccupancyManager()
				.findAllOccupancyWithRoomType());
		List<RoomType> allRoomType = new ArrayList<RoomType>();
		request.getSession().setAttribute("roomTypeId",
				occupancy.getRoomType().getId());

		allRoomType = getRoomTypeManager().findAllActiveRoomType();

		occupancyForm.setAllRoomType(allRoomType);
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
		// TODO Auto-generated method stub
		final OccupancyForm occupancyForm = (OccupancyForm) form;
		occupancyForm.setAllOccupancy(getOccupancyManager()
				.findAllOccupancyWithRoomType());
		occupancyForm.setAllRoomType(getRoomTypeManager()
				.findAllActiveRoomType());
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
		final OccupancyForm occupancyForm = (OccupancyForm) form;
		occupancyForm.reset(mapping, request);
		occupancyForm.setAllOccupancy(getOccupancyManager()
				.findAllOccupancyWithRoomType());
		occupancyForm.setAllRoomType(getRoomTypeManager()
				.findAllActiveRoomType());
		request.getSession().setAttribute("roomTypeId", "");
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

	@Override
	public ActionForward search(final ActionMapping mapping,
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

}
