/**
 * 
 */
package com.yd.etravel.domain.roomtype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;

@Entity
@Table(name = "T_ROOM_TYPE")
public class RoomType extends BaseObject {
    @Column
    private String comments;

    @Column
    private int maxPassengers;

    public String getComments() {
	return this.comments;
    }

    public void setComments(final String comments) {
	this.comments = comments;
    }

    public int getMaxPassengers() {
	return this.maxPassengers;
    }

    public void setMaxPassengers(final int maxPassengers) {
	this.maxPassengers = maxPassengers;
    }

}
