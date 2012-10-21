/**
 * 
 */
package com.yd.etravel.domain.occupancy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.roomtype.RoomType;

@Entity
@Table(name = "T_OCCUPANCY")
public class Occupancy extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2938068519647492817L;

	@Column
	private String comments;

	@Column
	private int adult;

	@Column
	private int child;

	@Column
	private int infant;

	@Column
	private int totalPax;

	@ManyToOne
	private RoomType roomType;

	public int getAdult() {
		return this.adult;
	}

	public int getChild() {
		return this.child;
	}

	public String getComments() {
		return this.comments;
	}

	public int getInfant() {
		return this.infant;
	}

	public RoomType getRoomType() {
		return this.roomType;
	}

	public int getTotalPax() {
		return this.totalPax;
	}

	public void setAdult(final int adult) {
		this.adult = adult;
	}

	public void setChild(final int child) {
		this.child = child;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public void setInfant(final int infant) {
		this.infant = infant;
	}

	public void setRoomType(final RoomType roomType) {
		this.roomType = roomType;
	}

	public void setTotalPax(final int totalPax) {
		this.totalPax = totalPax;
	}

}
