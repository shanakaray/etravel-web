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
	return this.fromDate;
    }

    public String getFromDateString() {
	return DateUtil.format(this.fromDate);
    }

    public void setFromDate(final Date fromDate) {
	this.fromDate = fromDate;
    }

    public Date getToDate() {
	return this.toDate;
    }

    public String getToDateString() {
	return DateUtil.format(this.toDate);
    }

    public void setToDate(final Date toDate) {
	this.toDate = toDate;
    }

    public int getUnit() {
	return this.unit;
    }

    public void setUnit(final int unit) {
	this.unit = unit;
    }

    public Room getRoom() {
	return this.room;
    }

    public void setRoom(final Room room) {
	this.room = room;
    }

    public int getAvailableUnit() {
	return this.availableUnit;
    }

    public void setAvailableUnit(final int availableUnit) {
	this.availableUnit = availableUnit;
    }

}
