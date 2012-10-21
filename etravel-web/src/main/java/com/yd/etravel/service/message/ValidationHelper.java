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
	public static UIMesssage getMessageHolder(final String key,
			final String bundleName, final String[] args) {
		final UIMesssage mh = new UIMesssage();
		mh.addError(key, bundleName, args);
		return mh;
	}

	// Utility method without parameter MessageHelper.
	public static UIMesssage getMessageHolder(final String key,
			final String[] args) {
		final UIMesssage mh = new UIMesssage();
		mh.addError(key, IConstants.IBundle.ETRAVEL, args);
		return mh;
	}

	public static UIMesssage getMessageHolder(final String key) {
		final UIMesssage mh = new UIMesssage();
		mh.addError(key, IConstants.IBundle.ETRAVEL, null);
		return mh;
	}

	public static void handleError(final Object objectState, final Exception e)
			throws ServiceException {
		handleError(objectState, e, false);
	}

	public static void handleError(final Object objectState, final Exception e,
			final boolean silent) throws ServiceException {

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
