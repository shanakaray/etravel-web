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
	/**
	 * 
	 */
	private static final long serialVersionUID = -9165463381263646588L;
	private Long id;
	private String code;
	private String name;
	private String comments;
	private boolean active;
	private List<RoomType> allRoomType = Collections.emptyList();
	private int maxPassengers;

	/**
	 * @return the active
	 */
	public boolean getActive() {
		return this.active;
	}

	/**
	 * @return the allRoomType
	 */
	public List<RoomType> getAllRoomType() {
		return this.allRoomType;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	public int getMaxPassengers() {
		return this.maxPassengers;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
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
		this.name = EMPTYSTRING;
		this.comments = EMPTYSTRING;
		this.active = true;
		this.id = 0L;
		this.allRoomType = Collections.emptyList();
		this.maxPassengers = 0;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * @param allRoomType
	 *            the allRoomType to set
	 */
	public void setAllRoomType(final List<RoomType> allRoomType) {
		this.allRoomType = allRoomType;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	public void setMaxPassengers(final int maxPassengers) {
		this.maxPassengers = maxPassengers;
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
	 * com.yd.etravel.web.common.BaseForm#validateBean(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		final ActionErrors errors = new ActionErrors();

		if (StringUtils.isEmpty(this.name)) {
			addErrors(errors, "etravel.roomType.name.required");
		}

		if (this.maxPassengers <= 0) {
			addErrors(errors, "etravel.roomtype.max.passengers");
		}

		return errors;

	}

}
