package com.lukasdietrich.irclib.model.messages.generic;

import com.lukasdietrich.irclib.model.User;

public abstract class UserTextMessage extends TextMessage {

	private User source;
	
	public UserTextMessage(User source, String target, String text) {
		super(target, text);
	
		this.source = source;
	}
	
	public User getSource() {
		return source;
	}

}
