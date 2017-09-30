package ds.list;

import java.util.Iterator;
import java.util.ListIterator;

import ds.MyArrayIterator;

public class MyArrayList<T> implements MyList<T> {

	private T[] mElements;
	private int mSize;
	protected final static int DEFAULT_CAPACITY = 100;  
	private final static int NOT_FOUND = -1;
	private MyArrayListIterator<T> mListIterator;
	
	public MyArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	public MyArrayList(int defCapacity) {
		// mElements = new T[defCapacity] is not allowed see 
		// http://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java
		mElements = (T[])(new Object[defCapacity]);  
		mSize = 0;
	}
	
	@Override
	public boolean add(T element) {
		ensureCapacity();			
		mElements[mSize++] = element;
		return true;
	}

	@Override
	public void add(int index, T element) {
		ensureCapacity();
		
		T[]elements = copy(index, (mSize - 1));
		mElements[index] = element;
		final int startIndex = index + 1;
		for (int i = 0; i < elements.length; i++) {
			mElements[startIndex + i] = elements[i];
		}
		mSize++;
	}
	
	private T[] copy(int start, int end) {		
		final int size = (end - start) + 1;
		T[]elements = (T[])(new Object[size]);
		for (int i = 0; i < size; i++) {
			elements[i] = mElements[start + i];
		}		
		return elements;		
	}

	@Override
	public boolean addAll(final MyList<T> elements) {
		final Iterator<T> iterator = elements.iterator();
		while (iterator.hasNext()) {
			add(iterator.next());
		}
		return true;
	}

	@Override
	public boolean addAll(int index, MyList<T> elements) {
		final Iterator<T> iterator = elements.iterator();
		for (int i = 0 ; iterator.hasNext(); i++) {
			add(index + i, iterator.next());
		}
		return true;
	}

	@Override
	public void clear() {
		if (mElements.length > 0) {
			mElements = (T[])(new Object[DEFAULT_CAPACITY]);
			mSize = 0;
		}
	}

	@Override
	public boolean contains(T element) {
		boolean contains = false;
		final Iterator<T> iterator = iterator();
		while (iterator.hasNext() && !contains) {
			if (iterator.next() == element) {
				contains = true;
			}				
		}
		return contains;
	}

	@Override
	public boolean containsAll(final MyList<T> elements) {
		if (elements.isEmpty())
			return false;
		
		boolean contains = true;
		final Iterator<T> iterator = elements.iterator();
		while (iterator.hasNext() && contains) {
			contains = contains(iterator.next());
		}
		return contains;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= mSize) 
			throw new IndexOutOfBoundsException();		
		return mElements[index];
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;
		final Iterator<T> iterator = iterator();
		for (int i = 0; iterator.hasNext() && index == NOT_FOUND; i++) {
			if (iterator.next() == element) {
				index = i;
			}			
		}
		return index;
	}

	@Override
	public boolean isEmpty() {
		return (mSize == 0);
	}

	@Override
	public Iterator<T> iterator() {		
		return new MyArrayIterator<T>(mElements, mSize);
	}

	@Override
	public int lastIndexOf(T element) {
		int index = NOT_FOUND;
		final Iterator<T> iterator = iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			if (iterator.next() == element) {
				index = i;
			}			
		}
		return index;
	}

	@Override
	public ListIterator<T> listIterator() {
		mListIterator = new MyArrayListIterator<T>(mElements, mSize, new MyListIteratorCallbackImpl(0));
		return mListIterator;
	}

	@Override
	public ListIterator<T> listIterator(int listIndex) {
		final T[]elements = copy(listIndex, (mSize - 1));
		mListIterator = new MyArrayListIterator<T>(elements, mSize, new MyListIteratorCallbackImpl(listIndex));
		return mListIterator;
	}

	@Override
	public boolean remove(T element) {		
		int index = indexOf(element);
		if (index == NOT_FOUND)
			return false;
		
		remove(index);
		return true;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= mSize) 
			throw new IndexOutOfBoundsException();
		
		T element = mElements[index];
		//shift the elements to left of removed index
		for (int i = index; i < mSize - 1; i++) {
			mElements[i] = mElements[i+1];		
		}
		mElements[mSize - 1] = null;
		mSize --;
		return element;
	}

	@Override
	public boolean removeAll(final MyList<T> elements) {
		final Iterator<T> iterator = elements.iterator();		
		while(iterator.hasNext()) {
			remove(iterator.next());
		}
		return true;
	}

	@Override
	public boolean retainAll(final MyList<T> elements) {
		T element;
		final Iterator<T> iterator = iterator();		
		while (iterator.hasNext()) {
			element = iterator.next();
			if(!elements.contains(element)) {
				remove(element);
			}
		}
		return true;
	}

	@Override
	public T set(int index, T element) {
		if (index < 0 || index >= mSize) 
			throw new IndexOutOfBoundsException();
		
		T oldElement = mElements[index];
		mElements[index] = element;
		return oldElement;	
	}

	@Override
	public int size() {
		return mSize;
	}

	@Override
	public MyList<T> subList(int start, int end) {		
		if(start < 0 || end >= mSize)
			throw new IndexOutOfBoundsException();
		
		if(start > end)
			throw new IllegalArgumentException();
		
		final MyList<T> retList = new MyArrayList<T>((end - start) + 1);
		for (int i = start; i <= end; i ++) {
			retList.add(mElements[i]);
		}
		return retList;
	}

	@Override
	public T[] toArray() {
		T[] elements = (T[])(new Object[mSize]);
		for (int i = 0; i< mSize; i++) {
			elements[i] = mElements[i];
		}
		return elements;
	}

	@Override
	public T[] toArray(T[] elements) {		
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		final Iterator<T> iterator = iterator();
		sb.append("{");
		while (iterator.hasNext()) {			
			sb.append(iterator.next());
			if(iterator.hasNext())
				sb.append(",");
		}
		sb.append("}");
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity() {
		if (mSize == mElements.length) {
			final T[] newElements = (T[])(new Object[mElements.length * 2]);
			for(int i = 0; i < mElements.length; i++ ) {
				newElements[i] = mElements[i];	
			}
			mElements = newElements;	
		}				
	}
	
	private class MyListIteratorCallbackImpl implements MyListIteratorCallback<T> {
		private int mListIndex;		
		public MyListIteratorCallbackImpl(int listIndex) {
			mListIndex = listIndex;
		}
		
		@Override
		public void add(int index, T element) {
			MyArrayList.this.add(mListIndex + index, element);
			mListIterator.set(mElements, mSize);
		}

		@Override
		public void remove(int index) {
			MyArrayList.this.remove(mListIndex + index);
			mListIterator.set(mElements, mSize);
		}
		
		@Override
		public void set(int index, T element) {
			MyArrayList.this.set(mListIndex + index, element);
			mListIterator.set(mElements, mSize);
		}
	}
}
