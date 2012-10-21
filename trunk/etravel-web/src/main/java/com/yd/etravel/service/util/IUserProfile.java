package com.yd.etravel.service.util;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public interface IUserProfile extends Serializable {
	public String getAddress();

	public Map<Long, String> getAssignedHotels();

	public String getContact();

	public String getEmail();

	public String getFirstname();

	public Set<String> getFunctionKeySet();

	public Long getId();

	public String getLastname();

	public String getNic();

	public Map<String, String> getParams();

	public String getPassword();

	public Set<String> getRoles();

	public String getUsername();

	public boolean hasAllRoles(Set<String> rolenames);

	public boolean hasFunction(String key);

	public boolean hasRole(String rolename);

	public void putHotel(Long id, String name);

	public void setAddress(String adress);

	public void setContact(String contact);

	public void setEmail(String email);

	public void setFirstname(String firstname);

	public void setFunctionKeySet(Set<String> functionKeySet);

	public void setId(Long id);

	public void setLastname(String lastname);

	public void setNic(String nic);

	public void setPassword(String username);

	public void setRoles(Set<String> roles);

	public void setUsername(String username);

}
