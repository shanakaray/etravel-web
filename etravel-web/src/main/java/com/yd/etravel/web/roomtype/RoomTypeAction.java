/**
 * 
 */
package com.yd.etravel.web.roomtype;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author Dharshana
 * 
 */
public class RoomTypeAction extends BaseAction {

    /**
	 * 
	 */
    public RoomTypeAction() {
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
    protected ActionForward add(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
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
    protected ActionForward back(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
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
    public ActionForward create(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
	    throws Exception {

	final RoomTypeForm roomTypeForm = (RoomTypeForm) form;
	boolean isnew = true;
	final RoomType roomType = new RoomType();

	if (roomTypeForm.getId() != null && roomTypeForm.getId().longValue() > 0) {
	    roomType.setId(roomTypeForm.getId());
	} else {
	    isnew = false;
	    roomType.setId(null);
	}
	roomType.setActive(roomTypeForm.getActive());
	roomType.setName(roomTypeForm.getName());
	roomType.setComments(roomTypeForm.getComments());
	roomType.setMaxPassengers(roomTypeForm.getMaxPassengers());
	getRoomTypeManager().save(roomType);
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
    protected ActionForward delete(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
	    throws Exception {
	final RoomTypeForm roomTypeForm = (RoomTypeForm) form;
	getRoomTypeManager().deleteRoomType(roomTypeForm.getId());
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
    protected ActionForward edit(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
	    throws Exception {

	final RoomTypeForm roomTypeForm = (RoomTypeForm) form;
	final RoomType roomType = getRoomTypeManager().findRoomTypeById(
		roomTypeForm.getId());
	roomTypeForm.setName(roomType.getName());
	roomTypeForm.setComments(roomType.getComments());
	roomTypeForm.setActive(roomType.isActive());
	roomTypeForm.setId(roomType.getId());
	final ArrayList<RoomType> roomTypelist = (ArrayList<RoomType>) getRoomTypeManager()
		.findAllRoomType();
	roomTypeForm.setAllRoomType(roomTypelist);
	roomTypeForm.setMaxPassengers(roomType.getMaxPassengers());
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
    protected ActionForward find(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
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
    protected ActionForward forward(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
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
    protected ActionForward init(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
	    throws Exception {
	final RoomTypeForm roomTypeForm = (RoomTypeForm) form;
	final ArrayList<RoomType> roomTypelist = (ArrayList<RoomType>) getRoomTypeManager()
		.findAllRoomType();
	roomTypeForm.setAllRoomType(roomTypelist);

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
    public ActionForward process(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
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
    protected ActionForward save(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
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
    protected ActionForward send(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
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
    protected ActionForward sort(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ActionForward search(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
