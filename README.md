# TrieKeyValueStore

It implements a Trie data structure to store key/value pairs, also it has a trie for all valid commands.

TrieRun class takes a file name (with complete path) as an input from user and then it reads the file line by line and execute the commands."TestFile.txt" present in the /src has the commands which can be used to test the working of the project.


com.junit package includes a JunitTest class and other test cases as Junit Test Suite

Five test cases which tests the following commands;

---JunitTestCreate:
In this case create command is tested and if everything goes fine it will create the store and return the expected result.

  create XYZSTORE      XYZSTORE created


---JunitTestDelete:
In this case delete command is tested so first we create a store using create command and then delete it.

  delete XYZSTORE      XYZSTORE deleted


---JunitTestInsert:
In this case insert command is tested ,we first create a store using create command then insert a KEY/VALUE in that store.

  insert COLOR BLUE into XYZSTORE      COLOR BLUE inserted into XYZSTORE


---JunitTestGet:
In this case get command is tested,first we create a store then insert any KEY/VALUE then finally use the get command to get its value.
  
  get COLOR from XYZSTORE     BLUE


---JunitTestExists:
In this case exists command is tested. This wil either return true/false upon existence of the key in the store,so after creating store and inserting KEY/VALUE it uses exists command.

  exists COLOR in XYZSTORE     true
