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

    private String oldPw;
    private String newPw;
    private String newRepPw;
    private Long userId;

    public String getOldPw() {
	return this.oldPw;
    }

    public void setOldPw(final String oldPw) {
	this.oldPw = oldPw;
    }

    public String getNewPw() {
	return this.newPw;
    }

    public void setNewPw(final String newPw) {
	this.newPw = newPw;
    }

    public String getNewRepPw() {
	return this.newRepPw;
    }

    public void setNewRepPw(final String newRepPw) {
	this.newRepPw = newRepPw;
    }

    public Long getUserId() {
	return this.userId;
    }

    public void setUserId(final Long userId) {
	this.userId = userId;
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
	this.oldPw = EMPTY_STRING;
	this.newPw = EMPTY_STRING;
	this.newRepPw = EMPTY_STRING;
	this.userId = 0L;
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

	    this.newPw = EMPTY_STRING;
	    this.newRepPw = EMPTY_STRING;

	}

	return errors;
    }

}
