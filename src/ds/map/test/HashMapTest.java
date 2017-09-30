package ds.map.test;

import java.util.HashMap;

public class HashMapTest {
	
	/* Implements "Map" interface
		* 1) Algorithm : Hash Table 
		* 2) Access: Random Access (by "key")
		* 3) Iteration Order - No 
		* 4) Allows Duplicate Keys - No  
		* 5) Allows "Null" - Yes (Key - Only one(consecutive NULL key replaces old value with new value), Value - any) 
		* 6) Thread Safe - No (Throws ConcurrentModificationException)
		* 7) Fail-Fast or Fail-Safe - Fail-Fast
	*/
	public static void Test() {
		final HashMap<String, String> hashMap = new HashMap<String, String>();		
		hashMap.put("Key#1", "Value#1");		
		hashMap.put(null, "Value#Null"); //NULL key is OK, creates new entry
		hashMap.put(null, "New Value#Null2"); //consecutive NULL key is OK but replaces old(above) value with new value
		hashMap.put("Key#2", "Value#2");
		hashMap.put("Key#3", "Value#3");		
		String value = hashMap.put("Key#3", "New Value#3"); // replaces old value
		System.out.println("Old Value : " + value);
		value = hashMap.get("Key#3"); // replaces old value
		System.out.println("New Value : " + value);		
	}
}
