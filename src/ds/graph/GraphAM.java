package ds.graph;

import java.util.LinkedList;
import java.util.Iterator;

/*
 * http://www.geeksforgeeks.org/graph-and-its-representations/
 */
public class GraphAM<T> {
	
	private LinkedList<T> mList[];
	
	public GraphAM(int vertices) {
		mList = new LinkedList[vertices];		
		for(int i = 0; i < vertices; i++) {
			mList[i] = new LinkedList();
		}
	}
	
	public void addEdge(int vertex, T edge) {
		mList[vertex].add(edge);
	}
	
	public void printGraph() {
		Iterator<T> it;
		T first = null;
		for (int i = 0; i < mList.length; i++) {
			System.out.print("(" + i + ") ");			
			for (T edge : mList[i]) {
				if (first == null) {
					first = edge;
				} else {
					System.out.print(first + "-->" + edge + ", ");
				}
			}
			System.out.println("");
			first = null;
		}		
	}

}
