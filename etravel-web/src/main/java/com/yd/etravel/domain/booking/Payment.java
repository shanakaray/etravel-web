/**
 * 
 */
package com.yd.etravel.domain.booking;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;

@Entity
@Table(name = "T_PAYMENT")
public class Payment extends BaseObject {

	private static final long serialVersionUID = -2912368260338991279L;

	@ManyToOne
	private Booking booking;

	@Column
	private BigDecimal totalPrice;

	public Booking getBooking() {
		return this.booking;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	public void setBooking(final Booking booking) {
		this.booking = booking;
	}

	public void setTotalPrice(final BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTotalPrice(final double payed) {
		setTotalPrice(format(payed));

	}
}
