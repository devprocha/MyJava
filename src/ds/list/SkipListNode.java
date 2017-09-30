package ds.list;

public class SkipListNode<T> {	
	T mData;
	SkipListNode<T> mNext;
	SkipListNode<T> mPrev;
	SkipListNode<T> mTop;
	SkipListNode<T> mDown;
	
	public SkipListNode(T data, SkipListNode<T> next, SkipListNode<T> prev, SkipListNode<T> top, SkipListNode<T> down) {
		mData = data;
		mNext = next;
		mPrev = prev;
		mTop  = top;
		mDown = down;
	}
	
	public void setNextNode(SkipListNode<T> next) {
		mNext = next;
	}
	
	public SkipListNode<T> getNextNode() {
		return mNext;
	}
	
	public boolean hasNextNode() {
		return (mNext != null);
	}
	
	public void setPrevNode(SkipListNode<T> prev) {
		mPrev = prev;
	}
	
	public SkipListNode<T> getPrevNode() {
		return mPrev;
	}
	
	public boolean hasPrevNode() {
		return (mPrev != null);
	}
	
	public void setTopNode(SkipListNode<T> top) {
		mTop = top;
	}
	
	public SkipListNode<T> getTopNode() {
		return mTop;
	}
	
	public boolean hasTopNode() {
		return (mTop != null);
	}
	
	public void setDownNode(SkipListNode<T> down) {
		mDown = down;
	}
	
	public SkipListNode<T> getDownNode() {
		return mDown;
	}
	
	public boolean hasDownNode() {
		return (mDown != null);
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
