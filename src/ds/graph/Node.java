package ds.graph;

import java.util.LinkedList;

public class Node <T> {
	private T mData;
	private LinkedList<Node<T>> mEdgeList; // contains list of directly connected edges
	
	public Node(T data) {
		mData = data;
		mEdgeList = new LinkedList<Node<T>>();		
	}
	
	public T getData() {
		return mData;
	}
	
	public LinkedList<Node<T>> getEdgeList() {
		return mEdgeList;
	}
	
	public void addEdge(Node node) {
		mEdgeList.add(node);		
	}
	
	public Node<T> addEdge(T data) {
		Node<T> node = new Node<T>(data);
		mEdgeList.add(node);
		return node;
	}	
}
