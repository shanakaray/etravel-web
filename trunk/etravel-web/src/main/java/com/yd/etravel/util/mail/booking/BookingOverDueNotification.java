/**
 * 
 */
package com.yd.etravel.util.mail.booking;

import javax.mail.MessagingException;

import com.yd.etravel.util.mail.MailMessage;

/**
 * @author yora com.yd.etravel.util.mail.booking.BookingOverDueNotification
 */
public class BookingOverDueNotification extends MailMessage {

    /**
	 * 
	 */
    public BookingOverDueNotification() {
	super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.util.mail.MailMessage#prepareBody()
     */
    @Override
    public void prepareBody() throws MessagingException {
	final StringBuilder sb = new StringBuilder();
	sb.append(
		"<html><style type='text/css' >body{font-family: Arial,Verdana, Helvetica, sans-serif;font-size: 12px;height: 100%;margin: 20px 20px 20px 20px;background-color:#f3f1e5;color:#4c4c4b;font-weight:bold;}</style>"
			+ "<body><table width='90%'><tr><td bgcolor='black'><img src='http://www.anilana.com/images/anilana_hotel_logo.gif'></img></td></tr><tr><td>Dear ")
		.append(this.param.get("user.firstname")).append(",<br><br>");
	sb.append(
		"Your Booking attempt with www.anilana.com is overdue and no longer available"
			+ ",<br> Please Re-confirm new booking with a payment.<br><br><br>"

			+ "<br><li>Booking Id: ")
		.append(this.param.get("booking.bookingId"))
		.append("<br><li>")
		.append("Hotel Name: ")
		.append(this.param.get("hotel.name"))
		.append("<br><li>")
		.append("Room Type: ")
		.append(this.param.get("room.name"))
		.append("<br><li>")
		.append("Number of Rooms: ")
		.append(this.param.get("room.count"))
		.append("<br><li>")
		.append("Check In: ")
		.append(this.param.get("booking.checkin"))
		.append("<br><li>")
		.append("Check Out: ")
		.append(this.param.get("booking.checkout"))
		.append("<br><li>")
		.append("Room Cost: ")
		.append(this.param.get("booking.roomcost"))
		.append("<br><li>")
		.append("Total Cost: ")
		.append(this.param.get("booking.totcost"))
		.append("<br><li>Customer Name: ")
		.append(this.param.get("user.firstname"))
		.append(" ")
		.append(this.param.get("user.lastname"))
		.append("<br><li>Customer Email: ")
		.append(this.param.get("user.email"))
		.append("</td></tr><tr><td><br><br>Regards,<br>Anilana admin</td></tr></table></body><html>");
	LOGGER.debug(sb.toString());
	this.msg.setText(sb.toString(), true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.util.mail.MailMessage#prepareHeader()
     */
    @Override
    public void prepareHeader() throws MessagingException {
	this.msg.setTo(this.param.get("user.email"));
    }

}
