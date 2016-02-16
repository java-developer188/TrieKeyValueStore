package com.compilation;

import com.trie.Trie;

public class CommandsHandler {
	Trie commandsTrie ;
	Trie keyValueStore;
	
	public CommandsHandler(){
		commandsTrie = new Trie();
		keyValueStore = new Trie();
		
		commandsTrie.insert("create", Compiler.CREATE_COMMAND);
		commandsTrie.insert("delete", Compiler.DELETE_COMMAND);
		commandsTrie.insert("insert", Compiler.INSERT_COMMAND);
		commandsTrie.insert("into", Compiler.INTO_COMMAND);
		commandsTrie.insert("get", Compiler.GET_COMMAND);
		commandsTrie.insert("from", Compiler.FROM_COMMAND);
		commandsTrie.insert("exists", Compiler.EXISTS_COMMAND);
		commandsTrie.insert("in", Compiler.IN_COMMAND);
	}

	public String  command(String s){
		Compiler compiler = new Compiler(commandsTrie, keyValueStore);

		if(compiler.compile(fineTune(s)))
			return (String) compiler.execute();
		
		return "";
	}
	
	private String[] fineTune(String s){
		String[] rawCommand = s.trim().split(" ");
		String result="";
		for(int i = 0 ; i <  rawCommand.length ; i++){
			if(!rawCommand[i].isEmpty())
				result += rawCommand[i]+" ";
		}
		return result.trim().split(" ");
	}
}
