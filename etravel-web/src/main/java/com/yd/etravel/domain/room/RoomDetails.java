/**
 * 
 */
package com.yd.etravel.domain.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;

@Entity
@Table(name = "T_ROOM_DETAIL")
public class RoomDetails extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8368552374015734547L;

	@ManyToOne
	private Room room;

	@Column
	private int doorNumber;

	@Column
	private boolean smoking;

	@Column
	private String comments;

	@Column
	private String bedType;

	/**
	 * @hibernate.property
	 * @return the bedType
	 */
	public String getBedType() {
		return this.bedType;
	}

	/**
	 * @hibernate.property
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @hibernate.property
	 * @return the doorNumber
	 */
	public int getDoorNumber() {
		return this.doorNumber;
	}

	public Room getRoom() {
		return this.room;
	}

	/**
	 * @hibernate.property
	 * @return the smoking
	 */
	public boolean getSmoking() {
		return this.smoking;
	}

	/**
	 * @param bedType
	 *            the bedType to set
	 */
	public void setBedType(final String bedType) {
		this.bedType = bedType;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * @param doorNumber
	 *            the doorNumber to set
	 */
	public void setDoorNumber(final int doorNumber) {
		this.doorNumber = doorNumber;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(final Room room) {
		this.room = room;
	}

	/**
	 * @param smoking
	 *            the smoking to set
	 */
	public void setSmoking(final boolean smoking) {
		this.smoking = smoking;
	}

}
