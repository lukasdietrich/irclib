package com.lukasdietrich.irclib.parser.mapper;

import com.lukasdietrich.irclib.model.User;
import com.lukasdietrich.irclib.model.messages.RawMessage;
import com.lukasdietrich.irclib.model.messages.specific.PrivateMessage;
import com.lukasdietrich.irclib.parser.MessageMapper;

public class PrivmsgMapper implements MessageMapper {

	@Override
	public PrivateMessage map(RawMessage message) {
		String[] split = message.getParameters().split(" :", 2);
		return new PrivateMessage(User.ofPrefix(message.getPrefix()).get(), split[0], split[1]);
	}

}
