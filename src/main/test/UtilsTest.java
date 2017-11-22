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
	/*
	 * O(n)
	 */
	private static int findLaregstNumber() {
		int[] arr = {0, 9, 10, 3, 4, 5, 6, 7, 8, 9};
		int len = arr.length; //10
		int largeNum = arr[0];
		for (int ctr = 0; ctr < len; ctr++) {
			if (arr[ctr] > largeNum) {
				largeNum = arr[ctr];
			}
		}
		
		return largeNum;
	}
}
