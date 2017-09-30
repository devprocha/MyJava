package references.test;

import java.lang.ref.SoftReference;

/*
 * Simply put, an object that has a SoftReference pointing to it will not be
 * garbage collected until the JVM absolutely needs memory
 */
public class SoftReferenceTest {
	private static String mStrRef = new String("I'm Alive!");
	private static SoftReferenceRunner mSoftReferenceRunner;
	 
	public static void Test () {
		mSoftReferenceRunner = new SoftReferenceRunner(mStrRef);
		final Thread t = new Thread(mSoftReferenceRunner);
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
	
	private static class SoftReferenceRunner implements Runnable {
		private static SoftReference<String> mSoftRef;
		
		public SoftReferenceRunner(String str) {
			mSoftRef = new SoftReference<String>(str);
		}
		
		@Override
		public void run() {
			while (mSoftRef.get() != null) {
				 System.out.println("Soft Reference: " + mSoftRef.get());
				 System.gc();
			}
			System.out.println("Soft Reference: " + "I'm Dead!");
		}		
	}
	
	
}