package ds.list.test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;

public class CopyOnWriteArrayListTest {	
		
	/* Thread-safe ArrayList
	 	* 1) Algorithm : Resizable "Array"
	 	* 2) Access: "Random Access" based on "index"
	 	* 3) Iteration Order - Yes (Insertion order)
	 	* 4) Allows Duplicates - Yes 
	 	* 5) Allows "Null" entries - Yes (one or more)
	 	* 6) Thread Safe - Yes (Doesn't throw ConcurrentModificationException)
	 	* 7) Fail-Fast or Fail-Safe - Fail-Safe
	 	* 8) Big-O Analysis
	 	* |------------------------------------|
	 	* |	 Add    | Remove  | Get  | Find    |
	 	* |---------|---------|------|---------|
	 	* |  O(n)	|  O(n)   | O(1) | O(n)    |
	 	* |---------|---------|------|---------|
	 	* Insert -  Insertion of new element in between the elements requires making of fresh copy of array and shifting of following elements
	 	* Delete - Deletion of element in between the elements requires making of fresh copy of array and shifting of following elements
	 	* http://stackoverflow.com/questions/13288854/arraylist-constant-time-and-linear-time-access 
	 	* 9) All mutation operations (Add, Remove, Set) makes fresh copy of array
	 */
	public static void Test() {
		CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<String>();
		arrayList.add("List#0");
		arrayList.add("List#1");
		arrayList.add("List#2");
		arrayList.add("List#3");
		arrayList.add("List#4");
		System.out.println("Size:" + arrayList.size());
		
		Iterator<String> it = arrayList.iterator();
		while (it.hasNext()) {			
			System.out.println(it.next());
			arrayList.add("List#5"); // Doesn't throw ConcurrentModificationException
		}
	}
}
