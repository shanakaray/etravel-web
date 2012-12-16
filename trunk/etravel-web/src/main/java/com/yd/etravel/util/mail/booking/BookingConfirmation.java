/**
 * 
 */
package com.yd.etravel.util.mail.booking;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import com.yd.etravel.util.mail.MailMessage;

@Component
public class BookingConfirmation extends MailMessage {

	public BookingConfirmation() {
	}

	@Override
	public void prepareBody() throws MessagingException {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				"<html><style type='text/css' >body{font-family: Arial,Verdana, Helvetica, sans-serif;font-size: 12px;height: 100%;margin: 20px 20px 20px 20px;background-color:#f3f1e5;color:#4c4c4b;font-weight:bold;}</style>"
						+ "<body><table width='90%'><tr><td bgcolor='black'><img src='http://www.anilana.com/images/anilana_hotel_logo.gif'></img></td></tr><tr><td>Dear ")
				.append(this.param.get("user.firstname")).append(",<br><br>");
		stringBuilder.append(
				"Your Booking attempt with www.anilana.com is Successfull !!<br><br>"

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
				.append("Paid Amount: ")
				.append(this.param.get("booking.paid"))
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
		LOGGER.debug(stringBuilder.toString());
		this.msg.setText(stringBuilder.toString(), true);
	}

	@Override
	public void prepareHeader() throws MessagingException {
		this.msg.setTo(this.param.get("user.email"));

	}

}
