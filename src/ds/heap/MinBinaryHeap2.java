package ds.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/*
 * Array representation of Binary Heap
 * http://www.geeksforgeeks.org/array-representation-of-binary-heap/
 */

public class MinBinaryHeap2< T extends Comparable <T>> {
	
	ArrayList<T> mHeap;	
	
	public MinBinaryHeap2() {
		mHeap = new ArrayList<T>();
	}
	
	/*
	 * Time Complexity: O(logV)
	 */	
	public int insert(T data) {
		mHeap.add(data); //always insert at the end of the list
		int index = mHeap.size() - 1;
		heapifyUp(index);
		return index;
	}
	
	/*
	 * Time Complexity: O(1)
	 */	
	public T getMin() {
		if (mHeap.size() == 0)
			return null;
		
		return mHeap.get(0);
	}
	
	/*
	 * Time Complexity: O(logV)
	 */	
	public T extractMin() {
		if (mHeap.isEmpty())
			return null;
		
		T min = getMin();
		int lastIndex = getLastIndex();
		mHeap.set(0, mHeap.get(lastIndex)); // replace root node value with last node value 
		mHeap.remove(lastIndex);
		if (!mHeap.isEmpty()) {
			heapifyDown(0);	
		}
		return min;
	}
	
	/*
	 * Time Complexity: O(V)
	 * Search takes O(V) and heapifying takes O(log V) i.e. O(V) + O (log V) = O(V)
	 * In Big-O analysis we drop the constants/small values log (V)
	 * 
	 */	
	public boolean delete(T data) {
		int index = getIndex(data); // O (n)
		if (index == -1)
			return false;
		
		int lastIndex = getLastIndex();
		mHeap.set(index, mHeap.get(lastIndex)); // replace node value with last node value
		mHeap.remove(lastIndex);
		heapifyDown(index);
		return true;
	}	

	/*
	 * Time Complexity: O(V)
	 */	
	public boolean contains(T data) {
		return mHeap.contains(data);
	}
	
	/*
	 * Time Complexity: O(1)
	 */	
	public int size() {
		return mHeap.size();
	}
	
	public T[] toArray() {
		T[] tempArr = (T[])mHeap.toArray();
		return Arrays.copyOf(tempArr, mHeap.size());
	}
	
	public Iterator<T> iterator() {
		return new BinaryHeapIterator(mHeap);
	}
	
	private int getIndex(T data) {
		int index = 0;
		for (T tempData : mHeap) {
			if (tempData.equals(data))
				return index;
			index++;
		}
		return -1;		
	}
	
	private int getLastIndex () {
		return (mHeap.size() - 1);
	}
	
	/*
	 * Time Complexity: O(logV)
	 */	
	private void heapifyUp(int index) {
		if (index == 0) // reached top (root)
			return;
	
		int pIndex = getParent(index);
		if (pIndex == -1) {
			return; //something went wrong
		}
		T pData = mHeap.get(pIndex);
		T data = mHeap.get(index);
		if (data.compareTo(pData) >= 0) {
			return; // no violation, do nothing
		}
		
		// swap
		mHeap.set(pIndex, data);
		mHeap.set(index, pData);
		heapifyUp(pIndex);
	}
	
	/*
	 * Time Complexity: O(logV)
	 */
	private void heapifyDown(int pIndex) {
		T pData = mHeap.get(pIndex);
		T smallestData = pData;
		int nodeIndexToSwap = -1;
		
		int lIndex = getLeft(pIndex);
		if (lIndex != -1) {
			T lData = mHeap.get(lIndex);
			if (pData.compareTo(lData) > 0) {
				smallestData = lData;
				nodeIndexToSwap = lIndex;
			}
		}
		
		int rIndex = getRight(pIndex);
		if (rIndex != -1) {
			T rData = mHeap.get(rIndex);
			if (rData.compareTo(smallestData) < 0) {
				smallestData = rData;
				nodeIndexToSwap = rIndex;
			}
		}		
		if (smallestData != pData) {
			// swap
			mHeap.set(pIndex, smallestData);
			mHeap.set(nodeIndexToSwap, pData);
			heapifyDown(nodeIndexToSwap);
		}
	}
	
	private int getParent(int childIndex) {
		int parentIndex = (childIndex - 1)/2;
		return (parentIndex < 0 ? 0 : parentIndex);
	}
	
	private int getLeft(int pIndex) {
		int leftIndex =  (pIndex * 2 + 1);
		return (leftIndex >= mHeap.size() ? -1 : leftIndex);
	}
	
	private int getRight(int pIndex) {
		int rightIndex =   (pIndex * 2 + 2);
		return (rightIndex >= mHeap.size() ? -1 : rightIndex);
	}
	
	private static class BinaryHeapIterator <T> implements Iterable<T>, Iterator<T> {

		private ArrayList<T> mHeap;
		private int mSize;
		private int mCursor;
		
		public BinaryHeapIterator(ArrayList<T> heap) {
			mHeap = heap;
			mSize = heap.size();
			mCursor = 0;
		}
		
		@Override
		public Iterator<T> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return (mCursor != mSize);
		}

		@Override
		public T next() {
			return mHeap.get(mCursor++);
		}		
	}
}
