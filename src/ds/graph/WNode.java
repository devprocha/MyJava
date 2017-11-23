package ds.graph;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/*
 * Node for weighted graph
 */
public class WNode <T> {
	private T mData;
	private LinkedHashSet<WNode<T>> mEdgeSet; // why set? lookup contains() is faster O(1)
	private LinkedList<Integer> mWeightList;
	
	public WNode(T data) {
		mData = data;
		mEdgeSet = new LinkedHashSet<WNode<T>>();
		mWeightList = new LinkedList<Integer>();	
	}
	
	public T getData() {
		return mData;
	}
	
	public void addEdge(WNode<T> node, int weight) {
		mEdgeSet.add(node);
		mWeightList.add(weight);
	}
	
	public LinkedHashSet<WNode<T>> getEdges() {
		return mEdgeSet;
	}
	
	public LinkedList<Integer> getWeightList() {
		return mWeightList;
	}
}
