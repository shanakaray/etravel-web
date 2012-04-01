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

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public String getName() {
	return user.getName();
    }

    public void setName(String name) {
	this.user.setName(name);
    }

    public String getPw() {
	return this.user.getPassword();
    }

    public void setPw(String password) {
	this.user.setPassword(password);
    }

    public String getAddress() {
	return this.user.getAddress();
    }

    public void setAddress(String address) {
	this.user.setAddress(address);
    }

    public String getContact() {
	return user.getContact();
    }

    public void setContact(String contact) {
	this.user.setContact(contact);
    }

    public String getEmail() {
	return user.getEmail();
    }

    public void setEmail(String email) {
	this.user.setEmail(email);
    }

    public String getFirstName() {
	return user.getFirstName();
    }

    public void setFirstName(String firstName) {
	this.user.setFirstName(firstName);
    }

    public String getLastName() {
	return user.getLastName();
    }

    public void setLastName(String lastName) {
	this.user.setLastName(lastName);
    }

    public String getRepw() {
	return repassword;
    }

    public void setRepw(String repassword) {
	this.repassword = repassword;
    }

    public Long[] getRoleIds() {
	return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
	this.roleIds = roleIds;
    }

    public List<Role> getAllRoles() {
	return allRoles;
    }

    public void setAllRoles(List<Role> allRoles) {
	this.allRoles = allRoles;
    }

    public List<User> getAllUsers() {
	return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
	this.allUsers = allUsers;
    }

    public Boolean getActive() {
	return this.user.isActive();
    }

    public void setActive(Boolean active) {
	this.user.setActive(active);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public boolean isPasswordReset() {
	return passwordReset;
    }

    public void setPasswordReset(boolean passwordReset) {
	this.passwordReset = passwordReset;
    }

    @Override
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {

	this.id = 0L;
	this.user = new User();
	this.repassword = IConstants.ICommon.EMPTY_STRING;
	this.roleIds = null;
	this.allRoles = Collections.EMPTY_LIST;
	this.allUsers = Collections.EMPTY_LIST;
	this.passwordReset = false;
    }

    @Override
    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();

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
	    this.user.setEmail(EMPTY_STRING);
	}

	if (passwordReset
		|| (this.getId() == null || this.getId().longValue() <= 0)) {
	    if (StringUtils.isEmpty(this.user.getPassword())) {
		addErrors(errors, "etravel.error.pw.required");
	    } else if (!this.user.getPassword().equals(repassword)) {
		addErrors(errors, "etravel.error.pw.notmached");

		this.setRepw(EMPTY_STRING);
		this.setPw(EMPTY_STRING);

		this.setPasswordReset(false);
	    }
	}

	return errors;
    }

}
