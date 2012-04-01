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
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getContact() {
	return contact;
    }

    public void setContact(String contact) {
	this.contact = contact;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getNic() {
	return nic;
    }

    public void setNic(String nic) {
	this.nic = nic;
    }

    public String getCusUsername() {
	return cusUsername;
    }

    public void setCusUsername(String cusUsername) {
	this.cusUsername = cusUsername;
    }

    public String getCusPassword() {
	return cusPassword;
    }

    public void setCusPassword(String cusPassword) {
	this.cusPassword = cusPassword;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getRepassword() {
	return repassword;
    }

    public void setRepassword(String repassword) {
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
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
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
    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();

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
	} else if (!this.cusPassword.equals(repassword)) {
	    addErrors(errors, "etravel.error.pw.notmached");
	    this.cusPassword = EMPTY_STRING;
	    this.repassword = EMPTY_STRING;
	}

	return errors;
    }

}
