package ds;

public class MyLinkedHashMap<K, V> {
	
	private static final int CAPACITY = 100;
	
	static class Entry<K, V> { //special doubly linked list
		K mKey;
		V mValue;
		Entry<K, V> mNext; // next entry in the same row
		Entry<K, V> mPrev; // as per insertion order
		Entry<K, V> mAfter; // as per insertion order
		
		public Entry(K key, V value, Entry<K, V> next, Entry<K, V> prev, Entry<K, V> after) {
			mKey = key;
			mValue = value;
			mNext = next;
			mAfter = after;
			mPrev = prev;
		}
	}
	
	private Entry<K, V> [] mTable; //bucket
	private Entry<K, V> mHead;
	private Entry<K, V> mTail;
		
	public MyLinkedHashMap() {
		mTable = new Entry[CAPACITY];
	}
	
	public V put(K key, V value) {
		if (key == null) { 
			throw new IllegalArgumentException();
		}
		
		V retValue = value;
		
		final Entry<K, V> entry = new Entry(key, value, null, null, null);		
		int hash = hash(key);
		if (mTable[hash] == null) {
			mTable[hash] = entry;
			if (mHead == null) {
				mHead = entry;
			} else {
				entry.mPrev = mTail;
				mTail = entry;
			}
		} else {
			boolean done = false;
			Entry<K, V> curr = mTable[hash];
			Entry<K, V> prev;
			while (curr != null && !done) {
				if (curr.mKey.equals(key)) { //update existing key with new value
					retValue = curr.mValue; 
					curr.mValue = value;
					done = true;
				} else if (curr.mNext == null) {
					curr.mNext = entry;
					curr.mAfter = entry;
					curr.mPrev = entry;
					done = true;
				} else {
					prev = curr;
					curr = curr.mNext; // iterate till end
				}
			}
		}
		return retValue;
	}
	
	public V get(K key) {
		if (key == null) { 
			throw new IllegalArgumentException();
		}
		
		final int hash = hash(key);		
		Entry<K, V> curr = mTable[hash];
		V retValue = null; 
		while (curr != null && retValue == null) {
			if (curr.mKey.equals(key)) {
				retValue = curr.mValue;
			} else {
				curr = curr.mNext;
			}
		}
		return retValue;
	}
	
	public boolean remove(K key) {
		if (key == null) { 
			throw new IllegalArgumentException();
		}
		
		final int hash = hash(key);		
		Entry<K, V> curr = mTable[hash];
		Entry<K, V> prev = null;
		boolean done = false;
		while (curr != null && !done) {
			if (curr.mKey.equals(key)) {
				if (prev != null) {
					prev.mNext = curr.mNext;
					done = true;		
				} else {
					mTable[hash] = curr.mNext;
				}
			} else {
				prev = curr;
				curr = curr.mNext;	
			}
		}	
		return done;
	}
	
	private int hash(K key) {
		int hashCode = key.hashCode();
		return Math.abs(hashCode) % CAPACITY;
	}
}
