/**
 * 
 */
package com.yd.etravel.web.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.search.ExtraItemDTO;
import com.yd.etravel.domain.custom.search.RoomDTO;
import com.yd.etravel.domain.custom.search.SearchRequestDTO;
import com.yd.etravel.domain.custom.search.SearchResultsDTO;
import com.yd.etravel.domain.extraitem.ExtraItem;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;
import com.yd.etravel.util.DateUtil;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author Dharshana
 * 
 */
public class SearchAction extends BaseAction {

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
	final SearchForm searchForm = (SearchForm) form;
	List<Hotel> allHotel = getHotelManager().findAllActiveHotels();
	searchForm.setAllHotel(allHotel);
	request.getSession().setAttribute("hotelId", "");

	return mapping.findForward(SUCCESS);
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
    protected ActionForward find(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	final SearchForm searchForm = (SearchForm) form;

	SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
	SearchRequestDTO searchRequestDTO = new SearchRequestDTO();

	searchRequestDTO.setCheckIn(DateUtil.parse(searchForm.getCheckIn()));
	searchRequestDTO.setCheckOut(DateUtil.parse(searchForm.getCheckOut()));
	searchRequestDTO.setRoomTypeId(searchForm.getRoomId() != null
		&& searchForm.getRoomId().longValue() > 0 ? searchForm
		.getRoomId() : null);
	if (searchForm.getHotelId() != null) {
	    searchRequestDTO.setHotelId(searchForm.getHotelId());
	}

	searchResultsDTO = getSearchManager().searchRoom(searchRequestDTO);


	
	if (searchResultsDTO.getRoomDTO() == null
		|| searchResultsDTO.getRoomDTO().isEmpty()) {
	    throw new ServiceException(
		    ValidationHelper
			    .getMessageHolder("etravel.search.noroom.available"));

	}

	searchResultsDTO = getSearchManager().searchRoom(searchRequestDTO);

	List<ExtraItemDTO> extraItemDTOList = new ArrayList<ExtraItemDTO>();
	List<ExtraItem> eiList = getItemManager().findExtraItemsByHotel(
		searchForm.getHotelId());

	for (ExtraItem extraItem : eiList) {
	    ExtraItemDTO eid = new ExtraItemDTO();

	    eid.setExtraItem(extraItem);
	    extraItemDTOList.add(eid);
	}
	searchResultsDTO.setExtraItemDTO(extraItemDTOList);
	searchForm.setSearchResultsDTO(searchResultsDTO);

	request.getSession().setAttribute("searchResultsDTO", searchResultsDTO);
	request.getSession().setAttribute("searchRequestDTO", searchRequestDTO);

	List<Hotel> allHotel = new ArrayList<Hotel>();

	allHotel = getHotelManager().findAllActiveHotels();// This can be get
	// from session also
	searchForm.setAllHotel(allHotel);
	request.getSession().setAttribute("hotelId", "");
	init(mapping, form, request, response);
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
	final SearchForm searchForm = (SearchForm) form;

	if (searchForm.getId() == null || searchForm.getId().longValue() < 1) {
	    throw new ServiceException(
		    ValidationHelper
			    .getMessageHolder("etravel.booking.noroom.selected"));

	}

	// SearchRequestDTO searchRequestDTO =(SearchRequestDTO)
	// request.getSession().getAttribute("searchRequestDTO");

	SearchResultsDTO searchResultsDTO = (SearchResultsDTO) request
		.getSession().getAttribute("searchResultsDTO");

	for (RoomDTO dto : searchResultsDTO.getRoomDTO()) {
	    if (dto.getId().longValue() == searchForm.getId().longValue()) {
		request.getSession().setAttribute("roomDTO", dto);
		break;
	    }

	}
	init(mapping, form, request, response);
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
	// TODO Auto-generated method stub
	final SearchForm searchForm = (SearchForm) form;
	// searchForm.reset(mapping, request);

	List<Hotel> allHotel = getHotelManager().findAllActiveHotels();
	searchForm.setAllHotel(allHotel);
	request.getSession().setAttribute("hotelId", "");

	if (searchForm.getHotelId() != null
		&& searchForm.getHotelId().longValue() > 0) {

	    List<Room> roomList = getRoomManager().findAllRoomWithRoomType(
		    searchForm.getHotelId());
	    searchForm.setAllRoom(roomList);

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
    public ActionForward process(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub

	final SearchForm searchForm = (SearchForm) form;
	// SearchRequestDTO searchRequestDTO =(SearchRequestDTO)
	// request.getSession().getAttribute("searchRequestDTO");
	if (searchForm.getId() == null || searchForm.getId().longValue() < 1) {
	    throw new ServiceException(
		    ValidationHelper
			    .getMessageHolder("etravel.booking.noroom.selected"));

	}

	SearchResultsDTO searchResultsDTO = (SearchResultsDTO) request
		.getSession().getAttribute("searchResultsDTO");

	for (RoomDTO dto : searchResultsDTO.getRoomDTO()) {
	    if (dto.getId().longValue() == searchForm.getId().longValue()) {
		request.getSession().setAttribute("roomDTO", dto);
		break;
	    }
	}

	ArrayList<ExtraItem> extraItem = new ArrayList<ExtraItem>();
	double itemsPrice = 0.00;

	if (searchForm.getExtraItemId() != null) {
	    for (int i = 0; i < searchForm.getExtraItemId().length; i++) {
		for (ExtraItemDTO edto : searchResultsDTO.getExtraItemDTO()) {

		    if (edto.getExtraItem().getId().longValue() == searchForm
			    .getExtraItemId()[i].longValue()) {
			edto.getExtraItem().setBookingComments(
				searchForm.getCommets()[i]);
			extraItem.add(edto.getExtraItem());
			itemsPrice += edto.getExtraItem().getCost()
				.doubleValue();
		    }
		}
	    }

	}

	request.getSession().setAttribute("itemscost", itemsPrice);
	request.getSession().setAttribute("extraItem", extraItem);

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
