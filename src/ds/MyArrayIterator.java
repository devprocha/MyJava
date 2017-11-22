package ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayIterator<T> implements Iterator<T>, Iterable<T> {

	private T[] mItems;
	private int mSize;
	private int mCursor;
	
	public MyArrayIterator(T[] items, int size) {
		mItems = items;
		mSize = size;
		mCursor = 0;
	}
	@Override
	public boolean hasNext() {
		return (mCursor != mSize);
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();
		
		return mItems[mCursor++];
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return this;
	}
}
