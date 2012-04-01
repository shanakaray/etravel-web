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
	return marCode;
    }

    public void setMarCode(String marCode) {
	this.marCode = marCode;
    }

    public String getMarName() {
	return marName;
    }

    public void setMarName(String marName) {
	this.marName = marName;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public BigDecimal getMarValue() {
	return marValue;
    }

    public void setMarValue(BigDecimal marValue) {
	this.marValue = marValue;
    }

    public BigDecimal getMarPercenatge() {
	return marPercenatge;
    }

    public void setMarPercenatge(BigDecimal marPercenatge) {
	this.marPercenatge = marPercenatge;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

}
