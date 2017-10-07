package ds.graph;

import java.util.LinkedList;
import java.util.ArrayList;

/*
 * https://www.youtube.com/watch?v=gXgEDyodOJU&index=38&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P
 * http://www.geeksforgeeks.org/graph-and-its-representations/
 * Space Complexity = O (V + E) where as V - no. of vertices, E - no.of edges
 * Time Complexity for isVerticesConnected() = O (V + E) = O(2*V) constants are omitted in Big-O analysis = O(V)
 * Time Complexity for printAdjacentVertices() = O (V + E) = O(2*V) constants are omitted in Big-O analysis = O(V)
 * 
 */
public class GraphAL<T> {
	
	private ArrayList<T> mVertexList; // why another list to store vertices index? can't we store it vertex directly in mEdgesList?
	                                  // ArrayList<LinkedList<T>> mEdgesList;
	
	private ArrayList<LinkedList<Integer>> mEdgesList; //index value of the vertices
	
	public GraphAL() {
		mVertexList = new ArrayList<T>();
		mEdgesList = new ArrayList<LinkedList<Integer>>();
	}
	
	public int addVertex(T vertex) {
		mVertexList.add(vertex);
		mEdgesList.add(new LinkedList<Integer>());
		return mVertexList.size() - 1; // returns the index of newly added vertex
	}
	
	public boolean addEdge(T startVertex, T endVertex) {
		int startIndex = getVertexIndex(startVertex);
		if(startIndex == -1) {
			return false;
		}
		int endIndex = getVertexIndex(endVertex);
		if(endIndex == -1) {
			return false;
		}
		if (addEdge(startIndex, endIndex)) { // A--B
			return addEdge(endIndex, startIndex); // Also, B--A	
		}
		return false;
	}
	
	public void printAdjacentVertices(T vertex) {
		int vertexIndex = getVertexIndex(vertex); // linear time O(V)
		LinkedList<Integer> edgeList = mEdgesList.get(vertexIndex); //constant time
		System.out.print("Adjacent vertices of vertex " + vertex + " are: ");			
		for (int index : edgeList) { // linear time O(E)
			System.out.print(mVertexList.get(index) + ",");
		}
		System.out.println("");
	}
	
	public void isVerticesConnected(T startVertex, T endVertex) {
		int startIndex = getVertexIndex(startVertex); // linear time O(V)
		int endIndex = getVertexIndex(endVertex); //// linear time O(V)
		LinkedList<Integer> startEdgeList = mEdgesList.get(startIndex);
		System.out.print("Vertices " + startVertex + " and " + endVertex + " are ");
		if (startEdgeList.contains(endIndex)) // linear time O(E)
			System.out.print("connected");
		else
			System.out.print("not connected");				
		System.out.println("");
	}
	
	private boolean addEdge(int startIndex, int endIndex) {
		LinkedList<Integer> edgeList = mEdgesList.get(startIndex);
		return edgeList.add(endIndex);
	}
	
	public void printGraph() {
		LinkedList<Integer> edgeList;
		for (int i = 0; i < mEdgesList.size(); i++) {
			edgeList = mEdgesList.get(i);
			System.out.print("(" + mVertexList.get(i) + ") ");			
			for (int vertexIndex : edgeList) {
				System.out.print(mVertexList.get(vertexIndex) + ",");
			}
			System.out.println("");
		}		
	}
	
	private int getVertexIndex(T vertex) {
		int index = 0;
		for (T vert: mVertexList) {
			if (vert.equals(vertex) || vert == vertex) {
				return index;
			}
			index++;
		}
		return -1;
	}
}
