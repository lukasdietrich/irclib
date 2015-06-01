package com.lukasdietrich.irclib.model.messages.specific;

import com.lukasdietrich.irclib.model.User;
import com.lukasdietrich.irclib.model.messages.generic.UserTextMessage;

public class PrivateMessage extends UserTextMessage {
	
	public PrivateMessage(User source, String target, String text) {
		super(source, target, text);
	}

	@Override
	public Type getType() {
		return Type.PRIVMSG;
	}

}
