/**
 * 
 */
package com.yd.etravel.domain.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.user.role.Role;
import com.yd.etravel.util.PasswordEncrypt;

@Entity
@Table(name = "T_USER")
public class User extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9107128069100674032L;

	@ManyToMany(cascade = { javax.persistence.CascadeType.ALL })
	@JoinTable(joinColumns = @JoinColumn(name = "USER_ID", nullable = false), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", nullable = false), uniqueConstraints = @UniqueConstraint(columnNames = {
			"USER_ID", "ROLE_ID" }))
	private List<Role> roles;

	@Column
	private String password;

	@Column
	private String address;

	@Column
	private String contact;

	@Column
	private String email;

	@Column
	private String firstName;

	@Column
	private String lastName;

	public void encriptPW() {
		this.password = PasswordEncrypt.encrypt(this.password);
	}

	public String getAddress() {
		return this.address;
	}

	public String getContact() {
		return this.contact;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Set<String> getFunctionSet() {
		final Set<String> key = new HashSet<String>();
		for (final Role role : getRoles()) {
			key.addAll(role.getFunctionNames());
		}
		return key;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public Set<Long> getRoleIds() {
		final Set<Long> set = new HashSet<Long>();
		final List<Role> roleList = getRoles();
		for (final Role role : roleList) {
			set.add(role.getId());
		}
		return set;
	}

	public Set<String> getRoleNames() {
		final Set<String> set = new HashSet<String>();
		final List<Role> roleList = getRoles();
		for (final Role role : roleList) {
			set.add(role.getName());
		}
		return set;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public boolean hasFunction(final String key) {
		for (final Role role : getRoles()) {
			if (role.hasFunction(key)) {
				return true;
			}
		}
		return false;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setContact(final String contact) {
		this.contact = contact;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setRoles(final List<Role> roles) {
		this.roles = roles;
	}

}
