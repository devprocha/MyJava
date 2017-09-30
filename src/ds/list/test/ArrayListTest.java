package ds.list.test;

import java.util.ArrayList;
import java.util.Iterator;

/* Resizable-array implementation of the "List" interface
	* 1) Data Structure: Resizable "Array"
	* 2) Access: "Random Access" based on "index"
	* 3) Iteration Order - Yes (Insertion order)
	* 4) Allows Duplicates - Yes 
	* 5) Allows "Null" entries - Yes (one or more)
	* 6) Thread Safe - No (Throws ConcurrentModificationException)
	* 7) Fail-Fast or Fail-Safe - Fail-Fast
	* 8) Big-O Analysis
	* |------------------------------------|
	* |	 Insert | Delete  | Get  | Find	   |
	* |---------|---------|------|---------|
	* |  O(n) 	|  O(n)   | O(1) | O(n)    |
	* |---------|---------|------|---------|
	* Insert -  Insertion of new element in between the elements requires shifting of following elements
	* Delete - Deletion of element in between the elements requires shifting of following elements
	* http://stackoverflow.com/questions/13288854/arraylist-constant-time-and-linear-time-access    
*/
public class ArrayListTest {
	
	public static void Test() {
		ArrayList<String> arrayList = new ArrayList<String>(); // with default capacity
		//ArrayList<String> arrayList = new ArrayList<String>(10); // with capacity 10
		// Insertion 
		arrayList.add("List#0"); // End
		arrayList.add(1, "List#0"); // Insert at specified index
		arrayList.add("List#1");
		arrayList.add("List#2");
		arrayList.add("List#3");
		arrayList.add("List#4");
		arrayList.add("List#4"); // Duplicate - OK
		arrayList.set(2, "NewList#2");  // Set at specified index
		arrayList.add(null); // Null - OK
		
		// Access - Random access based on insertion order i.e. Index
		String str = arrayList.get(2) ;
		
		// Deletion
		str = arrayList.remove(2); // by Index
		boolean status = arrayList.remove("List#0"); // by value
		
		// Iteration 
		Iterator<String> it = arrayList.iterator();
		while (it.hasNext()) {			
			System.out.println(it.next());
			//arrayList.add("List#5"); // Throws ConcurrentModificationException
		}
	}	
}
