/**
 * 
 */
package com.yd.etravel.domain.room;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.discount.Discount;

@Entity
@Table(name = "T_ROOM_DISCOUNT")
public class RoomDiscount extends BaseObject {
    @ManyToOne
    private Room room;

    @ManyToOne
    private Discount discount;

    public Room getRoom() {
	return this.room;
    }

    public void setRoom(final Room room) {
	this.room = room;
    }

    public Discount getDiscount() {
	return this.discount;
    }

    public void setDiscount(final Discount discount) {
	this.discount = discount;
    }

}
