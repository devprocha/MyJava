package ds.map.test;

import java.util.HashMap;
import java.util.IdentityHashMap;


public class IdentityHashMapTest {
	
	/* A Identity Hash Map uses reference-equality in place of object-equality when comparing keys (and values)
	 * i.e. In an IdentityHashMap, two keys k1 and k2 are considered equal if and only if (k1==k2).
	 * (In normal Map implementations (like HashMap) two keys k1 and k2 are considered equal if and only if k1.equals(k2)
	 * 1) Algorithm : Hash Table
	 * 2) Access: Random Access (by key)
	 * 3) Iteration Order - No 
	 * 4) Allows Duplicate Keys - No (Replaces old value with the new value)
	 * 5) Allows "Null" -  Yes (Key - Only one(consecutive NULL key replaces old value with new value), Value - any)
	 * 6) Thread Safe - No (Throws ConcurrentModificationException)
	 * 7) Fail-Fast or Fail-Safe - Fail-Fast
	 */
	public static void Test() {
		final IdentityHashMap<String, Integer> identityMap = new IdentityHashMap<String, Integer>();
		Integer status = identityMap.put(new String("a"), 1);
		status = identityMap.put(new String("a"), 2); // key "a" is not duplicate, uses reference-equality in place of object-equality when comparing
		status = identityMap.put(new String("a"), 3); // key "a" is not duplicate
		status = identityMap.put(new String("a"), 4); // key "a" is not duplicate
		status = identityMap.put("b", 5); 
		status = identityMap.put("b", 6); // key "b" is duplicate
		System.out.println("Identity Map KeySet Size :: " +  identityMap.keySet().size());
		
		final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		status = hashMap.put(new String("a"), null); 		 
		status = hashMap.put(new String("a"), 2); // key "a" is duplicate
		status = hashMap.put(new String("a"), 3); // key "a" is duplicate
		status = hashMap.put(new String("a"), 4); // key "a" is duplicate
		System.out.println("Hash Map KeySet Size :: " + hashMap.keySet().size());
	}
}
