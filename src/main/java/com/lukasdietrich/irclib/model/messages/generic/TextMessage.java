package com.lukasdietrich.irclib.model.messages.generic;

public abstract class TextMessage extends Message {

	private String target, text;
	
	public TextMessage(String target, String text) {
		super();
		
		this.target = target;
		this.text = text;
	}

	public String getTarget() {
		return target;
	}

	public String getText() {
		return text;
	}
	
}
