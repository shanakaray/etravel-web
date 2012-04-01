package com.yd.etravel.domain.season;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.room.Room;

@Entity
@Table(name = "T_ROOM_SEASONAL_RATE")
public class RoomSeasonalRate extends BaseObject {
    @ManyToOne
    private Season season;

    @ManyToOne
    private Room room;

    // private BigDecimal adultCost;
    // private BigDecimal childCost;
    // private BigDecimal infantCost;
    @Column
    private BigDecimal totalCost;

    public Season getSeason() {
	return season;
    }

    public void setSeason(Season season) {
	this.season = season;
    }

    public Room getRoom() {
	return room;
    }

    public void setRoom(Room room) {
	this.room = room;
    }

    public BigDecimal getTotalCost() {
	return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
	this.totalCost = totalCost;
    }

}
