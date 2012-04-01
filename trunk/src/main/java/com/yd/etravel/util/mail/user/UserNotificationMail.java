package com.yd.etravel.util.mail.user;

import javax.mail.MessagingException;

import com.yd.etravel.util.mail.MailMessage;

/**
 * 
 * @author yora
 * 
 */
public class UserNotificationMail extends MailMessage {

    public void prepareBody() throws MessagingException {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"<html><style type='text/css' >body{font-family: Arial,Verdana, Helvetica, sans-serif;font-size: 12px;height: 100%;margin: 20px 20px 20px 20px;background-color:#f3f1e5;color:#4c4c4b;font-weight:bold;}</style>"
			+ "<body><table width='90%'><tr><td bgcolor='black'><img src='http://www.anilana.com/images/anilana_hotel_logo.gif'></img></td></tr><tr><td>Dear ")
		.append(param.get("user.firstname")).append(",<br><br>");

	sb.append(
		"Your Registration with http://www.anilana.com/ was Successful.<br>")
		.append(" Please login to the booking system using,<br><br><br>")
		.append("<li>User name : <b>")
		.append(param.get("user.name"))
		.append("</b><li>")
		.append("Password: <b>")
		.append(param.get("user.password"))
		.append("</b><br></td></tr><tr><td><br><br>Regards,<br>Anilana admin</td></tr></table></body></html>");
	msg.setText(sb.toString(), true);
    }

    public void prepareHeader() throws MessagingException {
	msg.setTo(param.get("user.email"));
    }

}
