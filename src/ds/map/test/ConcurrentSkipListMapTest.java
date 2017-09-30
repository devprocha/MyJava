package ds.map.test;

import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapTest {
	
	/* Synchronized/thread-safe + sorted version of Map. 
	 * The map is sorted according to the natural ordering of its keys, or 
	 * by a Comparator provided at map creation time, depending on which constructor is used.
	 	* 1) Algorithm : Hash Table  
	 	* 2) Access: Random Access (by "key")
	 	* 3) Iteration Order - Yes (sorted order)  
	 	* 4) Allows Duplicate Keys - No (Replaces old value with the new value)
	 	* 5) Allows "Null" -  No (both Key & Value) 
	 	* 6) Thread Safe - Yes (Doesn't throw ConcurrentModificationException)
	 	* 7) Fail-Fast or Fail-Safe - Fail-Safe
	*/
	public static void Test() {
		final ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<String, String>();	
		boolean status = concurrentSkipListMap.put("key#1", "value#1")== null;
		status = concurrentSkipListMap.put("key#1", "value#1")==null; // duplicate keys are NOT allowed, replaces old value
		status = concurrentSkipListMap.put("key#2", "value#1")==null; // insertion order is not guaranteed
		status = concurrentSkipListMap.put("key#3", "value#1")==null;
		status = concurrentSkipListMap.put("key#4", "value#1")==null;
		//status = concurrentHashMap.put(null, "value#null")==null; // Throws NPE
	}
}
