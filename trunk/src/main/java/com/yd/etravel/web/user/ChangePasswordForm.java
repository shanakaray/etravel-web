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
	return oldPw;
    }

    public void setOldPw(String oldPw) {
	this.oldPw = oldPw;
    }

    public String getNewPw() {
	return newPw;
    }

    public void setNewPw(String newPw) {
	this.newPw = newPw;
    }

    public String getNewRepPw() {
	return newRepPw;
    }

    public void setNewRepPw(String newRepPw) {
	this.newRepPw = newRepPw;
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
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
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	oldPw = EMPTY_STRING;
	newPw = EMPTY_STRING;
	newRepPw = EMPTY_STRING;
	userId = 0L;
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
	if (StringUtils.isEmpty(oldPw)) {
	    addErrors(errors, "etravel.error.new.pw.required");
	}
	if (StringUtils.isEmpty(newPw)) {
	    addErrors(errors, "etravel.error.old.pw.required");
	} else if (newPw.trim().length() < 6) {
	    addErrors(errors, "etravel.error.pw.length");

	} else if (!newPw.equals(newRepPw)) {
	    addErrors(errors, "etravel.error.pw.notmached");

	    newPw = EMPTY_STRING;
	    newRepPw = EMPTY_STRING;

	}

	return errors;
    }

}
