package main.test;

public class BigOTest {

	public static void Test() {
		int[] array = {1, 2, 3, 4, 5, 100, 50, 10, 30};
		//findProductSum(array);
		//findProductSum2(array);
		int max = findMax(array);
		System.out.println("Max. number is: " + max);
	}
	
	private static int add(int n) {
		if (n <= 0) {
			return 0;
		}
		return n + add(n-1);
	}
	
	private static int add2(int n) {
		if (n <= 0) {
			return 1;
		}
		return add2(n-1) + add2(n-1);
	}
	
	private static void findProductSum(int[] array){
		
		int sum = 0;
		int product = 0;
		long start = System.currentTimeMillis( );
		System.out.println("START: " + start);
		for (int i =0; i < array.length; i++) {
			sum  += array[i];
			product  *= array[i];
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis( );
		System.out.println("END: " + end);
		System.out.println("Time to execute findProductSum: " + (end - start));
	}
	
	private static void findProductSum2(int[] array){
		
		int sum = 0;
		int product = 0;
		long start = System.currentTimeMillis( );
		for (int i =0; i < array.length; i++) {
			sum  += array[i];
		}
		for (int i =0; i < array.length; i++) {
			product  *= array[i];
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis( );
		System.out.println("Time to execute findProductSum2: " + (end - start));
	}
	
	private static int findMax(int[] array) {
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;		
	}
}