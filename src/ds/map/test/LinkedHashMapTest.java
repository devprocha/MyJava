package ds.map.test;

import java.util.LinkedHashMap;

public class LinkedHashMapTest {
	
	/* Extends the "HashMap" class
	 	* 1) Algorithm : Hash Table + special doubly-linked list 
	 	* 2) Access: Random Access (by "key")
	 	* 3) Iteration Order - Yes (insertion order) 
	 	* 4) Allows Duplicate Keys - No (Replaces old value with the new value)
	 	* 5) Allows "Null" -  Yes (Key - Only one(consecutive NULL key replaces old value with new value), Value - any)
	 	* 6) Thread Safe - No (Throws ConcurrentModificationException)
	 	* 7) Fail-Fast or Fail-Safe - Fail-Fast
	*/
	public static void Test() {
		final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();	
		boolean status = linkedHashMap.put("key#1", "value#1")== null;
		status = linkedHashMap.put("key#1", "value#1")==null; // duplicate keys are NOT allowed, replaces old value
		status = linkedHashMap.put("key#2", "value#1")==null; // insertion order is guaranteed
		status = linkedHashMap.put("key#3", "value#1")==null;
		status = linkedHashMap.put("key#4", "value#1")==null;
		status = linkedHashMap.put(null, "value#null")==null; // only one NULL key is allowed (consecutive NULL key replaces old value with new value)
	}	
}
