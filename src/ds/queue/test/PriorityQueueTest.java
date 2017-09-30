package ds.queue.test;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * An unbounded priority queue based on a priority heap.
 	* 1) Algorithm : Priority Heap
 	* 2) Insertion Order - Always "Tail/Last"
 	* 3) Deletion/Examine - Always "Head/First" element 
 	* 4) Ordered - Yes (Priority order)
 	* 5) Bounded/Unbounded - Unbounded
 	* 6) Allows Duplicates - Yes 
 	* 7) Allows "Null" - No (Throws NPE)
 	* 8) Thread Safe - No 
 	* 9) Fail-Fast or Fail-Safe - Fail-Fast
 * |--------------------------------------------------------------------
 * |	      |Throws exception(For only bounded Queues)| Special value|
 * |----------|-----------------------------------------|--------------|
 * | Insert   |add(e)	                                | offer(e)	   |
 * |----------|-----------------------------------------|--------------|
 * | Remove	  |remove()	                                | poll()       |
 * |----------|-----------------------------------------|--------------|
 * | Examine  |element()	                            | peek()	   |
 * |--------------------------------------------------------------------
 */
public class PriorityQueueTest {

	static class PQComparator implements Comparator<Integer> {
		 
		public int compare(Integer one, Integer two) {
			return one - two; 
		}
	}
	
	static class Element<T> implements Comparable<T> {
		T mElement;
		
		Element(T element) {
			mElement = element;
		}

		@Override
		public int compareTo(T element) {
			return 1;
		}
		
		public T get() {
			return mElement;
		}
	} 
 
	public static void Test() {
		int[] ia = {1, 10, 5, 3, 4, 7, 6, 9, 8, 2};
		// 1) Unbounded PQ with "natural ordering"
		PriorityQueue<Element<Integer>> pq1 = new PriorityQueue<Element<Integer>>(); 
		pq1.add(new Element<Integer>(1));
		pq1.add(new Element<Integer>(2));
		pq1.add(new Element<Integer>(3));
		pq1.add(new Element<Integer>(4));
		// pq1.add(null); NULL not allowed, throws NPE
		System.out.print("pq1: ");	
		System.out.print("[");		
		for (int x=0; x < 3; x++) {
			System.out.print(pq1.poll().get());
			System.out.print(", ");
		}
		System.out.println("]");
		
		// 2) Unbounded PQ with "Comparator"
		PQComparator pqs = new PQComparator(); //
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(5, pqs);
		for (int x : ia) {
			pq2.offer(x);
		}		
		System.out.print("pq2: ");		
		System.out.print("[");
		for (int x : ia) {
			System.out.print(pq2.poll());
			System.out.print(", ");
		}
		System.out.println("]"); 
	}
}
