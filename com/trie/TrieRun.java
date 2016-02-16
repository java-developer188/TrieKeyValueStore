package com.trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.compilation.CommandsHandler;
import com.compilation.Compiler;

public class TrieRun {
	
	public static void main(String[] args){
		
		CommandsHandler handler = new CommandsHandler();
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter File name ( with complete path):\n");
			File file = new File(scanner.next());
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			
			while((line = bufferedReader.readLine())!= null)
			{
				 handler.command(line);
			}
			scanner.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.print("File not found");
		} catch (IOException e) {
			System.out.print("I/O Error");
		}
		
		
//		CommandsHandler handler = new CommandsHandler();
//		
//		handler.command("      create                          haider");
//		handler.command("insert name ssh into haider");
//		handler.command("exists name in haider");
//		handler.command("get name from  haider");
//		handler.command("create haider");
//		handler.command("delete haider");
//		handler.command("insert name ssh into haider");
		
	}

}
