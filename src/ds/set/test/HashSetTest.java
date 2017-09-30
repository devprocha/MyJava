package ds.set.test;

import java.util.HashSet;
import java.util.Spliterator;
import java.util.Iterator;

public class HashSetTest {	
			 
	/* Implements "Set" interface
 		* 1) Data Structure: Hash Table (internally uses HashMap for the storage)
 		* 2) Access: No (No get() method)
 		* 3) Iteration Order - No 
 		* 4) Allows Duplicates - No 
 		* 5) Allows "Null" entries - Yes (only one entry)
 		* 6) Thread Safe - No (Throws ConcurrentModificationException)
 		* 7) Fail-Fast or Fail-Safe - Fail-Fast
 		* 8) Load Factor(LF) is 0.75f (LF = Total No.of Elements/Total Capacity(No.of Buckets))
 		*     http://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap  
 		* 9) Big-O Analysis
	 	* |------------------------------------|
	 	* |	 Insert | Remove  | Get  | Find    |
	 	* |---------|---------|------|---------|
	 	* |  O(1) 	| O(1)    | O(n) | O(1)    |
	 	* |---------|---------|------|---------|
	 	*  Remove - O(1) - If we remove head/tail element (using removeFirst(), removeLast(), remove() ),
	 	*         - O(n) - If we remove elements in between using index/element (remove(index), remove(element))
	 	* 
    */
	public static void Test() {		 
		 final HashSet<String> hashSet = new HashSet<String>();
		 // 1) Insertion
		 boolean status;
		 status = hashSet.add(new String("0"));
		 status = hashSet.add(new String("0")); // Error - No duplicates are allowed, returns False
		 status = hashSet.add(new String("1")); // insertion oder is NOT guaranteed
		 status = hashSet.add(new String("2")); 
		 status = hashSet.add(new String("3"));
		 status = hashSet.add("4");
		 status = hashSet.add("5");
		 status = hashSet.add(null); // only one NULL element is allowed
		 status = hashSet.add(null); // NULL element is not allowed	here
		 status = hashSet.remove("5");		 
		 System.out.println("HashSet:: " +  hashSet.toString()); // order is not guaranteed (insertion order)

		 // 2) Access - No get() method 

		 // 3) Deletion
		 status =  hashSet.remove("3");
		 System.out.println("HashSet:: " +  hashSet.toString()); // order is not guaranteed (insertion order)

		 // 4-a) Iteration - using iterator()
		 Iterator<String> it = hashSet.iterator();
		 System.out.print("HashSet iterator() Iteration:: [");
		 while (it.hasNext()) {
			 System.out.print(it.next()); // order is not guaranteed (insertion order)			 
			 //set.remove("2"); // Concurrent modification is not allowed - Throws java.util.ConcurrentModificationException	 
			 if (it.hasNext()) {
				 System.out.print(", ");
			 }
		 }
		 System.out.println("]");
		 
		 // 4-b) Iteration - using enhanced for loop
		 System.out.print("HashSet For loop Iteration:: [");
		 for (String str: hashSet) {
			 System.out.print(str); // order is not guaranteed (insertion order)			 
			 //set.remove("2"); // Concurrent modification is not allowed - Throws java.util.ConcurrentModificationException	 
			 System.out.print(", ");
		 }
		 System.out.println("]");
		 
		 // 4-c) Iteration - using forEach()
		 System.out.print("HashSet For-each loop Iteration:: [");
		 hashSet.forEach(s->System.out.print(s + ", ")); // order is not guaranteed (insertion order)		 
		 System.out.println("]");
		 
		// 4-d) Iteration - using spliterator()
		 Spliterator<String> sit = hashSet.spliterator();
		 System.out.println("HashSet::Iterator size before splitting: " +  sit.estimateSize());
		 Spliterator<String> partitionSit = sit.trySplit();
		 System.out.println("HashSet::Iterator size after splitting: " +  sit.estimateSize());
	}
}
