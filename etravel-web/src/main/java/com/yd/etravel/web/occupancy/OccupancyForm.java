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
    private List<Occupancy> allOccupancy = Collections.emptyList();
    private List<RoomType> allRoomType = Collections.emptyList();
    private Long[] roomTypeids;

    /**
     * @return the id
     */
    public Long getId() {
	return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
	this.id = id;
    }

    /**
     * @return the comments
     */
    public String getComments() {
	return this.comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(final String comments) {
	this.comments = comments;
    }

    /**
     * @return the adult
     */
    public int getAdult() {
	return this.adult;
    }

    /**
     * @param adult
     *            the adult to set
     */
    public void setAdult(final int adult) {
	this.adult = adult;
    }

    /**
     * @return the child
     */
    public int getChild() {
	return this.child;
    }

    /**
     * @param child
     *            the child to set
     */
    public void setChild(final int child) {
	this.child = child;
    }

    /**
     * @return the infant
     */
    public int getInfant() {
	return this.infant;
    }

    /**
     * @param infant
     *            the infant to set
     */
    public void setInfant(final int infant) {
	this.infant = infant;
    }

    /**
     * @return the totalPax
     */
    public int getTotalPax() {
	return this.totalPax;
    }

    /**
     * @param totalPax
     *            the totalPax to set
     */
    public void setTotalPax(final int totalPax) {
	this.totalPax = totalPax;
    }

    /**
     * @return the active
     */
    public boolean getActive() {
	return this.active;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(final boolean active) {
	this.active = active;
    }

    /**
     * @return the allOccupancy
     */
    public List<Occupancy> getAllOccupancy() {
	return this.allOccupancy;
    }

    /**
     * @param allOccupancy
     *            the allOccupancy to set
     */
    public void setAllOccupancy(final List<Occupancy> allOccupancy) {
	this.allOccupancy = allOccupancy;
    }

    /**
     * @return the allRoomType
     */
    public List<RoomType> getAllRoomType() {
	return this.allRoomType;
    }

    /**
     * @param allRoomType
     *            the allRoomType to set
     */
    public void setAllRoomType(final List<RoomType> allRoomType) {
	this.allRoomType = allRoomType;
    }

    /**
     * @return the roomTypeids
     */
    public Long[] getRoomTypeids() {
	return this.roomTypeids;
    }

    /**
     * @param roomTypeids
     *            the roomTypeids to set
     */
    public void setRoomTypeids(final Long[] roomTypeids) {
	this.roomTypeids = roomTypeids;
    }

    /**
     * @return the name
     */
    public String getName() {
	return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
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
    public void resetBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	// TODO Auto-generated method stub

	this.name = EMPTY_STRING;
	this.comments = EMPTY_STRING;
	this.adult = 0;
	this.child = 0;
	this.infant = 0;
	this.totalPax = 0;
	this.active = true;
	this.id = null;
	this.allOccupancy = Collections.emptyList();
	this.allRoomType = Collections.emptyList();

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
	    addErrors(errors, "etravel.occupancy.name.required");
	}
	if (StringUtils.isEmpty(this.roomTypeids)) {
	    addErrors(errors, "etravel.occupancy.roomType.required");
	}

	return errors;

    }
}
