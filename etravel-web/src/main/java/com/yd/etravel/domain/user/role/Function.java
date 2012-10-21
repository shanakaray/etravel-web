/**
 * 
 */
package com.yd.etravel.domain.user.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Oct 17, 2009 : 8:41:47 AM Type :
 *         com.yd.etravel.domain.user.role.Function
 * 
 */

@Entity
@Table(name = "T_FUNC")
public class Function extends BaseObject {

	@Column(name = "FUNC_KEY", unique = true, nullable = false)
	private String key;

	@Column(name = "FUNC_TYPE")
	private String type;

	public Function() {

	}

	public Function(final String key) {
		this.key = key;
	}

	public Function(final Long id) {
		super(id);
	}

	public Function(final Long id, final String key, final String code) {
		super(id, "", code);
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.key == null ? 0 : this.key.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Function)) {
			return false;
		}
		final Function other = (Function) obj;
		if (this.key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!this.key.equals(other.key)) {
			return false;
		}
		return true;
	}

	public enum FunctionType {
		LOGIC("logic"), DB("database"), MENU("menu"), VIEW("view");
		private String name;

		public String getName() {
			return this.name;
		}

		public void setName(final String name) {
			this.name = name;
		}

		FunctionType(final String name) {
			this.name = name;
		}

	}

}
