/**
 * 
 */
package com.yd.etravel.web.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author yora
 * 
 */
public class ChangePasswordForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -142915448887081580L;
	private String oldPw;
	private String newPw;
	private String newRepPw;
	private Long userId;

	public String getNewPw() {
		return this.newPw;
	}

	public String getNewRepPw() {
		return this.newRepPw;
	}

	public String getOldPw() {
		return this.oldPw;
	}

	public Long getUserId() {
		return this.userId;
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
		this.oldPw = EMPTYSTRING;
		this.newPw = EMPTYSTRING;
		this.newRepPw = EMPTYSTRING;
		this.userId = 0L;
	}

	public void setNewPw(final String newPw) {
		this.newPw = newPw;
	}

	public void setNewRepPw(final String newRepPw) {
		this.newRepPw = newRepPw;
	}

	public void setOldPw(final String oldPw) {
		this.oldPw = oldPw;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
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
		if (StringUtils.isEmpty(this.oldPw)) {
			addErrors(errors, "etravel.error.new.pw.required");
		}
		if (StringUtils.isEmpty(this.newPw)) {
			addErrors(errors, "etravel.error.old.pw.required");
		} else if (this.newPw.trim().length() < 6) {
			addErrors(errors, "etravel.error.pw.length");

		} else if (!this.newPw.equals(this.newRepPw)) {
			addErrors(errors, "etravel.error.pw.notmached");

			this.newPw = EMPTYSTRING;
			this.newRepPw = EMPTYSTRING;

		}

		return errors;
	}

}
