/**
 * 
 */
package com.yd.etravel.domain.custom.pax;

/**
 * @author : Yohan Ranasinghe. Created Date : Jan 29, 2009 : 6:27:44 PM Type :
 *         com.yd.etravel.domain.custom.pax.PaxSearchDTO
 * 
 */
public class PaxSearchDTO {

    private String type;
    private Integer maxage;
    private Integer minage;
    private Long id;
    private Long hotelId;

    /**
	 * 
	 */
    public PaxSearchDTO() {
	// TODO Auto-generated constructor stub
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Integer getMaxage() {
	return maxage;
    }

    public void setMaxage(Integer maxage) {
	this.maxage = maxage;
    }

    public Integer getMinage() {
	return minage;
    }

    public void setMinage(Integer minage) {
	this.minage = minage;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getHotelId() {
	return hotelId;
    }

    public void setHotelId(Long hotelId) {
	this.hotelId = hotelId;
    }

}
