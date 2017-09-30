package ds.list;

public interface MyListIteratorCallback<T> {
	
	public void add(int index, T element);
	public void remove(int index);
	public void set(int index, T element);
}
