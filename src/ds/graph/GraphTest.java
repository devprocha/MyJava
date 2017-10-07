package ds.graph;

public class GraphTest {
	public static void Test() {
		System.out.println("[Graph]Test Started..");
		TestGraphAL();
		TestGraphAL2();
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
		System.out.println("************************** End TestGraphAL2() *******************");
		System.out.println();
	}

}
