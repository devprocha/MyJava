package ds.queue.test;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/*
 * A thread-safe "bounded priority blocking queue" based on a priority heap.
 	* 1) Algorithm : Priority Heap
 	* 2) Insertion Order - Always "Tail/Last"
 	* 3) Deletion/Examine - Always "Head/First" element 
 	* 4) Ordered - Yes (Priority order)
 	* 5) Bounded/Unbounded - Bounded
 	* 6) Allows Duplicates - Yes 
 	* 7) Allows "Null" - No (Throws NPE)
 	* 8) Thread Safe - No 
 	* 9) Fail-Fast or Fail-Safe - Fail-Safe
 * |------------------------------------------------------------------------|
 * |	      |Throws exception| Special value|	Blocks|	Times out           |
 * |----------|----------------|--------------|-------|---------------------|
 * | Insert   |add(e)	       | offer(e)	  | put(e)|	offer(e, time, unit)|
 * |----------|----------------|--------------|-------|---------------------|
 * | Remove	  |remove()	       | poll()	      | take()|	poll(time, unit)    |
 * |----------|----------------|--------------|-------|---------------------|
 * | Examine  |element()	   | peek()	      | N/A   |						|
 * |------------------------------------------------------------------------|
 */
public class PriorityBlockingQueueTest {

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
		// 1) Bounded PBQ with "natural ordering"
		PriorityBlockingQueue<Element<Integer>> pq1 = new PriorityBlockingQueue<Element<Integer>>(); 
		pq1.add(new Element<Integer>(1)); // throws exception if queue is full
		pq1.put(new Element<Integer>(2)); // blocks if queue is full
		pq1.offer(new Element<Integer>(3)); // does nothing and returns special value if queue is full		
		// pq1.add(null); NULL not allowed, throws NPE
		System.out.print("pq1: ");	
		System.out.print("[");		
		for (int x=0; x < 3; x++) {
			System.out.print(pq1.poll().get());
			System.out.print(", ");
		}
		System.out.println("]");
		
		// 2) Bounded PBQ with "Comparator"
		PQComparator pqs = new PQComparator(); //
		PriorityBlockingQueue<Integer> pq2 = new PriorityBlockingQueue<Integer>(5, pqs);
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
