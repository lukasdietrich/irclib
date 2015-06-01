package com.lukasdietrich.irclib.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class MessageReader implements Runnable {

	private BufferedReader reader;
	private Consumer<String> consumer;
	
	public MessageReader(InputStream in, Consumer<String> consumer) {
		this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
		this.consumer = consumer;
	}
	
	public void run() {
		try {
			String line;
			
			while ((line = reader.readLine()) != null) {
				consumer.accept(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
