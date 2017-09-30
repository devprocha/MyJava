package ds;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class MyPhantomReference<T> {	
	
	PhantomReference <T> mPhantomRef;
	
	public MyPhantomReference(T trackMe, MyPhantomReferenceCallback cb) {
		ReferenceQueue <T> refQueue = new ReferenceQueue <T>();
		mPhantomRef = new PhantomReference <T>(trackMe, refQueue);
		final Thread t = new Thread (new PhantomReferenceThreadRunner<T>(refQueue, cb));
		t.start();
	}
	
	private static class PhantomReferenceThreadRunner<T> implements Runnable {
		private MyPhantomReferenceCallback mCallback;
		private ReferenceQueue <T> mRefQueue;
		
		public PhantomReferenceThreadRunner(ReferenceQueue <T> refQueue, MyPhantomReferenceCallback cb) {
			mRefQueue = refQueue;
			mCallback = cb;
		}
		
		@Override
		public void run() {
			try {
				//mRefQueue.poll(); // always returns NULL
				mRefQueue.remove(); // Blocks until "T trackMe" object is cleaned/freed by the GC				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				mCallback.onRelease();
			}
		}	
	}
}
