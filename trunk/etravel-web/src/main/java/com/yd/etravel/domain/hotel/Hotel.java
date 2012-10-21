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
	/**
	 * 
	 */
	private static final long serialVersionUID = 5486731096002281449L;

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

	/**
	 * @hibernate.property
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @hibernate.property
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @hibernate.property
	 */
	public String getContact() {
		return this.contact;
	}

	/**
	 * @hibernate.property
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @hibernate.property
	 */
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	/**
	 * @hibernate.property
	 */
	public int getRating() {
		return this.rating;
	}

	public List<User> getSuperUser() {
		return this.superUser;
	}

	public Long[] getUserIds() {
		final Set<Long> userIds = new HashSet<Long>();
		for (final User user : getSuperUser()) {
			userIds.add(user.getId());
		}

		return userIds.toArray(new Long[0]);
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setContact(final String contact) {
		this.contact = contact;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public void setName(final String name) {
		super.setName(name);
	}

	public void setRating(final int rating) {
		this.rating = rating;
	}

	public void setSuperUser(final List<User> superUser) {
		this.superUser = superUser;
	}

}
