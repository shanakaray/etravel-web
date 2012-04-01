/**
 * 
 */
package com.yd.etravel.web.roomavailability;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.room.availability.DailyAvailabilityDTO;
import com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.room.availability.RoomAvailability;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;
import com.yd.etravel.util.DateUtil;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author Dharshana
 * 
 */
public class RoomAvailabilityAction extends BaseAction {

    public static final String ROOM_ONCHANGE = "roomOnChange";
    public static final String HOTEL_ONCHANGE = "hotelOnChange";

    /**
	 * 
	 */
    public RoomAvailabilityAction() {
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
	boolean isnew = true;

	final RoomAvailabilityForm roomAvailabilityForm = (RoomAvailabilityForm) form;
	final RoomAvailability roomAvailability = new RoomAvailability();
	roomAvailability.setActive(roomAvailabilityForm.getActive());
	roomAvailability.setUnit(roomAvailabilityForm.getUnit());
	roomAvailability.setFromDate(DateUtil.parse(roomAvailabilityForm
		.getFromDate()));
	roomAvailability.setToDate(DateUtil.parse(roomAvailabilityForm
		.getToDate()));

	if (roomAvailability.getFromDate().getTime() >= roomAvailability
		.getToDate().getTime()) {

	    throw new ServiceException(
		    ValidationHelper
			    .getMessageHolder("etravel.roomAvailability.fromAndTo.date.valid"));

	}

	if (roomAvailabilityForm.getId() != null
		&& roomAvailabilityForm.getId() > 0) {
	    roomAvailability.setId(roomAvailabilityForm.getId());
	    isnew = false;
	    Date fd = new Date();
	    Date td = new Date();
	    if (request.getSession().getAttribute("fromDate") != null) {
		fd = (Date) request.getSession().getAttribute("fromDate");
	    }
	    if (request.getSession().getAttribute("toDate") != null) {
		td = (Date) request.getSession().getAttribute("toDate");
	    }

	    if (roomAvailability.getFromDate().getTime() != fd.getTime()) {

		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.roomAvailability.fromDate.changed"));
	    }

	    if (roomAvailability.getToDate().getTime() != td.getTime()) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.roomAvailability.toDate.changed"));

	    }
	}

	if (roomAvailabilityForm.getRoomId() != null
		&& roomAvailabilityForm.getRoomId().longValue() > 0) {
	    Room room = new Room();
	    room.setId(roomAvailabilityForm.getRoomId());
	    roomAvailability.setRoom(room);
	}

	// Occupancy occupancy=new Occupancy();
	//
	// if (roomAvailabilityForm.getOccupancyId() != null
	// && roomAvailabilityForm.getOccupancyId().longValue() > 0) {
	//
	//
	// occupancy.setId(roomAvailabilityForm.getOccupancyId());
	// roomAvailability.setOccupancy(occupancy);
	// }
	//
	// occupancy.setId(new Long(1));
	// roomAvailability.setOccupancy(occupancy);
	getRoomAvailabilityManager().save(roomAvailability);
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
	RoomAvailabilityForm roomAvailabilityForm = (RoomAvailabilityForm) form;
	getRoomAvailabilityManager().deleteRoomAvailability(
		roomAvailabilityForm.getId());
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

	RoomAvailabilityForm roomAvaForm = (RoomAvailabilityForm) form;
	RoomAvailability ra = getRoomAvailabilityManager()
		.findRoomAvailabilityWithRoomInfoById(roomAvaForm.getId());
	roomAvaForm.setUnit(ra.getUnit());
	roomAvaForm.setActive(ra.isActive());
	roomAvaForm.setFromDate(ra.getFromDateString());
	roomAvaForm.setToDate(ra.getToDateString());
	RoomAvailabilityDTO dto = new RoomAvailabilityDTO();
	dto.setHotelId(roomAvaForm.getHotelId() == null
		|| roomAvaForm.getHotelId().longValue() <= 0 ? null
		: roomAvaForm.getHotelId());
	roomAvaForm.setAllRoomAvailability(getRoomAvailabilityManager()
		.findAllRoomAvailabilityDTO(dto));
	roomAvaForm.setRoomType(ra.getRoom().getRoomType().getName());
	request.getSession().setAttribute("fromDate", ra.getFromDate());
	request.getSession().setAttribute("toDate", ra.getToDate());

	roomAvaForm.setHotelId(ra.getRoom().getHotel().getId());
	roomAvaForm.setRoomId(ra.getRoom().getId());
	// roomAvaForm.setOccupancyId(ra.getOccupancy().getId());
	roomAvaForm.setEdit(true);

	init(mapping, form, request, response);

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
	final RoomAvailabilityForm roomAvailabilityForm = (RoomAvailabilityForm) form;
	List<DailyAvailabilityDTO> allRoomDailyAvailability = getRoomAvailabilityManager()
		.findDailyAvailability(roomAvailabilityForm.getId());
	request.getSession().setAttribute("allRoomDailyAvailability",
		allRoomDailyAvailability);
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
	final RoomAvailabilityForm roomAvaForm = (RoomAvailabilityForm) form;

	roomAvaForm.setHotelList(getHotelManager().findAllActiveHotels());
	RoomAvailabilityDTO dto = new RoomAvailabilityDTO();
	dto.setHotelId(roomAvaForm.getHotelId() == null
		|| roomAvaForm.getHotelId().longValue() <= 0 ? null
		: roomAvaForm.getHotelId());
	roomAvaForm.setAllRoomAvailability(getRoomAvailabilityManager()
		.findAllRoomAvailabilityDTO(dto));

	if (roomAvaForm.getHotelId() != null
		&& roomAvaForm.getHotelId().longValue() > 0) {

	    List<Room> roomList = getRoomManager().findAllRoomWithRoomType(
		    roomAvaForm.getHotelId());
	    roomAvaForm.setAllRoom(roomList);

	    if (roomAvaForm.getRoomId() != null
		    && roomAvaForm.getRoomId().longValue() > 0) {
		for (Room room : roomList) {
		    if (room.getId().longValue() == roomAvaForm.getRoomId()
			    .longValue()) {
			roomAvaForm.setRoomType(room.getRoomType().getName());
			roomAvaForm.setRoomTypeId(room.getRoomType().getId());
			break;
		    }
		}
	    }

	}

	// if (roomAvaForm.getRoomTypeId()!=null &&
	// roomAvaForm.getRoomTypeId().longValue() > 0){
	// roomAvaForm.setAllOccupancy(getOccupancyManager().findAllOccupancyByRoomType(roomAvaForm.getRoomTypeId()));
	// }

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
	final String param = mapping.getParameter();

	if (param.equals(ROOM_ONCHANGE)) {
	    return roomOnChange(mapping, form, request, response);
	} else if (param.equals(HOTEL_ONCHANGE)) {
	    return hotelOnChange(mapping, form, request, response);
	}

	return mapping.findForward(ERROR);
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

    protected ActionForward roomOnChange(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	init(mapping, form, request, response);
	return mapping.findForward(SUCCESS);
    }

    protected ActionForward hotelOnChange(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	init(mapping, form, request, response);
	return mapping.findForward(SUCCESS);
    }

    @Override
    public ActionForward search(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
