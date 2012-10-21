/**
 * 
 */
package com.yd.etravel.domain.custom.user;

import java.io.Serializable;

/**
 * @author yora
 * 
 */
public class UserSearchDTO implements Serializable {

	private java.util.Set<Long> roleIds;
	private java.util.Set<String> userNameSet;
	private Long[] ids;
	private String name;
	private String password;
	private String address;
	private String firstName;
	private String lastName;
	private String status;
	private Boolean active;

	public java.util.Set<Long> getRoleIds() {
		return this.roleIds;
	}

	public void setRoleIds(final java.util.Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public boolean hasRolesIds() {
		return this.roleIds != null && !this.roleIds.isEmpty();
	}

	public Long[] getIds() {
		return this.ids;
	}

	public void setIds(final Long[] ids) {
		this.ids = ids;
	}

	public java.util.Set<String> getUserNameSet() {
		return this.userNameSet;
	}

	public void setUserNameSet(final java.util.Set<String> userNameSet) {
		this.userNameSet = userNameSet;
	}

}
