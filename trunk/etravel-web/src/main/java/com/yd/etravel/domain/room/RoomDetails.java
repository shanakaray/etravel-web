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

	public Room getRoom() {
		return this.room;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(final Room room) {
		this.room = room;
	}

	/**
	 * @hibernate.property
	 * @return the doorNumber
	 */
	public int getDoorNumber() {
		return this.doorNumber;
	}

	/**
	 * @param doorNumber
	 *            the doorNumber to set
	 */
	public void setDoorNumber(final int doorNumber) {
		this.doorNumber = doorNumber;
	}

	/**
	 * @hibernate.property
	 * @return the smoking
	 */
	public boolean getSmoking() {
		return this.smoking;
	}

	/**
	 * @param smoking
	 *            the smoking to set
	 */
	public void setSmoking(final boolean smoking) {
		this.smoking = smoking;
	}

	/**
	 * @hibernate.property
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
	 * @hibernate.property
	 * @return the bedType
	 */
	public String getBedType() {
		return this.bedType;
	}

	/**
	 * @param bedType
	 *            the bedType to set
	 */
	public void setBedType(final String bedType) {
		this.bedType = bedType;
	}

}
