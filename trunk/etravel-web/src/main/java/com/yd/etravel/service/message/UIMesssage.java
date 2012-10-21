package com.yd.etravel.service.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UIMesssage {
	public static class Message implements Serializable {

		private static final long serialVersionUID = 5804503046875538903L;
		private final String key;
		private final String bundle;
		private final Object[] arguments;

		public Message(final String key, final String bundle,
				final Object[] arguments) {
			this.key = key;
			this.bundle = bundle;
			this.arguments = arguments;
		}

		public String getKey() {
			return this.key;
		}

		public String getBundle() {
			return this.bundle;
		}

		public Object[] getArguments() {
			return this.arguments;
		}
	}

	private List<Message> errors = null;

	private List<Message> informations = null;

	public UIMesssage() {
		this.errors = new ArrayList<Message>();
		this.informations = new ArrayList<Message>();
	}

	public UIMesssage(final String key, final String bundle,
			final Object[] arguments, final boolean message) {
		this.errors = new ArrayList<Message>();
		this.informations = new ArrayList<Message>();
		if (message) {
			addInformation(key, bundle, arguments);
		} else {
			addError(key, bundle, arguments);
		}
	}

	public boolean hasInformations() {
		return getInformations().size() > 0;
	}

	public boolean hasErrors() {
		return getErrors().size() > 0;
	}

	public void addError(final Message message, final int index) {
		this.errors.add(index, message);
	}

	public void addInformation(final Message message, final int index) {
		this.informations.add(index, message);
	}

	public void addInformation(final String key, final String bundle,
			final Object arguments[]) {
		this.informations.add(new Message(key, bundle, arguments));
	}

	public void addInformation(final String key, final String bundle,
			final Object arguments[], final int index) {
		this.informations.add(index, new Message(key, bundle, arguments));
	}

	public void addError(final String key, final String bundle,
			final Object arguments[]) {
		this.errors.add(new Message(key, bundle, arguments));
	}

	public void addError(final String key, final String bundle,
			final Object arguments[], final int index) {
		this.errors.add(index, new Message(key, bundle, arguments));
	}

	public UIMesssage merge(final UIMesssage uiMesssage) {
		getErrors().addAll(uiMesssage.getErrors());
		getInformations().addAll(uiMesssage.getInformations());
		return this;
	}

	public List<Message> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<Message>();
		}
		return this.errors;
	}

	public List<Message> getInformations() {
		if (this.informations == null) {
			this.informations = new ArrayList<Message>();
		}
		return this.informations;
	}

}
