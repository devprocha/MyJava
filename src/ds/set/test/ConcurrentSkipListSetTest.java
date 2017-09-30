package ds.set.test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.Iterator;

public class ConcurrentSkipListSetTest {
	
	
	/* A scalable concurrent NavigableSet/SortedSet implementation based on a ConcurrentSkipListMap. 
		 * 1) Algorithm : Skip List (internally uses ConcurrentSkipListMap ) 
		 * 2) Access: No (No get() method)
		 * 3) Iteration Order - Yes (sorted order) 
		 * 4) Allows Duplicates - No 
		 * 5) Allows "Null" entries - No (Throws NPE)
		 * 6) Thread Safe - Yes (Doesn't throw ConcurrentModificationException)
		 * 7) Fail-Fast or Fail-Safe - Fail-Safe
	 */
	 public static void Test() {		 
		 final ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<String>();
		 // 1) - Insertion add()
		 boolean status = set.add(new String("0"));
		 status = set.add(new String("0")); // duplicates are NOT allowed
		 status = set.add(new String("1")); // sorted order
		 status = set.add(new String("2")); 
		 status = set.add(new String("3"));
		 System.out.println("ConcurrentSkipListSet:: " +  set.toString()); // order is not guaranteed (insertion order)
		 
		 // 2) - Deletion remove()
		 status =  set.remove("3");
		 System.out.println("ConcurrentSkipListSet:: " +  set.toString()); // order is not guaranteed (insertion order)

		 // 3) - Iteration iterator()
		 Iterator<String> it = set.iterator();
		 System.out.print("ConcurrentSkipListSet Iterator:: [");
		 while (it.hasNext()) {
			 System.out.print(it.next()); // order is not guaranteed (insertion order)			 
			 set.remove("2"); // Concurrent modification is allowed - Doesn't throw java.util.ConcurrentModificationException	 
			 if (it.hasNext()) {
				 System.out.print(", ");
			 }
		 }
		 System.out.println("]");
	}
}
