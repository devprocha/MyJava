package ds.queue.test;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * A double-ended-queue is a linear collection of elements that supports the insertion and removal of elements at both end points.
 	* 1) Algorithm : Array
 	* 2) Insertion Order - "Tail/Last" or "Head/First"
 	* 3) Deletion/Examine - "Head/First" or "Tail/Last" 
 	* 4) Ordered - Yes (Insertion order) 
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
 
public class ArrayDequeTest {
	 
	public static void Test() {	 
		ArrayDeque<Integer> adq = new ArrayDeque<Integer>();
		adq.add(1);//by default, inserts at the end of queue
		adq.addLast(2); //insert at the end of this queue
		adq.addFirst(3); //insert at the head of this queue

		System.out.print("pq1: ");	
		System.out.print("[");
		for (int x=0; x < 3; x++) {
			System.out.print(adq.peek());
			System.out.print(", ");
		}
		
		adq.removeFirst(); // retrieves and removes head of this queue
		adq.removeLast(); // retrieves and removes tail of this queue
		adq.remove(); // by default, retrieves and removes head of this queue
		
		System.out.print("pq1: ");	
		System.out.print("[");		
		for (int x=0; x < 3; x++) {
			System.out.print(adq.peek());
			System.out.print(", ");
		}
	}
}
