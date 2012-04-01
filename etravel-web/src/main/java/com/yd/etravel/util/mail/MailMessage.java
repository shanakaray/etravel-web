package com.yd.etravel.util.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.yd.etravel.util.StringUtils;

/**
 * 
 * @author shanaka
 * 
 */
public abstract class MailMessage {
    private JavaMailSenderImpl mailSender;
    protected MimeMessageHelper msg;
    protected static final Log LOGGER = LogFactory.getLog(MailMessage.class);
    protected String from;
    protected String subject;
    protected String bc;
    protected String cc;
    protected Map<String, String> param;

    public JavaMailSenderImpl getMailSender() {
	return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
	this.mailSender = mailSender;
    }

    public Map<String, String> getParam() {
	return param;
    }

    public void setParam(Map<String, String> param) {
	this.param = param;
    }

    public void addParam(Map<String, String> param) {
	if (this.param == null) {
	    this.param = new HashMap<String, String>();
	}

	for (String key : param.keySet()) {
	    this.param.put(key, param.get(key));
	}
    }

    public String getFrom() {
	return from;
    }

    public void setFrom(String from) {
	this.from = from;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getBc() {
	return bc;
    }

    public void setBc(String bc) {
	this.bc = bc;
    }

    public String getCc() {
	return cc;
    }

    public void setCc(String cc) {
	this.cc = cc;
    }

    public void sendMail() {
	try {

	    MimeMessage message = mailSender.createMimeMessage();
	    msg = new MimeMessageHelper(message, true);

	    prepare();
	    prepareBody();
	    prepareHeader();

	    mailSender.send(message);
	} catch (Exception ex) {
	    LOGGER.fatal(ex.getMessage(), ex);
	}
    }

    private void prepare() throws MessagingException {
	if (StringUtils.isNotEmpty(from)) {
	    msg.setFrom(from);
	}
	if (StringUtils.isNotEmpty(subject)) {
	    msg.setSubject(subject);
	}
	if (StringUtils.isNotEmpty(bc)) {
	    msg.setBcc(bc);
	}
	if (StringUtils.isNotEmpty(cc)) {
	    msg.setBcc(cc);
	}
    }

    public abstract void prepareBody() throws MessagingException;

    public abstract void prepareHeader() throws MessagingException;

}
