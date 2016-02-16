package com.junit;


import static org.junit.Assert.*;

import org.junit.Test;

import com.compilation.CommandsHandler;

public class JunitTestExist {


	@Test
	public void testExist() {
		System.out.println("Inside testExist()"); 
		String commandOne = "create TestStore";
		String commandTwo = "insert Language Urdu into TestStore";
		String commandThree = "exists Language in TestStore";
		String result = "true";
		CommandsHandler handler = new CommandsHandler();
		handler.command(commandOne);
		handler.command(commandTwo);
		assertEquals(result, handler.command(commandThree));    
	}
}

