package ds.map.test;

import java.util.Comparator;
import java.util.TreeMap;


public class TreeMapTest {
	
	private static class TestComparator<T> implements Comparator<T> {
		@Override
		public int compare(T k1, T k2) {
			return 0;
		}		
	}
	
	private static class MyKey {
		
		public int print() {
			return 0;
		}		
	}
	
	private static class MapKey<T> implements Comparable<T> {
		T mKey;
		
		MapKey(T key) {
			mKey = key;
		}

		@Override
		public int compareTo(T key) {
			return 1;
		}		
	}
			
	/* A Sorted Map
	 	* 1) Algorithm : Red Black Tree
	 	* 2) Access: Random Access
	 	* 3) Iteration Order - Yes (sorted key order) 
	 	* 4) Allows Duplicate Keys - No (Replaces old value with the new value)
	 	* 5) Allows "Null" - Key- No, Values - Yes
	 	* 6) Thread Safe - No (Throws ConcurrentModificationException)
	 	* 7) Fail-Fast or Fail-Safe - Fail-Fast
	*/
	public static void Test() {		
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
		treeMap.put("Key#1", 1);
		treeMap.put("Key#2", 2);
		treeMap.put("Key#2", 3); // Duplicate is not allowed. Replaces old value with new
		//treeMap.put(null, 1); //NULL key is not allowed; throws NPE 
		//treeMap.put(null, 2);
		System.out.println("Tree Map: " + treeMap.toString());
		
		// Sorts key based on natural ordering (sorts in "ascending order")
		TreeMap<MapKey<String>, Integer> treeMap2 = new TreeMap<MapKey<String>, Integer>(); // uses "natural ordering" provided by the key object for comparison i.e. MapKey.compareTo() method
		treeMap2.put(new MapKey<String>("x"), 1);
		treeMap2.put(new MapKey<String>("y"), 2);		
		// Comparable
		boolean status = treeMap2.put(new MapKey<String>("z"), 3) == null;
		MapKey<String> mapKey =  new MapKey<String>("xyz");		
		status = treeMap2.put(mapKey, 123) == null;
		status = treeMap2.put(mapKey, 1234) == null;
		System.out.println("Tree Map: " + treeMap2.toString());
		
		TreeMap<String, Integer> treeMap3 = new TreeMap<String, Integer>(new TestComparator());
		
		TreeMap<MyKey, Integer> treeMap4 = new TreeMap<MyKey, Integer>();
		treeMap4.put(new MyKey(), 10);
		treeMap4.put(new MyKey(), 20);
		
		
	}	
}
