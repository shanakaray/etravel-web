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

    public Hotel getHotel() {
	return this.hotel;
    }

    public void setHotel(final Hotel hotel) {
	this.hotel = hotel;
    }

    /**
     * @hibernate.property
     */
    public int getChildMax() {
	return this.childMax;
    }

    public void setChildMax(final int childMax) {
	this.childMax = childMax;
    }

    /**
     * @hibernate.property
     */
    public int getChildMin() {
	return this.childMin;
    }

    public void setChildMin(final int childMin) {
	this.childMin = childMin;
    }

    /**
     * @hibernate.property
     */
    public int getInfantMax() {
	return this.infantMax;
    }

    public void setInfantMax(final int infantMax) {
	this.infantMax = infantMax;
    }

    /**
     * @hibernate.property
     */
    public int getInfantMin() {
	return this.infantMin;
    }

    public void setInfantMin(final int infantMin) {
	this.infantMin = infantMin;
    }

    /**
     * @hibernate.property
     */
    public int getAdultMax() {
	return this.adultMax;
    }

    public void setAdultMax(final int adultMax) {
	this.adultMax = adultMax;
    }

    /**
     * @hibernate.property
     */
    public int getAdultMin() {
	return this.adultMin;
    }

    public void setAdultMin(final int adultMin) {
	this.adultMin = adultMin;
    }

}
