package com.junit;


import static org.junit.Assert.*;

import org.junit.Test;

import com.compilation.CommandsHandler;

public class JunitTestCreate {


	@Test
	public void testCreateStore() {
		System.out.println("Inside testCreateStore()"); 
		String command = "create TestStore";
		String result = "Store TestStore created";
		CommandsHandler handler = new CommandsHandler();
		assertEquals(result, handler.command(command));    
	}
}
