package com.yd.etravel.domain.booking;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.user.User;

@Entity
@Table(name = "T_BOOKING")
public class Booking extends BaseObject {

    private static final long serialVersionUID = -21233441337334686L;

    @ManyToOne
    @ForeignKey(name = "FK_BUSER")
    private User bookingUser;

    @ManyToOne
    @ForeignKey(name = "FK_AGENT")
    private User agent;

    @Column
    private String statusDes;

    @Column
    private Date depatureDate;

    @Column
    private Date bookingDate;

    @Column
    private BigDecimal totalPrice;

    @Column
    private BigDecimal roomPrice;

    @Column
    private BigDecimal totalCost;

    @Column
    private BigDecimal paidAmount;

    @Column
    private Date payDueDate;

    @Column
    private Date cancelDate;

    @Column
    private Date expireDate;

    @Column
    private String paymentMethod;

    @Column
    private boolean singleNight;

    public Booking() {
	super();
    }

    public Booking(final String code) {
	super();
	super.setCode(code);
    }

    public User getBookingUser() {
	return this.bookingUser;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setBookingUser(final User bookingUser) {
	this.bookingUser = bookingUser;
    }

    public Date getDepatureDate() {
	return this.depatureDate;
    }

    /**
     * @param depatureDate
     *            the depatureDate to set
     */
    public void setDepatureDate(final Date depatureDate) {
	this.depatureDate = depatureDate;
    }

    public Date getBookingDate() {
	return this.bookingDate;
    }

    public void setBookingDate(final Date bookingDate) {
	this.bookingDate = bookingDate;
    }

    public BigDecimal getTotalPrice() {
	return this.totalPrice;
    }

    public void setTotalPrice(final BigDecimal totalPrice) {
	this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalCost() {
	return this.totalCost;
    }

    /**
     * @param totalCost
     *            the totalCost to set
     */
    public void setTotalCost(final BigDecimal totalCost) {
	this.totalCost = totalCost;
    }

    public Date getPayDueDate() {
	return this.payDueDate;
    }

    /**
     * @param payDueDate
     *            the payDueDate to set
     */
    public void setPayDueDate(final Date payDueDate) {
	this.payDueDate = payDueDate;
    }

    public Date getCancelDate() {
	return this.cancelDate;
    }

    /**
     * @param cancelDate
     *            the cancelDate to set
     */
    public void setCancelDate(final Date cancelDate) {
	this.cancelDate = cancelDate;
    }

    public Date getExpireDate() {
	return this.expireDate;
    }

    public void setExpireDate(final Date expireDate) {
	this.expireDate = expireDate;
    }

    public String getStatusDes() {
	return this.statusDes;
    }

    public void setStatusDes(final String statusDes) {
	this.statusDes = statusDes;
    }

    public String getPaymentMethod() {
	return this.paymentMethod;
    }

    public void setPaymentMethod(final String paymentMethod) {
	this.paymentMethod = paymentMethod;
    }

    public BigDecimal getRoomPrice() {
	return this.roomPrice;
    }

    /**
     * @param roomPrice
     *            the roomPrice to set
     */
    public void setRoomPrice(final BigDecimal roomPrice) {
	this.roomPrice = roomPrice;
    }

    public User getAgent() {
	return this.agent;
    }

    /**
     * @param agent
     *            the agent to set
     */
    public void setAgent(final User agent) {
	this.agent = agent;
    }

    public BigDecimal getPaidAmount() {
	return this.paidAmount;
    }

    public String getPaidAmountCts() {
	return String.valueOf(this.paidAmount.multiply(new BigDecimal(100))
		.intValue());
    }

    public void setPaidAmount(final BigDecimal paidAmount) {
	this.paidAmount = paidAmount;
    }

    public void setPay(final BigDecimal paidAmount) {
	if (this.paidAmount == null || this.paidAmount.doubleValue() < 0) {
	    this.paidAmount = paidAmount;
	} else {
	    this.paidAmount.add(paidAmount);
	}
    }

    public boolean isSingleNight() {
	return this.singleNight;
    }

    public void setSingleNight(final boolean singleNight) {
	this.singleNight = singleNight;
    }

}
