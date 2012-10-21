package com.yd.etravel.domain.hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;

/**
 * @hibernate.class table="T_PAX"
 */
@Entity
@Table(name = "T_PAX")
public class Pax extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9025839616268858610L;

	@Column
	private int childMax;

	@Column
	private int childMin;

	@Column
	private int infantMax;

	@Column
	private int infantMin;

	@Column
	private int adultMax;

	@Column
	private int adultMin;

	@ManyToOne
	private Hotel hotel;

	public Pax() {

	}

	public Pax(final Long id, final String name, final String code) {
		super(id, name, code);
	}

	/**
	 * @hibernate.property
	 */
	public int getAdultMax() {
		return this.adultMax;
	}

	/**
	 * @hibernate.property
	 */
	public int getAdultMin() {
		return this.adultMin;
	}

	/**
	 * @hibernate.property
	 */
	public int getChildMax() {
		return this.childMax;
	}

	/**
	 * @hibernate.property
	 */
	public int getChildMin() {
		return this.childMin;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	/**
	 * @hibernate.property
	 */
	public int getInfantMax() {
		return this.infantMax;
	}

	/**
	 * @hibernate.property
	 */
	public int getInfantMin() {
		return this.infantMin;
	}

	public void setAdultMax(final int adultMax) {
		this.adultMax = adultMax;
	}

	public void setAdultMin(final int adultMin) {
		this.adultMin = adultMin;
	}

	public void setChildMax(final int childMax) {
		this.childMax = childMax;
	}

	public void setChildMin(final int childMin) {
		this.childMin = childMin;
	}

	public void setHotel(final Hotel hotel) {
		this.hotel = hotel;
	}

	public void setInfantMax(final int infantMax) {
		this.infantMax = infantMax;
	}

	public void setInfantMin(final int infantMin) {
		this.infantMin = infantMin;
	}

}
