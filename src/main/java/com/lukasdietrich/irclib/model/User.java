package com.lukasdietrich.irclib.model;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

	public static Optional<User> ofPrefix(String prefix) {
		Matcher matcher = Pattern.compile("([^!]+)!([^@]+)@(.+)").matcher(prefix);
		
		if (matcher.find())
			return Optional.of(new User(
				matcher.group(1),
				matcher.group(2),
				matcher.group(3)
			));
		
		return Optional.empty();
	}
	
	private String nick, user, host;
	
	public User(String nick, String user, String host) {
		this.nick = nick;
		this.user = user;
		this.host = host;
	}

	public String getNick() {
		return nick;
	}

	public String getUser() {
		return user;
	}

	public String getHost() {
		return host;
	}
	
}
