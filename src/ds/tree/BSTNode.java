package ds.tree;

public class BSTNode {
	private int mData;
	private BSTNode mLeft;
	private BSTNode mRight;
	private BSTNode mParent;
	
	public BSTNode(int data, BSTNode left, BSTNode right, BSTNode parent) {
		mData = data;
		mLeft = left;
		mRight = right;
		mParent = parent;		
	}
	
	public void setData(int data) {
		mData = data;
	}
	
	public int getData() {
		return mData;
	}
	
	public void setParent(BSTNode parent) {
		mParent = parent;
	}
	
	public BSTNode getParent() {
		return mParent;
	}
	
	public void setLeft(BSTNode left) {
		mLeft = left;
	}
	
	public BSTNode getLeft() {
		return mLeft;
	}
	
	public void setRight(BSTNode right) {
		mRight = right;
	}
	
	public BSTNode getRight() {
		return mRight;
	}
}
