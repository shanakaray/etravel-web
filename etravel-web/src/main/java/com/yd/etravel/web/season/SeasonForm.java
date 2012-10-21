/**
 * 
 */
package com.yd.etravel.web.season;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.season.Season;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author Dharshana
 * 
 */
public class SeasonForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -86205972061017676L;
	private Long id;
	private String code;
	private String fromDate;
	private String toDate;
	private String name;
	private String comments;
	private boolean active;
	private List<Season> allSeason = Collections.emptyList();
	private List<Hotel> allHotel = Collections.emptyList();
	private Long[] hotelIds;

	/**
	 * 
	 */
	public SeasonForm() {
		// TODO Auto-generated constructor stub
	}

	public boolean getActive() {
		return this.active;
	}

	public List<Hotel> getAllHotel() {
		return this.allHotel;
	}

	public List<Season> getAllSeason() {
		return this.allSeason;
	}

	public String getCode() {
		return this.code;
	}

	public String getComments() {
		return this.comments;
	}

	public String getFromDate() {
		return this.fromDate;
	}

	public Long[] getHotelIds() {
		return this.hotelIds;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getToDate() {
		return this.toDate;
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
		this.allHotel = Collections.emptyList();
		this.fromDate = EMPTY_STRING;
		this.toDate = EMPTY_STRING;
		this.comments = EMPTY_STRING;
		this.name = EMPTY_STRING;
		this.active = true;
		this.id = null;

	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setAllHotel(final List<Hotel> allHotel) {
		this.allHotel = allHotel;
	}

	public void setAllSeason(final List<Season> allSeason) {
		this.allSeason = allSeason;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public void setFromDate(final String fromDate) {
		this.fromDate = fromDate;
	}

	public void setHotelIds(final Long[] hotelIds) {
		this.hotelIds = hotelIds;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setToDate(final String toDate) {
		this.toDate = toDate;
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

		if (StringUtils.isEmpty(this.name)) {
			addErrors(errors, "etravel.season.name.required");
		}
		if (StringUtils.isEmpty(this.fromDate)) {
			addErrors(errors, "etravel.season.fromDate.required");
		}
		if (StringUtils.isEmpty(this.toDate)) {
			addErrors(errors, "etravel.season.toDate.required");
		}
		if (StringUtils.isEmpty(this.hotelIds)) {
			addErrors(errors, "etravel.season.hotel.required");
		}

		return errors;

	}

}
