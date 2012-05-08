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
	return this.mailSender;
    }

    public void setMailSender(final JavaMailSenderImpl mailSender) {
	this.mailSender = mailSender;
    }

    public Map<String, String> getParam() {
	return this.param;
    }

    public void setParam(final Map<String, String> param) {
	this.param = param;
    }

    public void addParam(final Map<String, String> param) {
	if (this.param == null) {
	    this.param = new HashMap<String, String>();
	}

	for (final String key : param.keySet()) {
	    this.param.put(key, param.get(key));
	}
    }

    public String getFrom() {
	return this.from;
    }

    public void setFrom(final String from) {
	this.from = from;
    }

    public String getSubject() {
	return this.subject;
    }

    public void setSubject(final String subject) {
	this.subject = subject;
    }

    public String getBc() {
	return this.bc;
    }

    public void setBc(final String bc) {
	this.bc = bc;
    }

    public String getCc() {
	return this.cc;
    }

    public void setCc(final String cc) {
	this.cc = cc;
    }

    public void sendMail() {
	try {

	    final MimeMessage message = this.mailSender.createMimeMessage();
	    this.msg = new MimeMessageHelper(message, true);

	    prepare();
	    prepareBody();
	    prepareHeader();

	    this.mailSender.send(message);
	} catch (final Exception ex) {
	    LOGGER.fatal(ex.getMessage(), ex);
	}
    }

    private void prepare() throws MessagingException {
	if (StringUtils.isNotEmpty(this.from)) {
	    this.msg.setFrom(this.from);
	}
	if (StringUtils.isNotEmpty(this.subject)) {
	    this.msg.setSubject(this.subject);
	}
	if (StringUtils.isNotEmpty(this.bc)) {
	    this.msg.setBcc(this.bc);
	}
	if (StringUtils.isNotEmpty(this.cc)) {
	    this.msg.setBcc(this.cc);
	}
    }

    public abstract void prepareBody() throws MessagingException;

    public abstract void prepareHeader() throws MessagingException;

}
