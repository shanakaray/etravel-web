/**
 * 
 */
package com.yd.etravel.util.mail.booking;

import javax.mail.MessagingException;

import com.yd.etravel.util.mail.MailMessage;

/**
 * @author shanaka
 * 
 */
public class BookingConfirmation extends MailMessage {

    /**
	 * 
	 */
    public BookingConfirmation() {
	// TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.util.mail.MailMessage#prepareBody()
     */
    @Override
    public void prepareBody() throws MessagingException {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"<html><style type='text/css' >body{font-family: Arial,Verdana, Helvetica, sans-serif;font-size: 12px;height: 100%;margin: 20px 20px 20px 20px;background-color:#f3f1e5;color:#4c4c4b;font-weight:bold;}</style>"
			+ "<body><table width='90%'><tr><td bgcolor='black'><img src='http://www.anilana.com/images/anilana_hotel_logo.gif'></img></td></tr><tr><td>Dear ")
		.append(param.get("user.firstname")).append(",<br><br>");
	sb.append(
		"Your Booking attempt with www.anilana.com is Successfull !!<br><br>"

		+ "<br><li>Booking Id: ")
		.append(param.get("booking.bookingId"))
		.append("<br><li>")
		.append("Hotel Name: ")
		.append(param.get("hotel.name"))
		.append("<br><li>")
		.append("Room Type: ")
		.append(param.get("room.name"))
		.append("<br><li>")
		.append("Number of Rooms: ")
		.append(param.get("room.count"))
		.append("<br><li>")
		.append("Check In: ")
		.append(param.get("booking.checkin"))
		.append("<br><li>")
		.append("Check Out: ")
		.append(param.get("booking.checkout"))
		.append("<br><li>")
		.append("Room Cost: ")
		.append(param.get("booking.roomcost"))
		.append("<br><li>")
		.append("Paid Amount: ")
		.append(param.get("booking.paid"))
		.append("<br><li>")
		.append("Total Cost: ")
		.append(param.get("booking.totcost"))
		.append("<br><li>Customer Name: ")
		.append(param.get("user.firstname"))
		.append(" ")
		.append(param.get("user.lastname"))
		.append("<br><li>Customer Email: ")
		.append(param.get("user.email"))

		.append("</td></tr><tr><td><br><br>Regards,<br>Anilana admin</td></tr></table></body><html>");
	LOGGER.debug(sb.toString());
	msg.setText(sb.toString(), true);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.yd.etravel.util.mail.MailMessage#prepareHeader()
     */
    @Override
    public void prepareHeader() throws MessagingException {
	msg.setTo(param.get("user.email"));

    }

}
