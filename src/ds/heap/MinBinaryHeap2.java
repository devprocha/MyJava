package ds.heap;

import java.util.ArrayList;

public class MinBinaryHeap2 {
	
	ArrayList<Integer> mHeap;	
	
	public MinBinaryHeap2() {
		mHeap = new ArrayList<Integer>();
	}
	
	public int insert(int data) {
		mHeap.add(data); //always insert at the end of the list
		int index = mHeap.size() - 1;
		heapifyUp(index);
		return index;
	}
	
	public int getMin() {
		return mHeap.get(0);
	}
	
	public int extractMin() {
		int min = getMin();
		mHeap.add(0, mHeap.get(mHeap.size() - 1)); // replace root node value with last node value 
		heapifyDown(0);
		return min;
	}
	
	public int delete(int index) {
		int value = mHeap.get(index);
		mHeap.add(index, mHeap.get(mHeap.size() - 1)); // replace node value with last node value
		heapifyDown(index);
		return 0;
	}
	
	private void heapifyUp(int childIndex) {
		if (childIndex == 0) // reached root
			return; 

		int childVal = mHeap.get(childIndex);
		int parentIndex = getParent(childIndex);
		int parentVal = mHeap.get(parentIndex);
		
		if (childVal < parentVal) {
			mHeap.add(parentIndex, childVal);
			mHeap.add(childIndex, parentVal);
			heapifyUp(parentIndex);
		}		
	}
	
	private void heapifyDown(int parentIndex) {		
		int parentVal = mHeap.get(parentIndex);		
		
		int leftIndex = getLeft(parentIndex);
		if (leftIndex != -1) {
			int leftVal = mHeap.get(leftIndex);		
			if (parentVal > leftVal) {
				mHeap.add(parentIndex, leftVal);
				mHeap.add(leftIndex, parentVal);
				heapifyDown(leftIndex);
			}	
		}		
		
		int rightIndex = getRight(parentIndex);
		if (rightIndex != -1) {
			int rightVal = mHeap.get(rightIndex);		
			if (parentVal > rightVal) {
				mHeap.add(parentIndex, rightVal);
				mHeap.add(rightIndex, parentVal);
				heapifyDown(rightIndex);
			}	
		}		
	}
	
	private int getParent(int childIndex) {
		int parentIndex = (childIndex -1)/2;
		return (parentIndex < 0 ? 0 : parentIndex);
	}
	
	private int getLeft(int index) {
		int leftIndex =  (index * 2 + 1);
		return (leftIndex >= mHeap.size() ? 0 : leftIndex);
	}
	
	private int getRight(int index) {
		int rightIndex =   (index * 2 + 2);
		return (rightIndex >= mHeap.size() ? 0 : rightIndex);
	}
}
