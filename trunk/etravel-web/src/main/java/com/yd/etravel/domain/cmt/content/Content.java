/**
 * 
 */
package com.yd.etravel.domain.cmt.content;

import com.yd.etravel.domain.common.BaseObject;

/**
 * @author yora com.yd.etravel.domain.cmt.content.Content
 * 
 */
/**
 * @hibernate.class table="T_CONTENT"
 */
public class Content extends BaseObject {

    private String text;
    private String url;
    private Category category;
    private Long pk;
    private String object;

    /**
	 * 
	 */
    public Content() {
    }

    /**
     * @param id
     * @param name
     * @param code
     */
    public Content(final Long id, final String name, final String code) {
	super(id, name, code);
	// TODO Auto-generated constructor stub
    }

    /**
     * @hibernate.many-to-one cascade="save-update"
     */
    public Category getCategory() {
	return this.category;
    }

    public void setCategory(final Category category) {
	this.category = category;
    }

    /**
     * @hibernate.property length="1000"
     */
    public String getText() {
	return this.text;
    }

    public void setText(final String text) {
	this.text = text;
    }

    /**
     * @hibernate.property
     */
    public String getUrl() {
	return this.url;
    }

    public void setUrl(final String url) {
	this.url = url;
    }

    /**
     * @hibernate.property
     */
    public Long getPk() {
	return this.pk;
    }

    public void setPk(final Long pk) {
	this.pk = pk;
    }

    /**
     * @hibernate.property
     */
    public String getObject() {
	return this.object;
    }

    public void setObject(final String object) {
	this.object = object;
    }

}
