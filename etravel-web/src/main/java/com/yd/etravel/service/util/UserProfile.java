/**
 *  
 */
package com.yd.etravel.service.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.yd.etravel.util.StringUtils;

/**
 * @author yora
 * 
 */
public class UserProfile implements IUserProfile {

    private String username;
    private String firstname;
    private String lastname;
    private String contact;
    private String email;
    private String nic;
    private String address;
    private String password;

    private Long id;

    private Set<String> roles = Collections.EMPTY_SET;
    private Set<String> functionKeySet = Collections.EMPTY_SET;
    private Map<Long, String> assignedHotels;

    public UserProfile() {
	this.assignedHotels = new HashMap<Long, String>();

    }

    public UserProfile(final String username, final String firstname, final String lastname,
	    final Set<String> roles) {
	super();
	this.username = username;
	this.firstname = firstname;
	this.lastname = lastname;
	this.roles = roles;
    }

    @Override
    public String getUsername() {
	return this.username;
    }

    @Override
    public void setUsername(final String username) {
	this.username = username;
    }

    @Override
    public String getFirstname() {
	if (!StringUtils.isEmpty(this.firstname)) {
	    String str = new String(new char[] { this.firstname.trim().charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(this.firstname.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return this.firstname;
	}
    }

    @Override
    public void setFirstname(final String firstname) {
	this.firstname = firstname;
    }

    @Override
    public String getLastname() {
	if (!StringUtils.isEmpty(this.lastname)) {
	    String str = new String(new char[] { this.lastname.trim().charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(this.lastname.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return this.lastname;
	}
    }

    @Override
    public void setLastname(final String lastname) {
	this.lastname = lastname;
    }

    @Override
    public Set<String> getRoles() {
	return this.roles;
    }

    @Override
    public void setRoles(final Set<String> roles) {
	this.roles = roles;
    }

    @Override
    public boolean hasRole(final String rolename) {
	return this.roles.contains(rolename);
    }

    @Override
    public boolean hasAllRoles(final Set<String> rolenames) {
	return this.roles.containsAll(rolenames);
    }

    @Override
    public String getContact() {
	return this.contact;
    }

    @Override
    public void setContact(final String contact) {
	this.contact = contact;
    }

    @Override
    public String getEmail() {
	return this.email;
    }

    @Override
    public void setEmail(final String email) {
	this.email = email;
    }

    @Override
    public String getNic() {
	return this.nic;
    }

    @Override
    public void setNic(final String nic) {
	this.nic = nic;
    }

    @Override
    public String getAddress() {
	return this.address;
    }

    @Override
    public void setAddress(final String address) {
	this.address = address;
    }

    @Override
    public Long getId() {
	return this.id;
    }

    @Override
    public void setId(final Long id) {
	this.id = id;
    }

    @Override
    public Set<String> getFunctionKeySet() {
	return this.functionKeySet;
    }

    @Override
    public void setFunctionKeySet(final Set<String> functionKeySet) {
	this.functionKeySet = functionKeySet;
    }

    @Override
    public boolean hasFunction(final String key) {
	return this.functionKeySet.contains(key);
    }

    @Override
    public String getPassword() {
	return this.password;
    }

    @Override
    public void setPassword(final String password) {
	this.password = password;
    }

    @Override
    public Map<String, String> getParams() {
	final Map<String, String> map = new HashMap<String, String>();
	map.put("user.firstname", getFirstname());
	map.put("user.lastname", getLastname());
	map.put("user.name", getUsername());
	map.put("user.password", getPassword());
	map.put("user.email", getEmail());
	return map;
    }

    @Override
    public Map<Long, String> getAssignedHotels() {
	return this.assignedHotels;
    }

    @Override
    public void putHotel(final Long id, final String name) {
	this.assignedHotels.put(id, name);
    }

}
