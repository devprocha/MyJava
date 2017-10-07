package ds.graph;

import java.util.LinkedList;
import java.util.ArrayList;

/*
 * https://www.youtube.com/watch?v=gXgEDyodOJU&index=38&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P
 * http://www.geeksforgeeks.org/graph-and-its-representations/
 * 
 * Space Complexity = O (V + E) where as V - no. of vertices, E - no.of edges
 * Time Complexity for isVerticesConnected() = O (V + E) = O(2*V) constants are omitted in Big-O analysis = O(V)
 * Time Complexity for printAdjacentVertices() = O (V + E) = O(2*V) constants are omitted in Big-O analysis = O(V)
 * 
 */
public class GraphAL2<T> {
	
	private ArrayList<LinkedList<T>> mEdgesList; //index value of the vertices
	
	public GraphAL2() {
		mEdgesList = new ArrayList<LinkedList<T>>();
	}
	
	public int addVertex(T vertex) {
		LinkedList<T> vertexList = new LinkedList<T>();
		vertexList.add(vertex);
		mEdgesList.add(vertexList);
		return mEdgesList.size() - 1; // returns the index of newly added vertex
	}
	
	public boolean addEdge(T startVertex, T endVertex) {
		LinkedList<T> startVertexList = getVertexEdgeList(startVertex);
		if(startVertexList == null) {
			return false;
		}
		LinkedList<T> endVertexList = getVertexEdgeList(endVertex);
		if(endVertexList == null) {
			return false;
		}
		if (startVertexList.add(endVertex)) { // A--B
			return endVertexList.add(startVertex); // Also, B--A	
		}
		return false;
	}
	
	public void printAdjacentVertices(T vertex) {
		LinkedList<T> startVertexList = getVertexEdgeList(vertex); // linear time O(V)
		System.out.print("Adjacent vertices of vertex " + vertex + " are: ");			
		for (T vert : startVertexList) { // linear time O(E)
			if (vert != vertex) {
				System.out.print(vert + ",");	
			}			
		}
		System.out.println("");
	}
	
	public void isVerticesConnected(T startVertex, T endVertex) {
		LinkedList<T> startVertexList = getVertexEdgeList(startVertex); // linear time O(V)
		System.out.print("Vertices " + startVertex + " and " + endVertex + " are ");
		if (startVertexList.contains(endVertex)) // linear time O(E)
			System.out.print("connected");
		else
			System.out.print("not connected");				
		System.out.println("");
	}
	
	
	public void printGraph() {
		for (LinkedList<T> edgeList: mEdgesList) {
			System.out.print("(" + edgeList.get(0) + ") ");			
			for (T vertex: edgeList) {
				if (edgeList.get(0) != vertex) {
					System.out.print(vertex + ",");	
				}				
			}
			System.out.println("");
		}		
	}
	
	private LinkedList<T> getVertexEdgeList(T vertex) {
		for (LinkedList<T> list: mEdgesList) {
			T vert = list.get(0);
			if (vert.equals(vertex) || vert == vertex) {
				return list;
			}
		}
		return null;
	}
}
