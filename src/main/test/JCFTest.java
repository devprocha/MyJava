package main.test;

import ds.list.test.ListTest;
import ds.set.test.SetTest;
import ds.map.test.MapTest;
import ds.queue.test.QueueTest;

public class JCFTest {

	/*
	 * http://www.javaworld.com/article/2073390/core-java/datastructures-and-algorithms-part-1.html
	 */
	public static void Test() {
		System.out.println("[Main]Test Started..");
		//ListTest.Test();
		//SetTest.Test();
		//MapTest.Test();
		QueueTest.Test();
		System.out.println("[Main]Test Exited");
	}	
}
