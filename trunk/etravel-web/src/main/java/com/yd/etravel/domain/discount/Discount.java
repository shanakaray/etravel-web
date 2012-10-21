/**
 * 
 */
package com.yd.etravel.domain.discount;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;

@Entity
@Table(name = "T_DISCOUNT")
public class Discount extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6936247290080880488L;

	@Column
	private String disName;

	@Column
	private String disType;

	@Column
	private BigDecimal adultDis;

	@Column
	private BigDecimal childDis;

	@Column
	private BigDecimal infantDis;

	@Column
	private BigDecimal disValue;

	@Column
	private BigDecimal disPercentage;

	@Column
	private String status;

	/**
	 * @hibernate.property
	 * @return the adultDis
	 */
	public BigDecimal getAdultDis() {
		return this.adultDis;
	}

	/**
	 * @hibernate.property
	 * @return the childDis
	 */
	public BigDecimal getChildDis() {
		return this.childDis;
	}

	/**
	 * @hibernate.property
	 * @return the disName
	 */
	public String getDisName() {
		return this.disName;
	}

	/**
	 * @hibernate.property
	 * @return the disPercentage
	 */
	public BigDecimal getDisPercentage() {
		return this.disPercentage;
	}

	/**
	 * @hibernate.property
	 * @return the disType
	 */
	public String getDisType() {
		return this.disType;
	}

	/**
	 * @hibernate.property
	 * @return the disValue
	 */
	public BigDecimal getDisValue() {
		return this.disValue;
	}

	/**
	 * @hibernate.property
	 * @return the infantDis
	 */
	public BigDecimal getInfantDis() {
		return this.infantDis;
	}

	/**
	 * @hibernate.property
	 * @return the status
	 */
	@Override
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param adultDis
	 *            the adultDis to set
	 */
	public void setAdultDis(final BigDecimal adultDis) {
		this.adultDis = adultDis;
	}

	/**
	 * @param childDis
	 *            the childDis to set
	 */
	public void setChildDis(final BigDecimal childDis) {
		this.childDis = childDis;
	}

	/**
	 * @param disName
	 *            the disName to set
	 */
	public void setDisName(final String disName) {
		this.disName = disName;
	}

	/**
	 * @param disPercentage
	 *            the disPercentage to set
	 */
	public void setDisPercentage(final BigDecimal disPercentage) {
		this.disPercentage = disPercentage;
	}

	/**
	 * @param disType
	 *            the disType to set
	 */
	public void setDisType(final String disType) {
		this.disType = disType;
	}

	/**
	 * @param disValue
	 *            the disValue to set
	 */
	public void setDisValue(final BigDecimal disValue) {
		this.disValue = disValue;
	}

	/**
	 * @param infantDis
	 *            the infantDis to set
	 */
	public void setInfantDis(final BigDecimal infantDis) {
		this.infantDis = infantDis;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	@Override
	public void setStatus(final String status) {
		this.status = status;
	}

}
