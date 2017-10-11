package ds.graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphTest {
	public static void Test() {
		System.out.println("[Graph]Test Started..");
		//TestGraphAL();
		//TestGraphAL2();
		TestGraphAL3();
		System.out.println("[Graph]Test Exited");
	}
	
	private static void TestGraphAL() {
		System.out.println();
		System.out.println("************************* Executing TestGraphAL() *******************");
		GraphAL<String> graphAL = new  GraphAL();
		int vertexIndex = graphAL.addVertex("A");
		vertexIndex = graphAL.addVertex("B");
		vertexIndex = graphAL.addVertex("C");
		vertexIndex = graphAL.addVertex("D");

		graphAL.addEdge("A", "B");
		graphAL.addEdge("A", "D");		
		graphAL.addEdge("B", "C");
		graphAL.addEdge("C", "D");	
		
		//graphAL.printGraph();
		graphAL.printAdjacentVertices("A");
		graphAL.printAdjacentVertices("B");
		graphAL.printAdjacentVertices("C");
		graphAL.printAdjacentVertices("D");
		
		graphAL.isVerticesConnected("A", "B");
		graphAL.isVerticesConnected("A", "D");		
		graphAL.isVerticesConnected("B", "C");
		graphAL.isVerticesConnected("C", "D");
		graphAL.isVerticesConnected("B", "A");
		graphAL.isVerticesConnected( "D", "A");		
		graphAL.isVerticesConnected("C", "B");
		graphAL.isVerticesConnected("D", "C");
		
		graphAL.isVerticesConnected("A", "C");
		graphAL.isVerticesConnected("B", "D");
		
		System.out.println("************************* End TestGraphAL() *******************");
		System.out.println();
	}
	
	private static void TestGraphAL2() {
		System.out.println("************************* Executing TestGraphAL2() *******************");
		GraphAL2<String> graphAL = new  GraphAL2();
		int vertexIndex = graphAL.add("A");
		vertexIndex = graphAL.add("B");
		vertexIndex = graphAL.add("C");
		vertexIndex = graphAL.add("D");

		graphAL.addEdge("A", "B");
		graphAL.addEdge("A", "D");		
		graphAL.addEdge("B", "C");
		graphAL.addEdge("C", "D");	
		
		//graphAL.printGraph();
		graphAL.printAdjacentNodes("A");
		graphAL.printAdjacentNodes("B");
		graphAL.printAdjacentNodes("C");
		graphAL.printAdjacentNodes("D");
		
		graphAL.isNodesConnected("A", "B");
		graphAL.isNodesConnected("A", "D");		
		graphAL.isNodesConnected("B", "C");
		graphAL.isNodesConnected("C", "D");
		graphAL.isNodesConnected("B", "A");
		graphAL.isNodesConnected( "D", "A");		
		graphAL.isNodesConnected("C", "B");
		graphAL.isNodesConnected("D", "C");
		
		graphAL.isNodesConnected("A", "C");
		graphAL.isNodesConnected	("B", "D");
		System.out.println("************************** End TestGraphAL2() *******************");
		System.out.println();
	}
	
	private static void TestGraphAL3() {
		System.out.println("************************* Executing TestGraphAL2() *******************");
		GraphAL3<String> graphAL = new GraphAL3();
		boolean status = graphAL.add("A");
		status = graphAL.add("B");
		status = graphAL.add("C");
		status = graphAL.add("D");
		status = graphAL.add("E");
		status = graphAL.add("F");
		status = graphAL.add("G");
		status = graphAL.add("H");

		graphAL.addEdge("A", "B");
		graphAL.addEdge("A", "C");
		graphAL.addEdge("A", "E");
		
		graphAL.addEdge("B", "C");
		graphAL.addEdge("B", "D");
		graphAL.addEdge("B", "E");
		
		graphAL.addEdge("C", "D");
		graphAL.addEdge("C", "F");
		graphAL.addEdge("C", "G");
		
		graphAL.addEdge("D", "E");
		graphAL.addEdge("D", "F");
		
		graphAL.addEdge("E", "F");
		graphAL.addEdge("E", "G");
		
		graphAL.addEdge("F", "G");
		
		graphAL.addEdge("G", "H");
		
//		ArrayList<LinkedList<String>> pathsList = graphAL.getAllPaths("A", "B");
//		if (pathsList != null) {
//			System.out.println("Total no.of paths between nodes A and B = " + pathsList.size());
//			for (LinkedList<String> pathList : pathsList) {
//				for (String node : pathList) {
//					System.out.print(node);
//		            System.out.print(" ");	
//				}			
//	            System.out.println();
//			}	
//		} else {
//			System.out.println("No path(s) found between nodes A and B");
//		}
		
		ArrayList<LinkedList<String>> pathsList = graphAL.getMinPaths("A", "G");
		if (pathsList != null) {
			System.out.println("Minimum path(s) between nodes A and H = " + pathsList.size());
			for (LinkedList<String> pathList : pathsList) {
				for (String node : pathList) {
					System.out.print(node);
		            System.out.print(" ");	
				}			
	            System.out.println();
			}	
		} else {
			System.out.println("No path(s) found between nodes A and B");
		}
		
		pathsList = graphAL.getMaxPaths("A", "G");
		if (pathsList != null) {
			System.out.println("Maximum path(s) between nodes A and H = " + pathsList.size());
			for (LinkedList<String> pathList : pathsList) {
				for (String node : pathList) {
					System.out.print(node);
		            System.out.print(" ");	
				}			
	            System.out.println();
			}	
		} else {
			System.out.println("No path(s) found between nodes A and B");
		}
				
		System.out.println("************************** End TestGraphAL2() *******************");
		System.out.println();
	}

}
