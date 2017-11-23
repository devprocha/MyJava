package ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WGraphAL<T> {
	private ArrayList<WNode<T>> mNodeList;	
	private boolean mDirected = false;
	
	public WGraphAL(boolean directed) {
		mNodeList = new ArrayList<WNode<T>>();
		mDirected = directed;
	}
	
	public boolean add(T src) {
		WNode<T> node = new WNode<T>(src);
		return mNodeList.add(node);
	}
	
	public boolean addEdge(T src, T dest, int weight) {
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		WNode<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		
		srcNode.addEdge(destNode, weight);
		if (!mDirected)
			destNode.addEdge(srcNode, weight);//undirected graph
		return true;
	}
	
	public boolean isAdjacent(T src, T dest) {
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}
		WNode<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		LinkedHashSet<WNode<T>> srcEdgeSet = srcNode.getEdges();	
		return srcEdgeSet.contains(destNode);	
	}
	
	public boolean remove(T src) {
		WNode<T> node = getNode(src);
		if (node == null) {
			return false;
		}
		return remove(node);
	}
	
	private boolean remove(WNode<T> node) {		
		mNodeList.remove(node);		
		// Dissociate all edges		
		return removeAllEdges(node);
	}
	
	public boolean contract(T src, T dest) {
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}
		WNode<T> destNode = getNode(src);
		if (destNode == null) {
			return false;
		}		
		mNodeList.remove(srcNode);
		return contractEdges(srcNode, destNode);
	}
	
	private boolean contractEdges(WNode<T> srcNode, WNode<T> destNode) {
		LinkedHashSet<WNode<T>> edgeSet;
		LinkedHashSet<WNode<T>> srcEdgeSet = srcNode.getEdges();
		LinkedHashSet<WNode<T>> destedgeSet = destNode.getEdges();
		for (WNode<T> node : srcEdgeSet) {
			edgeSet  = node.getEdges();
			edgeSet.remove(srcNode);
			edgeSet.add(destNode);
			destedgeSet.add(node);
		}
		return true;
	}
	
	public boolean removeEdge(T src, T dest) {
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}
		
		WNode<T> destNode = getNode(src);
		if (destNode == null) {
			return false;
		}
		// Dissociate edge
		LinkedHashSet<WNode<T>> srcEdgeSet = srcNode.getEdges();
		srcEdgeSet.remove(destNode);
		LinkedHashSet<WNode<T>> destedgeSet = destNode.getEdges();
		destedgeSet.remove(srcNode);
		return true;
	}
	
	public boolean contains(T data) {
		for (WNode<T> node : mNodeList) {
			if (node.getData() == data || node.getData().equals(data)) {
				return true;
			}
		}
		return false;
	}	
	
	private boolean removeAllEdges(WNode<T> node) {
		LinkedHashSet<WNode<T>> edgeSet = node.getEdges();
		LinkedHashSet<WNode<T>> tempedgeSet;
		for (WNode<T> tempNode : edgeSet) {
			tempedgeSet = tempNode.getEdges();
			tempedgeSet.remove(node);
		}
		return true;
	}
	
	/*
	 * Uses BFS approach using while loop
	 */
	public boolean hasPathBFS(T src, T dest) {
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		WNode<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		
		LinkedList<WNode<T>> queue = new LinkedList<WNode<T>>();
		queue.add(srcNode);
		HashSet<WNode<T>> visitedSet = new HashSet<WNode<T>>();	
		while (!queue.isEmpty()) {
			WNode<T> node = queue.poll();		
			if (visitedSet.contains(node))
				continue;			
			visitedSet.add(node);			
			LinkedHashSet<WNode<T>> edgeSet = node.getEdges();
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
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		WNode<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		
		LinkedList<WNode<T>> queue = new LinkedList<WNode<T>>();
		queue.add(srcNode);		
		HashSet<WNode<T>> visitedSet = new HashSet<WNode<T>>();
		return hasPathBFS2(queue, visitedSet, destNode);
	}
	
	private boolean hasPathBFS2(LinkedList<WNode<T>> queue, HashSet<WNode<T>> visitedSet, WNode<T> destNode) {
		if (queue.isEmpty())
			return false;
		
		WNode<T> node = queue.poll();			
		if (!visitedSet.contains(node)){
			visitedSet.add(node);			
			LinkedHashSet<WNode<T>> edgeSet = node.getEdges();
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
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return false;
		}		
		WNode<T> destNode = getNode(dest);
		if (destNode == null) {
			return false;
		}
		LinkedList<WNode<T>> visitedList = new LinkedList<WNode<T>>();
		visitedList.add(srcNode);
		return hasPathDFS(visitedList, destNode);
	}
	
	private boolean hasPathDFS(LinkedList<WNode<T>> visitedList, WNode<T> destNode) {		
		WNode<T> node = visitedList.getLast();		
		if (node.equals(destNode)) {
			return true;
		}		
		for (WNode<T> adjNode: node.getEdges()) {
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
	 * Time Complexity: O(|V| + |E|)
	 */
	public LinkedHashMap<LinkedList<T>, Integer> getAllPaths(T src, T dest) {
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return null;
		}
		WNode<T> destNode = getNode(dest);
		if (destNode == null) {
			return null;
		}
		
		// Maintain visited list stack
		LinkedList<WNode<T>> visitedList = new LinkedList<WNode<T>>();
		visitedList.add(srcNode);
		
		LinkedHashMap<LinkedList<T>, Integer> pathsMap = new LinkedHashMap<LinkedList<T>, Integer>();
		getAllPathsDFS(destNode, visitedList, pathsMap);
		visitedList.removeLast();
		return pathsMap;
	}
	
	private boolean getAllPathsDFS(WNode<T> destNode,
								LinkedList<WNode<T>> visitedList,
								LinkedHashMap<LinkedList<T>, Integer> pathsMap) {
		
		WNode<T> currNode = visitedList.getLast();
		// Check we reached destination node or not
		if (currNode.equals(destNode)) {
			return true;
		}

		// Repeat with current node edges
		for (WNode<T> adjNode : currNode.getEdges()) {
			if (!visitedList.contains(adjNode)) {
				visitedList.add(adjNode);
				if (getAllPathsDFS(destNode, visitedList, pathsMap)) {
					copyDataPath(visitedList, pathsMap);
				}
				visitedList.removeLast();
			}
		}
		return false;
	}
		
	public LinkedHashMap<LinkedList<T>, Integer> getShortestPath(T src, T dest) {
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return null;
		}
		
		WNode<T> destNode = getNode(dest);
		if (destNode == null) {
			return null;
		}
		
		LinkedHashMap<LinkedList<T>, Integer> pathsMap = dijkstra(srcNode);
		
		int destIndex = getNodeIndex(destNode);
		
		LinkedHashMap<LinkedList<T>, Integer> shortestPathsMap = new LinkedHashMap<LinkedList<T>, Integer>();
		
		Set<LinkedList<T>> pathSet = pathsMap.keySet();	
		int index = 0;
		for (LinkedList<T> pathList : pathSet) {
			if (index == destIndex) {
				shortestPathsMap.put(pathList, pathsMap.get(pathList));	
			}
			index++;
		}
		return shortestPathsMap;
	}
	
	/*
	 * Dijkstra's algorithm implementation using BFS/Standard Queue
	 * Time Complexity : O (|V| + |E|)
	 */
	public LinkedHashMap<LinkedList<T>, Integer> dijkstra(WNode<T> srcNode) {
		LinkedList<T>[] paths = new LinkedList[mNodeList.size()]; 
		
		Integer [] distances = new Integer[mNodeList.size()];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[0] = 0;
		
		Queue<WNode<T>> queue = new LinkedList<WNode<T>>();
		queue.addAll(mNodeList);
		
		while (!queue.isEmpty()) { // O (V)
			WNode<T> currNode = queue.poll(); // O(1)
			int currIndex = getNodeIndex(currNode); // O(V)
 			for (WNode<T> adjNode : currNode.getEdges()) {  // O(E)				
				int adjIndex = getNodeIndex(adjNode); // O(V)
				int newDist = weight(currNode, adjNode) + distances[currIndex]; // distance currNode->adjNode + startNode->currNode
				if (distances[adjIndex] == Integer.MAX_VALUE || newDist < distances[adjIndex]) {
					distances[adjIndex] = newDist;
					LinkedList<T> path = paths[currIndex] != null ? new LinkedList<T>(paths[currIndex]) : new LinkedList<T>();
					path.add(adjNode.getData());
					paths[adjIndex] = path;
				}
			}
		}
		
		LinkedHashMap<LinkedList<T>, Integer> pathMap = new LinkedHashMap<LinkedList<T>, Integer>();
		for (int i = 0; i < mNodeList.size(); i++) {
			pathMap.put(paths[i], distances[i]);			
		}
		return pathMap;
	}
	
	public LinkedHashMap<LinkedList<T>, Integer> getMinPaths(T src, T dest) {
		LinkedHashMap<LinkedList<T>, Integer> pathsMap = getAllPaths(src, dest);
		LinkedHashMap<LinkedList<T>, Integer> minPathMap = new LinkedHashMap<LinkedList<T>, Integer>();
		int minPathSize = Integer.MAX_VALUE;
		
		Set<LinkedList<T>> pathSet = pathsMap.keySet();
		for (LinkedList<T> pathList : pathSet) {
			int weight = pathsMap.get(pathList);
			if (weight == minPathSize) {
				minPathMap.put(pathList, weight);
			} else if (weight < minPathSize) {
				minPathMap = new LinkedHashMap<LinkedList<T>, Integer> ();
				minPathMap.put(pathList, weight);
				minPathSize = weight;
			}

		}
		return minPathMap;
	}
	
	public LinkedList<T> getShortestPath2(T src, T dest) {
		LinkedHashMap<LinkedList<T>, Integer> pathsMap = getAllPaths(src, dest);
		LinkedList<T> shortestPath = new LinkedList<T>();
		int shortestPathSize = Integer.MAX_VALUE;
		
		Set<LinkedList<T>> pathSet = pathsMap.keySet();
		for (LinkedList<T> pathList : pathSet) {
			int weight = pathsMap.get(pathList);
			if (weight < shortestPathSize) {
				shortestPath = pathList;
				shortestPathSize = weight;
			}			
		}
		return shortestPath;
	}
	
	public int[] shortestReach2(T src) {
		WNode<T> srcNode = getNode(src);
		if (srcNode == null) {
			return null;
		}
		
		int[] distances = new int[mNodeList.size()];
		Arrays.fill(distances, -1);
		distances[0] = 0;
		
		LinkedList<WNode<T>> queue = new LinkedList<WNode<T>>();
		queue.add(srcNode);
		
		int adjIndex;
		int currIndex;
		WNode<T> node;
		while (!queue.isEmpty()) {
			node = queue.poll();
			currIndex = getNodeIndex(node);
 			for (WNode<T> adjNode : node.getEdges()) {
				adjIndex = getNodeIndex(adjNode);
				if (distances[adjIndex] == -1) {
					distances[adjIndex] = distances[currIndex] + weight(node, adjNode);
					queue.add(adjNode);
				} else {
					// We have already calculated/visited distance to this node, check we got any shortest route 
					int newDist = distances[currIndex] + weight(node, adjNode);
					if (newDist < distances[adjIndex]) {
						distances[adjIndex] = newDist;
					}					
				}
			}
		}
		return distances;
	}
	
	private WNode<T> getNode(T data){
		for (WNode<T> node : mNodeList) {
			if (node.getData() == data || node.getData().equals(data)) {
				return node;
			}
		}
		return null;		
	}
	/*
	 * O(V)
	 */
	private int getNodeIndex(WNode<T> src) {
		int index = 0;
		for (WNode<T> node : mNodeList) {
			if (node == src) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	private void copyDataPath(LinkedList<WNode<T>> visitedList, LinkedHashMap<LinkedList<T>, Integer> pathsMap) {
		LinkedList<T> list = new LinkedList<T>();
		int weight = 0;
		WNode<T> prev = null;
		for (WNode<T> node : visitedList) {
			list.add(node.getData());
			weight +=  weight(prev, node);
			prev = node;
		}
		pathsMap.put(list, weight);
	}
	
	private int weight(WNode<T> src, WNode<T> dest) {
		if (src == null || dest == null) {
			return 0;
		}
		
		Iterator<WNode<T>> edgeSetIT = src.getEdges().iterator();
		Iterator<Integer> wtListIT = src.getWeightList().iterator();
		while (edgeSetIT.hasNext() && wtListIT.hasNext()) {
			WNode<T> node = edgeSetIT.next();
			int weight = wtListIT.next();
			if (node == dest) {
				return weight;
			}
		}
		return 0;		
		
	}
}
