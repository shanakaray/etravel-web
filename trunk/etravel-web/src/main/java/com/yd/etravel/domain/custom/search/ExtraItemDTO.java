/**
 * 
 */
package com.yd.etravel.domain.custom.search;

import java.io.Serializable;

import com.yd.etravel.domain.extraitem.ExtraItem;

/**
 * @author Dharshana
 * 
 */
public class ExtraItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2714566480162127288L;
	private ExtraItem extraItem;

	/**
	 * @return the extraItem
	 */
	public ExtraItem getExtraItem() {
		return this.extraItem;
	}

	/**
	 * @param extraItem
	 *            the extraItem to set
	 */
	public void setExtraItem(final ExtraItem extraItem) {
		this.extraItem = extraItem;
	}

}
