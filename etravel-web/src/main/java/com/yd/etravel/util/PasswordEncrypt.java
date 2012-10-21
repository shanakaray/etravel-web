/**
 * 
 */
package com.yd.etravel.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author yora
 * 
 */
public class PasswordEncrypt {
	static MessageDigest md = null;
	protected static final Log LOG = LogFactory.getLog(PasswordEncrypt.class);

	static {
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (final NoSuchAlgorithmException e) {
			LOG.fatal(e.getMessage(), e);
		}
	}

	public static String decrypt(final String plaintext) {
		try {
			md.update(plaintext.getBytes());

			final byte raw[] = md.digest();
			StringBuffer hash = null;

			hash = new StringBuffer().append(new BASE64Decoder()
					.decodeBuffer(new String(raw)));
			return new String(hash);
		} catch (final Exception e) {
			LOG.fatal(e.getMessage(), e);
		}
		return "";
	}

	public static String encrypt(final String plaintext) {
		try {
			md.update(plaintext.getBytes("UTF-8"));
		} catch (final UnsupportedEncodingException e) {
			LOG.fatal(e.getMessage(), e);
		}
		final byte raw[] = md.digest();
		final String hash = new BASE64Encoder().encode(raw);
		return hash;
	}

}
