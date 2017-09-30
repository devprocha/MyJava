package ds.hash;

import algorithms.Utils;

// http://www.cs.rmit.edu.au/online/blackboard/chapter/05/documents/contribute/chapter/05/linear-probing.html
// http://faculty.cs.niu.edu/~freedman/340/340notes/340hash.htm
// http://stackoverflow.com/questions/27742285/what-is-primary-and-secondary-clustering-in-hash
// https://www.youtube.com/watch?v=CwM-Cxilk4g

// Clustering Problem - Once large cluster is formed 
// (in a table/array elements are populated in a single place - no free space in between the elements) then probing time takes longer
// time to find free element. This effect/problem is known as Primary Clustering.
// E.g- If the primary hash index is x, subsequent probes go to x+1, x+2, x+3 and so on until it finds empty slot, 
// 
//Closed Hashing/Open Addressing - All elements are stored inside the hash table/array (no chaining is formed)

public class DoubleHashingHashTable<K, V> {
	
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

	public DoubleHashingHashTable() {
		mHashTable = new Entry[DEFAULT_CAPACITY];
		mCapacity = DEFAULT_CAPACITY;		
		mThreshold = (int)(DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
	}	
	
	public V put(K key, V value) {		
		int tableIndex = hash(key);
		V oldValue = updateEntry(key, value, tableIndex);
		if (oldValue == null) {
			addEntry(key, value, tableIndex);
			mSize++;
		}
		return oldValue;
	}
	
	public V get(K key) {		
		final int tableIndex = hash(key);		
		return getEntry(key, tableIndex);
	}
	
	public V remove(K key) {
		final int tableIndex = hash(key);	
		V retVal = removeEntry(key, tableIndex);
		if (retVal != null) {
			mSize --;
		}		
		return retVal;
	}
	
	public boolean contains(K key) {				
		final int tableIndex = hash(key);		
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
			// Collision
			tableIndex = resolveCollision(key, tableIndex);
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
			
		// Collision	
		tableIndex = resolveCollisionForKey(key, tableIndex);
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
		tableIndex = resolveCollisionForKey(key, tableIndex);
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
		tableIndex = resolveCollisionForKey(key, tableIndex);
		if (tableIndex != 1) {
			e = mHashTable[tableIndex];
			if (e != null) {
				retVal = e.mValue;				
			}
		}	
		return retVal;
	}
	
	private int resolveCollision(K key, int hash1) {
		int i = 1;
		int retVal = -1;
		int newHash;
		int hash2 = hash2(key);		
		do {
			newHash = (hash1 + hash2 * i) % mCapacity;
			if (mHashTable[newHash] == null) {
				retVal = newHash; // Found free spot
			}
			i++;
		} while (retVal == -1);
		return retVal;
	}
	
	private int resolveCollisionForKey(K key, int hash1) {
		int i = 1;
		int retVal = -1;
		int newHash;
		Entry<K, V> e = null;
		int hash2 = hash2(key);
		
		do {
			newHash = (hash1 + (i * hash2)) % mCapacity;
			e = mHashTable[newHash];
			if (e != null && (key == e.mKey || e.mKey.equals(key))) {
				return newHash;
			}
			i++;
		} while (retVal == -1);
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
	
	private int hash(K key) {
		if (key == null)
			return 0;
		
		int hashCode = key.hashCode();
		return (Math.abs(hashCode) % mCapacity);
	}
	
	private int hash2(K key) {
		int hashCode = key.hashCode();
		int R = getPrevPrimeNumber(mCapacity); // Prime number must be lesser than mCapacity
		
		 // (R - (x % R)) where R is a prime number less than table size
		return (R - (Math.abs(hashCode) % R)); // never returns zero
	}
	
	private int getNextPrimeNumber(int num) {
		int primeNum = num;
		while (!Utils.isPrime(primeNum)) {
			primeNum++;
		}
		return primeNum;
	}
	
	private int getPrevPrimeNumber(int num) {
		int primeNum = num - 1;
		while (!Utils.isPrime(primeNum)) {
			primeNum--;
		}
		return primeNum;
	}
}
