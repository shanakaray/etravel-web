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
import com.yd.etravel.domain.room.Room;
import com.yd.etravel.util.DateUtil;

@Entity
@Table(name = "T_ROOM_AVAILABILITY")
public class RoomAvailability extends BaseObject {
    @Column
    private Date fromDate;

    @Column
    private Date toDate;

    @Column
    private int unit;

    @Column
    private int availableUnit;

    @ManyToOne
    private Room room;

    public Date getFromDate() {
	return fromDate;
    }

    public String getFromDateString() {
	return DateUtil.format(fromDate);
    }

    public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
    }

    public Date getToDate() {
	return toDate;
    }

    public String getToDateString() {
	return DateUtil.format(toDate);
    }

    public void setToDate(Date toDate) {
	this.toDate = toDate;
    }

    public int getUnit() {
	return unit;
    }

    public void setUnit(int unit) {
	this.unit = unit;
    }

    public Room getRoom() {
	return room;
    }

    public void setRoom(Room room) {
	this.room = room;
    }

    public int getAvailableUnit() {
	return availableUnit;
    }

    public void setAvailableUnit(int availableUnit) {
	this.availableUnit = availableUnit;
    }

}
