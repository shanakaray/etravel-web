/**
 * 
 */
package com.yd.etravel.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Dharshana
 * @version 1.00.000 14 Feb 2009 TODO To change the template for this generated
 *          type comment go to Window - Preferences - Java - Code Style - Code
 *          Templates
 * 
 */
public class SortedCollection {

	/**
	 * Sort a collection by a Date field of objects of it.
	 * 
	 * @param list
	 * @param field
	 * @param assendingOrder
	 * @return
	 * @throws UnableToSortException
	 * @throws Exception
	 */
	private static Log log = LogFactory.getLog(SortedCollection.class);

	public static Collection<?> orderByDateField(final Collection<?> list,
			final String field, final boolean assendingOrder) throws Exception {
		final ArrayList sortedList = new ArrayList();
		try {
			SortedCollection sortColl = new SortedCollection();
			final ArrayList unsortedList = (ArrayList) list;
			final ArrayList nullFieldList = new ArrayList();

			// String methodName = "get" + field.substring(0,1).toUpperCase() +
			// field.substring(1);
			if ((unsortedList != null) && (unsortedList.size() > 0)) {
				if (assendingOrder) {
					for (int i = 0; i < unsortedList.size(); i++) {
						final Date oldFieldValue = (Date) sortColl.getObject(
								unsortedList.get(i), field);
						if (oldFieldValue != null) {
							boolean inserted = false;
							for (int j = 0; j < sortedList.size(); j++) {
								if (oldFieldValue.compareTo((Date) sortColl
										.getObject(sortedList.get(j), field)) < 0) {
									sortedList.add(j, unsortedList.get(i));
									inserted = true;
									break;
								}
							}
							if (!inserted) {
								sortedList.add(unsortedList.get(i));
							}
						} else {
							nullFieldList.add(unsortedList.get(i));
						}
					}
				} else {
					for (int i = 0; i < unsortedList.size(); i++) {
						final Date oldFieldValue = (Date) sortColl.getObject(
								unsortedList.get(i), field);
						if (oldFieldValue != null) {
							boolean inserted = false;
							for (int j = 0; j < sortedList.size(); j++) {
								if (oldFieldValue.compareTo((Date) sortColl
										.getObject(sortedList.get(j), field)) > 0) {
									sortedList.add(j, unsortedList.get(i));
									inserted = true;
									break;
								}
							}
							if (!inserted) {
								sortedList.add(unsortedList.get(i));
							}
						} else {
							nullFieldList.add(unsortedList.get(i));
						}

					}
				}
				if ((nullFieldList != null) && (nullFieldList.size() > 0)) {
					sortedList.addAll(nullFieldList);
				}
			}
			sortColl = null;
			System.gc();
		} catch (final Exception e) {
			log.info("Sort List Error : " + e.getMessage());

		}
		return sortedList;
	}

	/**
	 * Sort a collection by a field of objects of it.
	 * 
	 * @param list
	 * @param field
	 * @param assendingOrder
	 * @return
	 * @throws UnableToSortException
	 * @throws Exception
	 */
	@SuppressWarnings(value = {})
	public static Collection orderByField(final Collection list,
			final String field, final boolean assendingOrder) throws Exception {
		final ArrayList sortedList = new ArrayList(0);
		try {
			SortedCollection sortColl = new SortedCollection();
			final ArrayList unsortedList = (ArrayList) list;
			final ArrayList nullFieldList = new ArrayList();

			// String methodName = "get" + field.substring(0,1).toUpperCase() +
			// field.substring(1);
			if ((unsortedList != null) && (unsortedList.size() > 0)) {
				if (assendingOrder) {
					for (int i = 0; i < unsortedList.size(); i++) {
						final Object oldFieldValue = sortColl.getObject(
								unsortedList.get(i), field);
						if (oldFieldValue != null) {
							boolean inserted = false;
							for (int j = 0; j < sortedList.size(); j++) {
								if (oldFieldValue
										.toString()
										.toUpperCase()
										.compareTo(
												sortColl.getObject(
														sortedList.get(j),
														field).toString()
														.toUpperCase()) < 0) {
									sortedList.add(j, unsortedList.get(i));
									inserted = true;
									break;
								}
							}
							if (!inserted) {
								sortedList.add(unsortedList.get(i));
							}
						} else {
							nullFieldList.add(unsortedList.get(i));
						}
					}
				} else {
					for (int i = 0; i < unsortedList.size(); i++) {
						final Object oldFieldValue = sortColl.getObject(
								unsortedList.get(i), field);
						if (oldFieldValue != null) {
							boolean inserted = false;
							for (int j = 0; j < sortedList.size(); j++) {
								if (oldFieldValue
										.toString()
										.toUpperCase()
										.compareTo(
												sortColl.getObject(
														sortedList.get(j),
														field).toString()
														.toUpperCase()) > 0) {
									sortedList.add(j, unsortedList.get(i));
									inserted = true;
									break;
								}
							}
							if (!inserted) {
								sortedList.add(unsortedList.get(i));
							}
						} else {
							nullFieldList.add(unsortedList.get(i));
						}
					}
				}
				if ((nullFieldList != null) && (nullFieldList.size() > 0)) {
					sortedList.addAll(nullFieldList);
				}

			}
			sortColl = null;
			System.gc();
		} catch (final Exception e) {

		}
		return sortedList;
	}

	public Object getObject(final Object obj, final String fld)
			throws Exception {
		Object fldObj = new Object();
		try {
			final String methodName = "get" + fld.substring(0, 1).toUpperCase()
					+ fld.substring(1);
			final int ind = methodName.indexOf(".");
			if (ind >= 0) {
				if (obj.getClass()
						.getMethod(methodName.substring(0, ind), null)
						.invoke(obj, null) != null) {
					return getObject(
							obj.getClass()
									.getMethod(methodName.substring(0, ind),
											null).invoke(obj, null),
							methodName.substring(ind + 1));
				} else {
					return null;
				}
			}
			if (obj != null) {
				fldObj = obj.getClass().getMethod(methodName, null)
						.invoke(obj, null);
			} else {
				fldObj = null;
			}
		} catch (final NoSuchMethodException e) {
			try {
				final String methodName = "is"
						+ fld.substring(0, 1).toUpperCase() + fld.substring(1);
				if (obj != null) {
					fldObj = obj.getClass().getMethod(methodName, null)
							.invoke(obj, null);
				} else {
					fldObj = null;
				}
				return fldObj;
			} catch (final NoSuchMethodException exp) {
				// TODO: handle exception
				log.info("NoSuchMethodException in getObject : "
						+ exp.getMessage());
				throw new Exception();
			}
		} catch (final IllegalAccessException e) {
			log.info("IllegalAccessException in getObject : " + e.getMessage());
			throw new Exception(e.getMessage(), e);
		} catch (final InvocationTargetException e) {
			// TODO: handle exception
			log.info("InvocationTargetException in getObject : "
					+ e.getMessage());
			throw new Exception(e.getMessage(), e);
		} catch (final Exception e) {
			// TODO: handle exception
			log.info("Exception in getObject : " + e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
		return fldObj;
	}

}
