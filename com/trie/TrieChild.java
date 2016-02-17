package com.trie;

import java.util.LinkedList;

/**
 * Basic object or node in the Trie data structure. Contains the actual character,list of next characters (children)
 * , count and its value. Also a boolean indicating the end.
 * @author SSHaider
 *
 */
public class TrieChild {
	char content; 
	boolean isEnd; 
	int count;  
	LinkedList<TrieChild> children; 
	Object value ;
	

	public TrieChild(char c)
	{
		children = new LinkedList<TrieChild>();
		isEnd = false;
		content = c;
		count = 0;
		value = "";
	}  
	
/**
 * Returns the next TrieChild if exist for the provided character.
 * @param c char character to be searched
 * @return
 * <b>TrieChild</b> if exist in the children of the current TrieChild object.
 */
	public TrieChild findChildren(char c)
	{
		if (children != null)
			for (TrieChild child : children)
				if (child.content == c)
					return child;
		return null;
	}

	/**
	 * Adds a child to the list of children.
	 * @param child
	 */
	public void addChild(TrieChild child){
		if(child != null)
			children.add(child);
	}
	
	
	/**
	 * Removes a child from the children.
	 * @param child
	 */
	public void removeChild(TrieChild child){
		if(children.contains(child))
			children.remove(child);
	}
}

