package ds.tree;

public class Node {	
	private int mData;
	private Node mLeft;
	private Node mRight;
	private Node mParent;
	
	public Node(int data) {
		mData = data;
		mLeft = null;
		mRight = null;
		mParent = null;
	}
	public int getData() {
		return mData;
	}	
	public void setData(int data) {
		mData = data;
	}	
	public Node getLeft() {
		return mLeft;
	}
	public void setLeft(Node left) {
		mLeft = left;
	}
	public Node getRight() {
		return mRight;
	}
	public void setRight(Node right) {
		mRight = right;
	}
	public Node getParent() {
		return mParent;
	}
	public void setParent(Node parent) {
		mParent = parent;
	}
}
