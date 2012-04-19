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

    public ExtraItem(Long id, String name, String code) {
	super(id, name, code);
    }

    public BigDecimal getCost() {
	return cost;
    }

    public void setCost(BigDecimal cost) {
	this.cost = cost;
    }

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

    /**
     * 
     * @hibernate.property
     */
    public String getCurrency() {
	return currency;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

    public List<Hotel> getHotel() {
	return hotel;
    }

    public void setHotel(List<Hotel> hotel) {
	this.hotel = hotel;
    }

    public Long[] getHotelIds() {
	Set<Long> idSet = new HashSet<Long>();
	for (Hotel ho : hotel) {
	    idSet.add(ho.getId());
	}
	return (Long[]) idSet.toArray(new Long[0]);
    }

    public String getBookingComments() {
	return bookingComments;
    }

    public void setBookingComments(String bookingComments) {
	this.bookingComments = bookingComments;
    }

}
