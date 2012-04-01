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

    public Booking(String code) {
	super();
	super.setCode(code);
    }

    public User getBookingUser() {
	return bookingUser;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setBookingUser(User bookingUser) {
	this.bookingUser = bookingUser;
    }


    public Date getDepatureDate() {
	return depatureDate;
    }

    /**
     * @param depatureDate
     *            the depatureDate to set
     */
    public void setDepatureDate(Date depatureDate) {
	this.depatureDate = depatureDate;
    }

    public Date getBookingDate() {
	return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
	this.bookingDate = bookingDate;
    }

    public BigDecimal getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
	this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalCost() {
	return totalCost;
    }

    /**
     * @param totalCost
     *            the totalCost to set
     */
    public void setTotalCost(BigDecimal totalCost) {
	this.totalCost = totalCost;
    }

    public Date getPayDueDate() {
	return payDueDate;
    }

    /**
     * @param payDueDate
     *            the payDueDate to set
     */
    public void setPayDueDate(Date payDueDate) {
	this.payDueDate = payDueDate;
    }

    public Date getCancelDate() {
	return cancelDate;
    }

    /**
     * @param cancelDate
     *            the cancelDate to set
     */
    public void setCancelDate(Date cancelDate) {
	this.cancelDate = cancelDate;
    }

    public Date getExpireDate() {
	return expireDate;
    }

    public void setExpireDate(Date expireDate) {
	this.expireDate = expireDate;
    }

    public String getStatusDes() {
	return statusDes;
    }

    public void setStatusDes(String statusDes) {
	this.statusDes = statusDes;
    }

    public String getPaymentMethod() {
	return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
    }

    public BigDecimal getRoomPrice() {
	return roomPrice;
    }

    /**
     * @param roomPrice
     *            the roomPrice to set
     */
    public void setRoomPrice(BigDecimal roomPrice) {
	this.roomPrice = roomPrice;
    }

    public User getAgent() {
	return agent;
    }

    /**
     * @param agent
     *            the agent to set
     */
    public void setAgent(User agent) {
	this.agent = agent;
    }

    public BigDecimal getPaidAmount() {
	return paidAmount;
    }

    public String getPaidAmountCts() {
	return String.valueOf(paidAmount.multiply(new BigDecimal(100))
		.intValue());
    }

    public void setPaidAmount(BigDecimal paidAmount) {
	this.paidAmount = paidAmount;
    }

    public void setPay(BigDecimal paidAmount) {
	if (this.paidAmount == null || this.paidAmount.doubleValue() < 0) {
	    this.paidAmount = paidAmount;
	} else {
	    this.paidAmount.add(paidAmount);
	}
    }

    public boolean isSingleNight() {
	return singleNight;
    }

    public void setSingleNight(boolean singleNight) {
	this.singleNight = singleNight;
    }

}
