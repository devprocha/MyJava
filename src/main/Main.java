package main;

import main.test.BigOTest;
import main.test.DataStructuresTest;
import main.test.JCFTest;
import main.test.UtilsTest;

public class Main {

	public static void main(String [ ] args) {
		System.out.println("[Main]Test Started..");
		//JCFTest.Test();
		//StringTest.Test();
		DataStructuresTest.Test();
		//BigOTest.Test();
		//UtilsTest.Test();
		System.out.println("[Main]Test Exited");
	}
}