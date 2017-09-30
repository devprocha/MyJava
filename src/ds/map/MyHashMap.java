package ds.map;

import ds.hash.ChainingHashTable;

// http://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable

public class MyHashMap<K, V> {
		
	private ChainingHashTable<K, V> mHashTable; // Hash table which uses chaining technique 

	public MyHashMap() {
		mHashTable = new ChainingHashTable<K, V>();
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
	
	public boolean contains(K key) {
		return mHashTable.containsKey(key);
	}
	
	public int size() {
		return mHashTable.size();
	}
}
