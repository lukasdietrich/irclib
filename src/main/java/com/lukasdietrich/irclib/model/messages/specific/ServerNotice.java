package com.lukasdietrich.irclib.model.messages.specific;

import com.lukasdietrich.irclib.model.messages.generic.TextMessage;

public class ServerNotice extends TextMessage {

	public ServerNotice(String target, String text) {
		super(target, text);
	}

	@Override
	public Type getType() {
		return Type.NOTICE;
	}

}
