/**
 * 
 */
package com.yd.etravel.domain.room.availability;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.util.DateUtil;

@Entity
@Table(name = "T_ROOM_AVAILABILITY")
public class RoomDailyAvailability extends BaseObject {

	private static final long serialVersionUID = -6352662473914965372L;

	@Column
	private Date date;

	@Column
	private Integer allocatedUnit;

	@Column
	private Integer availableUnit;

	@ManyToOne
	private RoomAvailability roomAvailability;

	public Date getDate() {
		return this.date;
	}

	public String getDateString() {
		return DateUtil.format(this.date);
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Integer getAllocatedUnit() {
		return this.allocatedUnit;
	}

	public void setAllocatedUnit(final Integer allocatedUnit) {
		this.allocatedUnit = allocatedUnit;
	}

	public Integer getAvailableUnit() {
		return this.availableUnit;
	}

	public void addAvailableUnit(final int val) {
		this.availableUnit += val;
	}

	public void subAvailableUnit(final int val) {
		this.availableUnit -= val;
	}

	public void setAvailableUnit(final Integer availabalUnit) {
		this.availableUnit = availabalUnit;
	}

	public RoomAvailability getRoomAvailability() {
		return this.roomAvailability;
	}

	public void setRoomAvailability(final RoomAvailability roomAvailability) {
		this.roomAvailability = roomAvailability;
	}

}
