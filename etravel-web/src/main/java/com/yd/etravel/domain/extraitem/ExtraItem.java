/**
 * 
 */
package com.yd.etravel.domain.extraitem;

import static javax.persistence.CascadeType.ALL;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.hotel.Hotel;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 8, 2009 : 11:09:34 PM Type :
 *         com.yd.etravel.domain.extraitem.ExtraItem
 * 
 */

/*******************************************************************************
 */
@Entity
@Table(name = "T_EXTRA_ITEM")
public class ExtraItem extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9112122030262740274L;

	@Column
	private BigDecimal cost;

	@Column
	private String comments;

	@Column
	private String bookingComments;

	@Column
	private String currency;

	@ManyToMany(cascade = { ALL })
	@JoinTable(joinColumns = @JoinColumn(name = "ITEM_ID", nullable = false), inverseJoinColumns = @JoinColumn(name = "HOTEL_ID", nullable = false), uniqueConstraints = @UniqueConstraint(columnNames = {
			"ITEM_ID", "HOTEL_ID" }))
	private List<Hotel> hotel;

	public ExtraItem() {
	}

	public ExtraItem(final Long id, final String name, final String code) {
		super(id, name, code);
	}

	public String getBookingComments() {
		return this.bookingComments;
	}

	public String getComments() {
		return this.comments;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public String getCurrency() {
		return this.currency;
	}

	public List<Hotel> getHotel() {
		return this.hotel;
	}

	public Long[] getHotelIds() {
		final Set<Long> idSet = new HashSet<Long>();
		for (final Hotel ho : this.hotel) {
			idSet.add(ho.getId());
		}
		return idSet.toArray(new Long[0]);
	}

	public void setBookingComments(final String bookingComments) {
		this.bookingComments = bookingComments;
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

	public void setHotel(final List<Hotel> hotel) {
		this.hotel = hotel;
	}

}
