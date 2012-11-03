/**
 * 
 */
package com.yd.etravel.web.extraitem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.extraitem.ExtraItem;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.util.IConstants.ICurrency;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 9, 2009 : 4:42:47 PM Type :
 *         com.yd.etravel.web.extraitem.ExtraItemForm
 * 
 */
public class ExtraItemForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3058925732332335646L;
	private List<Hotel> hotelList;
	private List<ExtraItem> extraItemList;
	private List<String> currencyList;

	private BigDecimal cost;
	private String comments;
	private String currency;
	private String code;
	private String name;
	private Long[] hotelId;
	private Long id;

	/**
	 * 
	 */
	public ExtraItemForm() {
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
		return this.code;
	}

	public String getComments() {
		return this.comments;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public String getCurrency() {
		return this.currency;
	}

	public List<String> getCurrencyList() {
		return this.currencyList;
	}

	public List<ExtraItem> getExtraItemList() {
		return this.extraItemList;
	}

	public Long[] getHotelId() {
		return this.hotelId;
	}

	public List<Hotel> getHotelList() {
		return this.hotelList;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.hotelList = Collections.emptyList();
		this.extraItemList = Collections.emptyList();
		this.currencyList = new ArrayList<String>();
		this.currencyList.add(ICurrency.USD);
		this.code = EMPTYSTRING;
		this.cost = new BigDecimal(0.00);
		this.currency = ICurrency.USD;
		this.name = EMPTYSTRING;
		this.id = 0L;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public void setCost(final BigDecimal cost) {
		this.cost = cost;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public void setCurrencyList(final List<String> currencyList) {
		this.currencyList = currencyList;
	}

	public void setExtraItemList(final List<ExtraItem> extraItemList) {
		this.extraItemList = extraItemList;
	}

	public void setHotelId(final Long[] hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelList(final List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
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
			addErrors(errors, "etravel.extraitem.name.required");
		}

		if (StringUtils.isEmpty(this.code)) {
			addErrors(errors, "etravel.extraitem.code.required");
		}

		if (this.cost.doubleValue() < 0) {
			addErrors(errors, "etravel.extraitem.cost.required");
		}

		// if (hotelId == null || hotelId.length < 1) {
		// addErrors(errors, "etravel.hotelids.required");
		// }

		return errors;
	}

}
