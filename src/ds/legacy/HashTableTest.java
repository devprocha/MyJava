package ds.legacy;

import java.util.Hashtable;

public class HashTableTest {
	
	/* Synchronized/thread-safe version of Map (implements java.util.Map interface and extends java.util.Dictionary)
 		* 1) Algorithm : Hash Table
 		* 2) Access: Random Access (by "key")
 		* 3) Iteration Order - No   
 		* 4) Allows Duplicates - No
 		* 5) Allows "Null" -  Keys-No, Values-No  (Throws NPE) 
 		* 6) Thread Safe - Yes (Doesn't throw ConcurrentModificationException)
 		* 7) Fail-Fast or Fail-Safe - Fail-Safe
 		* http://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable
	*/
	public static void Test() {
		final Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("Key#1", "Value#1");
		ht.put("Key#2", "Value#2");
		
		//Alternatives: 1) non-thread safe - java.util.HashMap 2) thread safe - java.util.concurrent.ConcurrentHashMap		
	}
}
