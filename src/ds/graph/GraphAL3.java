package ds.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphAL3<T> {
	private ArrayList<Node<T>> mNodeList;
	
	public GraphAL3() {
		mNodeList = new ArrayList<Node<T>>();		
	}
	
	public boolean add(T data) {
		Node<T> node = new Node<T>(data);
		return mNodeList.add(node);
	}
	
	public boolean addEdge(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		Node<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		
		srcNode.addEdge(destNode);
		destNode.addEdge(srcNode);//undirected graph
		return true;
	}
	
	public boolean isAdjacent(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}
		Node<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		LinkedList<Node<T>> srcEdgeList = srcNode.getEdgeList();	
		return srcEdgeList.contains(destNode);	
	}
	
	/*
	 * Uses BFS approach
	 */
	public boolean isPathAvailable(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		Node<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		LinkedList<Node<T>> srcEdgeList = srcNode.getEdgeList();
		if (srcEdgeList.contains(destNode))
			return true;
		
		HashSet<T> visitedSet = new HashSet<T>();// why set? lookup is faster O(1)
		visitedSet.add(srcNode.getData());
		LinkedList<Node<T>> queue = new LinkedList<Node<T>>();
		queue.addAll(srcEdgeList);
		return isPathAvailable(queue, visitedSet, destNode);
	}
	
	/*
	 * Uses BFS approach
	 */
	private boolean isPathAvailable(LinkedList<Node<T>> queue, HashSet<T> visitedSet, Node<T> destNode) {
		if (queue.isEmpty()) {
			return false;
		}
		Node<T> node = queue.removeFirst();
		if (!visitedSet.contains(node.getData())) {
			visitedSet.add(node.getData());
			LinkedList<Node<T>> eddgeList = node.getEdgeList();
			boolean contains = eddgeList.contains(destNode);
			if (contains) {
				return true;
			}
			queue.addAll(eddgeList);
		}		
		return isPathAvailable(queue, visitedSet, destNode);
	}
	
	public ArrayList<LinkedList<T>> getPaths() {
		return null;		
	}
	
	public LinkedList<T> getShortestPath() {
		return null;
	}
	
	public LinkedList<T> getLongestPath() {
		return null;
	}
	
	private Node<T> getNode(T data){
		for (Node<T> node : mNodeList) {
			if (node.getData() == data || node.getData().equals(data)) {
				return node;
			}
		}
		return null;		
	}	
}
