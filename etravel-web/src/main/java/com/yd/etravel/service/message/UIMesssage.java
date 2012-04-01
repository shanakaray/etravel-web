package com.yd.etravel.service.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UIMesssage {
    public static class Message implements Serializable {

	private static final long serialVersionUID = 5804503046875538903L;
	private String key;
	private String bundle;
	private Object[] arguments;

	public Message(String key, String bundle, Object[] arguments) {
	    this.key = key;
	    this.bundle = bundle;
	    this.arguments = arguments;
	}

	public String getKey() {
	    return key;
	}

	public String getBundle() {
	    return bundle;
	}

	public Object[] getArguments() {
	    return arguments;
	}
    }

    private List<Message> errors = null;

    private List<Message> informations = null;

    public UIMesssage() {
	errors = new ArrayList<Message>();
	informations = new ArrayList<Message>();
    }

    public UIMesssage(String key, String bundle, Object[] arguments,
	    boolean message) {
	errors = new ArrayList<Message>();
	informations = new ArrayList<Message>();
	if (message) {
	    addInformation(key, bundle, arguments);
	} else {
	    addError(key, bundle, arguments);
	}
    }

    public boolean hasInformations() {
	return ((getInformations().size() > 0));
    }

    public boolean hasErrors() {
	return ((getErrors().size() > 0));
    }

    public void addError(Message message, int index) {
	errors.add(index, message);
    }

    public void addInformation(Message message, int index) {
	informations.add(index, message);
    }

    public void addInformation(String key, String bundle, Object arguments[]) {
	informations.add(new Message(key, bundle, arguments));
    }

    public void addInformation(String key, String bundle, Object arguments[],
	    int index) {
	informations.add(index, new Message(key, bundle, arguments));
    }

    public void addError(String key, String bundle, Object arguments[]) {
	errors.add(new Message(key, bundle, arguments));
    }

    public void addError(String key, String bundle, Object arguments[],
	    int index) {
	errors.add(index, new Message(key, bundle, arguments));
    }

    public UIMesssage merge(UIMesssage uiMesssage) {
	this.getErrors().addAll(uiMesssage.getErrors());
	this.getInformations().addAll(uiMesssage.getInformations());
	return this;
    }

    public List<Message> getErrors() {
	if (errors == null) {
	    errors = new ArrayList<Message>();
	}
	return errors;
    }

    public List<Message> getInformations() {
	if (informations == null) {
	    informations = new ArrayList<Message>();
	}
	return informations;
    }

}
