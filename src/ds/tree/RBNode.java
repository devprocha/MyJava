package ds.tree;

public class RBNode {
	
	public enum COLOR {
		RED, BLACK;
	}
	private int mData;
	private RBNode mLeft;
	private RBNode mRight;
	private RBNode mParent;
	private COLOR  mColor;
	
	public RBNode(int data, COLOR color, RBNode parent) {
		mData = data;
		mLeft = null;
		mRight = null;
		mParent = parent;
		mColor = color;
	}	
	int getData() {
		return mData;
	}
	void setData(int data) {
		mData = data;
	}
	RBNode getLeft() {
		return mLeft;
	}
	void setLeft(RBNode left) {
		mLeft = left;
	}
	RBNode getRight() {
		return mRight;
	}
	void setRight(RBNode right) {
		mRight = right;
	}
	RBNode getParent() {
		return mParent;
	}
	void setParent(RBNode parent) {
		mParent = parent;
	}
	COLOR getColor() {
		return mColor;
	}
	void setColor(COLOR color) {
		mColor = color;
	}
}
