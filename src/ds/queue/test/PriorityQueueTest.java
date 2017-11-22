package ds.queue.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import ds.queue.MyPriorityQueue;

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

	static class minHeapComparator implements Comparator<Integer> {
		 
		public int compare(Integer first, Integer second) {
			return first - second; //min-heap
		}
	}
	
	static class maxHeapComparator implements Comparator<Integer> {
		 
		public int compare(Integer first, Integer second) {
			return second - first; //max-heap
		}
	}
	
	static class Element implements Comparable<Element> {
		Integer mElement;
		
		Element(Integer element) {
			mElement = element;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 * Equals = 0
		 * Greater = positive number (>0)
		 * Lesser = negative number (<0)
		 */
		@Override
		public int compareTo(Element elementToCompare) {
			int diff = mElement - elementToCompare.get(); // by default (always) selfElement - elementToCompare --> min-heap
			if (diff == 0)
				return 0;
			else if (diff > 0)
				return 1;
			else // diff < 0
				return -1;
			
		   // or simply return mElement - elementToCompare.get();
		}
		
		public int get() {
			return mElement;
		}
	} 
	
	
	static class Element2 {
		Integer mElement;
		
		Element2(Integer element) {
			mElement = element;
		}


		public int compareTo(Element elementToCompare) {
			int diff = mElement - elementToCompare.get(); // by default (always) selfElement - elementToCompare --> min-heap
			if (diff == 0)
				return 0;
			else if (diff > 0)
				return 1;
			else // diff < 0
				return -1;
			
		   // or simply return mElement - elementToCompare.get();
		}
		
		public int get() {
			return mElement;
		}
	}
	
	public static void Test() {
		//testJavaPQ();
		testMyPQ();
	}
 
	public static void testJavaPQ() {
		int[] ia = {1, 10, 5, 3, 4, 7, 6, 9, 8, 2};
		// 1) Unbounded PQ with "natural ordering"
		PriorityQueue<Element> pq1 = new PriorityQueue<Element>();
		pq1.add(new Element(4));
		pq1.add(new Element(3));
		pq1.add(new Element(2));
		pq1.add(new Element(1));
		pq1.toArray();
		
		
		// pq1.add(null); NULL not allowed, throws NPE
		
		// Iteration - unordered
		System.out.print("pq1 unordered: ");	
		System.out.print("[");
		// Iteration order is not guaranteed
		Iterator<Element> it = pq1.iterator();
		while (it.hasNext()) {
			System.out.print(pq1.poll().get());
			System.out.print(", ");
		}
		System.out.println("]");
		
		// Iteration - ordered
		Element[] arr = (Element[])pq1.toArray();
		Arrays.sort(arr);
		System.out.print("pq1 ordered: ");	
		System.out.print("[");
		// Iteration order is not guaranteed
		for (Element el: arr){
			System.out.print(el.get());
			System.out.print(", ");
		}
		System.out.println("]");
		
		// 2) Unbounded PQ with "Comparator" (Min or Max Binary Heap)
		minHeapComparator pqcMin = new minHeapComparator(); //
		PriorityQueue<Integer> pqMin = new PriorityQueue<Integer>(5, pqcMin);
		for (int x : ia) {
			pqMin.offer(x);
		}		
		System.out.print("pqMin: ");	
		System.out.print("[");
		for (int x : ia) {
			System.out.print(pqMin.poll());
			System.out.print(", ");
		}
		System.out.println("]"); 
		
		maxHeapComparator pqcMax = new maxHeapComparator(); //
		PriorityQueue<Integer> pqMax = new PriorityQueue<Integer>(5, pqcMax);
		for (int x : ia) {
			pqMax.offer(x);
		}		
		System.out.print("pqMax: ");
		System.out.print("[");
		for (int x : ia) {
			System.out.print(pqMax.poll());
			System.out.print(", ");
		}
		System.out.println("]"); 
		
		// 3) Element with comparable interface implemented
		PriorityQueue<Integer> pq3= new PriorityQueue<Integer>(); 
		pq3.add(4);
		pq3.add(3);
		pq3.add(2);
		pq3.add(1);
		// pq1.add(null); NULL not allowed, throws NPE
		System.out.print("pq3: ");	
		System.out.print("[");
		Iterator<Integer> it2 = pq3.iterator();
		while (it2.hasNext()) {
			System.out.print(pq3.poll());
			System.out.print(", ");
		}
		System.out.println("]");
	}
	
	public static void testMyPQ() {
		int[] ia = {1, 10, 5, 3, 4, 7, 6, 9, 8, 2};
		// 1) Unbounded PQ with "natural ordering"
		MyPriorityQueue<Element> pq1 = new MyPriorityQueue<Element>();
		pq1.add(new Element(4));
		pq1.add(new Element(3));
		pq1.add(new Element(2));
		pq1.add(new Element(1));

		
		// pq1.add(null); NULL not allowed, throws NPE
		
		// Iteration - unordered
		System.out.print("mypq1 unordered: ");	
		System.out.print("[");
		// Iteration order is not guaranteed
		Iterator<Element> it = pq1.iterator();
		while (it.hasNext()) {
			System.out.print(pq1.poll().get());
			System.out.print(", ");
		}
		System.out.println("]");
		
		// Iteration - ordered
		Element[] arr = (Element[])pq1.toArray();
		Arrays.sort(arr);
		System.out.print("mypq1 ordered: ");	
		System.out.print("[");
		// Iteration order is not guaranteed
		for (Element el: arr){
			System.out.print(el);
			System.out.print(", ");
		}
		System.out.println("]");	
		
	}
}
