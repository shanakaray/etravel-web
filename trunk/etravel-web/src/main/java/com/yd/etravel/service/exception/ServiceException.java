/**
 * 
 */
package com.yd.etravel.service.exception;

import com.yd.etravel.service.message.UIMesssage;

/**
 * @author XPPRESP3
 * 
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception {

    private UIMesssage uiMesssage = null;

    /**
	 * 
	 */
    public ServiceException() {
	// TODO Auto-generated constructor stub
    }

    public ServiceException(final UIMesssage uiMesssage) {
	super();
	this.uiMesssage = uiMesssage;
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ServiceException(final UIMesssage uiMesssage, final Throwable arg1) {
	super(arg1);
	this.uiMesssage = uiMesssage;
    }

    public UIMesssage getUIMessage() {
	return this.uiMesssage;
    }

}
