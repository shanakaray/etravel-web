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

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

    public int getAdult() {
	return adult;
    }

    public void setAdult(int adult) {
	this.adult = adult;
    }

    public int getChild() {
	return child;
    }

    public void setChild(int child) {
	this.child = child;
    }

    public int getInfant() {
	return infant;
    }

    public void setInfant(int infant) {
	this.infant = infant;
    }

    public int getTotalPax() {
	return totalPax;
    }

    public void setTotalPax(int totalPax) {
	this.totalPax = totalPax;
    }

    public RoomType getRoomType() {
	return roomType;
    }

    public void setRoomType(RoomType roomType) {
	this.roomType = roomType;
    }

}
