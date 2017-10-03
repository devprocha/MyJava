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
		final Node node = insert(mRoot, data);
		if (node != null) {
			fixBinaryHeapForInsert(node);
			return true;
		}
		return false;
	}
	
	public boolean remove(int data) {
		if (mRoot == null) {
			return false;
		}		
		final Node node = remove(mRoot, data); // always remove root node - priority queue
		if (node != null) {
			fixBinaryHeapForRemove(node);
			return true;
		}
		return false;
	}
	
	public abstract boolean compare(int parentData, int childData);
	
	private Node insert (Node root, int data) {		
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
	
	private Node remove (Node root, int data) {
		final Node nodeToRemove = search(root, data);
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
	
	private Node search(Node root, int data) {
		if (root == null) {
			return null;
		}
		if (root.getData() == data) {
			return root;
		}	
		Node node = search (root.getLeft(), data);
		if (node == null) {
			node = search (root.getRight(), data);
		}
		return node;
	}
	
	private void fixBinaryHeapForInsert (Node node) {
		final Node parent =  node.getParent();
		if (parent == null) { // root node
			return; // reached root node - we are done now!
		}		
		if (compare(node.getData(), parent.getData())) {
			return; // no violation - do nothing
		}
		
		// Violation - Fix the Binary Tree - Switch Parent and Child node
		int parentData = parent.getData();
		parent.setData(node.getData());
		node.setData(parentData);
		fixBinaryHeapForInsert(parent); // repeat until tree is fixed		
	}
	
	private void fixBinaryHeapForRemove(Node node) {		
		final Node leafNode = getLastLeafNode(mRoot);
		if (leafNode == null) {
			return; // Error: It shouldn't come here
		}		
		node.setData(leafNode.getData());
		
		final Node leafParent =  leafNode.getParent();
		if (leafParent == null) {
			return; // Error: It shouldn't come here
		}
		if (leafParent.getLeft() == leafNode) {
			leafParent.setLeft(null);
		} else {
			leafParent.setRight(null);
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
