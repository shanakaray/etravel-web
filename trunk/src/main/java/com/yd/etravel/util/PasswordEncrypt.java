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
	} catch (NoSuchAlgorithmException e) {
	    LOG.fatal(e.getMessage(), e);
	}
    }

    public static String encrypt(String plaintext) {
	try {
	    md.update(plaintext.getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
	    LOG.fatal(e.getMessage(), e);
	}
	byte raw[] = md.digest();
	String hash = (new BASE64Encoder()).encode(raw);
	return hash;
    }

    public static String decrypt(String plaintext) {
	try {
	    md.update(plaintext.getBytes());

	    byte raw[] = md.digest();
	    StringBuffer hash = null;

	    hash = new StringBuffer().append(new BASE64Decoder()
		    .decodeBuffer(new String(raw)));
	    return new String(hash);
	} catch (Exception e) {
	    LOG.fatal(e.getMessage(), e);
	}
	return "";
    }

}
