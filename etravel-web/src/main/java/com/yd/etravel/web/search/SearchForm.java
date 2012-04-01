/**
 * 
 */
package com.yd.etravel.web.search;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.search.SearchResultsDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.util.DateUtil;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author Dharshana
 * 
 */
public class SearchForm extends BaseForm {

    private Long id;
    private Long roomId;
    private Long hotelId;
    private String hotelName;

    private String checkIn;
    private String checkOut;

    private SearchResultsDTO searchResultsDTO = new SearchResultsDTO();

    private List<Hotel> allHotel = Collections.EMPTY_LIST;
    // private Long[] hotelIds;
    private String[] commets;
    private Long[] extraItemId;
    private List<Room> allRoom;

    /**
     * @return the hotelId
     */
    public Long getHotelId() {
	return hotelId;
    }

    /**
     * @param hotelId
     *            the hotelId to set
     */
    public void setHotelId(Long hotelId) {
	this.hotelId = hotelId;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
	return hotelName;
    }

    /**
     * @param hotelName
     *            the hotelName to set
     */
    public void setHotelName(String hotelName) {
	this.hotelName = hotelName;
    }

    /**
     * @return the checkIn
     */
    public String getCheckIn() {
	return checkIn;
    }

    /**
     * @param checkIn
     *            the checkIn to set
     */
    public void setCheckIn(String checkIn) {
	this.checkIn = checkIn;
    }

    /**
     * @return the checkOut
     */
    public String getCheckOut() {
	return checkOut;
    }

    /**
     * @param checkOut
     *            the checkOut to set
     */
    public void setCheckOut(String checkOut) {
	this.checkOut = checkOut;
    }

    /**
     * @return the searchResultsDTO
     */
    public SearchResultsDTO getSearchResultsDTO() {
	return searchResultsDTO;
    }

    /**
     * @param searchResultsDTO
     *            the searchResultsDTO to set
     */
    public void setSearchResultsDTO(SearchResultsDTO searchResultsDTO) {
	this.searchResultsDTO = searchResultsDTO;
    }

    /**
     * @return the allHotel
     */
    public List<Hotel> getAllHotel() {
	return allHotel;
    }

    /**
     * @param allHotel
     *            the allHotel to set
     */
    public void setAllHotel(List<Hotel> allHotel) {
	this.allHotel = allHotel;
    }

    /**
     * @return the hotelIds
     * 
     *         public Long[] getHotelIds() { return hotelIds; }
     * 
     * 
     *         public void setHotelIds(Long[] hotelIds) { this.hotelIds =
     *         hotelIds; }
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	allRoom = Collections.EMPTY_LIST;
	roomId = -1l;
	this.hotelId = -1l;
    }

    public Long getRoomId() {
	return roomId;
    }

    public void setRoomId(Long roomId) {
	this.roomId = roomId;
    }

    public List<Room> getAllRoom() {
	return allRoom;
    }

    public void setAllRoom(List<Room> allRoom) {
	this.allRoom = allRoom;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#validateBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();

	if (StringUtils.isEmpty(this.checkIn)
		|| StringUtils.isEmpty(this.checkOut)) {
	    addErrors(errors, "etravel.error.search.data.empty");
	} else {
	    Date checkInDate = DateUtil.parse(this.checkIn);
	    Date checkOutDate = DateUtil.parse(this.checkOut);

	    if (checkInDate.getTime() >= checkOutDate.getTime()) {
		addErrors(errors, "etravel.error.search.data.empty");
	    }
	}

	return errors;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    public String[] getCommets() {
	return commets;
    }

    public void setCommets(String[] commets) {
	this.commets = commets;
    }

    public Long[] getExtraItemId() {
	return extraItemId;
    }

    public void setExtraItemId(Long[] extraItemId) {
	this.extraItemId = extraItemId;
    }

}
