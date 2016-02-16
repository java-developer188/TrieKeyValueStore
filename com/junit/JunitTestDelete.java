package com.junit;


import static org.junit.Assert.*;

import org.junit.Test;

import com.compilation.CommandsHandler;

public class JunitTestDelete {


	@Test
	public void testDeleteStore() {
		System.out.println("Inside testDeleteStore()"); 
		String commandOne = "create TestStore";
		String commandTwo = "delete TestStore";
		String result = "Store TestStore deleted";
		CommandsHandler handler = new CommandsHandler();
		handler.command(commandOne);
		assertEquals(result, handler.command(commandTwo));    
	}
}

