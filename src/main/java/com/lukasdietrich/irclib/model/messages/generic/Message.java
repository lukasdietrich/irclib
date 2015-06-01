package com.lukasdietrich.irclib.model.messages.generic;

public abstract class Message {

	private long timestamp;
	
	public Message(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Message() {
		this.timestamp = System.currentTimeMillis();
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public <E extends Message> E as(Class<E> classOfMessage) {
		return classOfMessage.cast(this);
	}
	
	public abstract Type getType();
	
	public enum Type {
		PRIVMSG("PRIVMSG"),
		NOTICE("NOTICE"),
		JOIN("JOIN"),
		PART("PART"),
		QUIT("QUIT"),
		KICK("KICK"),
		BAN("BAN"),
		MODE("MODE"),
		NICK("NICKCHANGE"),
		TOPIC("TOPIC"),
		MOTD("372"),
		WHOIS(null);
		
		private String command;
		
		Type (String command) {
			this.command = command;
		}
		
		public String getCommand() {
			return command;
		}
	}
	
}
