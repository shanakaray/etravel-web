package com.yd.etravel.web.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

public class AuthanticationForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4010044878943623934L;
	private String username;
	private String password;

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.username = EMPTY_STRING;
		this.password = EMPTY_STRING;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		final ActionErrors errors = new ActionErrors();

		if (StringUtils.isEmpty(this.username)) {
			addErrors(errors, "etravel.error.username.required");
		}

		if (StringUtils.isEmpty(this.password)) {
			addErrors(errors, "etravel.error.password.required");
		}

		return errors;
	}

}
