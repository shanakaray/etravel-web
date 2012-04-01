package com.yd.etravel.web.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

public class AuthanticationForm extends BaseForm {

    private String username;
    private String password;

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	this.username = EMPTY_STRING;
	this.password = EMPTY_STRING;
    }

    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();

	if (StringUtils.isEmpty(this.username)) {
	    addErrors(errors, "etravel.error.username.required");
	}

	if (StringUtils.isEmpty(this.password)) {
	    addErrors(errors, "etravel.error.password.required");
	}

	return errors;
    }

}
