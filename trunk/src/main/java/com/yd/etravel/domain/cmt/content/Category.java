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

    public Category() {
    }

    /**
     * @param id
     * @param name
     * @param code
     */
    public Category(Long id, String name, String code) {
	super(id, name, code);
    }

}
