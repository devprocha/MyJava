package ds.queue.test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/*
 * An unbounded blocking queue of Delayed elements, in which an element can only be "taken" when its "delay has expired"
    * 1) Algorithm : Array
 	* 2) Insertion Order - Always "Tail/Last"
 	* 3) Deletion/Examine - Always "Head/First" element 
 	* 4) Ordered - Yes 
 	* 5) Bounded/Unbounded - Unbounded
 	* 6) Allows Duplicates - Yes 
 	* 7) Allows "Null" - No (Throws NPE)
 	* 8) Thread Safe - Yes 
 	* 9) Fail-Fast or Fail-Safe - Fail-Safe
 */
public class DelayQueueTest {
	
	private static final long DELAY_TIME_SECS = 10*1000; //10 seconds
	static class MyDelayedElement implements Delayed {
		long mDelayedTime = 0;
		
		public MyDelayedElement(long delaySecs) {
			mDelayedTime = delaySecs;
		}
		
		@Override
		public int compareTo(Delayed obj) {
			return 0;
		}

		@Override
		public long getDelay(TimeUnit arg0) {
			final long delayedTime = mDelayedTime - System.currentTimeMillis();
			return delayedTime; // delayedTime <= 0 - Time out 
		}
	}
	
	public static void Test() {
		final DelayQueue<Delayed> delayQueue = new DelayQueue<Delayed>();
		final Delayed element = new MyDelayedElement(System.currentTimeMillis() + DELAY_TIME_SECS);		
		delayQueue.put(element);		
		try {
			System.out.println("DelayQueue: before take()");
			delayQueue.take(); // blocks until Delayed element is expired (DELAY_TIME_SECS)
			System.out.println("DelayQueue: after take()");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		System.out.print("DelayQueue: Exiting");
	}
}
