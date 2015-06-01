package com.lukasdietrich.irclib.parser.mapper;

import java.util.Optional;

import com.lukasdietrich.irclib.model.User;
import com.lukasdietrich.irclib.model.messages.RawMessage;
import com.lukasdietrich.irclib.model.messages.generic.TextMessage;
import com.lukasdietrich.irclib.model.messages.specific.NoticeMessage;
import com.lukasdietrich.irclib.model.messages.specific.ServerNotice;
import com.lukasdietrich.irclib.parser.MessageMapper;

public class NoticeMapper implements MessageMapper {

	@Override
	public TextMessage map(RawMessage message) {
		String[] split = message.getParameters().split(" :", 2);
		Optional<User> user = User.ofPrefix(message.getPrefix());
		
		if (user.isPresent())
			return new NoticeMessage(user.get(), split[0], split[1]);
		else
			return new ServerNotice(split[0], split[1]);
	}

}
