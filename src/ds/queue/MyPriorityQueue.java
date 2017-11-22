package ds.queue;

import java.util.Iterator;

import ds.heap.MinBinaryHeap2;

public class MyPriorityQueue <T extends Comparable<T>> {
	private MinBinaryHeap2 <T> mHeap;
	
	public MyPriorityQueue() {
		mHeap = new  MinBinaryHeap2<T>();
	}
	
	public boolean add(T element) {
		return (mHeap.insert(element) != -1);
	}
	
	public T get() {
		return mHeap.getMin();
	}
	
	public T poll() {
		return mHeap.extractMin();
	}
	
	public boolean remove(T element) {
		return mHeap.delete(element);
	}
	
	public boolean contains(T element) {
		return mHeap.contains(element);
	}
	
	/*
	 * element - Element whose priority need to be updated with newElement priority
	 * element and newElement are both same however newElement has new priority 
	 * 
	 * Time Complexity: O(V) --> (O(V) + O(logV) = O(V))
	 */	
	public boolean decreaseOrUpdate(T element, T newElement) {
		if (remove(element)) { // O(V)
			return add(newElement); // O(logV)
		}
		return false;
	}
	
	public int size() {
		return mHeap.size();
	}
	
	public T[] toArray() {
		return mHeap.toArray();
	}
	
	public Iterator<T> iterator() {
		return mHeap.iterator();
	}
}
