package com.compilation;

import com.trie.*;

/**
 * Verify the command's syntax and execute the action as per command.
 * @author SSHaider
 *
 */

public class Compiler {

	Trie		commandsTrie;
	Trie		keyValueStore;
	int 		command;
	String[] 	arguments;

	public static final int CREATE_COMMAND = 1;
	public static final int DELETE_COMMAND = 2;
	public static final int INSERT_COMMAND = 3;
	public static final int INTO_COMMAND = 4;
	public static final int GET_COMMAND = 5;
	public static final int FROM_COMMAND = 6;
	public static final int EXISTS_COMMAND = 7;
	public static final int IN_COMMAND = 8;


	public Compiler(Trie commandsTrie,Trie keyValueStore){
		this.commandsTrie = commandsTrie;
		this.keyValueStore = keyValueStore;
	}

/**
 * Verify the syntax. Takes all tokens (array of words in the command) to verify 
 * the syntax and arguments count.
 * @param tokens
 * @return 
 * <p><b>true</b> if syntax and argument count is correct</p>
 * <p><b>false</b> if syntax or argument count is not correct.</p>
 */
	public boolean compile(String[] tokens){
		return compile(tokens,false);
	}

	/**
	 * Take tokens to verify. <p>If <b>onlySyntax</b> is true then it simple returns a boolean upon command verification
	 * else it properly shows error message. </p>
	 * @param tokens
	 * @param onlySyntax
	 * @return
	 * <p><b>true</b> if syntax and argument count is correct</p>
	 * <p><b>false</b> if syntax or argument count is not correct.</p>
	 */
	private boolean compile(String[] tokens,boolean onlySyntax){
		if(commandsTrie.search(tokens[0])){
			int code = (int) commandsTrie.getValue(tokens[0]);
			switch (code) {
			case CREATE_COMMAND:
				if(1 == tokens.length-1){
					command = CREATE_COMMAND;
					arguments = tokens;
					return true;
				}
				else{
					System.out.print(getCommandString(tokens)+"        Syntax Error\n\n");
					return false;
				}

			case DELETE_COMMAND:
				if(1 == tokens.length-1){
					command = DELETE_COMMAND;
					arguments = tokens;
					return true;
				}
				else{
					System.out.print(getCommandString(tokens)+"        Syntax Error\n\n");
					return false;
				}

			case INSERT_COMMAND:
				if(4 == tokens.length-1){
					command = INSERT_COMMAND;
					arguments = tokens;
					if(compile(new String[]{tokens[3],tokens[4]},true)){
						if(INTO_COMMAND == (int)commandsTrie.getValue(tokens[3]))		
							return true;
						else{
							System.out.print(getCommandString(null)+"        Syntax Error near "+tokens[3]+"\n\n");
							return false;
						}		
					}
					else{
						//System.out.print(getCommandString()+"        Syntax Error\n\n");
						return false;
					}
				}
				else{
					System.out.print(getCommandString(tokens)+"        Syntax Error\n\n");
					return false;
				}

			case GET_COMMAND:
				if(3 == tokens.length-1){
					command = GET_COMMAND;
					arguments = tokens;
					if(compile(new String[]{tokens[2],tokens[3]},true)){			
						if(FROM_COMMAND == (int)commandsTrie.getValue(tokens[2]))		
							return true;
						else{
							System.out.print(getCommandString(null)+"        Syntax Error near "+tokens[2]+"\n\n");
							return false;
						}	
					}
					else{
						//System.out.print(getCommandString()+"        Syntax Error\n\n");
						return false;
					}
				}
				else{
					System.out.print(getCommandString(tokens)+"        Syntax Error\n\n");
					return false;
				}

			case FROM_COMMAND:
				if(1 == tokens.length-1){
					if(!onlySyntax){
						command = FROM_COMMAND;
						arguments = tokens;
					}
					return true;
				}
				return false;

			case EXISTS_COMMAND:
				if(3 == tokens.length-1){
					command = EXISTS_COMMAND;
					arguments = tokens;
					if(compile(new String[]{tokens[2],tokens[3]},true)){				
						if(IN_COMMAND == (int)commandsTrie.getValue(tokens[2]))		
							return true;
						else{
							System.out.print(getCommandString(null)+"        Syntax Error near "+tokens[2]+"\n\n");
							return false;
						}	
					}
					else{
						//System.out.print(getCommandString()+"        Syntax Error\n\n");
						return false;
					}
				}
				else{
					System.out.print(getCommandString(tokens)+"        Syntax Error\n\n");
					return false;
				}

			case IN_COMMAND:
				if(1 == tokens.length-1){
					if(!onlySyntax){
						command = IN_COMMAND;
						arguments = tokens;
					}
					return true;
				}
				return false;

			case INTO_COMMAND:
				if(1 == tokens.length-1){
					if(!onlySyntax){
						command = INTO_COMMAND;
						arguments = tokens;
					}
					return true;
				}
				return false;

			default:
				return false;
			}
		}
		else{
			System.out.print(getCommandString(tokens)+"        Syntax Error near "+tokens[0]+"\n\n");
			return false;
		}
	}

/**
 * Perform action as per command.
 * @return
 * Object
 * @throws IllegalArgumentException
 * If the store does not exist
 */
	public Object execute() throws IllegalArgumentException{

		switch (this.command) 
		{

		case CREATE_COMMAND:
			if(!keyValueStore.search(arguments[1]))
			{
				keyValueStore.insert(arguments[1], new Trie());
				System.out.print(getCommandString(null)+"        Store "+arguments[1]+" created\n\n");
				return "Store "+arguments[1]+" created";
			}
			else{
				System.out.print(getCommandString(null)+"        Store "+arguments[1]+" already exist\n\n");
				return "Store "+arguments[1]+" already exist";
			}


		case DELETE_COMMAND:
			if(keyValueStore.search(arguments[1]))
			{
				keyValueStore.remove(arguments[1]);
				System.out.print(getCommandString(null)+"        Store "+arguments[1]+" deleted\n\n");
				return "Store "+arguments[1]+" deleted";
			}
			else{
				System.out.print(getCommandString(null)+"        Store "+arguments[1]+" does not exist\n\n");	
				return "Store "+arguments[1]+" does not exist";
			}


		case INSERT_COMMAND:
			Compiler compiler_into = new Compiler(commandsTrie, keyValueStore);

			if(compiler_into.compile(new String[]{arguments[3],arguments[4]}))
			{	
				try{
					Object trieObject = compiler_into.execute();
					if(trieObject != null && trieObject instanceof Trie){
						Trie trie = (Trie) trieObject;
						trie.insert(arguments[1], arguments[2]);
						System.out.print(getCommandString(null)+"        "+arguments[1]+" "+arguments[2]+" inserted into "+arguments[4]+"\n\n");	
						return arguments[1]+" "+arguments[2]+" inserted into "+arguments[4];
					}
				}
				catch(IllegalArgumentException e){
					System.out.print(getCommandString(null)+"        Store "+arguments[4]+" does not exist\n\n");
					return "Store "+arguments[4]+" does not exist";
				}
			}
			else{
				System.out.print(getCommandString(null)+"        Error near "+arguments[3]+"\n\n");	
				return "Error near "+arguments[3];
			}


		case GET_COMMAND:
			Compiler compiler_from = new Compiler(commandsTrie, keyValueStore);

			if(compiler_from.compile(new String[]{arguments[2],arguments[3]}))
			{	
				try{
					Object trieObject = compiler_from.execute();
					if(trieObject != null && trieObject instanceof Trie){
						Trie trie = (Trie) trieObject;
						if(trie.search(arguments[1])){
							System.out.print(getCommandString(null)+"        "+(String)trie.getValue(arguments[1])+"\n\n");
							return (String)trie.getValue(arguments[1]);
						}
						else{
							System.out.print(getCommandString(null)+"        "+arguments[1]+" does not exist in "+arguments[3]+"\n\n");
							return arguments[1]+" does not exist in "+arguments[3];
						}
					}
				}
				catch(IllegalArgumentException e){
					System.out.print(getCommandString(null)+"        Store "+arguments[3]+" does not exist\n\n");
					return "Store "+arguments[3]+" does not exist\n\n";
				}
			}
			else{
				System.out.print(getCommandString(null)+"        Error near "+arguments[2]+"\n\n");	
				return "Error near "+arguments[2];
			}


		case EXISTS_COMMAND:
			Compiler compiler_in = new Compiler(commandsTrie, keyValueStore);

			if(compiler_in.compile(new String[]{arguments[2],arguments[3]}))
			{	
				try{
					Object trieObject = compiler_in.execute();
					if(trieObject != null && trieObject instanceof Trie){
						Trie trie = (Trie) trieObject;
						if(trie.search(arguments[1])){
							System.out.print(getCommandString(null)+"        true\n\n");
							return "true";
						}
						else{
							System.out.print(getCommandString(null)+"        false\n\n");	
							return "false";
						}
					}
				}
				catch(IllegalArgumentException e){
					System.out.print(getCommandString(null)+"        Store "+arguments[3]+" does not exist\n\n");
					return "Store "+arguments[3]+" does not exist";
				}
			}
			else{
				System.out.print(getCommandString(null)+"        Error near "+arguments[2]+"\n\n");	
				return "Error near "+arguments[2];
			}


		case IN_COMMAND:

		case INTO_COMMAND:

		case FROM_COMMAND:
			if(keyValueStore.search(arguments[1])){
				return keyValueStore.getValue(arguments[1]);
			}
			else 
				throw new IllegalArgumentException();
		}
		return "";
	}

	private String getCommandString(String[] args){
		if(args == null)
			args = arguments;

		String commandString = "";
		for(String s : args){
			commandString += s + " ";
		}
		return commandString;
	}
}
