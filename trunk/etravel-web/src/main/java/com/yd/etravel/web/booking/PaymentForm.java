/**
 * 
 */
package com.yd.etravel.web.booking;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.web.common.BaseForm;

/**
 * @author Dharshana
 * 
 */
public class PaymentForm extends BaseForm {

    private Long id;
    private Integer cardNumber;
    private Date expirDate;
    private String securityCode;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private String country;
    private BigDecimal amount;

    /**
     * @return the id
     */
    public Long getId() {
	return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
	this.id = id;
    }

    /**
     * @return the cardNumber
     */
    public Integer getCardNumber() {
	return this.cardNumber;
    }

    /**
     * @param cardNumber
     *            the cardNumber to set
     */
    public void setCardNumber(final Integer cardNumber) {
	this.cardNumber = cardNumber;
    }

    /**
     * @return the expirDate
     */
    public Date getExpirDate() {
	return this.expirDate;
    }

    /**
     * @param expirDate
     *            the expirDate to set
     */
    public void setExpirDate(final Date expirDate) {
	this.expirDate = expirDate;
    }

    /**
     * @return the securityCode
     */
    public String getSecurityCode() {
	return this.securityCode;
    }

    /**
     * @param securityCode
     *            the securityCode to set
     */
    public void setSecurityCode(final String securityCode) {
	this.securityCode = securityCode;
    }

    /**
     * @return the address
     */
    public String getAddress() {
	return this.address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(final String address) {
	this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
	return this.city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(final String city) {
	this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
	return this.province;
    }

    /**
     * @param province
     *            the province to set
     */
    public void setProvince(final String province) {
	this.province = province;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
	return this.zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(final String zipCode) {
	this.zipCode = zipCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
	return this.country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(final String country) {
	this.country = country;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
	return this.amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(final BigDecimal amount) {
	this.amount = amount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void resetBean(final ActionMapping mapping, final HttpServletRequest request) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#validateBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validateBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	// TODO Auto-generated method stub

	final ActionErrors errors = new ActionErrors();

	return errors;
    }

}
