/**
 * 
 */
package com.yd.etravel.web.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author : Yohan Ranasinghe. Created Date : Feb 4, 2009 : 4:14:50 PM Type :
 *         com.yd.etravel.web.customer.CustomerForm
 * 
 */
public class CustomerForm extends BaseForm {
    private Long id;
    private String cusUsername;
    private String cusPassword;
    private String repassword;
    private String address;
    private String contact;
    private String email;
    private String firstName;
    private String lastName;
    private String nic;

    /**
	 * 
	 */
    public CustomerForm() {
    }

    public String getAddress() {
	return this.address;
    }

    public void setAddress(final String address) {
	this.address = address;
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

    public String getFirstName() {
	return this.firstName;
    }

    public void setFirstName(final String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return this.lastName;
    }

    public void setLastName(final String lastName) {
	this.lastName = lastName;
    }

    public String getNic() {
	return this.nic;
    }

    public void setNic(final String nic) {
	this.nic = nic;
    }

    public String getCusUsername() {
	return this.cusUsername;
    }

    public void setCusUsername(final String cusUsername) {
	this.cusUsername = cusUsername;
    }

    public String getCusPassword() {
	return this.cusPassword;
    }

    public void setCusPassword(final String cusPassword) {
	this.cusPassword = cusPassword;
    }

    public Long getId() {
	return this.id;
    }

    public void setId(final Long id) {
	this.id = id;
    }

    public String getRepassword() {
	return this.repassword;
    }

    public void setRepassword(final String repassword) {
	this.repassword = repassword;
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
	this.address = EMPTY_STRING;
	this.contact = EMPTY_STRING;
	this.email = EMPTY_STRING;
	this.firstName = EMPTY_STRING;
	this.lastName = EMPTY_STRING;
	this.nic = EMPTY_STRING;
	this.cusUsername = EMPTY_STRING;
	this.cusPassword = EMPTY_STRING;
	this.repassword = EMPTY_STRING;
	this.id = 0L;
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

	if (StringUtils.isEmpty(this.firstName)) {
	    addErrors(errors, "etravel.error.customer.firstname.required");
	}

	if (StringUtils.isEmpty(this.lastName)) {
	    addErrors(errors, "etravel.error.customer.lastname.required");
	}

	if (StringUtils.isEmpty(this.contact)) {
	    addErrors(errors, "etravel.error.customer.contact.required");
	}

	// if (StringUtils.isEmpty(this.nic)) {
	// addErrors(errors, "etravel.error.customer.nic.required");
	// }

	if (StringUtils.isEmpty(this.cusUsername)) {
	    addErrors(errors, "etravel.error.username.username.required");
	}

	if (StringUtils.isEmpty(this.email)) {
	    addErrors(errors, "etravel.error.email.empty");

	} else if (!StringUtils.isValidEmail(this.email)) {
	    addErrors(errors, "etravel.error.email.invalid");
	    this.email = EMPTY_STRING;
	}

	if (StringUtils.isEmpty(this.cusPassword)) {
	    addErrors(errors, "etravel.error.pw.required");
	} else if (this.cusPassword.trim().length() < 6) {
	    addErrors(errors, "etravel.error.pw.length");
	    this.cusPassword = EMPTY_STRING;
	    this.repassword = EMPTY_STRING;
	} else if (!this.cusPassword.equals(this.repassword)) {
	    addErrors(errors, "etravel.error.pw.notmached");
	    this.cusPassword = EMPTY_STRING;
	    this.repassword = EMPTY_STRING;
	}

	return errors;
    }

}
