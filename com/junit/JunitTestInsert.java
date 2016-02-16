package com.junit;


import static org.junit.Assert.*;

import org.junit.Test;

import com.compilation.CommandsHandler;

public class JunitTestInsert {


	@Test
	public void testInsert() {
		System.out.println("Inside testInsert()"); 
		String commandOne = "create TestStore";
		String commandTwo = "insert Age 23 into TestStore";
		String result = "Age 23 inserted into TestStore";
		CommandsHandler handler = new CommandsHandler();
		handler.command(commandOne);
		assertEquals(result, handler.command(commandTwo));    
	}
}

