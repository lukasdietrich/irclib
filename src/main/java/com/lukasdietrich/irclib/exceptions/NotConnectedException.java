package com.lukasdietrich.irclib.exceptions;

public class NotConnectedException extends RuntimeException {
	private static final long serialVersionUID = 7137466028900903136L;

	public NotConnectedException() {
		super();
	}

	public NotConnectedException(String message) {
		super(message);
	}
	
}
