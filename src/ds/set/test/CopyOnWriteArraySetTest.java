package ds.set.test;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Iterator;

public class CopyOnWriteArraySetTest {	
	
	/* A Synchronized/thread-safe set 
	 * 1) Algorithm : Array 
	 * 2) Access: Yes (No get() method)
	 * 3) Iteration Order - No 
	 * 4) Allows Duplicates - No 
	 * 5) Allows "Null" entries - Yes (only one entry)
	 * 6) Thread Safe - Yes (Doesn't throw ConcurrentModificationException)
	 * 7) Fail-Fast or Fail-Safe - Fail-Safe
    */
	 public static void Test() {		 
		 final CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();
		 // 1) - Insertion add()
		 boolean status = set.add(new String("0"));
		 status = set.add(new String("0")); // duplicates are NOT allowed
		 status = set.add(new String("1")); // insertion oder is NOT guaranteed
		 status = set.add(new String("2")); 
		 status = set.add(new String("3")); 
		 status = set.add(null); // only one NULL element is allowed
		 status = set.add(null); // NULL element is not allowed		 
		 System.out.println("CopyOnWriteArraySet:: " +  set.toString()); // order is not guaranteed (insertion order)
		 
		 // 2) - Deletion remove()
		 status =  set.remove("3");
		 System.out.println("CopyOnWriteArraySet:: " +  set.toString()); // order is not guaranteed (insertion order)

		 // 3) - Iteration iterator()
		 Iterator<String> it = set.iterator();
		 System.out.print("CopyOnWriteArraySet Iterator:: [");
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
