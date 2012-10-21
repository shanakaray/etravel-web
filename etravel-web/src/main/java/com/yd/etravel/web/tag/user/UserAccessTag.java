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

	/**
	 * 
	 */
	private static final long serialVersionUID = 2618721780013697865L;
	private String property;
	private String name;

	public UserAccessTag() {
		super();
		this.name = IUser.USER_PROFILE;
	}

	private boolean condition() {
		final HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		if (request.getSession(true).getAttribute(this.name) != null) {
			final IUserProfile profile = (IUserProfile) request
					.getSession(true).getAttribute(getName());
			return profile.hasFunction(this.property);
		}

		return false;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		if (condition()) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	public String getName() {
		return this.name;
	}

	public String getProperty() {
		return this.property;
	}

	@Override
	public void release() {
		super.release();
		this.name = IUser.USER_PROFILE;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setProperty(final String property) {
		this.property = property;
	}

}
