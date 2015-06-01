package com.lukasdietrich.irclib.model;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class Config {

	private String hostname;
	private int port;
	private boolean ssl;
	
	private String user, nick, realname;
	private String password;
	
	public Config(String hostname, int port, boolean ssl, String user, String nick, String realname, String password) {
		this.hostname = hostname;
		this.port = port;
		this.ssl = ssl;
		this.user = user;
		this.nick = nick;
		this.realname = realname;
		this.password = password;
	}

	public String getHostname() {
		return hostname;
	}

	public int getPort() {
		return port;
	}

	public boolean isSsl() {
		return ssl;
	}

	public String getUser() {
		return user;
	}

	public String getNick() {
		return nick;
	}

	public String getRealname() {
		return realname;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean hasPassword() {
		return password != null && password.length() > 0;
	}
	
	public SocketFactory getDefaultSocketFactory() {
		return isSsl() 
			? SSLSocketFactory.getDefault()
			: SocketFactory.getDefault();
	}
	
}
