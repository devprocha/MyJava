package ds.map.test;

import java.util.EnumMap;


public class EnumMapTest {
	
	static enum STATE {
		IDLE, RUNNING, STOP;
	}
	
	/* A specialized Map implementation for use with enum type keys
	 	* 1) Algorithm : Resizable Array
	 	* 2) Access: Random Access (by Enum key)
	 	* 3) Iteration Order - Yes (enum ordinal order) 
	 	* 4) Allows Duplicate Keys - No (Throws NPE)
	 	* 5) Allows "Null" -  Yes (Key - Only one(consecutive NULL key replaces old value with new value), Value - any)
	 	* 6) Thread Safe - No (Doesn't throw ConcurrentModificationException)
	 	* 7) Fail-Fast or Fail-Safe - Fail-Fast
	 	* http://javarevisited.blogspot.com/2012/09/what-is-enummap-in-java-example-tutorial.html
	 */
	public static void Test() {
		final EnumMap<STATE, String> enumMap = new EnumMap<STATE, String>(STATE.class);		
		enumMap.put(STATE.RUNNING, "Running"); //ordered based on the enum constant declaration
		enumMap.put(STATE.STOP, "Stop");
		enumMap.put(STATE.IDLE, "Idle");
		System.out.println("Enum Map: " + enumMap.toString());	//Printing order is based on enum ordinal 	
	}
}
