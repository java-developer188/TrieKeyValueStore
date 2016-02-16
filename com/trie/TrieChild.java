package com.trie;

import java.util.LinkedList;

public class TrieChild {
	char content; 
	boolean isEnd; 
	int count;  
	LinkedList<TrieChild> children; 
	Object value ;
	
	public static final int NULL 		= 0;
	public static final int COMMAND 	= 1;
	public static final int ARGUMENT 	= 2;
	

	public TrieChild(char c)
	{
		children = new LinkedList<TrieChild>();
		isEnd = false;
		content = c;
		count = 0;
		value = "";
	}  

	public TrieChild findChildren(char c)
	{
		if (children != null)
			for (TrieChild child : children)
				if (child.content == c)
					return child;
		return null;
	}

	public void addChild(TrieChild child){
		if(child != null)
			children.add(child);
	}
	
	public void removeChild(TrieChild child){
		if(children.contains(child))
			children.remove(child);
	}
}

