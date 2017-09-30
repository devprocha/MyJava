package references.test;

import java.lang.ref.WeakReference;

/*
 * The objects that are referenced only by weak references are garbage collected eagerly; 
 * the GC won’t wait until it needs memory in that case.
 */

public class WeakReferenceTest {
	private static String mStrRef = new String("I'm Alive!");
	private static WeakReferenceRunner mWeakReferenceRunner;
	 
	public static void Test () {
		mWeakReferenceRunner = new WeakReferenceRunner(mStrRef);
		final Thread t = new Thread(mWeakReferenceRunner);
	    t.start();
	    try {
			Thread.sleep(2000);
			// Dereference the strong reference
		    mStrRef = null;
		    System.gc();
			System.out.println("Strong Reference: I'm Dead!");
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	private static class WeakReferenceRunner implements Runnable {
		private static WeakReference<String> mWeakRef;
		
		public WeakReferenceRunner(String str) {
			mWeakRef = new WeakReference<String>(str);
		}
		
		@Override
		public void run() {
			while (mWeakRef.get() != null) {
				 System.out.println("Weak Reference: " + mWeakRef.get());
				 System.gc();
			}
			System.out.println("Weak Reference: " + "I'm Dead!");
		}		
	}
	
	
}