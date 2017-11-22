package ds.heap;

import java.util.LinkedList;
import java.util.Queue;

import ds.tree.Node;

/*
 * http://www.geeksforgeeks.org/binary-heap/
 * https://www.cs.usfca.edu/~galles/visualization/Heap.html
 */
public abstract class BinaryHeap {
	private Node mRoot;
	
	public boolean insert(int data) {
		if (mRoot == null) {
			mRoot = new Node(data);
			return true;
		}
		final Node node = insertBFS(mRoot, data);
		if (node != null) {
			heapifyUp(node);
			return true;
		}
		return false;
	}
	
	public boolean remove(int data) {
		if (mRoot == null) {
			return false;
		}		
		final Node node = remove(mRoot, data);
		if (node != null) {
			heapifyDown(node);
			return true;
		}
		return false;
	}
	
	public boolean contains(int data) {
		if (mRoot == null) {
			return false;
		}		
		final Node node = searchDFS(mRoot, data);		
		return node != null ? true : false;
	}
	
	public int getMin() {
		if (mRoot == null) {
			return -1;
		}		
		return mRoot.getData();
	}	
	
	public int extractMin() {
		if (mRoot == null) {
			return -1;
		}		
		final int rootData = mRoot.getData();
		final Node node = remove(mRoot, rootData);
		if (node != null) {
			heapifyDown(node);
			return rootData;
		}
		return -1;
	}
	
	public abstract boolean compare(int parentData, int childData);
	
	private Node insertBFS(Node root, int data) {		
		final Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			final Node node = queue.poll();
			if (node.getLeft() == null) {
				final Node newNode = new Node(data);
				root.setLeft(newNode);
				return newNode;
			} else if (root.getRight() == null) {
				final Node newNode = new Node(data);
				root.setRight(newNode);
				return newNode;
			} else {
				queue.add(node.getLeft());
				queue.add(node.getRight());
			}			
		}
		return null;
	}
	
	private Node remove(Node root, int data) {
		final Node nodeToRemove = searchBFS(root, data);
		if (nodeToRemove == null) {
			return null;
		}
		final Node leafNode = getLastLeafNode(root);
		if (leafNode == null) {
			return null; //something is wrong!
		}
		final Node leafParent = leafNode.getParent();
		if (leafParent == null) {
			return null;
		}		
		if (leafParent.getLeft() == leafNode) {
			leafParent.setLeft(null);
		} else if (leafParent.getRight() == leafNode) {
			leafParent.setRight(null);
		}		
		nodeToRemove.setData(leafNode.getData());
		return nodeToRemove;
	}
	
	private Node searchDFS(Node root, int data) {
		if (root == null) {
			return null;
		}
		if (root.getData() == data) { 
			return root; //found
		}
		
		Node node = searchDFS (root.getLeft(), data);
		if (node == null) {
			node = searchDFS (root.getRight(), data);
		}
		return node;
	}
	
	/*
	 * More efficient compare to searchDFS
	 */
	private Node searchBFS(Node root, int data) {
		final Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			final Node node = queue.poll();
			if (node.getData() == data)
				return node;
			
			if (node.getLeft() != null) {
				queue.add(node.getLeft());	
			}
			if (node.getRight() != null) {
				queue.add(node.getRight());	
			}				
		}
		return null;
	}
	
	private void heapifyUp(Node node) {
		if (node == mRoot) // check for root node
			return; // reached root node - we are done now!
		
		final Node parent =  node.getParent();
		if (compare(node.getData(), parent.getData())) {
			return; // no violation - do nothing
		}
		
		// Violation - Fix the Binary Tree - Switch Parent and Child node
		int parentData = parent.getData();
		parent.setData(node.getData());
		node.setData(parentData);
		heapifyUp(parent); // repeat until tree is fixed		
	}
	
	private void heapifyDown(Node parent) {
		int parentData = parent.getData();
		int smallest = parentData;
		Node nodeToSwap = null;
		
		// find the smallest(compare to parent node) child node to swap
		Node lNode = parent.getLeft();
		if (lNode != null) {
			if (lNode.getData() < smallest) {
				smallest = lNode.getData();
				nodeToSwap = lNode;
			}
		}		
		Node rNode = parent.getRight();
		if (rNode != null) {
			if (rNode.getData() < smallest) {
				smallest = rNode.getData();
				nodeToSwap = rNode;
			}
		}
		
		if (smallest != parentData) {
			parent.setData(nodeToSwap.getData());
			nodeToSwap.setData(parentData);
			heapifyDown(nodeToSwap);
		}
	}
	
	private Node getLastLeafNode(Node root) {
		final Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		Node lastLeafNode = null;
		while (!queue.isEmpty()) {
			lastLeafNode = queue.poll();
			if (lastLeafNode.getLeft() != null) {
				queue.add(lastLeafNode.getLeft());
			}
			if (lastLeafNode.getRight() != null) {
				queue.add(lastLeafNode.getRight());
			}
		}
		return lastLeafNode;
	}	
}
