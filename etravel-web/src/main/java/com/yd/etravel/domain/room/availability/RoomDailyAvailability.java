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
@Table(name = "T_ROOM_DAILY_AVAILABILITY")
public class RoomDailyAvailability extends BaseObject {
    @Column
    private Date date;

    @Column
    private Integer allocatedUnit;

    @Column
    private Integer availabalUnit;

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

    public Integer getAvailabalUnit() {
	return this.availabalUnit;
    }

    public void addAvailabalUnit(final int val) {
	this.availabalUnit += val;
    }

    public void subAvailabalUnit(final int val) {
	this.availabalUnit -= val;
    }

    public void setAvailabalUnit(final Integer availabalUnit) {
	this.availabalUnit = availabalUnit;
    }

    public RoomAvailability getRoomAvailability() {
	return this.roomAvailability;
    }

    public void setRoomAvailability(final RoomAvailability roomAvailability) {
	this.roomAvailability = roomAvailability;
    }

}
