package ds.list;

import java.util.Iterator;
import java.util.ListIterator;

public interface MyList<T> {
	public boolean add(T element);
	public void add(int index, T element);
	public boolean addAll(MyList<T> elements);
	public boolean addAll(int index, MyList<T> elements);
	public void clear();
	public boolean contains(T element);
	public boolean containsAll(final MyList<T> elements);
	public T get(int index);
	public int indexOf(T element);
	public boolean isEmpty();
	public Iterator<T> iterator();
	public int lastIndexOf(T element);
	public ListIterator<T> listIterator();
	public ListIterator<T> listIterator(int index);
	public boolean remove(T element);
	public T remove(int index);
	public boolean removeAll(final MyList<T> elements);
	public boolean retainAll(final MyList<T> elements);
	public T set(int index, T element);
	public int size();
	public MyList<T> subList(int start, int end);
	public T[] toArray();
	public T[] toArray(T[] elements);	
}
