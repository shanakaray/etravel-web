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

    public Function(String key) {
	this.key = key;
    }

    public Function(Long id) {
	super(id);
    }

    public Function(Long id, String key, String code) {
	super(id, "", code);
	this.key = key;
    }

    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((key == null) ? 0 : key.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Function))
	    return false;
	final Function other = (Function) obj;
	if (key == null) {
	    if (other.key != null)
		return false;
	} else if (!key.equals(other.key))
	    return false;
	return true;
    }

    public enum FunctionType {
	LOGIC("logic"), DB("database"), MENU("menu"), VIEW("view");
	private String name;

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	FunctionType(String name) {
	    this.name = name;
	}

    }

}
