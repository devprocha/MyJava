package main.test;

public class StringTest {

	public static void Test() {
		
		//http://www.journaldev.com/797/what-is-java-string-pool
		String strLit1 = "Hello"; // Creates instance of String in String pool if doesn't exist else returns the existing reference
		String strLit2 = "Hello";
		String strObj = new String ("Hello");		
		System.out.println("strLit1 == strLit2-->" + (strLit1 == strLit2));
		System.out.println("strLit1 == strObj-->" + (strLit1 == strObj));
		System.out.println("strLit2 == strObj-->" + (strLit2 == strObj));
		
	}
}