package main.test;

import references.test.*;

public class ReferenceTest {	
	/*
	 * http://javapapers.com/core-java/java-weak-reference/
	 */
	public static void Test() {
		SoftReferenceTest.Test();
		WeakReferenceTest.Test();
		PhantomReferenceTest.Test();		
	}
}
