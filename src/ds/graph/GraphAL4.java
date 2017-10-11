package ds.graph;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class GraphAL4 {
    private Map<String, LinkedHashSet<String>> map = new HashMap();

    public void addEdge(String node1, String node2) {
        LinkedHashSet<String> edgeSet = map.get(node1);
        if(edgeSet==null) {
        	edgeSet = new LinkedHashSet();
            map.put(node1, edgeSet);
        }
        edgeSet.add(node2);
    }

    public void addTwoWayVertex(String node1, String node2) {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }

    public boolean isConnected(String node1, String node2) {
        Set edgeSet = map.get(node1);
        if(edgeSet==null) {
            return false;
        }
        return edgeSet.contains(node2);
    }

    public LinkedList<String> getEdgeList(String node) {
        LinkedHashSet<String> edgeSet = map.get(node);
        if(edgeSet==null) {
            return new LinkedList();
        }
        return new LinkedList<String>(edgeSet);
    }
    
      
    private void depthFirst(String end, LinkedList<String> visited) {
        LinkedList<String> edgeList = getEdgeList(visited.getLast());
        // examine adjacent nodes
        for (String node : edgeList) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(end)) {
                visited.add(node);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        
        for (String node : edgeList) {
            if (visited.contains(node) || node.equals(end)) {
                continue;
            }
            visited.addLast(node);
            depthFirst(end, visited);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<String> visited) {
        for (String node : visited) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
    }
}
