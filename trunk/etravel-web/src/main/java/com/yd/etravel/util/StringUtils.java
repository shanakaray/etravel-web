package com.yd.etravel.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yd.etravel.service.util.UserProfile;
import com.yd.etravel.util.IConstants.ICommon;

public class StringUtils implements ICommon {

	public static int chompLength = 32;

	public static Map<String, String> getProperties(final Object object) {
		final Map<String, String> map = new HashMap<String, String>();
		final Method fields[] = object.getClass().getMethods();
		try {
			for (final Method method : fields) {
				if (method.getName().startsWith("get")
						&& method.getReturnType().equals(String.class)) {
					map.put(method.getName(), (String) object.getClass()
							.getMethod(method.getName()).invoke(object, null));
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();

		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(final Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String) {
			return ((String) obj).trim().length() <= 0;
		} else if (obj instanceof Date) {
			return ((Date) obj).getTime() <= 0;
		} else if (obj instanceof Long) {
			return ((Long) obj).longValue() <= 0;
		} else if (obj.getClass().isArray()) {
			return Array.getLength(obj) <= 0;
		} else if (obj instanceof Collection) {
			return ((Collection) obj).size() <= 0;
		} else if (obj instanceof Iterator) {
			return !((Iterator) obj).hasNext();
		} else if (obj instanceof Map) {
			return ((Map) obj).entrySet().size() <= 0;
		} else if (obj instanceof Enumeration) {
			return !((Enumeration) obj).hasMoreElements();
		}
		return false; // Object not null!
	}

	public static boolean isEmpty(final String value) {
		boolean flag = false;
		if ((value == null) || value.trim().equals(ICommon.EMPTYSTRING)) {
			flag = true;
		}
		return flag;
	}

	public static boolean isNotEmpty(final Object obj) {
		return !isEmpty(obj);
	}

	public static boolean isValidEmail(final String email) {
		final Pattern p = Pattern.compile(EMAILVALIDATION);
		final Matcher m = p.matcher(email);
		// check whether match is found
		return m.matches();
	}

	public static void main(final String[] args) {
		getProperties(new UserProfile());
	}

	/**
	 * HTML encodes the string, replaces the reserved characters with html code
	 * equivalent
	 * 
	 * @param pText
	 * @return html encoded string
	 * @throws UnsupportedEncodingException
	 */
	public static String urlEncode(final String pText)
			throws UnsupportedEncodingException {

		return URLEncoder.encode(pText, "UTF-8");

	}

}
