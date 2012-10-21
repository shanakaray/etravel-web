/**
 * 
 */
package com.yd.etravel.domain.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseObject implements Serializable {
	private static final long serialVersionUID = -1568074688075491097L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column
	private String name;

	@Column
	private String code;

	@Column
	private Date createdDate;

	@Column
	private Date modifiedDate;

	@Column
	private String createdBy;

	@Column
	private String modifiedBy;

	@Column
	private String status;

	@Column
	private boolean active;

	public BaseObject() {
		super();
	}

	public BaseObject(final Long id, final String name, final String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public BaseObject(final Long id) {
		super();
		this.id = id;
	}

	public BaseObject(final String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(final String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

}
