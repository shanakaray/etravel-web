/**
 * 
 */
package com.yd.etravel.domain.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.user.role.Role;
import com.yd.etravel.util.PasswordEncrypt;

@Entity
@Table(name = "T_USER")
public class User extends BaseObject {

    @ManyToMany
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

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getContact() {
	return contact;
    }

    public void setContact(String contact) {
	this.contact = contact;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public List<Role> getRoles() {
	return roles;
    }

    public void setRoles(List<Role> roles) {
	this.roles = roles;
    }

    public Set<String> getRoleNames() {
	Set<String> set = new HashSet<String>();
	List<Role> roleList = getRoles();
	for (Role role : roleList) {
	    set.add(role.getName());
	}
	return set;
    }

    public Set<Long> getRoleIds() {
	Set<Long> set = new HashSet<Long>();
	List<Role> roleList = getRoles();
	for (Role role : roleList) {
	    set.add(role.getId());
	}
	return set;
    }

    public boolean hasFunction(String key) {
	for (Role role : getRoles()) {
	    if (role.hasFunction(key)) {
		return true;
	    }
	}
	return false;
    }

    public Set<String> getFunctionSet() {
	Set<String> key = new HashSet<String>();
	for (Role role : getRoles()) {
	    key.addAll(role.getFunctionNames());
	}
	return key;
    }

    public void encriptPW() {
	this.password = PasswordEncrypt.encrypt(this.password);
    }

}
