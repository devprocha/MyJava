package main.test;

import algorithms.Utils;

public class UtilsTest {

	
	public static void Test() {
		System.out.println("[UtilsTest]Test Started..");
		UtilsTest.TestIsPrime();
		System.out.println("[UtilsTest]Test Exited");
	}
	
	public static void TestIsPrime() {
		int start = 1;
		int end = 100;
		System.out.println("Prime numbers between " + start + " and " + end + " are as follows:");
		for (int i = start; i <= end; i++) {			
			if(Utils.isPrime(i)) {
				System.out.print(i + ",");				
			}
		}
	}	
}
