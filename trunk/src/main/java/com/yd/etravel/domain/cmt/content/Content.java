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
    public Content(Long id, String name, String code) {
	super(id, name, code);
	// TODO Auto-generated constructor stub
    }

    /**
     * @hibernate.many-to-one cascade="save-update"
     */
    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    /**
     * @hibernate.property length="1000"
     */
    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    /**
     * @hibernate.property
     */
    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    /**
     * @hibernate.property
     */
    public Long getPk() {
	return pk;
    }

    public void setPk(Long pk) {
	this.pk = pk;
    }

    /**
     * @hibernate.property
     */
    public String getObject() {
	return object;
    }

    public void setObject(String object) {
	this.object = object;
    }

}
