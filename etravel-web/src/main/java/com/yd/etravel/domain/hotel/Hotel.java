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

    public Hotel(Long id, String name, String code) {
	super(id, name, code);
    }

    public String getName() {
	return super.getName();
    }

    public void setName(String name) {
	super.setName(name);
    }

    /**
     * @hibernate.property
     */
    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    /**
     * @hibernate.property
     */
    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    /**
     * @hibernate.property
     */
    public String getContact() {
	return contact;
    }

    public void setContact(String contact) {
	this.contact = contact;
    }

    /**
     * @hibernate.property
     */
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * @hibernate.property
     */
    public int getRating() {
	return rating;
    }

    public void setRating(int rating) {
	this.rating = rating;
    }

    public List<User> getSuperUser() {
	return superUser;
    }

    public void setSuperUser(List<User> superUser) {
	this.superUser = superUser;
    }

    /**
     * @hibernate.property
     */
    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public Long[] getUserIds() {
	Set<Long> userIds = new HashSet<Long>();
	for (User u : getSuperUser()) {
	    userIds.add(u.getId());
	}

	return userIds.toArray(new Long[0]);
    }

}
