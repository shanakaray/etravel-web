/**
 * 
 */
package com.yd.etravel.domain.custom.user;

/**
 * @author yora
 * 
 */
public class UserSearchDTO {

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
	return roleIds;
    }

    public void setRoleIds(java.util.Set<Long> roleIds) {
	this.roleIds = roleIds;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

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

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    public boolean hasRolesIds() {
	return (roleIds != null && !roleIds.isEmpty());
    }

    public Long[] getIds() {
	return ids;
    }

    public void setIds(Long[] ids) {
	this.ids = ids;
    }

    public java.util.Set<String> getUserNameSet() {
	return userNameSet;
    }

    public void setUserNameSet(java.util.Set<String> userNameSet) {
	this.userNameSet = userNameSet;
    }

}
