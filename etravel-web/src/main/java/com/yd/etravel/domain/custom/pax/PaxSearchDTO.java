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

	public Long getHotelId() {
		return this.hotelId;
	}

	public Long getId() {
		return this.id;
	}

	public Integer getMaxage() {
		return this.maxage;
	}

	public Integer getMinage() {
		return this.minage;
	}

	public String getType() {
		return this.type;
	}

	public void setHotelId(final Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setMaxage(final Integer maxage) {
		this.maxage = maxage;
	}

	public void setMinage(final Integer minage) {
		this.minage = minage;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
