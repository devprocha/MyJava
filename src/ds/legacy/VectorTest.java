package ds.legacy;

import java.util.Vector;

public class VectorTest {
	
	/* Synchronized/thread-safe version of Reiszable-Array (implements java.util.List interface)
 		* 1) Algorithm : Reiszable-Array
 		* 2) Access: Random Access (by "index")
 		* 3) Iteration Order - Yes (insertion order)  
 		* 4) Allows Duplicates - Yes
 		* 5) Allows "Null" -  No (Throws NPE) 
 		* 6) Thread Safe - Yes (Doesn't throw ConcurrentModificationException)
 		* 7) Fail-Fast or Fail-Safe - Fail-Safe
	*/
	public static void Test() {
		final Vector<String> vector = new Vector<String>();
		vector.add("1");
		vector.add("2");
		//vector.add(null); //throws NPE
		
		//Alternatives: 1) non-thread safe - java.util.ArrayList 2) thread safe - java.util.concurrent.CopyOnWriteArrayList		
	}
}
