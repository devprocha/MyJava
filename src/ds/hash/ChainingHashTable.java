package ds.hash;

import java.lang.ref.WeakReference;
import java.util.Map;

import algorithms.Utils;

public class ChainingHashTable<K, V> {
	
	private static final float LOAD_FACTOR = 0.75f; // http://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap
	private static final int DEFAULT_TABLESIZE = 17; // No.of buckets in the table, preferably prime number to avoid clustering and even distribution 
	private int mSize;
	
	private static class Entry<K, V> implements Map.Entry<K, V> {

		private K mKey;
		private V mValue;
		private Entry<K, V> mNext;
		
		public Entry(K key, V value, Entry<K, V> next) {
			mKey = key;
			mValue = value;
			mNext = next;
		}
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return mKey;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return mValue;
		}

		@Override
		public V setValue(V value) {
		    final V oldValue = mValue;
		    mValue = value;
			return oldValue;
		}
	}
	
	private int mTableSize;
	private Entry<K, V>[] mTable; // Array of buckets - Why can't be ArrayList<> instead of static array []?
	
	private WeakReference<Entry<K, V>>[] mWeakTable; //For Weak Hash Table
	
	public ChainingHashTable() {
		mTable = new Entry[DEFAULT_TABLESIZE];
		mTableSize = DEFAULT_TABLESIZE;
		mSize = 0;
	}
	
	public V put(K key, V value) {
		// Check if already entry exist for key
		final Entry<K, V> entry = getEntryByKey(key);
		if (entry != null) {
			final V oldValue = entry.mValue;
			entry.mValue = value; // replace with new value
			return oldValue;		
		}
		
		// Create new entry		
		addEntry(key, value);
		return null;
	}
	
	public V get(K key) {
		return getValue(key);
	}
	
	public boolean containsKey(K key) {
		 Entry<K, V> entry = getEntryByKey(key);
		 return (entry != null ? true: false);
	}
	
	public boolean containsValue(V value) {
		 Entry<K, V> entry = getEntryByValue(value);
		 return (entry != null ? true: false);
	}
	
	public V remove(K key) {
		return removeEntry(key);
	}
	
	public int size() {
		return mSize;
	}
	
	private void ensureLoadFactor(){
		final float currentLF = mSize / mTableSize;
		if (currentLF >= LOAD_FACTOR) {
			rehash();
		}
	}
	
	private void addEntry(K key, V value) {
		ensureLoadFactor(); // Make sure we have the right table size		
		final int index = hash(key);
		final Entry<K, V> next = mTable[index]; // if bucket is non-empty then collision is occurred
		final Entry<K, V> newEntry = new Entry(key, value, next);  
		mTable[index] = newEntry; //insert at the front of list (bucket)
		mSize++;
	}
	
	private V removeEntry(K key) {
		if (key == null) {
			return null;
		}
		
		int index = hash(key);
		Entry<K, V> currEntry = mTable[index];
		Entry<K, V> prevEntry = null; 
		while(currEntry != null) {
			if (currEntry.mKey.equals(key)) {
				if (prevEntry != null) {
					prevEntry.mNext = currEntry.mNext;
				}
				mSize --;
				return currEntry.mValue;
			}
			prevEntry = currEntry;
			currEntry = currEntry.mNext;
		}
		return null;
	}
	
	private V getValue(K key) {
		if (key == null) {
			return null;
		}
		
		int index = hash(key);
		Entry<K, V> entry = mTable[index];
		while(entry != null) {
			if (entry.mKey.equals(key)) {
				return entry.mValue;
			}
			entry = entry.mNext;
		}
		return null;
	}
	
	private Entry<K, V> getEntryByKey(K key) {
		if (key == null) {
			return null;
		}
		
		int index = hash(key);
		Entry<K, V> entry = mTable[index];
		while(entry != null) {
			if (entry.mKey.equals(key)) {
				return entry;
			}
			entry = entry.mNext;
		}
		return null;
	}
	
	private Entry<K, V> getEntryByValue(V value) {
		if (value == null) {
			return null;
		}
		
		for (int i = 0; i < mTable.length; i++) {
			Entry<K, V> entry = mTable[i];
			while(entry != null) {
				if (entry.mValue.equals(value)) {
					return entry;
				}
				entry = entry.mNext;
			}
		}	
		return null;
	}
	
	// Generates the bucket index to store element in the table 
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
		return Math.abs(key.hashCode()) % mTableSize;
	}
	
	private void rehash() {
		mTable = doRehash();
		mTableSize = mTable.length;
	}
	
	private Entry<K, V>[] doRehash() {
		int newTableSize = getNextPrimeNumber(mTableSize * 2);
		final Entry<K, V>[] newTable = new Entry[newTableSize];
		transfer(mTable, newTable);
		return newTable;
	}
	
	private void transfer(Entry<K, V>[] src, Entry<K, V>[] dest) {
		for(int i = 0; i < src.length; i++) {
			Entry<K, V> srcEntry = src[i];
			while(srcEntry != null) {
				int index = hash(srcEntry.mKey);
				Entry<K, V> destNext = dest[index];
				Entry<K, V> srcNext = srcEntry.mNext;
				dest[index] = srcEntry;
				dest[index].mNext = destNext;
				srcEntry = srcNext;
			}
		}
	}
	
	private int getNextPrimeNumber(int num) {
		int primeNum = num;
		while (!Utils.isPrime(primeNum)) {
			primeNum++;
		}
		return primeNum;
	}
}
