package com.lukasdietrich.irclib.parser.mapper;

import com.lukasdietrich.irclib.model.messages.RawMessage;
import com.lukasdietrich.irclib.model.messages.specific.MotdMessage;
import com.lukasdietrich.irclib.parser.MessageMapper;

public class MotdMapper implements MessageMapper {

	@Override
	public MotdMessage map(RawMessage message) {
		String[] split = message.getParameters().split(" :", 2);
		return new MotdMessage(split[0], split[1]);
	}

}
