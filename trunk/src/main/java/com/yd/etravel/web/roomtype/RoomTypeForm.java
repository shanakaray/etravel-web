/**
 * 
 */
package com.yd.etravel.web.roomtype;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author Dharshana
 * 
 */
public class RoomTypeForm extends BaseForm {
    private Long id;
    private String code;
    private String name;
    private String comments;
    private boolean active;
    private List<RoomType> allRoomType = Collections.EMPTY_LIST;
    private int maxPassengers;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
	return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
	this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the comments
     */
    public String getComments() {
	return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(String comments) {
	this.comments = comments;
    }

    /**
     * @return the active
     */
    public boolean getActive() {
	return active;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(boolean active) {
	this.active = active;
    }

    /**
     * @return the allRoomType
     */
    public List<RoomType> getAllRoomType() {
	return allRoomType;
    }

    /**
     * @param allRoomType
     *            the allRoomType to set
     */
    public void setAllRoomType(List<RoomType> allRoomType) {
	this.allRoomType = allRoomType;
    }

    public int getMaxPassengers() {
	return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
	this.maxPassengers = maxPassengers;
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
	// TODO Auto-generated method stub
	this.name = EMPTY_STRING;
	this.comments = EMPTY_STRING;
	this.active = true;
	this.id = 0L;
	this.allRoomType = Collections.EMPTY_LIST;
	this.maxPassengers = 0;
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
	    addErrors(errors, "etravel.roomType.name.required");
	}

	if (maxPassengers <= 0) {
	    addErrors(errors, "etravel.roomtype.max.passengers");
	}

	return errors;

    }

}
