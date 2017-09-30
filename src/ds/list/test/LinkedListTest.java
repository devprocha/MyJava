package ds.list.test;

import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListTest {	
	
	/* Doubly-linked list implementation of the "List and Deque" interfaces
 		* 1) Data Structure: List
 		* 2) Access: "Sequential Access" based on "index"
 		* 3) Iteration Order - Yes (Insertion order)
 		* 4) Allows Duplicates - Yes 
 		* 5) Allows "Null" entries - Yes (one or more)
 		* 6) Thread Safe - No (Throws ConcurrentModificationException)
 		* 7) Fail-Fast or Fail-Safe - Fail-Fast
 		* 8) Big-O Analysis
	 	* |------------------------------------|
	 	* |	 Insert | Delete  | Get  | Find    |
	 	* |---------|---------|------|---------|
	 	* |  O(1) 	| O(1)    | O(n) | O(n)    |
	 	* |---------|---------|------|---------|
	 	* http://stackoverflow.com/questions/840648/why-is-inserting-in-the-middle-of-a-linked-list-o1
	 	* 
    */
	public static void Test() {
		LinkedList<String> linkedList = new LinkedList<String>();
		// Insertion
		linkedList.add("List#0");
		linkedList.add(1, "List#0"); // At specified index
		linkedList.add("List#1");
		linkedList.add("List#2");
		linkedList.add("List#3");
		linkedList.add("List#4");
		linkedList.add("List#4"); // Duplicate - OK
		linkedList.add(null); // Null - OK
		
		// Access - Random access based on insertion order i.e. Index
		String str = linkedList.get(2) ;
		
		// Deletion
		str = linkedList.remove(2); // by Index
		boolean status = linkedList.remove("List#0"); // by value
		
		// Iteration 
		Iterator<String> it = linkedList.iterator();
		while (it.hasNext()) {			
			System.out.println(it.next());
			//arrayList.add("List#5"); // Throws ConcurrentModificationException
		}
	}
}
