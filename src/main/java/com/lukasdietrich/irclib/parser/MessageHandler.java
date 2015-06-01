package com.lukasdietrich.irclib.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.lukasdietrich.irclib.IrcClient;
import com.lukasdietrich.irclib.interfaces.MessageListener;
import com.lukasdietrich.irclib.interfaces.RawMessageListener;
import com.lukasdietrich.irclib.model.messages.RawMessage;
import com.lukasdietrich.irclib.model.messages.generic.Message;
import com.lukasdietrich.irclib.model.messages.generic.Message.Type;
import com.lukasdietrich.irclib.parser.mapper.*;

public class MessageHandler {

	private IrcClient client;
	
	private List<RawMessageListener> rawListeners;
	private List<MessageListener> messageListeners;
	
	private Map<String, MessageMapper> mappers;
	
	public MessageHandler(IrcClient client) {
		this.client = client;
		this.mappers = new HashMap<>();
		
		this.rawListeners = new Vector<>();
		this.messageListeners = new Vector<>();
		
		{
			registerMapper(Type.PRIVMSG, 	new PrivmsgMapper());
			registerMapper(Type.NOTICE, 	new NoticeMapper());
			registerMapper(Type.MOTD,		new MotdMapper());
		}
	}
	
	private void registerMapper(Type type, MessageMapper mapper) {
		mappers.put(type.getCommand(), mapper);
	}
	
	public void addMessageListener(MessageListener listener) {
		if (!messageListeners.contains(listener))
			messageListeners.add(listener);
	}
	
	public boolean removeMessageListener(MessageListener listener) {
		return messageListeners.remove(listener);
	}
	
	public void addRawMessageListener(RawMessageListener listener) {
		if (!rawListeners.contains(listener))
			rawListeners.add(listener);
	}
	
	public boolean removeRawMessageListener(RawMessageListener listener) {
		return rawListeners.remove(listener);
	}
	
	protected void handle(RawMessage raw) {
		for (RawMessageListener listener : rawListeners)
			try {
				listener.onMessage(client, raw);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		MessageMapper mapper = mappers.get(raw.getCommand());
		
		if (mapper != null) {
			Message msg = mapper.map(raw);
			for (MessageListener listener : messageListeners)
				try {
					listener.onMessage(client, msg);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
	}
	
}
