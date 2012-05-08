/**
 * 
 */
package com.yd.etravel.service.exception;

import com.yd.etravel.service.message.UIMesssage;

/**
 * @author XPPRESP3
 * 
 */
public class ValidationException extends ServiceException {

    /**
	 * 
	 */
    public ValidationException() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @param uiMesssage
     */
    public ValidationException(final UIMesssage uiMesssage) {
	super(uiMesssage);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param uiMesssage
     * @param arg1
     */
    public ValidationException(final UIMesssage uiMesssage, final Throwable arg1) {
	super(uiMesssage, arg1);
	// TODO Auto-generated constructor stub
    }

}
