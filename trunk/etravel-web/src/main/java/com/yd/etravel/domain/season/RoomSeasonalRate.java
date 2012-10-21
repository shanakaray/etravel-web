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

	/**
	 * 
	 */
	private static final long serialVersionUID = -8816174091724964282L;

	@ManyToOne
	private Season season;

	@ManyToOne
	private Room room;

	@Column
	private BigDecimal totalCost;

	public Room getRoom() {
		return this.room;
	}

	public Season getSeason() {
		return this.season;
	}

	public BigDecimal getTotalCost() {
		return this.totalCost;
	}

	public void setRoom(final Room room) {
		this.room = room;
	}

	public void setSeason(final Season season) {
		this.season = season;
	}

	public void setTotalCost(final BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

}
