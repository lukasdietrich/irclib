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
	
	@Override
	public boolean equals(Object arg) {
		if (arg instanceof RawMessage) {
			RawMessage msg = (RawMessage) arg;
			
			return (msg.prefix == null && prefix == null 
					|| msg.prefix != null && prefix != null && msg.prefix.equals(prefix))
				&& msg.command.equals(command)
				&& msg.parameters.equals(parameters);
		}
		
		return false;
	}
	
}
