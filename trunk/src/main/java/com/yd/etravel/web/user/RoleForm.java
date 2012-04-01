/**
 * 
 */
package com.yd.etravel.web.user;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.user.role.Role;
import com.yd.etravel.util.IConstants.ICommon;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author yora com.yd.etravel.web.user.RoleForm
 * 
 */
public class RoleForm extends BaseForm {
    private Long id;
    private String name;
    private String discription;
    private List<Role> allRoles = Collections.EMPTY_LIST;
    private boolean active;

    /**
	 * 
	 */
    public RoleForm() {

    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Role> getAllRoles() {
	return allRoles;
    }

    public void setAllRoles(List<Role> allRoles) {
	this.allRoles = allRoles;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public String getDiscription() {
	return discription;
    }

    public void setDiscription(String discription) {
	this.discription = discription;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void resetBean(ActionMapping mapping, HttpServletRequest request) {
	this.name = ICommon.EMPTY_STRING;
	this.discription = ICommon.EMPTY_STRING;
	this.active = false;
	this.allRoles = Collections.EMPTY_LIST;
	this.id = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#validateBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validateBean(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();

	if (StringUtils.isEmpty(this.name)) {
	    addErrors(errors, "etravel.error.rolename.required");
	}
	return errors;
    }

}
