package com.trie;

public class Trie
{
    private TrieChild root;
 

    public Trie()
    {
        root = new TrieChild(' '); 
    }
    
    
     /* Function to insert word */
    public void insert(String word)
    {
        if (search(word) == true) 
            return;        
        TrieChild current = root; 
        for (char ch : word.toCharArray() )
        {
            TrieChild child = current.findChildren(ch);
            if (child != null)
                current = child;
            else 
            {
                 current.addChild(new TrieChild(ch));
                 current = current.findChildren(ch);
            }
            current.count++;
        }
        current.isEnd = true;
    }
    
    public void insert(String word , Object value)
    {
        if (search(word) == true) 
            return;        
        TrieChild current = root; 
        for (char ch : word.toCharArray() )
        {
            TrieChild child = current.findChildren(ch);
            if (child != null)
                current = child;
            else 
            {
                 current.addChild(new TrieChild(ch));
                 current = current.findChildren(ch);
            }
            current.count++;
        }
        current.isEnd = true;
        current.value = value;
    }
    
    
    /* Function to search for word */
    public boolean search(String word)
    {
        TrieChild current = root;  
        
        for (char ch : word.toCharArray() )
        {
            if (current.findChildren(ch) == null)
                return false;
            else
                current = current.findChildren(ch);
        }      
        
        //now checking if this word is the end and no character is 
        //further present otherwise our required word is not present
        if (current.isEnd == true) 
            return true;
        
        return false;
    }
    
    public Object getValue(String word)
    {
    	if(search(word))
    	{
        TrieChild current = root;  
        
        for (char ch : word.toCharArray() )
        {
                current = current.findChildren(ch);
        }      
        
        //now checking if this word is the end and no character is 
        //further present otherwise our required word is not present
        if (current.isEnd == true) 
            return current.value;
    	}
    	 return "";
    }
    
    
    /* Function to remove a word */
    public void remove(String word)
    {
        if (search(word) == false)
        {
            System.out.println(word +" does not exist in trie\n");
            return;
        }      
        
        TrieChild current = root;
        for (char ch : word.toCharArray()) 
        { 
            TrieChild child = current.findChildren(ch);
            if (child.count == 1) 
            {
                current.removeChild(child);
                return;
            } 
            else 
            {
                child.count--;
                current = child;
            }
        }
        current.isEnd = false;
    }    
}