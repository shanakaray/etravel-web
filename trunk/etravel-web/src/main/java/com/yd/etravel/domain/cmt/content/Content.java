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

	private static final long serialVersionUID = 4577466925804323125L;
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
	}

	/**
	 * @hibernate.many-to-one cascade="save-update"
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * @hibernate.property
	 */
	public String getObject() {
		return this.object;
	}

	/**
	 * @hibernate.property
	 */
	public Long getPk() {
		return this.pk;
	}

	/**
	 * @hibernate.property length="1000"
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @hibernate.property
	 */
	public String getUrl() {
		return this.url;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public void setObject(final String object) {
		this.object = object;
	}

	public void setPk(final Long pk) {
		this.pk = pk;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

}
