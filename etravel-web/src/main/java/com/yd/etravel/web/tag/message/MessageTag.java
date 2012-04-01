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

    public int doStartTag() throws JspException {
	ResponseUtils.write(pageContext, renderTag());
	return SKIP_BODY;
    }

    protected String renderTag() {
	StringBuffer buf = new StringBuffer("");
	if (pageContext.getSession().getAttribute(ICommon.INFO_MSG_KEY) != null) {
	    List<String> msgList = (List<String>) pageContext.getSession()
		    .getAttribute(ICommon.INFO_MSG_KEY);
	    for (String string : msgList) {
		buf.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>&#187;&nbsp;</strong>"
			+ string + "<BR/>");
	    }
	    msgList.clear();
	}

	return buf.toString();
    }

}
