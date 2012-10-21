/**
 * 
 */
package com.yd.etravel.domain.markup;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;

@Entity
@Table(name = "T_MARKUP")
public class Markup extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5287110185253110589L;

	@Column
	private String marCode;

	@Column
	private String marName;

	@Column
	private String comment;

	@Column
	private BigDecimal marValue;

	@Column
	private BigDecimal marPercenatge;

	@Column
	private String status;

	public String getComment() {
		return this.comment;
	}

	public String getMarCode() {
		return this.marCode;
	}

	public String getMarName() {
		return this.marName;
	}

	public BigDecimal getMarPercenatge() {
		return this.marPercenatge;
	}

	public BigDecimal getMarValue() {
		return this.marValue;
	}

	@Override
	public String getStatus() {
		return this.status;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public void setMarCode(final String marCode) {
		this.marCode = marCode;
	}

	public void setMarName(final String marName) {
		this.marName = marName;
	}

	public void setMarPercenatge(final BigDecimal marPercenatge) {
		this.marPercenatge = marPercenatge;
	}

	public void setMarValue(final BigDecimal marValue) {
		this.marValue = marValue;
	}

	@Override
	public void setStatus(final String status) {
		this.status = status;
	}

}
