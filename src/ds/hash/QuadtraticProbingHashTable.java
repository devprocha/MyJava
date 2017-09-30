package ds.hash;

import algorithms.Utils;

public class QuadtraticProbingHashTable<K, V> {
	
	private static final int DEFAULT_CAPACITY = 17; // Table size should be always prime number	
	private static final float DEFAULT_LOAD_FACTOR = 0.5f; // http://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap
	private int mSize = 0;
	private int mCapacity;	
	private int mThreshold;
	
	private static class Entry<K, V> { 
		K mKey;
		V mValue;
		
		Entry(K key, V value) {
			mKey = key;
			mValue = value;
		}
	}
		
	private Entry<K, V>[] mHashTable; // array of buckets 

	public QuadtraticProbingHashTable() {
		mHashTable = new Entry[DEFAULT_CAPACITY];
		mCapacity = DEFAULT_CAPACITY;		
		mThreshold = (int)(DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
	}	
	
	public V put(K key, V value) {		
		int tableIndex = indexFor(key);
		V oldValue = updateEntry(key, value, tableIndex);
		if (oldValue == null) {
			addEntry(key, value, tableIndex);
			mSize++;
		}
		return oldValue;
	}
	
	public V get(K key) {		
		final int tableIndex = indexFor(key);		
		return getEntry(key, tableIndex);
	}
	
	public V remove(K key) {
		final int tableIndex = indexFor(key);	
		V retVal = removeEntry(key, tableIndex);
		if (retVal != null) {
			mSize --;
		}		
		return retVal;
	}
	
	public boolean contains(K key) {				
		final int tableIndex = indexFor(key);		
		return (findEntry(key, tableIndex) != null);
	}
	
	public int size() {				
		return mSize;
	}
	
	private void addEntry(K key, V value, int tableIndex) {
		ensureCapacity(); // resizes mHashTable if it's full
		
		final Entry<K, V> newEntry = new Entry<K, V>(key, value);
		if (mHashTable[tableIndex] == null) {
			mHashTable[tableIndex] = newEntry; // Insert at the specified position					
		} else {
			// Collision - start quadratic probing
			tableIndex = probeEntry(tableIndex);
			mHashTable[tableIndex] = newEntry;
		}		
	}
	
	private V updateEntry(K key, V value, int tableIndex) {
		V retVal = null;
		Entry<K, V> e = mHashTable[tableIndex];	
		if (e != null) {
			if (key == e.mKey || e.mKey.equals(key)) {			
				retVal = e.mValue;
				e.mValue = value;
				return retVal;
			}
		}
			
		// Collision - start linear probing		
		tableIndex = probeEntry(tableIndex, key);
		if (tableIndex != -1) {
			e = mHashTable[tableIndex];
			if (e != null) {
				retVal = e.mValue;
				e.mValue = value;
			}	
		}
		return retVal;
	}
	
	private V getEntry(K key, int tableIndex) {		
		return findEntry(key, tableIndex);
	}
	
	private V removeEntry(K key, int tableIndex) {
		V retVal = null;
		Entry<K, V> e = mHashTable[tableIndex];	
		if (e != null) {
			if (key == e.mKey || e.mKey.equals(key)) {			
				retVal = e.mValue;
				mHashTable[tableIndex] = null;
				return retVal;
			}
		}
			
		// Collision - start linear probing		
		tableIndex = probeEntry(tableIndex, key);
		if (tableIndex != 1) {
			e = mHashTable[tableIndex];
			if (e != null) {
				retVal = e.mValue;
				mHashTable[tableIndex] = null;
			}	
		}		
		return retVal;
	}
	
	private V findEntry(K key, int tableIndex) {
		Entry<K, V> e = mHashTable[tableIndex];	
		if (e != null) {
			if (key == e.mKey || e.mKey.equals(key)) {
				return e.mValue;
			}
		}	
			
		// Collision - start linear probing	
		V retVal = null;
		tableIndex = probeEntry(tableIndex, key);
		if (tableIndex != 1) {
			e = mHashTable[tableIndex];
			if (e != null) {
				retVal = e.mValue;				
			}
		}	
		return retVal;
	}
	
	private int probeEntry(int probeIndex) {
		int startIndex;		
		int i = 1;
		do {
			startIndex = (probeIndex + i*i) % mCapacity;
			if (mHashTable[startIndex] == null) {
				return startIndex;
			}
			i++;
		} while (startIndex != probeIndex);
		return -1;
	}
	
	private int probeEntry(int probeIndex, K key) {
		int retVal = -1;
		Entry<K, V> e = null;
		int startIndex = probeIndex;
		int i = 1;
		while (retVal == -1) {
			startIndex = (startIndex + i*i) % mCapacity;
			e = mHashTable[startIndex];
			if (e != null && (key == e.mKey || e.mKey.equals(key))) {				
				retVal = startIndex;
			}
		}	
		return retVal;
	}
	
	private void ensureCapacity() {
		// TODO - Resize only for increase and not for decrease?		
		if (mSize >= mThreshold) {
			resize(); //rehash
		}
	}
	
	private void resize() {
		final int newCapacity = getNextPrimeNumber(mCapacity * 2);
		Entry[] newTable = new Entry[newCapacity];
		transfer(mHashTable, newTable);
		mHashTable = newTable;
		mCapacity = newCapacity;
		mThreshold = (int) (mCapacity * DEFAULT_LOAD_FACTOR);		
	}
	
	private void transfer(Entry[] srcTable, Entry[] destTable) {
		for (int i = 0; i < srcTable.length; i++) {
			destTable[i] = srcTable[i];
		}
	}
	
	private int indexFor(K key) {
		if (key == null)
			return 0;		
		int hashCode = key.hashCode();
		return (Math.abs(hashCode) % mCapacity);
	}
	
	private int getNextPrimeNumber(int num) {
		int primeNum = num;
		while (!Utils.isPrime(primeNum)) {
			primeNum++;
		}
		return primeNum;
	}
}
