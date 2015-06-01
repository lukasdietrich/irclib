package com.lukasdietrich.irclib.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public final class MessageWriter {

	private Writer writer;
	
	public MessageWriter(OutputStream out) {
		this.writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
	}
	
	public void raw(String format, Object... args) throws IOException {
		writer
			.append(String.format(format, args))
			.append("\r\n")
			.flush();
	}
	
	public void join(String channel) throws IOException {
		raw("JOIN %s", channel);
	}
	
	public void part(String channel) throws IOException {
		raw("PART %s", channel);
	}
	
	public void privmsg(String channel) throws IOException {
		raw("PRIVMSG :%s", channel);
	}
	
}
