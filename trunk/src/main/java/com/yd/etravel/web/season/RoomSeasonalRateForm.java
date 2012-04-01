/**
 * 
 */
package com.yd.etravel.web.season;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.domain.season.RoomSeasonalRate;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author Dharshana
 * 
 */
public class RoomSeasonalRateForm extends BaseForm {
    private Long id;
    private Long hotelId;
    private BigDecimal adultCost;
    private BigDecimal childCost;
    private BigDecimal infantCost;
    private BigDecimal totalCost;
    private String comments;
    private boolean active;
    private List<Season> allSeason;
    private List<Room> allRoom;
    private List<RoomSeasonalRate> allRoomSeasonalRate;
    private Long roomId;
    private Long seasonId;
    private List<Hotel> hotelList;

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

    /**
     * @return the adultCost
     */
    public BigDecimal getAdultCost() {
	return adultCost;
    }

    /**
     * @param adultCost
     *            the adultCost to set
     */
    public void setAdultCost(BigDecimal adultCost) {
	this.adultCost = adultCost;
    }

    /**
     * @return the childCost
     */
    public BigDecimal getChildCost() {
	return childCost;
    }

    /**
     * @param childCost
     *            the childCost to set
     */
    public void setChildCost(BigDecimal childCost) {
	this.childCost = childCost;
    }

    /**
     * @return the infantCost
     */
    public BigDecimal getInfantCost() {
	return infantCost;
    }

    /**
     * @param infantCost
     *            the infantCost to set
     */
    public void setInfantCost(BigDecimal infantCost) {
	this.infantCost = infantCost;
    }

    /**
     * @return the totalCost
     */
    public BigDecimal getTotalCost() {
	return totalCost;
    }

    /**
     * @param totalCost
     *            the totalCost to set
     */
    public void setTotalCost(BigDecimal totalCost) {
	this.totalCost = totalCost;
    }

    /**
     * @return the comments
     */
    public String getComments() {
	return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(String comments) {
	this.comments = comments;
    }

    /**
     * @return the active
     */
    public boolean getActive() {
	return active;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(boolean active) {
	this.active = active;
    }

    /**
     * @return the allSeason
     */
    public List<Season> getAllSeason() {
	return allSeason;
    }

    /**
     * @param allSeason
     *            the allSeason to set
     */
    public void setAllSeason(List<Season> allSeason) {
	this.allSeason = allSeason;
    }

    /**
     * @return the allRoom
     */
    public List<Room> getAllRoom() {
	return allRoom;
    }

    /**
     * @param allRoom
     *            the allRoom to set
     */
    public void setAllRoom(List<Room> allRoom) {
	this.allRoom = allRoom;
    }

    /**
     * @return the allRoomSeasonalRate
     */
    public List<RoomSeasonalRate> getAllRoomSeasonalRate() {
	return allRoomSeasonalRate;
    }

    /**
     * @param allRoomSeasonalRate
     *            the allRoomSeasonalRate to set
     */
    public void setAllRoomSeasonalRate(
	    List<RoomSeasonalRate> allRoomSeasonalRate) {
	this.allRoomSeasonalRate = allRoomSeasonalRate;
    }

    public Long getRoomId() {
	return roomId;
    }

    public void setRoomId(Long roomId) {
	this.roomId = roomId;
    }

    public Long getSeasonId() {
	return seasonId;
    }

    public void setSeasonId(Long seasonId) {
	this.seasonId = seasonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	// TODO Auto-generated method stub

	this.allSeason = Collections.EMPTY_LIST;
	this.allRoom = Collections.EMPTY_LIST;
	this.allRoomSeasonalRate = Collections.EMPTY_LIST;
	this.comments = EMPTY_STRING;
	this.adultCost = new BigDecimal(0.0);
	this.childCost = new BigDecimal(0.0);
	this.infantCost = new BigDecimal(0.0);
	this.totalCost = new BigDecimal(0.0);
	hotelId = 0L;
	this.active = true;
	this.id = null;
	hotelList = Collections.EMPTY_LIST;
	this.roomId = 0L;
	this.seasonId = 0L;

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
	if (StringUtils.isEmpty(this.hotelId)) {
	    addErrors(errors, "etravel.error.pax.hotelid.required");
	}
	if (StringUtils.isEmpty(this.roomId)) {
	    addErrors(errors, "etravel.error.roomtype.required");
	}

	if (StringUtils.isEmpty(this.seasonId)) {
	    addErrors(errors, "etravel.error.season.required");
	}

	return errors;

    }

    public Long getHotelId() {
	return hotelId;
    }

    public void setHotelId(Long hotelId) {
	this.hotelId = hotelId;
    }

    public List<Hotel> getHotelList() {
	return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
	this.hotelList = hotelList;
    }

}
