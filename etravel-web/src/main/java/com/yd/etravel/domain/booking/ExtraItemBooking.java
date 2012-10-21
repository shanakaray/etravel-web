/**
 * 
 */
package com.yd.etravel.domain.booking;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.extraitem.ExtraItem;

@Entity
@Table(name = "T_EXTRA_ITEM_BOOKING")
public class ExtraItemBooking extends BaseObject {

	private static final long serialVersionUID = 3158821878330977859L;

	@ManyToOne
	@ForeignKey(name = "FK_BOOKING")
	private Booking booking;

	@Column
	private String comments;

	@ManyToOne
	@ForeignKey(name = "FK_EXTRA_ITEM")
	private ExtraItem extraItem;

	@Column
	private BigDecimal price;

	public Booking getBooking() {
		return this.booking;
	}

	public String getComments() {
		return this.comments;
	}

	public ExtraItem getExtraItem() {
		return this.extraItem;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setBooking(final Booking booking) {
		this.booking = booking;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public void setExtraItem(final ExtraItem extraItem) {
		this.extraItem = extraItem;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

}
