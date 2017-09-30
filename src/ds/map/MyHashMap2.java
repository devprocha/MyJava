package ds.map;

import ds.hash.ChainingHashTable;

// http://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable
// http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/HashMap.java

public class MyHashMap2<K, V> {
	
	private ChainingHashTable<K, V> mHashTable;
	
	public MyHashMap2() {
		mHashTable = new ChainingHashTable();
	}
	public V put(K key, V value) {		
		return mHashTable.put(key, value);
	}
	
	public V get(K key) {		
		return mHashTable.get(key);
	}
	
	public V remove(K key) {
		return mHashTable.remove(key);
	}
	
	public boolean containsKey(K key) {
		return mHashTable.containsKey(key);
	}
	
	public boolean containsValue(V value) {
		return mHashTable.containsValue(value);
	}
}
