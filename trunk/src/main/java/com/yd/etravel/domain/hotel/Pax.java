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

    public Pax(Long id, String name, String code) {
	super(id, name, code);
    }

    public Hotel getHotel() {
	return hotel;
    }

    public void setHotel(Hotel hotel) {
	this.hotel = hotel;
    }

    /**
     * @hibernate.property
     */
    public int getChildMax() {
	return childMax;
    }

    public void setChildMax(int childMax) {
	this.childMax = childMax;
    }

    /**
     * @hibernate.property
     */
    public int getChildMin() {
	return childMin;
    }

    public void setChildMin(int childMin) {
	this.childMin = childMin;
    }

    /**
     * @hibernate.property
     */
    public int getInfantMax() {
	return infantMax;
    }

    public void setInfantMax(int infantMax) {
	this.infantMax = infantMax;
    }

    /**
     * @hibernate.property
     */
    public int getInfantMin() {
	return infantMin;
    }

    public void setInfantMin(int infantMin) {
	this.infantMin = infantMin;
    }

    /**
     * @hibernate.property
     */
    public int getAdultMax() {
	return adultMax;
    }

    public void setAdultMax(int adultMax) {
	this.adultMax = adultMax;
    }

    /**
     * @hibernate.property
     */
    public int getAdultMin() {
	return adultMin;
    }

    public void setAdultMin(int adultMin) {
	this.adultMin = adultMin;
    }

}
