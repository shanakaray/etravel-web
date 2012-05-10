/**
 * 
 */
package com.yd.etravel.web.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.user.role.Function;
import com.yd.etravel.domain.user.role.Role;
import com.yd.etravel.web.common.BaseForm;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Oct 20, 2009 : 6:51:58 AM Type :
 *         com.yd.etravel.web.user.FunctionForm
 * 
 */

public class FunctionForm extends BaseForm {

    private List<Function> fuctionList = Collections.EMPTY_LIST;
    private Long roleId;
    private Long[] functionIds;
    private List<Role> allRoles = Collections.EMPTY_LIST;
    private Long[] allFunctionIds;

    /**
	 * 
	 */
    public FunctionForm() {
	this.functionIds = new Long[0];
    }

    public List<Function> getFuctionList() {

	return this.fuctionList;
    }

    public Set<Function> getSelectedFuctionList() {
	final Set<Function> sfunc = new HashSet<Function>();
	if (getFunctionIds() != null) {
	    for (final Long s : getFunctionIds()) {
		for (final Function f : this.fuctionList) {
		    if (f.getId().equals(s)) {
			sfunc.add(f);
			break;
		    }
		}
	    }
	}
	return sfunc;
    }

    public List<Function> getRemainFuctionList() {
	final List<Function> sfunc = new ArrayList<Function>();
	final Long stmp[] = getFunctionIds();
	for (final Function f : this.fuctionList) {
	    boolean flag = false;
	    if (stmp != null) {
		for (final Long s : stmp) {
		    if (f.getId().equals(s)) {
			flag = true;
			break;
		    }
		}
	    }
	    if (!flag) {
		sfunc.add(f);
	    }
	}
	return sfunc;
    }

    public void setFuctionList(final List<Function> fuctionList) {
	this.fuctionList = fuctionList;
    }

    public Long getRoleId() {
	return this.roleId;
    }

    public void setRoleId(final Long roleId) {
	this.roleId = roleId;
    }

    public Long[] getFunctionIds() {
	return this.functionIds;
    }

    public void setFunctionIds(final Long[] functionIds) {
	this.functionIds = functionIds;
    }

    public void setFunctionIds(final List<Function> functionIds) {
	this.functionIds = null;
	final List<Long> llist = new ArrayList<Long>();
	for (final Function f : functionIds) {
	    llist.add(f.getId());
	}

	if (!llist.isEmpty()) {
	    this.functionIds = llist.toArray(new Long[0]);
	}
    }

    public List<Role> getAllRoles() {
	return this.allRoles;
    }

    public void setAllRoles(final List<Role> allRoles) {
	this.allRoles = allRoles;
    }

    public Long[] getAllFunctionIds() {
	return this.allFunctionIds;
    }

    public void setAllFunctionIds(final Long[] allFunctionIds) {
	this.allFunctionIds = allFunctionIds;
    }

    @Override
    public void resetBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	this.fuctionList = Collections.EMPTY_LIST;
	this.roleId = -1l;
	this.functionIds = new Long[0];
	this.allFunctionIds = new Long[0];
	this.allRoles = Collections.EMPTY_LIST;

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
	// TODO Auto-generated method stub
	return null;
    }

}
