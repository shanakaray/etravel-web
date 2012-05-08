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

    public String getMarCode() {
	return this.marCode;
    }

    public void setMarCode(final String marCode) {
	this.marCode = marCode;
    }

    public String getMarName() {
	return this.marName;
    }

    public void setMarName(final String marName) {
	this.marName = marName;
    }

    public String getComment() {
	return this.comment;
    }

    public void setComment(final String comment) {
	this.comment = comment;
    }

    public BigDecimal getMarValue() {
	return this.marValue;
    }

    public void setMarValue(final BigDecimal marValue) {
	this.marValue = marValue;
    }

    public BigDecimal getMarPercenatge() {
	return this.marPercenatge;
    }

    public void setMarPercenatge(final BigDecimal marPercenatge) {
	this.marPercenatge = marPercenatge;
    }

    @Override
    public String getStatus() {
	return this.status;
    }

    @Override
    public void setStatus(final String status) {
	this.status = status;
    }

}
