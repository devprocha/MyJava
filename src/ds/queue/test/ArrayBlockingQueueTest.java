package ds.queue.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/*
 * A Thread safe "Bounded-Queue" based on a "Resizable-Array" implementation that "additionally" supports 
 * operations("blocking") that wait for the queue to become non-empty 
 * when retrieving an element, and wait for space to become available in the queue when storing an element.
    * 1) Algorithm : Array
 	* 2) Insertion Order - "Tail/Last"
 	* 3) Deletion/Examine - "Head/First" 
 	* 4) Ordered - Yes (Insertion order) 
 	* 5) Bounded/Unbounded - Bounded
 	* 6) Allows Duplicates - Yes 
 	* 7) Allows "Null" - No (Throws NPE)
 	* 8) Thread Safe - Yes 
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
public class ArrayBlockingQueueTest {
	 
	public static void Test() {	 
		ArrayBlockingQueue<String> bq = new ArrayBlockingQueue<String>(10); // Bounded queue with the size of 10
		
		ProducerThread pt = new ProducerThread(bq);
		pt.start();
		
		ConsumerThread ct = new ConsumerThread(bq);
		ct.start();
	}
	
	private static class ProducerThread implements Runnable {
		final ArrayBlockingQueue<String> mSharedQueue;
		final Thread mThread = new Thread(this);
		
		public ProducerThread (ArrayBlockingQueue<String> sharedQueue) {
			mSharedQueue = sharedQueue;
		}
		
		public void start () {
			mThread.start();
		}
		
		@Override
		public void run() {
			final String event = "EVENT";
			System.out.println("Producer: waiting");
			try {
				//mSharedQueue.add(event); // If queue is full - Throws exception
				//mSharedQueue.offer(event); // If queue is full - Does nothing and returns special value 
				mSharedQueue.put(event); //If queue is full - Blocks until queue is non-full
				System.out.println("Producer produced: " + event);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
	
	private static class ConsumerThread implements Runnable {
		final ArrayBlockingQueue<String> mSharedQueue;
		final Thread mThread = new Thread(this);
		
		public ConsumerThread (ArrayBlockingQueue<String> sharedQueue) {
			mSharedQueue = sharedQueue;
		}
		
		public void start () {
			mThread.start();
		}
		
		@Override
		public void run() {			
			System.out.println("Consumer: waiting");
			try {
				//final String event = mSharedQueue.remove();//If queue is empty - Throws exception
				//final String event = mSharedQueue.poll();//If queue is empty - Does nothing and returns special value
				final String event = mSharedQueue.take();//If queue is empty - Blocks until queue is non-empty
				System.out.println("Consumer consumed: " + event);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
