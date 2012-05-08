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
	return this.name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public List<Role> getAllRoles() {
	return this.allRoles;
    }

    public void setAllRoles(final List<Role> allRoles) {
	this.allRoles = allRoles;
    }

    public boolean isActive() {
	return this.active;
    }

    public void setActive(final boolean active) {
	this.active = active;
    }

    public String getDiscription() {
	return this.discription;
    }

    public void setDiscription(final String discription) {
	this.discription = discription;
    }

    public Long getId() {
	return this.id;
    }

    public void setId(final Long id) {
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
    public void resetBean(final ActionMapping mapping, final HttpServletRequest request) {
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
    public ActionErrors validateBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	final ActionErrors errors = new ActionErrors();

	if (StringUtils.isEmpty(this.name)) {
	    addErrors(errors, "etravel.error.rolename.required");
	}
	return errors;
    }

}
