package ds.map.test;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapTest {
	
	public static void Test() {
		HashMapTest.Test();
		//LinkedHashMapTest.Test();
		TreeMapTest.Test();
		//IdentityHashMapTest.Test();
		//WeakHashMapTest.Test();
		//EnumMapTest.Test();
	}
}
