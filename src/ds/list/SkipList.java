package ds.list;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import java.util.AbstractSequentialList;

public class SkipList<T> extends AbstractSequentialList<T> implements Deque<T> {
	
	SkipListNode<T> mHead;
	SkipListNode<T> mTail;
	int mSize;
	private SkipListIterator<T> mListIterator;
	private static final int NOT_FOUND = -1;

	@Override
	public boolean add(T element) {	
		final SkipListNode<T> node = new SkipListNode<T>(element, null, null, null, null);
		if (mHead == null) {         // Adding first time
			mHead = node;			
		} else if (mHead == mTail) { // Adding second time
			mHead.setNextNode(node);
			node.setPrevNode(mHead);
		} else {
			mTail.setNextNode(node);
			node.setPrevNode(mTail);
		}
		mTail = node;
		mSize++;
		return true;		
	}

	//TODO
	@Override
	public void add(int index, T element) {
		if (index < 0 || index > mSize)
			throw new IllegalArgumentException();
		
		final SkipListNode<T> node = new SkipListNode<T>(element, null, null, null, null);
		if (index == 0) {          // Adding first
			if (mHead != null) {
				node.setNextNode(mHead);
				mHead.setPrevNode(node);
			}
			mHead = node;
			if (mTail == null) {
				mTail = node;	
			}						
		} else if (index == mSize) { // Adding last
			mTail.setNextNode(node);
			node.setPrevNode(mTail);			
			mTail = node;
		} else { // Adding in between
			final SkipListNode<T> curr = getNode(index);
			final SkipListNode<T> prev = curr.getPrevNode();
			prev.setNextNode(node);
			node.setPrevNode(prev);			
			node.setNextNode(curr);			
			curr.setPrevNode(node);			
		}
		mSize++;		
	}

	@Override
	public boolean addAll(Collection<? extends T> elements) {		
		for (T element : elements) {
			add(element);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> elements) {
		int i = 0;
		for (T element : elements) {
			add(index + i, element);
			i++;
		}
		return true;
	}

	@Override
	public void clear() {
		checkForEmpty();
		mHead = null;
		mTail = null;
		mSize = 0;
	}

	@Override
	public boolean contains(Object element) {
		checkForEmpty();		
		boolean contains = false;
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();		
		while (li.hasNext() && !contains) {
			if (li.next() == element)
				contains = true;
		}
		return contains;
	}

	@Override
	public boolean containsAll(Collection<?> elements) {
		checkForEmpty();		
		boolean contains = true;
		final Iterator<?> li = elements.iterator();		
		while (li.hasNext() && contains) {
			contains = contains(li.next());
		}
		return contains;
	}

	@Override
	public T get(int index) {
		checkForEmpty();
		T element = null;		
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();		
		while (li.hasNext() && element == null) {			 
			if (li.nextIndex() == index) {
				element = li.next();
			} else li.next();
		}
		return element;
	}

	@Override
	public int indexOf(Object element) {
		int index = NOT_FOUND;		
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();		
		while (li.hasNext() && index == NOT_FOUND) {
			if (li.next() == element)
				index = li.nextIndex();
		}
		return index;
	}

	@Override
	public boolean isEmpty() {		
		return (mSize < 0);
	}

	@Override
	public int lastIndexOf(Object element) {
		checkForEmpty();
		int index = NOT_FOUND;		
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();		
		while (li.hasNext()) {
			if (li.next() == element)
				index = li.nextIndex();
		}
		return index;
	}

	@Override
	public ListIterator<T> listIterator() {
		checkForEmpty();
		mListIterator =  new SkipListIterator<T>(mHead, new MyListIteratorCallbackImpl(0));
		return mListIterator;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		checkForEmpty();
		final SkipListNode<T> subList = (SkipListNode<T>) subList(index, mSize -1);
		mListIterator =  new SkipListIterator<T>(subList, new MyListIteratorCallbackImpl(0));
		return mListIterator;
	}
	
	public ListIterator<T> listIterator(SkipListNode<T> head) {
		checkForEmpty();
		mListIterator =  new SkipListIterator<T>(head, new MyListIteratorCallbackImpl(0));
		return mListIterator;
	}

	@Override
	public boolean remove(Object element) {
		checkForEmpty();		
		final SkipListNode<T> node = getNode(element);
		if (node == null) {
			throw new IllegalArgumentException();
		}
		return remove(node);
	}

	@Override
	public T remove(int index) {
		checkForEmpty();		
		final SkipListNode<T> node = getNode(index);
		if (node == null) {
			throw new IllegalArgumentException();
		}
		final T element = node.getData();
		return (remove(node) ? element : null);
	}

	@Override
	public boolean removeAll(Collection<?> elements) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> elements) {
		checkForEmpty();
		T element;
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();
		while (li.hasNext()) {
			element = li.next();
			if (!elements.contains(element)) {			
				remove(element);
			}
		}
		return true;
	}

	@Override
	public T set(int index, T element) {
		return null;
	}

	@Override
	public int size() {
		return mSize;
	}

	@Override
	public List<T> subList(int start, int end) {
		checkForEmpty();
		boolean done = false;
		final List<T> subList = new SkipList<T>();
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();		
		while (li.hasNext() && done == false) {
			if (li.nextIndex() >= start && li.nextIndex() <= end) {
				subList.add(li.next());
				if (li.nextIndex() == end) {
					done = true;
				}
			} else li.next();				
		}		
		return (done ? subList : null);
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] index) {
		return null;
	}

	@Override
	public void addFirst(T element) {
		add(0, element);
	}

	@Override
	public void addLast(T element) {
		add(element);		
	}

	@Override
	public Iterator<T> descendingIterator() {
		checkForEmpty();
		mListIterator =  new SkipListIterator<T>(reverse(mHead), new MyListIteratorCallbackImpl(0));
		return mListIterator;
	}

	@Override
	public T element() {		
		return mHead.getData();
	}

	@Override
	public T getFirst() {		
		return mHead.getData();
	}

	@Override
	public T getLast() {
		return mTail.getData();
	}

	@Override
	public boolean offer(T element) {
		try {
			add(element);	
		} catch (IllegalStateException e) {
			return false;
		}	
		return true;
	}

	@Override
	public boolean offerFirst(T element) {
		try {
			add(0, element);	
		} catch (IllegalStateException e) {
			return false;
		}	
		return true;
	}

	@Override
	public boolean offerLast(T element) {
		return offer(element);
	}

	@Override
	public T peek() {		
		return getFirst();
	}

	@Override
	public T peekFirst() {
		return getFirst();
	}

	@Override
	public T peekLast() {
		return getLast();
	}

	@Override
	public T poll() {
		T element = getFirst();		
		return (remove(element) ? element : null);
	}

	@Override
	public T pollFirst() {
		return poll();
	}

	@Override
	public T pollLast() {
		T element = getLast();		
		return (remove(element) ? element : null);
	}

	@Override
	public T pop() {		
		return pollFirst();
	}

	@Override
	public void push(T element) {
		addFirst(element);		
	}

	@Override
	public T remove() {
		return pollFirst();
	}

	@Override
	public T removeFirst() {
		return pollFirst();
	}

	@Override
	public boolean removeFirstOccurrence(Object element) {
		return remove(element);
	}

	@Override
	public T removeLast() {
		return pollLast();
	}

	@Override
	public boolean removeLastOccurrence(Object element) {
		boolean removed = false;
		SkipListNode<T> curr = null;
		SkipListNode<T> prev;	
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();
		while (li.hasNext()) {
			if ((curr = li.nextNode()).getData() != element) {		
				curr = null;
			}
		}		
		if (curr != null) {
			prev = curr.getPrevNode();
			if (prev != null) {
				prev.setNextNode(curr.getNextNode());					
			} else {
				mHead = curr.getNextNode();
			}
			curr.setNextNode(null);
			removed = true;			
		}
		return removed;
	}
	
	private SkipListNode<T> getNode(int index) {
		SkipListNode<T> node = null;
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();		
		while (li.hasNext() && node == null) {
			if (li.nextIndex() == index) {
				node = li.nextNode();
			} else li.nextNode();
		}
		return node;
	}
	
	private SkipListNode<T> getNode(Object element) {
		SkipListNode<T> retNode = null;
		SkipListNode<T> tempNode = null;
		final SkipListIterator<T> li = (SkipListIterator<T>) listIterator();		
		while (li.hasNext() && retNode == null) {
			tempNode = li.nextNode();
			if (tempNode.getData() == element) {
				retNode = tempNode;
			}
		}
		return retNode;
	}
	
	private boolean remove(SkipListNode<T> node) {
		final SkipListNode<T> prev = node.getPrevNode();
		final SkipListNode<T> next = node.getNextNode();
		if (prev != null) {
			prev.setNextNode(next);			
		}
		if (next != null) {
			next.setPrevNode(prev);	
		}		
		if (mHead == node) {
			mHead = next;
		}		
		if (mTail == node) {
			mTail = prev;
		}
		mSize--;
		return true;
	}
	
	public void reverse() {
		SkipListNode<T> temp = reverse(mHead);		
		mTail = mHead;
		mHead = temp;
	}
	
	/*
	 * Iterative Method
     * Iterate trough the linked list. In loop, change next to prev, prev to current and current to next.
	 */
	public SkipListNode<T> reverse(SkipListNode<T> node) {
		SkipListNode<T> prev = null;
		SkipListNode<T> current = node;
		SkipListNode<T> next = null;
        while (current != null) {
            next = current.getNextNode();
            current.setNextNode(prev);
            current.setPrevNode(next);
            prev = current;
            current = next;
        }
        node = prev;
        return node;
	 }
	
	private void checkForEmpty() {
		if (mSize == 0) {
			throw new IllegalArgumentException();
		}
	}
	
	private class MyListIteratorCallbackImpl implements MyListIteratorCallback<T> {

		private int mListIndex;		
		public MyListIteratorCallbackImpl(int listIndex) {
			mListIndex = listIndex;
		}
		
		@Override
		public void add(int index, T element) {
			SkipList.this.add(mListIndex + index, element);
			mListIterator.set(mHead);
		}

		@Override
		public void remove(int index) {
			SkipList.this.remove(mListIndex + index);
			mListIterator.set(mHead);
		}
		
		@Override
		public void set(int index, T element) {
			SkipList.this.set(mListIndex + index, element);
			mListIterator.set(mHead);
		}
	}
}
