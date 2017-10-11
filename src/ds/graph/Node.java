package ds.graph;

import java.util.LinkedHashSet;

public class Node <T> {
	private T mData;
	private LinkedHashSet<Node<T>> mEdgeSet; // why set? lookup contains() is faster O(1)
	
	public Node(T data) {
		mData = data;
		mEdgeSet = new LinkedHashSet<Node<T>>();		
	}
	
	public T getData() {
		return mData;
	}
	
	public LinkedHashSet<Node<T>> getEdgeSet() {
		return mEdgeSet;
	}
	
	public void addEdge(Node<T> node) {
		mEdgeSet.add(node);
	}
	
	public Node<T> addEdge(T data) {
		Node<T> node = new Node<T>(data);
		mEdgeSet.add(node);
		return node;
	}
}
