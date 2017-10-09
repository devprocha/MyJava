package ds.graph;

import java.util.LinkedList;
import java.util.ArrayList;

/*
 * https://www.youtube.com/watch?v=gXgEDyodOJU&index=38&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P
 * http://www.geeksforgeeks.org/graph-and-its-representations/
 * 
 * Space Complexity = O (V + E) where as V - no. of nodes, E - no.of edges
 * Time Complexity for isNodesConnected() = O (V + E) = O(2*V) constants are omitted in Big-O analysis = O(V)
 * Time Complexity for printAdjacentNodes() = O (V + E) = O(2*V) constants are omitted in Big-O analysis = O(V)
 * 
 */
public class GraphAL2<T> {
	
	private ArrayList<LinkedList<T>> mNodeList; //index value of the nodes
	
	public GraphAL2() {
		mNodeList = new ArrayList<LinkedList<T>>();
	}
	
	public int add(T node) {
		LinkedList<T> edgeList = new LinkedList<T>();
		edgeList.add(node);
		mNodeList.add(edgeList);
		return mNodeList.size() - 1; // returns the index of newly added node
	}
	
	public boolean addEdge(T srcNode, T destNode) {
		LinkedList<T> srcEdgeList = mNodeList.get(getNodeIndex(srcNode));
		if(srcEdgeList == null) {
			return false;
		}
		LinkedList<T> destEdgeList = mNodeList.get(getNodeIndex(destNode));
		if(destEdgeList == null) {
			return false;
		}
		if (srcEdgeList.add(destNode)) { // A--B
			return destEdgeList.add(srcNode); // Also, B--A	
		}
		return false;
	}
	
	public void printAdjacentNodes(T srcNode) {
		LinkedList<T> edgeList = mNodeList.get(getNodeIndex(srcNode)); // linear time O(V)
		System.out.print("Adjacent nodes of node " + srcNode + " are: ");			
		for (T node : edgeList) { // linear time O(E)
			if (node != srcNode) {
				System.out.print(node + ",");	
			}			
		}
		System.out.println("");
	}
	
	public void isNodesConnected(T srcNode, T destNode) {
		LinkedList<T> edgeList = mNodeList.get(getNodeIndex(srcNode));  // linear time O(V)
		System.out.print("Nodes " + srcNode + " and " + destNode + " are ");
		if (edgeList.contains(destNode)) // linear time O(E)
			System.out.print("connected");
		else
			System.out.print("not connected");				
		System.out.println("");
	}	
	
	public void printGraph() {
		for (LinkedList<T> edgeList: mNodeList) {
			System.out.print("(" + edgeList.get(0) + ") ");			
			for (T node: edgeList) {
				if (edgeList.get(0) != node) {
					System.out.print(node + ",");	
				}				
			}
			System.out.println("");
		}		
	}
	
	
	private int getNodeIndex(T node) {
		int index = 0;
		for (LinkedList<T> list: mNodeList) {
			T vert = list.get(0); // first node in the list is always source node
			if (vert.equals(node) || vert == node) {
				return index;
			}
			index++;
		}
		return -1;
	}
}
