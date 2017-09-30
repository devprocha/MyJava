package ds.set.test;

import java.util.LinkedHashSet;

public class LinkedHashSetTest {	
	
	/* Extends the "HashSet" class
		* 1) Algorithm : Hash Table + Linked List (internally uses LinkedHashMap for the storage)
		* 2) Access: No (No get() method, only through iterator())
		* 3) Iteration Order - Yes (insertion order) 
		* 4) Allows Duplicates - No 
		* 5) Allows "Null" entries - Yes (only one entry)
		* 6) Thread Safe - No (Throws ConcurrentModificationException)
		* 7) Fail-Fast or Fail-Safe - Fail-Fast
	*/
	 public static void Test() {
		 final LinkedHashSet<String> set = new LinkedHashSet<String>();	
		 boolean status = set.add(new String("0"));
		 status = set.add(new String("0")); // duplicates are NOT allowed
		 status = set.add(new String("1")); // insertion order is guaranteed
		 status = set.add(new String("2"));
		 status = set.add(new String("3"));
		 status = set.add(null); // only one NULL element is allowed
		 status = set.add(null); // NULL element is not allowed
		 for (String str : set) {  // order is guaranteed (insertion order)
			 System.out.print(str);
			 System.out.print(",");
		 }
	}	
}
