/**
 * 
 */
package com.yd.etravel.web.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.web.common.BaseForm;

/**
 * @author yora
 * 
 */
public class LogoutForm extends BaseForm {

    /**
	 * 
	 */
    public LogoutForm() {
	// TODO Auto-generated constructor stub
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
	// TODO Auto-generated method stub

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
	// TODO Auto-generated method stub
	return null;
    }

}
