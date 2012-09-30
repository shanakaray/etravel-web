package com.yd.etravel.service.util;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public interface IUserProfile extends Serializable {
    public String getPassword();

    public void setPassword(String username);

    public String getUsername();

    public void setUsername(String username);

    public String getFirstname();

    public void setFirstname(String firstname);

    public String getLastname();

    public void setLastname(String lastname);

    public Set<String> getRoles();

    public void setRoles(Set<String> roles);

    public boolean hasRole(String rolename);

    public boolean hasAllRoles(Set<String> rolenames);

    public String getContact();

    public void setContact(String contact);

    public String getEmail();

    public void setEmail(String email);

    public String getNic();

    public void setNic(String nic);

    public String getAddress();

    public void setAddress(String adress);

    public Long getId();

    public void setId(Long id);

    public Set<String> getFunctionKeySet();

    public void setFunctionKeySet(Set<String> functionKeySet);

    public boolean hasFunction(String key);

    public Map<String, String> getParams();

    public Map<Long, String> getAssignedHotels();

    public void putHotel(Long id, String name);

}
