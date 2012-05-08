/**
 * 
 */
package com.yd.etravel.web.tag.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.yd.etravel.service.util.IUserProfile;
import com.yd.etravel.util.IConstants.IUser;

/**
 * @author yora
 * 
 */
public class UserAccessTag extends TagSupport {

    private String property;
    private String name;

    public UserAccessTag() {
	super();
	this.name = IUser.USER_PROFILE;
    }

    public String getProperty() {
	return this.property;
    }

    public void setProperty(final String property) {
	this.property = property;
    }

    public String getName() {
	return this.name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
	if (condition()) {
	    return EVAL_BODY_INCLUDE;
	} else {
	    return SKIP_BODY;
	}
    }

    @Override
    public int doEndTag() throws JspException {
	return EVAL_PAGE;
    }

    @Override
    public void release() {
	super.release();
	this.name = IUser.USER_PROFILE;
    }

    private boolean condition() {
	final HttpServletRequest request = (HttpServletRequest) this.pageContext
		.getRequest();
	if (request.getSession(true).getAttribute(this.name) != null) {
	    final IUserProfile profile = (IUserProfile) request.getSession(true)
		    .getAttribute(getName());
	    return profile.hasFunction(this.property);
	}

	return false;
    }

}
