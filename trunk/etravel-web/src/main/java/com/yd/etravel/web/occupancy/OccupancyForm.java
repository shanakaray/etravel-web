/**
 * 
 */
package com.yd.etravel.web.occupancy;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.occupancy.Occupancy;
import com.yd.etravel.domain.roomtype.RoomType;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author Dharshana
 * 
 */
public class OccupancyForm extends BaseForm {

    private String name;
    private Long id;
    private String comments;
    private int adult;
    private int child;
    private int infant;
    private int totalPax;
    private boolean active;
    private List<Occupancy> allOccupancy = Collections.EMPTY_LIST;
    private List<RoomType> allRoomType = Collections.EMPTY_LIST;
    private Long[] roomTypeids;

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
     * @return the adult
     */
    public int getAdult() {
	return adult;
    }

    /**
     * @param adult
     *            the adult to set
     */
    public void setAdult(int adult) {
	this.adult = adult;
    }

    /**
     * @return the child
     */
    public int getChild() {
	return child;
    }

    /**
     * @param child
     *            the child to set
     */
    public void setChild(int child) {
	this.child = child;
    }

    /**
     * @return the infant
     */
    public int getInfant() {
	return infant;
    }

    /**
     * @param infant
     *            the infant to set
     */
    public void setInfant(int infant) {
	this.infant = infant;
    }

    /**
     * @return the totalPax
     */
    public int getTotalPax() {
	return totalPax;
    }

    /**
     * @param totalPax
     *            the totalPax to set
     */
    public void setTotalPax(int totalPax) {
	this.totalPax = totalPax;
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
     * @return the allOccupancy
     */
    public List<Occupancy> getAllOccupancy() {
	return allOccupancy;
    }

    /**
     * @param allOccupancy
     *            the allOccupancy to set
     */
    public void setAllOccupancy(List<Occupancy> allOccupancy) {
	this.allOccupancy = allOccupancy;
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

    /**
     * @return the roomTypeids
     */
    public Long[] getRoomTypeids() {
	return roomTypeids;
    }

    /**
     * @param roomTypeids
     *            the roomTypeids to set
     */
    public void setRoomTypeids(Long[] roomTypeids) {
	this.roomTypeids = roomTypeids;
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
	this.adult = 0;
	this.child = 0;
	this.infant = 0;
	this.totalPax = 0;
	this.active = true;
	this.id = null;
	allOccupancy = Collections.EMPTY_LIST;
	allRoomType = Collections.EMPTY_LIST;

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
	    addErrors(errors, "etravel.occupancy.name.required");
	}
	if (StringUtils.isEmpty(this.roomTypeids)) {
	    addErrors(errors, "etravel.occupancy.roomType.required");
	}

	return errors;

    }
}
