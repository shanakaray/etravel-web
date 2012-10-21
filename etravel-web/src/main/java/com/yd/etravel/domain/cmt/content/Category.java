/**
 * 
 */
package com.yd.etravel.domain.cmt.content;

import com.yd.etravel.domain.common.BaseObject;

/**
 * @author yora
 * 
 */
/**
 * @hibernate.class table="T_CATEGORY"
 */
public class Category extends BaseObject {

	private static final long serialVersionUID = 6473139219266050582L;

	public Category() {
	}

	/**
	 * @param id
	 * @param name
	 * @param code
	 */
	public Category(final Long id, final String name, final String code) {
		super(id, name, code);
	}

}
