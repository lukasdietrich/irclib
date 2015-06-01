package com.lukasdietrich.irclib.interfaces;

import com.lukasdietrich.irclib.IrcClient;
import com.lukasdietrich.irclib.model.messages.RawMessage;

@FunctionalInterface
public interface RawMessageListener {

	public void onMessage(IrcClient client, RawMessage msg) throws Exception;
	
}
