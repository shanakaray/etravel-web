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
	 * @return the disName
	 */
	public String getDisName() {
		return this.disName;
	}

	/**
	 * @param disName
	 *            the disName to set
	 */
	public void setDisName(final String disName) {
		this.disName = disName;
	}

	/**
	 * @hibernate.property
	 * @return the disType
	 */
	public String getDisType() {
		return this.disType;
	}

	/**
	 * @param disType
	 *            the disType to set
	 */
	public void setDisType(final String disType) {
		this.disType = disType;
	}

	/**
	 * @hibernate.property
	 * @return the adultDis
	 */
	public BigDecimal getAdultDis() {
		return this.adultDis;
	}

	/**
	 * @param adultDis
	 *            the adultDis to set
	 */
	public void setAdultDis(final BigDecimal adultDis) {
		this.adultDis = adultDis;
	}

	/**
	 * @hibernate.property
	 * @return the childDis
	 */
	public BigDecimal getChildDis() {
		return this.childDis;
	}

	/**
	 * @param childDis
	 *            the childDis to set
	 */
	public void setChildDis(final BigDecimal childDis) {
		this.childDis = childDis;
	}

	/**
	 * @hibernate.property
	 * @return the infantDis
	 */
	public BigDecimal getInfantDis() {
		return this.infantDis;
	}

	/**
	 * @param infantDis
	 *            the infantDis to set
	 */
	public void setInfantDis(final BigDecimal infantDis) {
		this.infantDis = infantDis;
	}

	/**
	 * @hibernate.property
	 * @return the disValue
	 */
	public BigDecimal getDisValue() {
		return this.disValue;
	}

	/**
	 * @param disValue
	 *            the disValue to set
	 */
	public void setDisValue(final BigDecimal disValue) {
		this.disValue = disValue;
	}

	/**
	 * @hibernate.property
	 * @return the disPercentage
	 */
	public BigDecimal getDisPercentage() {
		return this.disPercentage;
	}

	/**
	 * @param disPercentage
	 *            the disPercentage to set
	 */
	public void setDisPercentage(final BigDecimal disPercentage) {
		this.disPercentage = disPercentage;
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
	 * @param status
	 *            the status to set
	 */
	@Override
	public void setStatus(final String status) {
		this.status = status;
	}

}
