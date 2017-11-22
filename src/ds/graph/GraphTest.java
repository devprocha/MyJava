package ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

public class GraphTest {
	public static void Test() {
		System.out.println("[Graph]Test Started..");
		//TestGraphAL();
		//TestGraphAL2();
		//TestGraphAL3();
		//TestGraphAL3_Directed();
		TestWGraphAL();
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
		System.out.println("************************* Executing TestGraphAL3() *******************");
		GraphAL3<String> graphAL = new GraphAL3(false);
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
		graphAL.addEdge("A", "D");
		graphAL.addEdge("A", "E");
		
		graphAL.addEdge("B", "C");
		graphAL.addEdge("B", "D");
		graphAL.addEdge("B", "E");
		
		graphAL.addEdge("C", "E");
		graphAL.addEdge("C", "F");
		
		graphAL.addEdge("D", "E");
		graphAL.addEdge("D", "F");
		
		graphAL.addEdge("E", "F");
		graphAL.addEdge("E", "G");
		
		graphAL.addEdge("F", "G");
		
		graphAL.addEdge("G", "H");
		
		boolean hasPath = graphAL.hasPathBFS("A", "H");
		hasPath = graphAL.hasPathBFS("A", "E");
		hasPath = graphAL.hasPathBFS("A", "F");
		
		hasPath = graphAL.hasPathBFS2("A", "H");
		hasPath = graphAL.hasPathBFS2("A", "E");
		hasPath = graphAL.hasPathBFS2("A", "F");
		
		hasPath = graphAL.hasPathDFS("A", "H");
		hasPath = graphAL.hasPathDFS("A", "E");
		hasPath = graphAL.hasPathDFS("A", "F");
		
		ArrayList<LinkedList<String>> pathsList = graphAL.getAllPaths("A", "E");
		if (pathsList != null) {
			System.out.println("Total no.of paths between nodes A and E = " + pathsList.size());
			for (LinkedList<String> pathList : pathsList) {
				for (String node : pathList) {
					System.out.print(node);
		            System.out.print(" ");	
				}			
	            System.out.println();
			}	
		} else {
			System.out.println("No path(s) found between nodes A and E");
		}
		
		ArrayList<LinkedList<String>> pathsList2 = graphAL.getMinPaths("A", "E");
		if (pathsList2 != null) {
			System.out.println("Minimum path(s) between nodes A and E = " + pathsList2.size());
			for (LinkedList<String> pathList : pathsList2) {
				for (String node : pathList) {
					System.out.print(node);
		            System.out.print(" ");	
				}			
	            System.out.println();
			}	
		} else {
			System.out.println("No path(s) found between nodes A and H");
		}
		
		pathsList = graphAL.getMaxPaths("A", "E");
		if (pathsList != null) {
			System.out.println("Maximum path(s) between nodes A and E = " + pathsList.size());
			for (LinkedList<String> pathList : pathsList) {
				for (String node : pathList) {
					System.out.print(node);
		            System.out.print(" ");	
				}			
	            System.out.println();
			}	
		} else {
			System.out.println("No path(s) found between nodes A and H");
		}
				
		System.out.println("************************** End TestGraphAL3() *******************");
		System.out.println();
	}
	
	private static void TestGraphAL3_Directed() {
		System.out.println("************************* Executing TestGraphAL3_Directed() *******************");
		GraphAL3<String> graphAL = new GraphAL3(true);
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
		
		ArrayList<LinkedList<String>> pathsList = graphAL.getAllPaths("A", "H");
		if (pathsList != null) {
			System.out.println("Total no.of paths between nodes A and H = " + pathsList.size());
			for (LinkedList<String> pathList : pathsList) {
				for (String node : pathList) {
					System.out.print(node);
		            System.out.print(" ");	
				}			
	            System.out.println();
			}	
		} else {
			System.out.println("No path(s) found between nodes A and H");
		}
		
		ArrayList<LinkedList<String>> pathsList2 = graphAL.getMinPaths("A", "H");
		if (pathsList2 != null) {
			System.out.println("Minimum path(s) between nodes A and H = " + pathsList2.size());
			for (LinkedList<String> pathList : pathsList2) {
				for (String node : pathList) {
					System.out.print(node);
		            System.out.print(" ");	
				}			
	            System.out.println();
			}	
		} else {
			System.out.println("No path(s) found between nodes A and H");
		}
		
		pathsList = graphAL.getMaxPaths("A", "H");
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
			System.out.println("No path(s) found between nodes A and H");
		}
				
		System.out.println("************************** End TestGraphAL3_Directed() *******************");
		System.out.println();
	}
	
	private static void TestWGraphAL() {
		System.out.println("************************* Executing TestWGraphAL() *******************");
		WGraphAL<String> graphAL = new WGraphAL(false);
		boolean status = graphAL.add("A");
		status = graphAL.add("B");
		status = graphAL.add("C");
		status = graphAL.add("D");
		status = graphAL.add("E");
		status = graphAL.add("F");
		status = graphAL.add("G");
		status = graphAL.add("H");

		graphAL.addEdge("A", "B", 1);
		graphAL.addEdge("A", "C", 10);
		graphAL.addEdge("A", "E", 10);
		
		graphAL.addEdge("B", "C", 1);
		graphAL.addEdge("B", "D", 1);
		graphAL.addEdge("B", "E", 1);
		
		graphAL.addEdge("C", "D", 1);
		graphAL.addEdge("C", "F", 1);
		graphAL.addEdge("C", "G", 1);
		
		graphAL.addEdge("D", "E", 1);
		graphAL.addEdge("D", "F", 1);
		
		graphAL.addEdge("E", "F", 1);
		graphAL.addEdge("E", "G", 1);
		
		graphAL.addEdge("F", "G", 1);
		
		graphAL.addEdge("G", "H", 1);
		
//		LinkedHashMap<LinkedList<String>, Integer> pathsMap = graphAL.getAllPaths("A", "D");
//		if (pathsMap != null) {
//			System.out.println("Total no.of paths between nodes A and D = " + pathsMap.size());
//			Set<LinkedList<String>> pathSet = pathsMap.keySet();
//			for (LinkedList<String> pathList : pathSet) {
//				for (String node : pathList) {
//					System.out.print(node);
//		            System.out.print(" ");
//				}
//				System.out.print("= " + pathsMap.get(pathList));
//	            System.out.println();
//			}	
//		} else {
//			System.out.println("No path(s) found between nodes A and H");
//		}
//		
//		LinkedHashMap<LinkedList<String>, Integer> minPathsMap = graphAL.getShortestPath("A");
//		if (minPathsMap != null) {
//			System.out.println("Shortest path from node A to all other nodes are:");
//			Set<LinkedList<String>> pathSet = minPathsMap.keySet();
//			for (LinkedList<String> pathList : pathSet) {
//				if (pathList != null) {
//					System.out.print("A ");
//					for (String node : pathList) {					
//						System.out.print(node);
//			            System.out.print(" ");
//					}
//					System.out.print("= " + minPathsMap.get(pathList));
//		            System.out.println();	
//				}
//			}	
//		} else {
//			System.out.println("No path(s) found between nodes A and H");
//		}
		
		LinkedHashMap<LinkedList<String>, Integer> pathsMap = graphAL.getShortestPath("A", "F");
		if (pathsMap != null) {
			System.out.println("Total no.of paths between nodes A and H = " + pathsMap.size());
			Set<LinkedList<String>> pathSet = pathsMap.keySet();
			for (LinkedList<String> pathList : pathSet) {
				System.out.print("A ");
				for (String node : pathList) {
					System.out.print(node);
		            System.out.print(" ");
				}
				System.out.print("= " + pathsMap.get(pathList));
	            System.out.println();
			}	
		} else {
			System.out.println("No path(s) found between nodes A and H");
		}
		
//		LinkedList<String> pathList = graphAL.getShortestPath2("A", "H");
//		System.out.println();
//		System.out.print("Shortest path from A to H is: ");
//		for (String node : pathList) {				
//			System.out.print(node);
//            System.out.print(" ");
//		}
//		System.out.println();
//			
//				
//		int[] distances = graphAL.shortestReach2("A");
//		System.out.println();
//		System.out.println("Shortest Reach:" + Arrays.toString(distances));
		
		
					
		System.out.println("************************** End TestWGraphAL() *******************");
		System.out.println();
	}

}
