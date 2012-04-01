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
	return date;
    }

    public String getDateString() {
	return DateUtil.format(date);
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public Integer getAllocatedUnit() {
	return allocatedUnit;
    }

    public void setAllocatedUnit(Integer allocatedUnit) {
	this.allocatedUnit = allocatedUnit;
    }

    public Integer getAvailabalUnit() {
	return availabalUnit;
    }

    public void addAvailabalUnit(int val) {
	this.availabalUnit += val;
    }

    public void subAvailabalUnit(int val) {
	this.availabalUnit -= val;
    }

    public void setAvailabalUnit(Integer availabalUnit) {
	this.availabalUnit = availabalUnit;
    }

    public RoomAvailability getRoomAvailability() {
	return roomAvailability;
    }

    public void setRoomAvailability(RoomAvailability roomAvailability) {
	this.roomAvailability = roomAvailability;
    }

}
