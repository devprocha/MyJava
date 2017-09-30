package ds.list;

public class MyListNode<T> {	
	T mData;
	MyListNode<T> mNext;
	MyListNode<T> mPrev;
	
	public MyListNode(T data, MyListNode<T> next, MyListNode<T> prev) {
		mData = data;
		mNext = next;
		mPrev = prev;
	}
	
	public void setNextNode(MyListNode<T> next) {
		mNext = next;
	}
	
	public MyListNode<T> getNextNode() {
		return mNext;
	}
	
	public boolean hasNextNode() {
		return (mNext != null);
	}
	
	public void setPrevNode(MyListNode<T> prev) {
		mPrev = prev;
	}
	
	public MyListNode<T> getPrevNode() {
		return mPrev;
	}
	
	public boolean hasPrevNode() {
		return (mPrev != null);
	}
	
	public void setData(T data) {
		mData = data;
	}
	
	public T getData() {
		return mData;
	}
	
	public boolean hasData() {
		return (mData != null);
	}
}
