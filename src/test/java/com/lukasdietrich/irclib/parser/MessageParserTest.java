package com.lukasdietrich.irclib.parser;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.lukasdietrich.irclib.model.messages.RawMessage;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageParserTest {
	
	private MessageParser parser;
	
	@Before
	public void before() {
		parser = new MessageParser(null);
	}
	
	@Test
	public void testParse() {
		Assert.assertEquals(
			new RawMessage("prefix", "command1", "parameter1 parameter2"), 
			parser.parse(":prefix command1 parameter1 parameter2")
		);
	}
	
	@Test
	public void testParseWithoutPrefix() {
		Assert.assertEquals(
			new RawMessage(null, "command1", "parameter1 parameter2"),
			parser.parse("command1 parameter1 parameter2")
		);
	}
	
	@Test
	public void testParseRandom() {
		String prefix 		= String.valueOf(UUID.randomUUID().getMostSignificantBits()),
			   command 		= String.valueOf(UUID.randomUUID().getMostSignificantBits()),
			   parameters 	= String.valueOf(UUID.randomUUID().getMostSignificantBits());
	
		Assert.assertEquals(
			new RawMessage(prefix, command, parameters),
			parser.parse(String.format(":%s %s %s", prefix, command, parameters))
		);
	}
	

}
