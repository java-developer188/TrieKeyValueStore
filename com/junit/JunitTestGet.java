package com.junit;


import static org.junit.Assert.*;

import org.junit.Test;

import com.compilation.CommandsHandler;

public class JunitTestGet {


	@Test
	public void testGet() {
		System.out.println("Inside testGet()"); 
		String commandOne = "create TestStore";
		String commandTwo = "insert Country Pakistan into TestStore";
		String commandThree = "get Country from TestStore";
		String result = "Pakistan";
		CommandsHandler handler = new CommandsHandler();
		handler.command(commandOne);
		handler.command(commandTwo);
		assertEquals(result, handler.command(commandThree));    
	}
}

