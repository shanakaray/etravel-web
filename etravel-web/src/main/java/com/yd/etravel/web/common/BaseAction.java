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
import org.apache.struts.util.MessageResources;

import com.yd.etravel.service.booking.IBookingManager;
import com.yd.etravel.service.cmt.content.IContentManager;
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
    private IContentManager contentManager;

    // Spring Managers End

    protected volatile ActionMessages actionMessages; // NOPMD by yohan on
						      // 1/28/12 5:28 PM
    protected volatile List<String> infoMessages; // NOPMD by yohan on 1/28/12
						  // 5:28 PM

    protected BaseAction() {
	this.actionMessages = new ActionMessages();
	this.infoMessages = new ArrayList<String>();
    }

    @Override
    public ActionForward execute(final ActionMapping mapping,
	    final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {
	ActionForward actionForward = null;
	final BaseForm baseForm = (BaseForm) form;
	try {

	    this.actionMessages.clear();

	    if (request.getSession().getAttribute(IUser.USER_PROFILE) != null) {
		final IUserProfile profile = (IUserProfile) request
			.getSession().getAttribute(IUser.USER_PROFILE);
		Thread.currentThread().setName(profile.getUsername());
	    }

	    if (historyCheck(baseForm, request)) {

	    } else {
		LOG.info("<<HISTORY CHECK FAIL!!>>>");
	    }

	    final String param = mapping.getParameter();
	    final WebMethod method = param != null ? WebMethod.valueOf(param)
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

	} catch (final ValidationException e) {

	    final List<Message> msgList = new ArrayList<Message>();
	    msgList.addAll(e.getUIMessage().getInformations());
	    msgList.addAll(e.getUIMessage().getErrors());

	    actionForward = mapping.getInputForward();

	    if (actionForward == null
		    || StringUtils.isEmpty(actionForward.getPath())) {

		actionForward = init(mapping, form, request, response);
	    }

	    LOG.info(e.getMessage());

	} catch (final ServiceException e) {

	    if (e.getUIMessage() == null) {

		LOG.fatal(e.getMessage(), e);
		return mapping.findForward(ERROR);

	    } else {

		final List<Message> msgList = new ArrayList<Message>();
		msgList.addAll(e.getUIMessage().getInformations());
		msgList.addAll(e.getUIMessage().getErrors());
		actionForward = mapping.getInputForward();
		if (actionForward == null
			|| StringUtils.isEmpty(actionForward.getPath())) {

		    actionForward = init(mapping, form, request, response);
		}

		for (final Message message : msgList) {
		    this.actionMessages.add(Globals.ERROR_KEY,
			    new ActionMessage(message.getKey()));
		}
		LOG.info(e.getMessage());
	    }

	} catch (final Exception e) {
	    LOG.fatal(e.getMessage(), e);
	    return mapping.findForward(ERROR);

	} finally {

	    addErrors(request, this.actionMessages);
	    if (!this.infoMessages.isEmpty()) {
		request.getSession().setAttribute(ICommon.INFO_MSG_KEY,
			this.infoMessages);
	    }

	}
	return actionForward;
    }

    private boolean historyCheck(final BaseForm baseForm,
	    final HttpServletRequest request) {
	final long historyVal = request.getSession().getAttribute(
		IUser.HISTORY_TOKEN) != null ? Long.valueOf(request
		.getSession().getAttribute(IUser.HISTORY_TOKEN).toString())
		: 0l;
	if (baseForm.getTjwToken() > 0 && baseForm.getTjwToken() != historyVal) {
	    return false;
	}

	return true;
    }

    protected boolean hasAccess(final String function,
	    final HttpServletRequest request) {
	final IUserProfile profile = getUserProfile(request);
	if (profile != null) {
	    return profile.hasFunction(function);
	}
	return false;
    }

    private void setHistoryToken(final BaseForm baseForm,
	    final HttpServletRequest request) {
	final long historyVal = System.nanoTime();
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

    protected void addActionMessages(final String key,
	    final ActionMessage actionMessage) {
	this.actionMessages.add(key, actionMessage);
    }

    protected void addInfoMessages(final String msg) {
	this.infoMessages.add(MessageResources.getMessageResources(
		ICommon.MSG_RES).getMessage(msg));
    }

    public void setUserManager(final IUserManager userManager) {
	this.userManager = userManager;
    }

    protected IUserManager getUserManager() {
	return this.userManager;
    }

    protected ISeasonManager getSeasonManager() {
	return this.seasonManager;
    }

    public void setSeasonManager(final ISeasonManager seasonManager) {
	this.seasonManager = seasonManager;
    }

    protected IHotelManager getHotelManager() {
	return this.hotelManager;
    }

    public void setHotelManager(final IHotelManager hotelManager) {
	this.hotelManager = hotelManager;
    }

    /**
     * @return the roomTypeManager
     */

    public IRoomTypeManager getRoomTypeManager() {
	return this.roomTypeManager;
    }

    /**
     * @param roomTypeManager
     *            the roomTypeManager to set
     */

    public void setRoomTypeManager(final IRoomTypeManager roomTypeManager) {
	this.roomTypeManager = roomTypeManager;
    }

    /**
     * @return the occupancyManager
     */

    public IOccupancyManager getOccupancyManager() {
	return this.occupancyManager;
    }

    /**
     * @param occupancyManager
     *            the occupancyManager to set
     */

    public void setOccupancyManager(final IOccupancyManager occupancyManager) {
	this.occupancyManager = occupancyManager;
    }

    public IPaxManager getPaxManager() {
	return this.paxManager;
    }

    public void setPaxManager(final IPaxManager paxManager) {
	this.paxManager = paxManager;
    }

    /**
     * @return the roomAvailabilityManager
     */
    public IRoomAvailabilityManager getRoomAvailabilityManager() {
	return this.roomAvailabilityManager;
    }

    /**
     * @param roomAvailabilityManager
     *            the roomAvailabilityManager to set
     */
    public void setRoomAvailabilityManager(
	    final IRoomAvailabilityManager roomAvailabilityManager) {
	this.roomAvailabilityManager = roomAvailabilityManager;
    }

    public IRoomManager getRoomManager() {
	return this.roomManager;
    }

    public void setRoomManager(final IRoomManager roomManager) {
	this.roomManager = roomManager;
    }

    /**
     * @return the searchManager
     */
    public ISearchManager getSearchManager() {
	return this.searchManager;
    }

    /**
     * @param searchManager
     *            the searchManager to set
     */
    public void setSearchManager(final ISearchManager searchManager) {
	this.searchManager = searchManager;
    }

    public IExtraItemManager getItemManager() {
	return this.itemManager;
    }

    public void setItemManager(final IExtraItemManager itemManager) {
	this.itemManager = itemManager;
    }

    /**
     * @return the bookingManager
     */
    public IBookingManager getBookingManager() {
	return this.bookingManager;
    }

    /**
     * @param bookingManager
     *            the bookingManager to set
     */
    public void setBookingManager(final IBookingManager bookingManager) {
	this.bookingManager = bookingManager;
    }

    public IContentManager getContentManager() {
	return this.contentManager;
    }

    public void setContentManager(final IContentManager contentManager) {
	this.contentManager = contentManager;
    }

    protected IUserProfile getUserProfile(final HttpServletRequest request) {
	return (IUserProfile) request.getSession().getAttribute(
		IUser.USER_PROFILE);
    }

    public enum WebMethod {
	process, init, create, save, search, delete, edit, send, back, find, forward, add, sort, OnChange, roomOnChange, hotelOnChange;
    }

}
