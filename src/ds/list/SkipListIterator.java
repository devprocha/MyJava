package ds.list;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SkipListIterator<T> implements ListIterator<T> {
	private SkipListNode<T> mLastRet;
	private SkipListNode<T> mCurr;
	private int       mCusror;
	private final MyListIteratorCallback<T> mListIteratorCallback;
	
	public SkipListIterator(SkipListNode<T> head, MyListIteratorCallback<T> cb) {
		mCurr = head;
		mLastRet = null;
		mListIteratorCallback = cb;
	}
	@Override
	public boolean hasNext() {
		return (mCurr != null);	
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();

		mCusror++;
		T data = mCurr.getData();
		mLastRet = mCurr;
		mCurr = mCurr.getNextNode();
		return data;
	}
	
	public SkipListNode<T> nextNode() {
		if (!hasNext())
			throw new NoSuchElementException();

		mCusror++;		
		mLastRet = mCurr;
		mCurr = mCurr.getNextNode();
		return mLastRet;
	}
	
	@Override
	public int nextIndex() {	
		return mCusror;
	}
	
	@Override
	public boolean hasPrevious() {
		if (mCurr == null) { // we reached end of the list
			if (mLastRet != null) {
				return true;
			}
			return false;
		}
		return true;
	}
	
	@Override
	public T previous() {
		if (!hasPrevious())
			throw new NoSuchElementException();
		
		if (mCurr == null) { // we reached end of the list, traverse back
			mCurr = mLastRet;
		}
		
		mCusror--;
		T data = mCurr.getData();
		mLastRet = mCurr;
		mCurr = mCurr.getPrevNode();
		return data;
	}
	
	public SkipListNode<T> previousNode() {
		if (!hasPrevious())
			throw new NoSuchElementException();
		
		if (mCurr == null) { // we reached end of the list, traverse back
			mCurr = mLastRet;
		}
		
		mCusror--;
		mLastRet = mCurr;
		mCurr = mCurr.getPrevNode();
		return mLastRet;
	}
	
	@Override
	public int previousIndex() {	
		return (mCusror -1);
	}
	
	@Override
	public void add(T element) {
		mListIteratorCallback.add(mCusror, element);
	}
	
	@Override
	public void remove() {
		mListIteratorCallback.remove(mCusror);
	}
	
	@Override
	public void set(T element) {
		mListIteratorCallback.set(mCusror, element);
	}
	
	public void set(SkipListNode<T> head) {
		mCurr = head;
		mLastRet = null;
	}
}
