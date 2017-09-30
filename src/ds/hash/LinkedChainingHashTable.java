package ds.hash;

// http://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable

public class LinkedChainingHashTable<K, V> {
	
	private static final int DEFAULT_CAPACITY = 16;	
	private static final float DEFAULT_LOAD_FACTOR = 0.7f; // http://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap
	private int mSize = 0;
	private int mCapacity;	
	private int mThreshold;	
	private Entry<K, V>   mFirstEntry;
	private Entry<K, V>   mLastEntry;
	private Entry<K, V>[] mHashTable; // array of buckets
	
	private static class Entry<K, V> { //special single linked list
		K mKey;
		V mValue;
		Entry<K, V> mNext;
		Entry<K, V> mPrev;
		Entry<K, V> mNext2; // Links insertion order
		Entry<K, V> mPrev2; // Links insertion order
		
		Entry(K key, V value, Entry<K, V> next, Entry<K, V> prev, Entry<K, V> next2, Entry<K, V> prev2) {
			mKey = key;
			mValue = value;
			mNext = next;		
			mPrev = prev;
			mNext2 = next2;		
			mPrev2 = prev2;			
		}
	}		

	public LinkedChainingHashTable() {
		mHashTable = new Entry[DEFAULT_CAPACITY];
		mCapacity = DEFAULT_CAPACITY;		
		mThreshold = (int)(DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
	}	
	
	public V put(K key, V value) {		
		int tableIndex = indexFor(key);
		V oldValue = updateEntry(key, value, tableIndex);
		if (oldValue == null) {
			addEntry(key, value, tableIndex);			
		}
		return oldValue;
	}
	
	public V get(K key) {
		return getEntry(key, indexFor(key));
	}
	
	public V remove(K key) {			
		return removeEntry(key, indexFor(key));
	}
	
	public boolean contains(K key) {
		return (findEntry(key, indexFor(key)) != null);
	}
	
	public int size() {				
		return mSize;
	}
	
	private void addEntry(K key, V value, int tableIndex) {
		// resize hash table if it's full
		ensureCapacity(); 
		
		// Insert new node at the beginning of the bucket
		final Entry<K, V> tmpEntry = mHashTable[tableIndex]; // if non-null means collision occurred
		final Entry<K, V> newEntry = new Entry<K, V>(key, value, tmpEntry, null, null, mLastEntry);	
		mHashTable[tableIndex] = newEntry; // Insert at the head node
		
		if (tmpEntry != null) {
			tmpEntry.mPrev = newEntry;
		}
		if (mLastEntry != null) {
			mLastEntry.mNext2 = newEntry;
		}
		if (mFirstEntry == null) {
			mFirstEntry = newEntry;	
		}
		mLastEntry = newEntry;
	}
	
	private V updateEntry(K key, V value, int tableIndex) {
		for (Entry<K, V> e = mHashTable[tableIndex]; e != null; e = e.mNext) {
			if (key == e.mKey || e.mKey.equals(key)) {
				V oldValue = e.mValue;
				e.mValue = value;
				return oldValue;
			}
		}
		return null;
	}
	
	private V getEntry(K key, int tableIndex) {		
		return findEntry(key, tableIndex);
	}
	
	private V removeEntry(K key, int tableIndex) {
		Entry<K, V> prev = null;
		for (Entry<K, V> e = mHashTable[tableIndex]; e != null; prev = e, e = e.mNext) {			
			if (key == e.mKey || e.mKey.equals(key)) {
				if (prev == null) { // head node in a bucket
					mHashTable[tableIndex] = e.mNext;
					if (e.mNext != null) {
						e.mNext.mPrev = null; 
					}					
				} else {
					prev.mNext = e.mNext;
					e.mNext.mPrev = e.mPrev;
				}				
				if (e.mPrev2 != null) {
					e.mPrev2.mNext2 = e.mNext2;						
				}					
				if (e.mNext2 != null) {
					e.mNext2.mPrev2 = e.mPrev2;						
				}
				return e.mValue;
			}
		}
		return null;
	}
	
	private V findEntry(K key, int tableIndex) {
		for (Entry<K, V> e = mHashTable[tableIndex]; e != null; e = e.mNext) {
			if (key == e.mKey || e.mKey.equals(key)) {				
				return e.mValue;
			}
		}
		return null;
	}
	
	private void ensureCapacity() {
		// TODO - Resize only for increase and not for decrease?		
		if (mSize >= mThreshold) {
			resize(); //rehash
		}
	}
	
	private void resize() { 
		final int newCapacity = (mCapacity * 2);
		Entry[] newTable = new Entry[newCapacity];
		transfer(mHashTable, newTable);
		mHashTable = newTable;
		mCapacity = newCapacity;
		mThreshold = (int) (mCapacity * DEFAULT_LOAD_FACTOR);		
	}
	
	private void transfer(Entry[] srcTable, Entry[] destTable) {
		Entry<K, V> eSrc;
		Entry<K, V> eSrcNext;
		Entry<K, V> eDest;
		int tableIndex;
		for (int i = 0; i < srcTable.length; i++) {
			eSrc = srcTable[i];
			while (eSrc != null) {
				eSrcNext = eSrc.mNext;
				tableIndex = indexFor(eSrc.mKey);				
				eDest = destTable[tableIndex]; 
				destTable[tableIndex] = eSrc; // this reverses the linked list
				destTable[tableIndex].mNext = eDest;
				eDest.mPrev = eSrc;
				destTable[tableIndex].mPrev = null;
				eSrc = eSrcNext;
			}
		}	
	}
	
	private int indexFor(K key) {
		if (key == null)
			return 0;		
		int hashCode = key.hashCode();
		return (Math.abs(hashCode) % mCapacity);
	}
}
