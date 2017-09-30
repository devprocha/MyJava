package ds.set.test;

import java.util.TreeSet;

public class TreeSetTest {	
	
	/* A NavigableSet implementation based on a TreeMap
		* 1) Algorithm : Red Black Tree (internally uses TreeMap for the storage)
		* 2) Access: No (No get() method)
		* 3) Iteration Order - Yes (sorted order) 
		* 4) Allows Duplicates - No 
		* 5) Allows "Null" entries - No
		* 6) Thread Safe - No (Throws ConcurrentModificationException)
		* 7) Fail-Fast or Fail-Safe - Fail-Fast
	*/
	public static void Test() {
		 final TreeSet<String> treeSet = new TreeSet<String>();
		 boolean status = treeSet.add(new String("A"));
		 status = treeSet.add(new String("A")); // duplicates are NOT allowed
		 status = treeSet.add(new String("C")); // insertion order is based on sorting (natural ordering - ascending)
		 status = treeSet.add(new String("E"));
		 status = treeSet.add(new String("D"));
		 status = treeSet.add(new String("B"));
		 //status = treeSet.add(null); // NULL element is not allowed - Throws NPE
		 
		 for (String str : treeSet) { // order is guaranteed (sorting order)
			 System.out.print(str);
			 System.out.print(",");
		 }
	}
}
