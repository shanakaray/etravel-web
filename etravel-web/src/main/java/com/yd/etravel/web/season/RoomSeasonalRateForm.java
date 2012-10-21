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
	/**
	 * 
	 */
	private static final long serialVersionUID = -2028064367879052713L;
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
	 * @return the active
	 */
	public boolean getActive() {
		return this.active;
	}

	/**
	 * @return the adultCost
	 */
	public BigDecimal getAdultCost() {
		return this.adultCost;
	}

	/**
	 * @return the allRoom
	 */
	public List<Room> getAllRoom() {
		return this.allRoom;
	}

	/**
	 * @return the allRoomSeasonalRate
	 */
	public List<RoomSeasonalRate> getAllRoomSeasonalRate() {
		return this.allRoomSeasonalRate;
	}

	/**
	 * @return the allSeason
	 */
	public List<Season> getAllSeason() {
		return this.allSeason;
	}

	/**
	 * @return the childCost
	 */
	public BigDecimal getChildCost() {
		return this.childCost;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public List<Hotel> getHotelList() {
		return this.hotelList;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the infantCost
	 */
	public BigDecimal getInfantCost() {
		return this.infantCost;
	}

	public Long getRoomId() {
		return this.roomId;
	}

	public Long getSeasonId() {
		return this.seasonId;
	}

	/**
	 * @return the totalCost
	 */
	public BigDecimal getTotalCost() {
		return this.totalCost;
	}

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
		// TODO Auto-generated method stub

		this.allSeason = Collections.emptyList();
		this.allRoom = Collections.emptyList();
		this.allRoomSeasonalRate = Collections.emptyList();
		this.comments = EMPTY_STRING;
		this.adultCost = new BigDecimal(0.0);
		this.childCost = new BigDecimal(0.0);
		this.infantCost = new BigDecimal(0.0);
		this.totalCost = new BigDecimal(0.0);
		this.hotelId = 0L;
		this.active = true;
		this.id = null;
		this.hotelList = Collections.emptyList();
		this.roomId = 0L;
		this.seasonId = 0L;

	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * @param adultCost
	 *            the adultCost to set
	 */
	public void setAdultCost(final BigDecimal adultCost) {
		this.adultCost = adultCost;
	}

	/**
	 * @param allRoom
	 *            the allRoom to set
	 */
	public void setAllRoom(final List<Room> allRoom) {
		this.allRoom = allRoom;
	}

	/**
	 * @param allRoomSeasonalRate
	 *            the allRoomSeasonalRate to set
	 */
	public void setAllRoomSeasonalRate(
			final List<RoomSeasonalRate> allRoomSeasonalRate) {
		this.allRoomSeasonalRate = allRoomSeasonalRate;
	}

	/**
	 * @param allSeason
	 *            the allSeason to set
	 */
	public void setAllSeason(final List<Season> allSeason) {
		this.allSeason = allSeason;
	}

	/**
	 * @param childCost
	 *            the childCost to set
	 */
	public void setChildCost(final BigDecimal childCost) {
		this.childCost = childCost;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelList(final List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param infantCost
	 *            the infantCost to set
	 */
	public void setInfantCost(final BigDecimal infantCost) {
		this.infantCost = infantCost;
	}

	public void setRoomId(final Long roomId) {
		this.roomId = roomId;
	}

	public void setSeasonId(final Long seasonId) {
		this.seasonId = seasonId;
	}

	/**
	 * @param totalCost
	 *            the totalCost to set
	 */
	public void setTotalCost(final BigDecimal totalCost) {
		this.totalCost = totalCost;
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

}
