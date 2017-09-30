package ds.map.test;

import java.util.concurrent.ConcurrentHashMap;


public class ConcurrentHashMapTest {
	
	/* Synchronized/thread-safe version of Map
 	* 1) Algorithm : Hash Table  
 	* 2) Access: Random Access (by "key")
 	* 3) Iteration Order - No  
 	* 4) Allows Duplicate Keys - No (Replaces old value with the new value)
 	* 5) Allows "Null" -  No (both Keys & Values) 
 	* 6) Thread Safe - Yes (Doesn't throw ConcurrentModificationException)
 	* 7) Fail-Fast or Fail-Safe - Fail-Safe
	*/
	public static void TestConcurrentHashMap() {
		final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();	
		boolean status = concurrentHashMap.put("key#1", "value#1")== null;
		status = concurrentHashMap.put("key#1", "value#1")==null; // duplicate keys are NOT allowed, replaces old value
		status = concurrentHashMap.put("key#2", "value#1")==null; // insertion order is not guaranteed
		status = concurrentHashMap.put("key#3", "value#1")==null;
		status = concurrentHashMap.put("key#4", "value#1")==null;
		//status = concurrentHashMap.put(null, "value#null")==null; // Throws NPE
	}
}
