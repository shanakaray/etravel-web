/**
 * 
 */
package com.yd.etravel.service.message;

import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.exception.ValidationException;
import com.yd.etravel.util.IConstants;

/**
 * @author XPPRESP3
 * 
 */
public class ValidationHelper {

    // Utility method without parameter MessageHelper.
    public static UIMesssage getMessageHolder(String key, String bundleName,
	    String[] args) {
	UIMesssage mh = new UIMesssage();
	mh.addError(key, bundleName, args);
	return mh;
    }

    // Utility method without parameter MessageHelper.
    public static UIMesssage getMessageHolder(String key, String[] args) {
	UIMesssage mh = new UIMesssage();
	mh.addError(key, IConstants.IBundle.ETRAVEL, args);
	return mh;
    }

    public static UIMesssage getMessageHolder(String key) {
	UIMesssage mh = new UIMesssage();
	mh.addError(key, IConstants.IBundle.ETRAVEL, null);
	return mh;
    }

    public static void handleError(Object objectState, Exception e)
	    throws ServiceException {
	handleError(objectState, e, false);
    }

    public static void handleError(Object objectState, Exception e,
	    boolean silent) throws ServiceException {

	if (e != null) {
	    e.printStackTrace();

	    if (e instanceof RuntimeException) {

	    }
	    if (e instanceof ValidationException) {

	    } else if (e instanceof PersistenceException) {

	    } else {

	    }
	}

    }
}
