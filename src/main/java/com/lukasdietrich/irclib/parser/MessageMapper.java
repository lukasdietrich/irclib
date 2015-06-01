package com.lukasdietrich.irclib.parser;

import com.lukasdietrich.irclib.model.messages.RawMessage;
import com.lukasdietrich.irclib.model.messages.generic.Message;

public interface MessageMapper {

	public Message map(RawMessage message);
	
}
