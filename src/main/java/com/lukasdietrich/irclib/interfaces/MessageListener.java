package com.lukasdietrich.irclib.interfaces;

import com.lukasdietrich.irclib.IrcClient;
import com.lukasdietrich.irclib.model.messages.generic.Message;

@FunctionalInterface
public interface MessageListener {

	public void onMessage(IrcClient client, Message msg) throws Exception;
	
}
