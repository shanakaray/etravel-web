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

    public UserProfile(String username, String firstname, String lastname,
	    Set<String> roles) {
	super();
	this.username = username;
	this.firstname = firstname;
	this.lastname = lastname;
	this.roles = roles;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getFirstname() {
	if (!StringUtils.isEmpty(firstname)) {
	    String str = new String(new char[] { firstname.trim().charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(firstname.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return firstname;
	}
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	if (!StringUtils.isEmpty(lastname)) {
	    String str = new String(new char[] { lastname.trim().charAt(0) });
	    str = str.toUpperCase();
	    return new StringBuilder(lastname.trim()).replace(0, 1, str)
		    .toString();
	} else {
	    return lastname;
	}
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public Set<String> getRoles() {
	return roles;
    }

    public void setRoles(Set<String> roles) {
	this.roles = roles;
    }

    public boolean hasRole(String rolename) {
	return this.roles.contains(rolename);
    }

    public boolean hasAllRoles(Set<String> rolenames) {
	return this.roles.containsAll(rolenames);
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

    public String getNic() {
	return nic;
    }

    public void setNic(String nic) {
	this.nic = nic;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Set<String> getFunctionKeySet() {
	return functionKeySet;
    }

    public void setFunctionKeySet(Set<String> functionKeySet) {
	this.functionKeySet = functionKeySet;
    }

    public boolean hasFunction(String key) {
	return this.functionKeySet.contains(key);
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Map<String, String> getParams() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("user.firstname", getFirstname());
	map.put("user.lastname", getLastname());
	map.put("user.name", getUsername());
	map.put("user.password", getPassword());
	map.put("user.email", getEmail());
	return map;
    }

    public Map<Long, String> getAssignedHotels() {
	return assignedHotels;
    }

    public void putHotel(Long id, String name) {
	assignedHotels.put(id, name);
    }

}
