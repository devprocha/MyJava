package references.test;

import ds.MyPhantomReference;
import ds.MyPhantomReferenceCallback;
/*
 * Phantom reference is used to keep track the object instance.
 * Phantom references are most often used for scheduling pre-mortem cleanup actions 
 * in a more flexible way than is possible with the Java finalization mechanism.
 */

public class PhantomReferenceTest {
	private static String mStrRef = new String("I'm Alive!");	//keep track of this object instance
	public static void Test () {
		MyPhantomReference<String> phantomRef = new MyPhantomReference<String>(mStrRef, new PhantomReferenceTracker());		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mStrRef = null;
		System.gc();
	}

	private static class PhantomReferenceTracker implements MyPhantomReferenceCallback {
		@Override
		public void onRelease() {
			System.out.print("onRelease() : mStrRef is Dead!");
		}
	}	
}