package com.lukasdietrich.irclib.model.messages;


public class RawMessage {

	private String prefix, command, parameters;
	
	public RawMessage(String prefix, String command, String parameters) {
		this.prefix = prefix;
		this.command = command.toUpperCase();
		this.parameters = parameters;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getCommand() {
		return command;
	}

	public String getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return String.format("RawMessage [prefix=%s, command=%s, parameters=%s]", prefix, command, parameters);
	}
	
}
