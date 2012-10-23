/**
 * 
 */
package com.yd.etravel.web.tag.message;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

import com.yd.etravel.util.IConstants.ICommon;

/**
 * @author yora com.yd.etravel.web.tag.message.MessageTag
 */
public class MessageTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5046660280263949563L;

	@Override
	public int doStartTag() throws JspException {
		ResponseUtils.write(this.pageContext, renderTag());
		return SKIP_BODY;
	}

	protected String renderTag() {
		final StringBuffer buf = new StringBuffer("");
		if (this.pageContext.getSession().getAttribute(ICommon.INFO_MSG_KEY) != null) {
			final List<String> msgList = getMessageList();
			for (final String string : msgList) {
				buf.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>&#187;&nbsp;</strong>"
						+ string + "<BR/>");
			}
			msgList.clear();
		}

		return buf.toString();
	}

	@SuppressWarnings("unchecked")
	private List<String> getMessageList() {
		return (List<String>) this.pageContext.getSession().getAttribute(
				ICommon.INFO_MSG_KEY);
	}

}
