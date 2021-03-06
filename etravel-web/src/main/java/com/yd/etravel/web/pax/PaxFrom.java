/**
 * 
 */
package com.yd.etravel.web.pax;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.hotel.Pax;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Jan 29, 2009 : 7:57:34 AM Type :
 *         com.yd.etravel.web.pax.PaxFrom
 * 
 */
public class PaxFrom extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5269439491988484181L;

	private Long id;

	private int childMax;
	private int childMin;

	private int infantMax;
	private int infantMin;

	private int adultMax;
	private int adultMin;

	private Long hotelId;
	private List<Hotel> hotelList;
	private List<Pax> paxList;
	private Map<Long, String> hotelNameMap;

	public int getAdultMax() {
		return this.adultMax;
	}

	public int getAdultMin() {
		return this.adultMin;
	}

	public int getChildMax() {
		return this.childMax;
	}

	public int getChildMin() {
		return this.childMin;
	}

	public Hotel getHotel(final Long id) {
		Hotel hotel = null;
		for (final Hotel hot : this.hotelList) {
			if (hot.getId().equals(id)) {
				hotel = hot;
				break;
			}
		}
		return hotel;
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public List<Hotel> getHotelList() {
		return this.hotelList;
	}

	public String getHotelName(final Long id) {
		if ((this.hotelNameMap != null) && this.hotelNameMap.containsKey(id)) {
			return this.hotelNameMap.get(id).toString();
		}
		return EMPTYSTRING;
	}

	public Long getId() {
		return this.id;
	}

	public int getInfantMax() {
		return this.infantMax;
	}

	public int getInfantMin() {
		return this.infantMin;
	}

	public List<Pax> getPaxList() {
		return this.paxList;
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
		this.childMax = 0;
		this.childMin = 0;

		this.infantMax = 0;
		this.infantMin = 0;

		this.adultMax = 0;
		this.adultMin = 0;

		this.hotelId = 0L;
		this.hotelList = Collections.emptyList();

		this.paxList = Collections.emptyList();
		this.hotelNameMap = Collections.emptyMap();
		this.id = 0L;
	}

	public void setAdultMax(final int adultMax) {
		this.adultMax = adultMax;
	}

	public void setAdultMin(final int adultMin) {
		this.adultMin = adultMin;
	}

	public void setChildMax(final int childMax) {
		this.childMax = childMax;
	}

	public void setChildMin(final int childMin) {
		this.childMin = childMin;
	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelList(final List<Hotel> hotelList) {
		this.hotelList = hotelList;
		this.hotelNameMap = new HashMap<Long, String>();
		for (final Hotel hotel : hotelList) {
			this.hotelNameMap.put(hotel.getId(), hotel.getName());
		}
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setInfantMax(final int infantMax) {
		this.infantMax = infantMax;
	}

	public void setInfantMin(final int infantMin) {
		this.infantMin = infantMin;
	}

	public void setPaxList(final List<Pax> paxList) {
		this.paxList = paxList;
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

		if ((this.infantMin < 0) || (this.infantMin > 150)) {
			addErrors(errors, "etravel.error.pax.infant.minage");
			this.infantMin = 0;
		}

		if ((this.infantMax < 0) || (this.infantMax > 150)) {
			addErrors(errors, "etravel.error.pax.infant.maxage");
			this.infantMax = 0;
		}

		if ((this.childMin < 0) || (this.childMin > 150)) {
			addErrors(errors, "etravel.error.pax.child.minage");
			this.childMin = 0;
		}

		if ((this.childMax < 0) || (this.childMax > 150)) {
			addErrors(errors, "etravel.error.pax.child.maxage");
			this.childMax = 0;
		}

		if ((this.adultMin < 0) || (this.adultMin > 150)) {
			addErrors(errors, "etravel.error.pax.adult.minage");
			this.adultMin = 0;
		}

		if ((this.adultMax < 0) || (this.adultMax > 150)) {
			addErrors(errors, "etravel.error.pax.adult.maxage");
			this.adultMax = 0;
		}

		if ((this.infantMin > this.infantMax)
				|| (this.infantMax > this.childMin)
				|| (this.childMin > this.childMax)
				|| (this.childMax > this.adultMin)
				|| (this.adultMin > this.adultMax)) {
			addErrors(errors, "etravel.error.pax.invalidage");
			this.childMax = 0;
			this.childMin = 0;

			this.infantMax = 0;
			this.infantMin = 0;

			this.adultMax = 0;
			this.adultMin = 0;
		}

		return errors;
	}

}
