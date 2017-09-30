package ds.map.test;

import java.util.WeakHashMap;


public class WeakHashMapTest {
	
	/* Hash table based implementation of the Map interface, with "weak" keys
	 * 1) Algorithm : Hash Table
	 * 2) Access: Random Access (by key)
	 * 3) Iteration Order - No 
	 * 4) Allows Duplicate Keys - No (Replaces old value with the new value)
	 * 5) Allows "Null" -  Yes (Key - Only one(consecutive NULL key replaces old value with new value), Value - any)
	 * 6) Thread Safe - No (Throws ConcurrentModificationException)
	 * 7) Fail-Fast or Fail-Safe - Fail-Fast
	 */
	public static void Test() {
		final WeakHashMap<String, String> weakHashMap = new WeakHashMap<String, String>();		
		//final String strKey = new String("Maine");
		//weakHashMap.put(strKey, "Augusta"); // strong reference to key "strKey"
		
		weakHashMap.put(new String("Maine"), "Augusta"); // No reference to key i.e. put and forget. GC will reclaim the memory in next GC cycle.
	    Runnable runner = new Runnable() {
	        public void run() {
	        	while (weakHashMap.containsKey("Maine")) {
	        		try {
		                Thread.sleep(500);
		              } catch (InterruptedException ignored) {		             	 
		              }
	        		System.out.println("Thread waiting");
		            System.gc();
		        }
	         }
	      };
	     Thread t = new Thread(runner);
	     t.start();
	     System.out.println("Main waiting");
	     try {
	        t.join();
	     } catch (InterruptedException ignored) {
	    	 
	     }
	}
}
