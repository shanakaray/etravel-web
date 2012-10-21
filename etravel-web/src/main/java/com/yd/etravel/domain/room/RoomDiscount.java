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
	/**
	 * 
	 */
	private static final long serialVersionUID = 8829144035930050766L;

	@ManyToOne
	private Room room;

	@ManyToOne
	private Discount discount;

	public Discount getDiscount() {
		return this.discount;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setDiscount(final Discount discount) {
		this.discount = discount;
	}

	public void setRoom(final Room room) {
		this.room = room;
	}

}
