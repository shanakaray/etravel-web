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
    @ManyToOne
    private Booking booking;

    @Column
    private BigDecimal totalPrice;

    public Booking getBooking() {
	return booking;
    }

    public void setBooking(Booking booking) {
	this.booking = booking;
    }

    public BigDecimal getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
	this.totalPrice = totalPrice;
    }
}
