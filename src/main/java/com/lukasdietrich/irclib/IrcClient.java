package com.lukasdietrich.irclib;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ThreadFactory;

import com.lukasdietrich.irclib.exceptions.NotConnectedException;
import com.lukasdietrich.irclib.interfaces.RawMessageListener;
import com.lukasdietrich.irclib.io.MessageWriter;
import com.lukasdietrich.irclib.io.MessageReader;
import com.lukasdietrich.irclib.model.Config;
import com.lukasdietrich.irclib.model.messages.RawMessage;
import com.lukasdietrich.irclib.parser.MessageHandler;
import com.lukasdietrich.irclib.parser.MessageParser;

public class IrcClient {

	private Config config;
	private Socket socket;
	
	private MessageWriter writer;
	private MessageHandler handler;
	
	public IrcClient(Config config) {
		this.config = config;
		this.handler = new MessageHandler(this);
		
		this.handler.addRawMessageListener(new InternalListener());
	}
	
	public void connect(ThreadFactory threadFactory) throws IOException {
		socket = config
					.getDefaultSocketFactory()
					.createSocket(config.getHostname(), config.getPort());
		
		writer = new MessageWriter(socket.getOutputStream());
		
		if (config.hasPassword())
			writer.raw("PASS %s", config.getPassword());
		
		writer.raw("NICK %s", config.getNick());
		writer.raw("USER %s 0 * :%s", config.getUser(), config.getRealname());
		
		threadFactory
			.newThread(new MessageReader(socket.getInputStream(), new MessageParser(handler)))
			.start();
	}
	
	public void connect() throws IOException {
		connect(r -> new Thread(r));
	}
	
	public MessageWriter send() {
		if (writer != null)
			return writer;
		
		throw new NotConnectedException("Connection was never established or failed !");
	}
	
	public MessageHandler listener() {
		return handler;
	}
	
	private class InternalListener implements RawMessageListener {
		
		private Deque<Character> nickAlternatives = new LinkedList<>(Arrays.asList('_', '1', '2', '3'));

		@Override
		public void onMessage(IrcClient client, RawMessage msg) throws Exception {		
			switch (msg.getCommand()) {
				case "PING":
					client.send().raw("PONG %s", msg.getParameters());
					break;
					
				case "433":
					if (!nickAlternatives.isEmpty())
						client.send().raw("NICK %s", config.getNick() + nickAlternatives.pop());
			}
		}
		
	}
	
}
