package com.lukasdietrich.irclib.parser;

import java.util.function.Consumer;

import com.lukasdietrich.irclib.model.messages.RawMessage;

public class MessageParser implements Consumer<String> {

	private final String SPACE = " ";
	private MessageHandler handler;
	
	public MessageParser(MessageHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public void accept(String arg) {
		if ((arg = arg.trim()).length() == 0)
			return;
	
		RawMessage msg;
		String[] split;
		
		if (arg.startsWith(":")) {
			split = arg.split(SPACE, 3);
			msg = new RawMessage(split[0].substring(1), split[1], split[2]);
		} else {
			split = arg.split(SPACE, 2);
			msg = new RawMessage(null, split[0], split[1]);
		}
		
		handler.handle(msg);
	}

}
