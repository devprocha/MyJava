package ds.queue.test;

import java.util.concurrent.SynchronousQueue;

/*
 * A blocking queue in which each (only one) insert operation must wait for a corresponding remove operation by another thread, and vice versa.
 * A synchronous queue does not have any internal capacity, not even a capacity of one.
 * http://javarevisited.blogspot.com/2014/06/synchronousqueue-example-in-java.html 
 */
public class SynchronousQueueTest {
	
	public static void Test() {		
		final SynchronousQueue<String> queue = new SynchronousQueue<String>(true);
		
		final ProducerThread producer = new ProducerThread(queue);
		producer.start();
		
		final ConsumerThread consumer = new ConsumerThread(queue);
		consumer.start();
	}
	
	private static class ProducerThread implements Runnable {
		final SynchronousQueue<String> mQueue;
		final Thread mThread = new Thread(this);
		
		public ProducerThread (SynchronousQueue<String> queue) {
			mQueue = queue;
		}
		
		public void start () {
			mThread.start();
		}
		
		@Override
		public void run() {
			final String event = "FOUR";
			try {
				mQueue.put(event); // thread will block here until consumer takes it 
				System.out.printf("[%s] published event : %s %n", Thread.currentThread().getName(), event);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
	
	private static class ConsumerThread implements Runnable {
		final SynchronousQueue<String> mQueue;
		final Thread mThread = new Thread(this);
		
		public ConsumerThread(SynchronousQueue<String> queue) {
			mQueue = queue;
		}
		
		public void start() {
			mThread.start();
		}
		
		@Override
		public void run() {			
			try {
				final String event =  mQueue.take(); // thread will block here if Queue is empty else takes of(Hand off)
				System.out.printf("[%s] consumed event : %s %n", Thread.currentThread().getName(), event);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}		
	}
}
