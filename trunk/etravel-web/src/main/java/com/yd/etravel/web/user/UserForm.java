/**
 * 
 */
package com.yd.etravel.web.user;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.user.User;
import com.yd.etravel.domain.user.role.Role;
import com.yd.etravel.util.IConstants;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * 
 * 
 * @author : Yohan Ranasinghe. Created Date : Mar 14, 2009 : 3:24:58 PM Type :
 *         com.yd.etravel.web.user.UserForm
 * 
 */
public class UserForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2236879976181160458L;
	private User user;
	private Long id;
	private String repassword;
	private Long[] roleIds;
	private List<Role> allRoles;
	private List<User> allUsers;

	private boolean passwordReset;

	/**
	 * 
	 */
	public UserForm() {
	}

	public Boolean getActive() {
		return this.user.isActive();
	}

	public String getAddress() {
		return this.user.getAddress();
	}

	public List<Role> getAllRoles() {
		return this.allRoles;
	}

	public List<User> getAllUsers() {
		return this.allUsers;
	}

	public String getContact() {
		return this.user.getContact();
	}

	public String getEmail() {
		return this.user.getEmail();
	}

	public String getFirstName() {
		return this.user.getFirstName();
	}

	public Long getId() {
		return this.id;
	}

	public String getLastName() {
		return this.user.getLastName();
	}

	public String getName() {
		return this.user.getName();
	}

	public String getPw() {
		return this.user.getPassword();
	}

	public String getRepw() {
		return this.repassword;
	}

	public Long[] getRoleIds() {
		return this.roleIds;
	}

	public User getUser() {
		return this.user;
	}

	public boolean isPasswordReset() {
		return this.passwordReset;
	}

	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {

		this.id = 0L;
		this.user = new User();
		this.repassword = IConstants.ICommon.EMPTYSTRING;
		this.roleIds = null;
		this.allRoles = Collections.emptyList();
		this.allUsers = Collections.emptyList();
		this.passwordReset = false;
	}

	public void setActive(final Boolean active) {
		this.user.setActive(active);
	}

	public void setAddress(final String address) {
		this.user.setAddress(address);
	}

	public void setAllRoles(final List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public void setAllUsers(final List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public void setContact(final String contact) {
		this.user.setContact(contact);
	}

	public void setEmail(final String email) {
		this.user.setEmail(email);
	}

	public void setFirstName(final String firstName) {
		this.user.setFirstName(firstName);
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setLastName(final String lastName) {
		this.user.setLastName(lastName);
	}

	public void setName(final String name) {
		this.user.setName(name);
	}

	public void setPasswordReset(final boolean passwordReset) {
		this.passwordReset = passwordReset;
	}

	public void setPw(final String password) {
		this.user.setPassword(password);
	}

	public void setRepw(final String repassword) {
		this.repassword = repassword;
	}

	public void setRoleIds(final Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		final ActionErrors errors = new ActionErrors();

		if (StringUtils.isEmpty(this.user.getFirstName())) {
			addErrors(errors, "etravel.error.fistname.required");
		}
		if (StringUtils.isEmpty(this.user.getLastName())) {
			addErrors(errors, "etravel.error.lastname.required");
		}
		if (StringUtils.isEmpty(this.user.getName())) {
			addErrors(errors, "etravel.error.username.required");
		}

		if (!StringUtils.isEmpty(this.user.getEmail())
				&& !StringUtils.isValidEmail(this.user.getEmail())) {
			addErrors(errors, "etravel.error.email.invalid");
			this.user.setEmail(EMPTYSTRING);
		}

		if (this.passwordReset || (getId() == null)
				|| (getId().longValue() <= 0)) {
			if (StringUtils.isEmpty(this.user.getPassword())) {
				addErrors(errors, "etravel.error.pw.required");
			} else if (!this.user.getPassword().equals(this.repassword)) {
				addErrors(errors, "etravel.error.pw.notmached");

				setRepw(EMPTYSTRING);
				setPw(EMPTYSTRING);

				setPasswordReset(false);
			}
		}

		return errors;
	}

}
