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
	/**
	 * 
	 */
	private static final long serialVersionUID = -2983829208384874214L;
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

	public String getContact() {
		return this.contact;
	}

	public String getCusPassword() {
		return this.cusPassword;
	}

	public String getCusUsername() {
		return this.cusUsername;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Long getId() {
		return this.id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getNic() {
		return this.nic;
	}

	public String getRepassword() {
		return this.repassword;
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
		this.address = EMPTYSTRING;
		this.contact = EMPTYSTRING;
		this.email = EMPTYSTRING;
		this.firstName = EMPTYSTRING;
		this.lastName = EMPTYSTRING;
		this.nic = EMPTYSTRING;
		this.cusUsername = EMPTYSTRING;
		this.cusPassword = EMPTYSTRING;
		this.repassword = EMPTYSTRING;
		this.id = 0L;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setContact(final String contact) {
		this.contact = contact;
	}

	public void setCusPassword(final String cusPassword) {
		this.cusPassword = cusPassword;
	}

	public void setCusUsername(final String cusUsername) {
		this.cusUsername = cusUsername;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setNic(final String nic) {
		this.nic = nic;
	}

	public void setRepassword(final String repassword) {
		this.repassword = repassword;
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
			this.email = EMPTYSTRING;
		}

		if (StringUtils.isEmpty(this.cusPassword)) {
			addErrors(errors, "etravel.error.pw.required");
		} else if (this.cusPassword.trim().length() < 6) {
			addErrors(errors, "etravel.error.pw.length");
			this.cusPassword = EMPTYSTRING;
			this.repassword = EMPTYSTRING;
		} else if (!this.cusPassword.equals(this.repassword)) {
			addErrors(errors, "etravel.error.pw.notmached");
			this.cusPassword = EMPTYSTRING;
			this.repassword = EMPTYSTRING;
		}

		return errors;
	}

}
