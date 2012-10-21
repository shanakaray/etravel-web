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

	/**
	 * 
	 */
	private static final long serialVersionUID = 4661265258786033944L;
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
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * @return the cardNumber
	 */
	public Integer getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @return the expirDate
	 */
	public Date getExpirDate() {
		return this.expirDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return this.province;
	}

	/**
	 * @return the securityCode
	 */
	public String getSecurityCode() {
		return this.securityCode;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return this.zipCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @param cardNumber
	 *            the cardNumber to set
	 */
	public void setCardNumber(final Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @param expirDate
	 *            the expirDate to set
	 */
	public void setExpirDate(final Date expirDate) {
		this.expirDate = expirDate;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(final String province) {
		this.province = province;
	}

	/**
	 * @param securityCode
	 *            the securityCode to set
	 */
	public void setSecurityCode(final String securityCode) {
		this.securityCode = securityCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
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
