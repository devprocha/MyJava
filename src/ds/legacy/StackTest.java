package ds.legacy;

import java.util.Stack;

public class StackTest {
	
	/* The Stack class represents a last-in-first-out (LIFO) stack of objects. Extends the Vector class.
 		* 1) Algorithm : Reiszable-Array
 		* 2) Insertion : Always at "Tail/Last"
 		* 3) Deletion  : Always from "Tail/Last"
 		* 3) Iteration Order - Yes (insertion order)  
 		* 4) Allows Duplicates - Yes
 		* 5) Allows "Null" -  No (Throws NPE) 
 		* 6) Thread Safe - Yes (Doesn't throw ConcurrentModificationException)
 		* 7) Fail-Fast or Fail-Safe - Fail-Safe
	*/
	public static void Test() {
		final Stack<String> stack = new Stack<String>();
		stack.add("1");
		stack.add("2");
		//stack.add(null); //throws NPE
		
		//Alternatives: 1) non-thread safe - java.util.ArrayDequeue 2) thread safe - java.util.concurrent.LinkedBlockDequeue		
	}
}
