package ds.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class GraphAL3<T> {
	private ArrayList<Node<T>> mNodeList;
	private boolean mDirected = false;
	
	public GraphAL3(boolean directed) {
		mNodeList = new ArrayList<Node<T>>();
		mDirected = directed;
	}
	
	public boolean add(T src) {
		Node<T> node = new Node<T>(src);
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
		if (!mDirected)
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
		LinkedHashSet<Node<T>> srcEdgeSet = srcNode.getEdgeSet();	
		return srcEdgeSet.contains(destNode);	
	}
	
	public boolean remove(T src) {
		Node<T> node = getNode(src);
		if (node == null) {
			return false;
		}
		return remove(node);
	}
	
	private boolean remove(Node<T> node) {		
		mNodeList.remove(node);		
		// Dissociate all edges
		return removeAllEdges(node);
	}
	
	public boolean contract(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}
		Node<T> destNode = getNode(src);
		if (destNode == null) {
			return false;
		}		
		mNodeList.remove(srcNode);
		return contractEdges(srcNode, destNode);
	}
	
	private boolean contractEdges(Node<T> srcNode, Node<T> destNode) {
		LinkedHashSet<Node<T>> edgeSet;
		LinkedHashSet<Node<T>> srcEdgeSet = srcNode.getEdgeSet();
		LinkedHashSet<Node<T>> destedgeSet = destNode.getEdgeSet();
		for (Node<T> node : srcEdgeSet) {
			edgeSet  = node.getEdgeSet();
			edgeSet.remove(srcNode);
			edgeSet.add(destNode);
			destedgeSet.add(node);
		}
		return true;
	}
	
	public boolean removeEdge(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}
		
		Node<T> destNode = getNode(src);
		if (destNode == null) {
			return false;
		}
		// Dissociate edge
		LinkedHashSet<Node<T>> srcEdgeSet = srcNode.getEdgeSet();
		srcEdgeSet.remove(destNode);
		LinkedHashSet<Node<T>> destedgeSet = destNode.getEdgeSet();
		destedgeSet.remove(srcNode);
		return true;
	}
	
	public boolean contains(T data) {
		for (Node<T> node : mNodeList) {
			if (node.getData() == data || node.getData().equals(data)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean removeAllEdges(Node<T> node) {
		LinkedHashSet<Node<T>> edgeSet = node.getEdgeSet();
		LinkedHashSet<Node<T>> tempedgeSet;
		for (Node<T> tempNode : edgeSet) {
			tempedgeSet = tempNode.getEdgeSet();
			tempedgeSet.remove(node);
		}
		return true;
	}
	
	/*
	 * Uses BFS approach using while loop
	 */
	public boolean hasPathBFS(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		Node<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		
		LinkedList<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(srcNode);
		HashSet<Node<T>> visitedSet = new HashSet<Node<T>>();	
		while (!queue.isEmpty()) {
			Node<T> node = queue.poll();		
			if (visitedSet.contains(node))
				continue;			
			visitedSet.add(node);			
			LinkedHashSet<Node<T>> edgeSet = node.getEdgeSet();
			if (edgeSet.contains(destNode))
				return true;			
			queue.addAll(edgeSet);
		}
		return false;
	}
	
	/*
	 * Uses BFS approach using recursion
	 */
	public boolean hasPathBFS2(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		Node<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		
		LinkedList<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(srcNode);		
		HashSet<Node<T>> visitedSet = new HashSet<Node<T>>();
		return hasPathBFS2(queue, visitedSet, destNode);
	}
	
	private boolean hasPathBFS2(LinkedList<Node<T>> queue, HashSet<Node<T>> visitedSet, Node<T> destNode) {
		if (queue.isEmpty())
			return false;
		
		Node<T> node = queue.poll();			
		if (!visitedSet.contains(node)){
			visitedSet.add(node);			
			LinkedHashSet<Node<T>> edgeSet = node.getEdgeSet();
			if (edgeSet.contains(destNode))
				return true;	
			queue.addAll(edgeSet);	
		}
		return hasPathBFS2(queue, visitedSet, destNode);
	}
		
	/*
	 * Uses DFS approach using recursion 
	 * DFS approach is not efficient for finding path
	 */
	
	public boolean hasPathDFS(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		Node<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		LinkedList<Node<T>> visitedList = new LinkedList<Node<T>>();
		visitedList.add(srcNode);
		return hasPathDFS(visitedList, destNode);
	}
	
	private boolean hasPathDFS(LinkedList<Node<T>> visitedList, Node<T> destNode) {		
		Node<T> node = visitedList.getLast();		
		if (node.equals(destNode)) {
			return true;
		}		
		for (Node<T> adjNode: node.getEdgeSet()) {
			if (!visitedList.contains(adjNode)) {
				visitedList.add(adjNode);
				if (hasPathDFS(visitedList, destNode))
					return true;
				//not found go to next adj node
			}
		}		
		return false;
	}
	
	/*
	 * Returns all the possible paths between source node and destination node using DFS  
	 * Time Complexity: O(|V| + |E|)
	 */
	public ArrayList<LinkedList<T>> getAllPaths(T src, T dest) {
		Node<T> srcNode = getNode(src);
		if (srcNode == null) {
			return null;
		}		
		Node<T> destNode = getNode(dest);
		if (destNode == null) {
			return null;
		}
		
		// Maintain visited list stack
		LinkedList<Node<T>> visitedList = new LinkedList<Node<T>>();
		visitedList.add(srcNode);
		
		ArrayList<LinkedList<T>> pathsList = new ArrayList<LinkedList<T>>();
		getPathsDFS(destNode, visitedList, pathsList);
		visitedList.removeLast();
		return pathsList;
	}
	
	private boolean getPathsDFS(Node<T> destNode, LinkedList<Node<T>> visitedList, ArrayList<LinkedList<T>> pathsList) {
		Node<T> currNode = visitedList.getLast();
		// Check we reached destination node or not
		if (currNode.equals(destNode)) {		
			return true;
		}
		
		// Repeat with current node edges
		for (Node<T> adjNode : currNode.getEdgeSet()) {
			if (!visitedList.contains(adjNode)) {
				visitedList.add(adjNode);
				if (getPathsDFS(destNode, visitedList, pathsList)) {
					copyDataPath(visitedList, pathsList);
				}
				visitedList.removeLast();
			}
		}
		return false;
	}
		
	private void copyDataPath(LinkedList<Node<T>> visitedList, ArrayList<LinkedList<T>> pathsList) {
		LinkedList<T> list = new LinkedList<T>();
		for (Node<T> node : visitedList) {
			list.add(node.getData());
		}
		pathsList.add(list);
	}
	
	public ArrayList<LinkedList<T>> getMinPaths(T src, T dest) {
		ArrayList<LinkedList<T>> pathsList = getAllPaths(src, dest);
		ArrayList<LinkedList<T>> minPathList = new ArrayList<LinkedList<T>>();
		int minPathSize = Integer.MAX_VALUE;
		for (LinkedList<T> pathList : pathsList) {
			if (pathList.size() == minPathSize) {
				minPathList.add(pathList);
			} else if (pathList.size() < minPathSize) {
				minPathList = new ArrayList<LinkedList<T>>();
				minPathList.add(pathList);
				minPathSize = pathList.size();
			}
		}
		return minPathList;
	}
	
	public ArrayList<LinkedList<T>> getMaxPaths(T src, T dest) {
		ArrayList<LinkedList<T>> pathsList = getAllPaths(src, dest);
		ArrayList<LinkedList<T>> maxPathList = new ArrayList<LinkedList<T>>();
		int maxPathSize = 0;
		for (LinkedList<T> pathList : pathsList) {
			if (pathList.size() == maxPathSize) {
				maxPathList.add(pathList);
			} else if (pathList.size() > maxPathSize) {
				maxPathList = new ArrayList<LinkedList<T>>();
				maxPathList.add(pathList);
				maxPathSize = pathList.size();
			}
		}
		return maxPathList;
	}
		
	public LinkedList<T> getShortestPath(T src, T dest) {
		return null;
	}
	
	public LinkedList<T> getLongestPath(T src, T dest) {
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
