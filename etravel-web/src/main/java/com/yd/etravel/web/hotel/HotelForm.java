/**
 * 
 */
package com.yd.etravel.web.hotel;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author yora
 * 
 */
public class HotelForm extends BaseForm {

    private String name;
    private String address;
    private String city;
    private String country;
    private String contact;
    private String email;
    private int rating;
    private Long[] superUserId;
    private Long id;
    private List<User> adminList;
    private List<Hotel> hotelList;
    private boolean active;

    /**
	 * 
	 */
    public HotelForm() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return this.name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public String getAddress() {
	return this.address;
    }

    public void setAddress(final String address) {
	this.address = address;
    }

    public String getCity() {
	return this.city;
    }

    public void setCity(final String city) {
	this.city = city;
    }

    public String getContact() {
	return this.contact;
    }

    public void setContact(final String contact) {
	this.contact = contact;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    public int getRating() {
	return this.rating;
    }

    public void setRating(final int rating) {
	this.rating = rating;
    }

    public Long[] getSuperUserId() {
	return this.superUserId;
    }

    public void setSuperUserId(final Long[] superUserId) {
	this.superUserId = superUserId;
    }

    public List<User> getAdminList() {
	return this.adminList;
    }

    public void setAdminList(final List<User> adminList) {
	this.adminList = adminList;
    }

    public String getCountry() {
	return this.country;
    }

    public void setCountry(final String country) {
	this.country = country;
    }

    public List<Hotel> getHotelList() {
	return this.hotelList;
    }

    public void setHotelList(final List<Hotel> hotelList) {
	this.hotelList = hotelList;
    }

    public boolean isActive() {
	return this.active;
    }

    public void setActive(final boolean active) {
	this.active = active;
    }

    public Long getId() {
	return this.id;
    }

    public void setId(final Long id) {
	this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void resetBean(final ActionMapping mapping, final HttpServletRequest request) {
	this.adminList = Collections.EMPTY_LIST;
	this.hotelList = Collections.EMPTY_LIST;
	this.address = EMPTY_STRING;
	this.city = EMPTY_STRING;
	this.contact = EMPTY_STRING;
	this.email = EMPTY_STRING;
	this.name = EMPTY_STRING;
	this.country = EMPTY_STRING;
	this.rating = 0;
	this.superUserId = null;
	this.active = true;
	this.id = null;
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
	    addErrors(errors, "etravel.error.hotel.name.required");
	}
	if (StringUtils.isEmpty(this.address)) {
	    addErrors(errors, "etravel.error.hotel.address.required");
	}
	if (StringUtils.isEmpty(this.city)) {
	    addErrors(errors, "etravel.error.hotel.city.required");
	}
	if (StringUtils.isEmpty(this.country)) {
	    addErrors(errors, "etravel.error.hotel.country.required");
	}
	if (StringUtils.isEmpty(this.contact)) {
	    addErrors(errors, "etravel.error.hotel.contact.required");
	}
	if (StringUtils.isEmpty(this.superUserId)) {
	    addErrors(errors, "etravel.error.hotel.user.required");
	}
	if (!StringUtils.isEmpty(this.email)
		&& !StringUtils.isValidEmail(this.email)) {
	    addErrors(errors, "etravel.error.hotel.email.invalid");
	    this.email = EMPTY_STRING;
	}

	return errors;
    }

}
