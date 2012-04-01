package com.yd.etravel.web.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.PropertyMessageResources;

import com.yd.etravel.service.booking.IBookingManager;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.exception.ValidationException;
import com.yd.etravel.service.extraitem.IExtraItemManager;
import com.yd.etravel.service.hotel.IHotelManager;
import com.yd.etravel.service.message.UIMesssage.Message;
import com.yd.etravel.service.occupancy.IOccupancyManager;
import com.yd.etravel.service.pax.IPaxManager;
import com.yd.etravel.service.room.IRoomManager;
import com.yd.etravel.service.room.availability.IRoomAvailabilityManager;
import com.yd.etravel.service.roomtype.IRoomTypeManager;
import com.yd.etravel.service.search.ISearchManager;
import com.yd.etravel.service.season.ISeasonManager;
import com.yd.etravel.service.user.IUserManager;
import com.yd.etravel.service.util.IUserProfile;
import com.yd.etravel.util.IConstants.ICommon;
import com.yd.etravel.util.IConstants.IUser;
import com.yd.etravel.util.StringUtils;

public abstract class BaseAction extends Action implements
	com.yd.etravel.util.IConstants.IForwards,
	com.yd.etravel.util.IConstants.IMsg {

    protected static final Log LOG = LogFactory.getLog(BaseAction.class);

    // Spring Managers
    private IUserManager userManager;
    private ISeasonManager seasonManager;
    private IHotelManager hotelManager;
    private IRoomTypeManager roomTypeManager;
    private IOccupancyManager occupancyManager;
    private IPaxManager paxManager;
    private IRoomAvailabilityManager roomAvailabilityManager;
    private IRoomManager roomManager;
    private IExtraItemManager itemManager;
    private ISearchManager searchManager;
    private IBookingManager bookingManager;

    // Spring Managers End

    protected volatile ActionMessages actionMessages; // NOPMD by yohan on
						      // 1/28/12 5:28 PM
    protected volatile List<String> infoMessages; // NOPMD by yohan on 1/28/12
						  // 5:28 PM

    protected BaseAction() {
	this.actionMessages = new ActionMessages();
	this.infoMessages = new ArrayList<String>();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	ActionForward actionForward = null;
	BaseForm baseForm = (BaseForm) form;
	try {

	    this.actionMessages.clear();

	    if (request.getSession().getAttribute(IUser.USER_PROFILE) != null) {
		IUserProfile profile = (IUserProfile) request.getSession()
			.getAttribute(IUser.USER_PROFILE);
		Thread.currentThread().setName(profile.getUsername());
	    }

	    if (historyCheck(baseForm, request)) {

	    } else {
		LOG.info("<<HISTORY CHECK FAIL!!>>>");
	    }

	    final String param = mapping.getParameter();
	    WebMethod method = (param != null) ? WebMethod.valueOf(param)
		    : WebMethod.process;

	    switch (method) {

	    case init:
		actionForward = init(mapping, form, request, response);
		break;

	    case create:
		actionForward = create(mapping, form, request, response);
		break;

	    case search:
		actionForward = search(mapping, form, request, response);
		break;

	    case delete:
		actionForward = delete(mapping, form, request, response);
		break;

	    case edit:
		actionForward = edit(mapping, form, request, response);
		break;

	    case send:
		actionForward = send(mapping, form, request, response);
		break;

	    case back:
		actionForward = back(mapping, form, request, response);
		break;

	    case find:
		actionForward = find(mapping, form, request, response);
		break;

	    case forward:
		actionForward = forward(mapping, form, request, response);
		break;

	    case add:
		actionForward = add(mapping, form, request, response);
		break;

	    case sort:
		actionForward = sort(mapping, form, request, response);
		break;

	    case save:
		actionForward = save(mapping, form, request, response);
		break;

	    case process:
		actionForward = process(mapping, form, request, response);
		break;

	    default:
		actionForward = process(mapping, form, request, response);
		break;
	    }

	    saveMessages(request, this.actionMessages);
	    setHistoryToken(baseForm, request);

	} catch (ValidationException e) {

	    List<Message> msgList = new ArrayList<Message>();
	    msgList.addAll(e.getUIMessage().getInformations());
	    msgList.addAll(e.getUIMessage().getErrors());

	    actionForward = mapping.getInputForward();

	    if (actionForward == null
		    || StringUtils.isEmpty(actionForward.getPath())) {

		actionForward = init(mapping, form, request, response);
	    }

	    LOG.info(e.getMessage());

	} catch (ServiceException e) {

	    if (e.getUIMessage() == null) {

		LOG.fatal(e.getMessage(), e);
		return mapping.findForward(ERROR);

	    } else {

		List<Message> msgList = new ArrayList<Message>();
		msgList.addAll(e.getUIMessage().getInformations());
		msgList.addAll(e.getUIMessage().getErrors());
		actionForward = mapping.getInputForward();
		if (actionForward == null
			|| StringUtils.isEmpty(actionForward.getPath())) {

		    actionForward = init(mapping, form, request, response);
		}

		for (Message message : msgList) {
		    actionMessages.add(Globals.ERROR_KEY, new ActionMessage(
			    message.getKey()));
		}
		LOG.info(e.getMessage());
	    }

	} catch (Exception e) {
	    LOG.fatal(e.getMessage(), e);
	    return mapping.findForward(ERROR);

	} finally {

	    addErrors(request, actionMessages);
	    if (!this.infoMessages.isEmpty()) {
		request.getSession().setAttribute(ICommon.INFO_MSG_KEY,
			this.infoMessages);
	    }

	}
	return actionForward;
    }

    private boolean historyCheck(BaseForm baseForm, HttpServletRequest request) {
	long historyVal = request.getSession()
		.getAttribute(IUser.HISTORY_TOKEN) != null ? Long
		.valueOf(request.getSession().getAttribute(IUser.HISTORY_TOKEN)
			.toString()) : 0l;
	if (baseForm.getTjwToken() > 0 && baseForm.getTjwToken() != historyVal) {
	    return false;
	}

	return true;
    }

    protected boolean hasAccess(String function, HttpServletRequest request) {
	IUserProfile profile = getUserProfile(request);
	if (profile != null) {
	    return profile.hasFunction(function);
	}
	return false;
    }

    private void setHistoryToken(BaseForm baseForm, HttpServletRequest request) {
	long historyVal = System.nanoTime();
	request.getSession().setAttribute(IUser.HISTORY_TOKEN, historyVal);
	baseForm.setTjwToken(historyVal);

    }

    protected abstract ActionForward sort(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward add(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward forward(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward find(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward back(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward send(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward edit(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward delete(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward save(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected abstract ActionForward init(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    public abstract ActionForward process(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    public abstract ActionForward search(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    public abstract ActionForward create(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception;

    protected void addActionMessages(String key, ActionMessage actionMessage) {
	this.actionMessages.add(key, actionMessage);
    }

    protected void addInfoMessages(String msg) {
	this.infoMessages.add(PropertyMessageResources.getMessageResources(
		ICommon.MSG_RES).getMessage(msg));
    }

    public void setUserManager(IUserManager userManager) {
	this.userManager = userManager;
    }

    protected IUserManager getUserManager() {
	return userManager;
    }

    protected ISeasonManager getSeasonManager() {
	return seasonManager;
    }

    public void setSeasonManager(ISeasonManager seasonManager) {
	this.seasonManager = seasonManager;
    }

    protected IHotelManager getHotelManager() {
	return hotelManager;
    }

    public void setHotelManager(IHotelManager hotelManager) {
	this.hotelManager = hotelManager;
    }

    /**
     * @return the roomTypeManager
     */

    public IRoomTypeManager getRoomTypeManager() {
	return roomTypeManager;
    }

    /**
     * @param roomTypeManager
     *            the roomTypeManager to set
     */

    public void setRoomTypeManager(IRoomTypeManager roomTypeManager) {
	this.roomTypeManager = roomTypeManager;
    }

    /**
     * @return the occupancyManager
     */

    public IOccupancyManager getOccupancyManager() {
	return occupancyManager;
    }

    /**
     * @param occupancyManager
     *            the occupancyManager to set
     */

    public void setOccupancyManager(IOccupancyManager occupancyManager) {
	this.occupancyManager = occupancyManager;
    }

    public IPaxManager getPaxManager() {
	return paxManager;
    }

    public void setPaxManager(IPaxManager paxManager) {
	this.paxManager = paxManager;
    }

    /**
     * @return the roomAvailabilityManager
     */
    public IRoomAvailabilityManager getRoomAvailabilityManager() {
	return roomAvailabilityManager;
    }

    /**
     * @param roomAvailabilityManager
     *            the roomAvailabilityManager to set
     */
    public void setRoomAvailabilityManager(
	    IRoomAvailabilityManager roomAvailabilityManager) {
	this.roomAvailabilityManager = roomAvailabilityManager;
    }

    public IRoomManager getRoomManager() {
	return roomManager;
    }

    public void setRoomManager(IRoomManager roomManager) {
	this.roomManager = roomManager;
    }

    /**
     * @return the searchManager
     */
    public ISearchManager getSearchManager() {
	return searchManager;
    }

    /**
     * @param searchManager
     *            the searchManager to set
     */
    public void setSearchManager(ISearchManager searchManager) {
	this.searchManager = searchManager;
    }

    public IExtraItemManager getItemManager() {
	return itemManager;
    }

    public void setItemManager(IExtraItemManager itemManager) {
	this.itemManager = itemManager;
    }

    /**
     * @return the bookingManager
     */
    public IBookingManager getBookingManager() {
	return bookingManager;
    }

    /**
     * @param bookingManager
     *            the bookingManager to set
     */
    public void setBookingManager(IBookingManager bookingManager) {
	this.bookingManager = bookingManager;
    }

    protected IUserProfile getUserProfile(HttpServletRequest request) {
	return (IUserProfile) request.getSession().getAttribute(
		IUser.USER_PROFILE);
    }

    public enum WebMethod {
	process, init, create, save, search, delete, edit, send, back, find, forward, add, sort, OnChange, roomOnChange, hotelOnChange;
    }

}
