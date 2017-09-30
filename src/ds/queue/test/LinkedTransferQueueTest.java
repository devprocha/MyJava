package ds.queue.test;

import java.util.concurrent.LinkedTransferQueue;

/* 
 * TransferQueue is same as SynchronousQueue but provides additional operation tryTransfer() i.e. 
 * Don't block if there is no waiting consumer(s)
 * 
 * http://stackoverflow.com/questions/27218688/difference-beetwen-synchronousqueue-vs-transferqueue
 * 
 * */
public class LinkedTransferQueueTest {
	final private static LinkedTransferQueue<String> mTransferQueue = new LinkedTransferQueue<String>();
	
	public static void Test() {		
		final ProducerThread producer = new ProducerThread(mTransferQueue);
		producer.start();
		
		final ConsumerThread consumer = new ConsumerThread(mTransferQueue);
		consumer.start();
	}
	
	private static class ProducerThread implements Runnable {
		final LinkedTransferQueue<String> mQueue;
		final Thread mThread = new Thread(this);
		
		public ProducerThread (LinkedTransferQueue<String> queue) {
			mQueue = queue;
		}
		
		public void start () {
			mThread.start();
		}
		
		@Override
		public void run() {
			final String event = "EVENT";
			System.out.println("Producer: transferring");
			try {
				mQueue.transfer(event); // waits until consumer takes it
				System.out.println("Producer: transferred to wating to consumer");
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(2*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Producer: trying to transfer");
			boolean status = mQueue.tryTransfer(event); // doesn't wait, transfers to waiting consumer if any else, just returns it (doesn't add it to queue)
			System.out.println((status == true ? "Producer: transferred to wating to consumer" : "Producer: not transferred (no waiting consumer)"));
		}
	}
	
	private static class ConsumerThread implements Runnable {
		final LinkedTransferQueue<String> mQueue;
		final Thread mThread = new Thread(this);
		
		public ConsumerThread(LinkedTransferQueue<String> queue) {
			mQueue = queue;
		}
		
		public void start() {
			mThread.start();
		}
		
		@Override
		public void run() {			
			System.out.println("Consumer: waiting");
			try {				
				String event =  mQueue.take(); // waits until producer puts it 
				System.out.printf("Consumer: consumed event : %s %n", event);
				
				System.out.println("Consumer: waiting for tryTransfer");
				event =  mQueue.take(); // waits until producer puts it
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}		
	}
}
