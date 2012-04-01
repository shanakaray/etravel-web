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
	return property;
    }

    public void setProperty(String property) {
	this.property = property;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int doStartTag() throws JspException {
	if (condition())
	    return (EVAL_BODY_INCLUDE);
	else
	    return (SKIP_BODY);
    }

    public int doEndTag() throws JspException {
	return (EVAL_PAGE);
    }

    public void release() {
	super.release();
	this.name = IUser.USER_PROFILE;
    }

    private boolean condition() {
	HttpServletRequest request = (HttpServletRequest) pageContext
		.getRequest();
	if (request.getSession(true).getAttribute(name) != null) {
	    IUserProfile profile = (IUserProfile) request.getSession(true)
		    .getAttribute(getName());
	    return profile.hasFunction(property);
	}

	return false;
    }

}
