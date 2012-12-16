/**
 * 
 */
package com.yd.etravel.persistence.exception;

@SuppressWarnings("serial")
public class PersistenceException extends Exception {

	public PersistenceException() {
	}

	public PersistenceException(final String arg0) {
		super(arg0);
	}

	public PersistenceException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public PersistenceException(final Throwable arg0) {
		super(arg0);
	}

}