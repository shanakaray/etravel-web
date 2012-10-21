/**
 * 
 */
package com.yd.etravel.domain.season;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.util.DateUtil;

@Entity
@Table(name = "T_SEASON")
public class Season extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5948772438828750808L;

	@Column
	private Date fromDate;

	@Column
	private Date toDate;

	@Column
	private String comments;

	@ManyToOne
	private Hotel hotel;

	/**
	 * @hibernate.property
	 * @return the status
	 */
	public String getComments() {
		return this.comments;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public String getFromDateString() {
		return DateUtil.format(this.fromDate);
	}

	/**
	 * @hibernate.many-to-one cascade="save-update"
	 * @return the season
	 */
	public Hotel getHotel() {
		return this.hotel;
	}

	/**
	 * @hibernate.property
	 * @return the toDate
	 */
	public Date getToDate() {
		return this.toDate;
	}

	public String getToDateString() {
		return DateUtil.format(this.toDate);
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setHotel(final Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

}
