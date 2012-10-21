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

	/**
	 * 
	 */
	private static final long serialVersionUID = -7508228666756369501L;
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

	public Boolean getActive() {
		return this.active;
	}

	public String getAddress() {
		return this.address;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Long[] getIds() {
		return this.ids;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

	public java.util.Set<Long> getRoleIds() {
		return this.roleIds;
	}

	public String getStatus() {
		return this.status;
	}

	public java.util.Set<String> getUserNameSet() {
		return this.userNameSet;
	}

	public boolean hasRolesIds() {
		return (this.roleIds != null) && !this.roleIds.isEmpty();
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setIds(final Long[] ids) {
		this.ids = ids;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setRoleIds(final java.util.Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public void setUserNameSet(final java.util.Set<String> userNameSet) {
		this.userNameSet = userNameSet;
	}

}
