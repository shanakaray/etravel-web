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
	return this.hotelId;
    }

    /**
     * @param hotelId
     *            the hotelId to set
     */
    public void setHotelId(final Long hotelId) {
	this.hotelId = hotelId;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
	return this.hotelName;
    }

    /**
     * @param hotelName
     *            the hotelName to set
     */
    public void setHotelName(final String hotelName) {
	this.hotelName = hotelName;
    }

    /**
     * @return the checkIn
     */
    public String getCheckIn() {
	return this.checkIn;
    }

    /**
     * @param checkIn
     *            the checkIn to set
     */
    public void setCheckIn(final String checkIn) {
	this.checkIn = checkIn;
    }

    /**
     * @return the checkOut
     */
    public String getCheckOut() {
	return this.checkOut;
    }

    /**
     * @param checkOut
     *            the checkOut to set
     */
    public void setCheckOut(final String checkOut) {
	this.checkOut = checkOut;
    }

    /**
     * @return the searchResultsDTO
     */
    public SearchResultsDTO getSearchResultsDTO() {
	return this.searchResultsDTO;
    }

    /**
     * @param searchResultsDTO
     *            the searchResultsDTO to set
     */
    public void setSearchResultsDTO(final SearchResultsDTO searchResultsDTO) {
	this.searchResultsDTO = searchResultsDTO;
    }

    /**
     * @return the allHotel
     */
    public List<Hotel> getAllHotel() {
	return this.allHotel;
    }

    /**
     * @param allHotel
     *            the allHotel to set
     */
    public void setAllHotel(final List<Hotel> allHotel) {
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
    public void resetBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	this.allRoom = Collections.EMPTY_LIST;
	this.roomId = -1l;
	this.hotelId = -1l;
    }

    public Long getRoomId() {
	return this.roomId;
    }

    public void setRoomId(final Long roomId) {
	this.roomId = roomId;
    }

    public List<Room> getAllRoom() {
	return this.allRoom;
    }

    public void setAllRoom(final List<Room> allRoom) {
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
    public ActionErrors validateBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	final ActionErrors errors = new ActionErrors();

	if (StringUtils.isEmpty(this.checkIn)
		|| StringUtils.isEmpty(this.checkOut)) {
	    addErrors(errors, "etravel.error.search.data.empty");
	} else {
	    final Date checkInDate = DateUtil.parse(this.checkIn);
	    final Date checkOutDate = DateUtil.parse(this.checkOut);

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
	return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
	this.id = id;
    }

    public String[] getCommets() {
	return this.commets;
    }

    public void setCommets(final String[] commets) {
	this.commets = commets;
    }

    public Long[] getExtraItemId() {
	return this.extraItemId;
    }

    public void setExtraItemId(final Long[] extraItemId) {
	this.extraItemId = extraItemId;
    }

}
