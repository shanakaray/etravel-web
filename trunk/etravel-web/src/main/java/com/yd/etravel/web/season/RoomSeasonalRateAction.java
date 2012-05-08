/**
 * 
 */
package com.yd.etravel.web.season;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author Dharshana
 * 
 */
public class RoomSeasonalRateAction extends BaseAction {

    /**
	 * 
	 */
    public RoomSeasonalRateAction() {
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
	// TODO Auto-generated method stub

	final RoomSeasonalRateForm roomSeasonalRateForm = (RoomSeasonalRateForm) form;
	boolean isnew = true;
	final RoomSeasonalRate roomRate = new RoomSeasonalRate();

	roomRate.setActive(roomSeasonalRateForm.getActive());
	// roomRate.setAdultCost(roomSeasonalRateForm.getAdultCost());
	// roomRate.setChildCost(roomSeasonalRateForm.getChildCost());
	// roomRate.setInfantCost(roomSeasonalRateForm.getInfantCost());
	roomRate.setTotalCost(roomSeasonalRateForm.getTotalCost());

	if (roomSeasonalRateForm.getId() != null
		&& roomSeasonalRateForm.getId() > 0) {
	    roomRate.setId(roomSeasonalRateForm.getId());
	    isnew = false;
	}

	final Season season = new Season();
	season.setId(roomSeasonalRateForm.getSeasonId());
	roomRate.setSeason(season);

	final Room room = new Room();
	room.setId(roomSeasonalRateForm.getRoomId());

	roomRate.setRoom(room);

	getSeasonManager().save(roomRate);
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
	final RoomSeasonalRateForm roomSeasonalRateForm = (RoomSeasonalRateForm) form;
	getSeasonManager().deleteRoomSeasonalRate(roomSeasonalRateForm.getId());
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
    protected ActionForward edit(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	final RoomSeasonalRateForm roomForm = (RoomSeasonalRateForm) form;
	final RoomSeasonalRate roomSeasonalRate = getSeasonManager()
		.findRoomSeasonalRateById(roomForm.getId());

	roomForm.setActive(roomSeasonalRate.isActive());
	// roomForm.setAdultCost(roomSeasonalRate.getAdultCost());
	// roomForm.setChildCost(roomSeasonalRate.getChildCost());
	// roomForm.setInfantCost(roomSeasonalRate.getInfantCost());
	roomForm.setTotalCost(roomSeasonalRate.getTotalCost());
	roomForm.setId(roomSeasonalRate.getId());
	roomForm.setHotelId(roomSeasonalRate.getRoom().getHotel().getId());
	roomForm.setSeasonId(roomSeasonalRate.getSeason().getId());
	roomForm.setRoomId(roomSeasonalRate.getRoom().getId());

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
	final RoomSeasonalRateForm roomForm = (RoomSeasonalRateForm) form;

	roomForm.setHotelList(getHotelManager().findAllActiveHotels());

	roomForm.setAllRoomSeasonalRate(getSeasonManager()
		.findAllRoomSeasonalRateWithRoomAndSeason(
			roomForm.getHotelId() != null
				&& roomForm.getHotelId().longValue() > 0 ? roomForm
				.getHotelId() : null));

	if (roomForm.getHotelId() != null
		&& roomForm.getHotelId().longValue() > 0) {
	    final List<Room> rooms = getRoomManager().findAllRoomWithRoomType(
		    roomForm.getHotelId());
	    roomForm.setAllRoom(rooms);

	    final List<Season> allSeason = getSeasonManager().findSeasonByHotel(
		    roomForm.getHotelId() != null
			    && roomForm.getHotelId().longValue() > 0 ? roomForm
			    .getHotelId() : null);
	    roomForm.setAllSeason(allSeason);

	}

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
	return init(mapping, form, request, response);
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
