package ds.list;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayListIterator<T> implements ListIterator<T> {

	private T[] mItems;
	private int mSize;
	private int mCursor;
	private int mLastRet;
	private final MyListIteratorCallback<T> mListIteratorCallback;
	
	public MyArrayListIterator(T[] items, int size, MyListIteratorCallback<T> cb) {
		mItems = items;
		mSize = size;
		mCursor = 0;
		mLastRet = -1;
		mListIteratorCallback = cb;
	}
	@Override
	public boolean hasNext() {
		return (mCursor != mSize);
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();
		mLastRet = mCursor;
		return mItems[mCursor++];
	}
	
	@Override
	public int nextIndex() {
		return mCursor + 1;
	}
	
	@Override
	public boolean hasPrevious() {
		if (mLastRet == -1)
			return false;
		else if (mCursor != -1){
		  return true;	
		}	
		return false;
	}
	
	@Override
	public T previous() {
		if (!hasPrevious())
			throw new NoSuchElementException();
		
		if (mCursor == mSize) {
			mCursor = mLastRet;
		}
		mLastRet = mCursor;
		return mItems[mCursor--];
	}
	
	@Override
	public int previousIndex() {	
		return (mCursor - 1);
	}
	
	@Override
	public void add(T element) {
		mListIteratorCallback.add(mCursor, element);		
	}
	
	@Override
	public void remove() {
		mListIteratorCallback.remove(mCursor);
	}
	
	@Override
	public void set(T element) {
		mListIteratorCallback.set(mCursor, element);
	}
	
	public void set(T[] elements, int size) {
		mItems = elements;
		mSize = size;		
	}
}
