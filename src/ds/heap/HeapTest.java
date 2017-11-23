package ds.heap;

public class HeapTest {
	
	public static void run(){
		testMinBinaryHeap2();
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
	
	
	private static void testMinBinaryHeap2() {
		MinBinaryHeap2<Element> heap2 = new MinBinaryHeap2<Element>(); 
	}

}
