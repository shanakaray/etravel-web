/**
 * 
 */
package com.yd.etravel.domain.hotel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.user.User;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 9, 2009 : 2:42:44 PM Type :
 *         com.yd.etravel.domain.hotel.Hotel
 * 
 */

@Entity
@Table(name = "T_HOTEL")
public class Hotel extends BaseObject {
    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String contact;

    @Column
    private String email;

    @Column
    private int rating;

    @ManyToMany(cascade = { CascadeType.ALL })
    private List<User> superUser;

    public Hotel() {
	super();
    }

    public Hotel(final Long id, final String name, final String code) {
	super(id, name, code);
    }

    @Override
    public String getName() {
	return super.getName();
    }

    @Override
    public void setName(final String name) {
	super.setName(name);
    }

    /**
     * @hibernate.property
     */
    public String getAddress() {
	return this.address;
    }

    public void setAddress(final String address) {
	this.address = address;
    }

    /**
     * @hibernate.property
     */
    public String getCity() {
	return this.city;
    }

    public void setCity(final String city) {
	this.city = city;
    }

    /**
     * @hibernate.property
     */
    public String getContact() {
	return this.contact;
    }

    public void setContact(final String contact) {
	this.contact = contact;
    }

    /**
     * @hibernate.property
     */
    public String getEmail() {
	return this.email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    /**
     * @hibernate.property
     */
    public int getRating() {
	return this.rating;
    }

    public void setRating(final int rating) {
	this.rating = rating;
    }

    public List<User> getSuperUser() {
	return this.superUser;
    }

    public void setSuperUser(final List<User> superUser) {
	this.superUser = superUser;
    }

    /**
     * @hibernate.property
     */
    public String getCountry() {
	return this.country;
    }

    public void setCountry(final String country) {
	this.country = country;
    }

    public Long[] getUserIds() {
	final Set<Long> userIds = new HashSet<Long>();
	for (final User u : getSuperUser()) {
	    userIds.add(u.getId());
	}

	return userIds.toArray(new Long[0]);
    }

}
