package com.yd.etravel.web.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorActionForm;

import com.yd.etravel.util.IConstants;

public abstract class BaseForm extends ValidatorActionForm implements
		IConstants.ICommon {

	private static final long serialVersionUID = 3689069551690199091L;
	private long tjwToken;

	public BaseForm() {
		super();
		this.tjwToken = 0l;
	}

	public long getTjwToken() {
		return tjwToken;
	}

	public void setTjwToken(long tjwToken) {
		this.tjwToken = tjwToken;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.tjwToken = 0l;
		resetBean(mapping, request);
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return validateBean(mapping, request);
	}

	public abstract ActionErrors validateBean(ActionMapping mapping,
			HttpServletRequest request);

	public abstract void resetBean(ActionMapping mapping,
			HttpServletRequest request);

	protected void addErrors(ActionErrors actionMessages, String key) {
		actionMessages.add(Globals.ERROR_KEY, new ActionMessage(key));
	}

}
