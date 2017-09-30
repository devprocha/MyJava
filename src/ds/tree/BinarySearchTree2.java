package ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree2 {
	
	private BSTNode mRoot;
	
	public boolean insert(int data) {
		if (mRoot == null) {
			mRoot = new BSTNode(data, null, null, null);
			return true;
		}		
		return insert(data, mRoot);
	}
	
	public boolean contains(int data) {
		if (mRoot == null) {			
			return false;
		}
		return find(data, mRoot) != null;
	}
	
	public boolean remove(int data) {
		if (mRoot == null) {			
			return false;
		}
		return remove(data, mRoot) != null;
	}
	
	public int height() {
		if (mRoot == null) {			
			return 0;
		}
		return height(mRoot);
	}
	
	public int levels() {
		if (mRoot == null) {			
			return 0;
		}
		return height(mRoot) + 1; // level = height + 1
	}
	
	public int size() {
		if (mRoot == null) {			
			return 0;
		}
		return size(mRoot);
	}
	
	public void printPaths() {
		final ArrayList<ArrayList<Integer>> pathList = new ArrayList<ArrayList<Integer>>();
		getPathList(mRoot, pathList, null);
		for (ArrayList<Integer> list: pathList) {
			for (int data: list) {
				System.out.print(data + "-->");
			}
			System.out.println("");
		}
	}
	private boolean insert(int data, BSTNode node) {
		if (data < node.getData()) { //insert at left subtree
			if (node.getLeft() == null) {
				node.setLeft(new BSTNode(data, null, null, node));
				return true;
			} else {
				return insert(data, node.getLeft());
			}
		} else if (data > node.getData()) { //insert at right subtree
			if (node.getRight() == null) {
				node.setRight(new BSTNode(data, null, null, node));
				return true;
			} else {
				return insert(data, node.getRight());
			}		
		}	
		return false; // no duplicates are allowed
	}
	
	private BSTNode find(int data, BSTNode node) {
		if (node.getData() == data) {
			return node;			
		}
		if (data < node.getData()) { //search in left subtree
			if (node.getLeft() != null) {				
				return find(data, node.getLeft());
			}		
		} else if (data > node.getData()) { //search in right subtree
			if (node.getRight() != null) {				
				return find(data, node.getRight());
			}
		}
		return null;
	}
	/*
	 * Three scenarios to consider to remove the element (after search) 
	 * 1) Remove element at the root node
	 *    a) Find the largest or smallest element from the left or right subtree respectively
	 *       and replace root element value with this value
	 *    b) Delete the found element node        
	 * 2) Remove element at the leaf node
	 *    a) Make node has null
	 * 3) Remove element in between
	 *    - Same as step#1
	 * 
	 */
	private BSTNode remove(int data, BSTNode root) {

		// Find the node which we want to delete
		final BSTNode node = find(data, root);
		if (node == null)
			return null;
		
		final BSTNode parent = node.getParent();
		
		// Check if it's a Leaf node - no children
		if (node.getLeft() == null && node.getRight() ==null) {
			if (parent.getLeft() == node) {
				parent.setLeft(null);
			} else { 
				parent.setRight(null);
			}
			return node;
		} else if (node.getLeft() == null || node.getRight() ==null) { // only one child 
			if (node.getLeft() != null) { // has left child
				node.setData(node.getLeft().getData()); // replace node value with child node value
				node.setLeft(null); // remove left child 
			} else { // has right child
				node.setData(node.getRight().getData()); // replace node value with child node value
				node.setRight(null); // remove right child node
			}
		} else { // two children
			// Find node with largest or smallest value either in left or right sub tree respectively
			//final BSTNode LargestNode = findLargestNode(node.getLeft());
			final BSTNode smallestNode = findSmallestNode(node.getRight());
			node.setData(smallestNode.getData()); // replace data of the node to be deleted with the smallest node data
			//remove the smallest node
			remove(smallestNode.getData(), node);
		}		
		return node;
	}
	
	private BSTNode findSmallestNode(BSTNode root) {
		if (root.getLeft() != null) {
			return findSmallestNode(root.getLeft());
		}
		return root;
	}
	
	private BSTNode findLargestNode(BSTNode root) {
		if (root.getRight() != null) {
			return findSmallestNode(root.getRight());
		}
		return root;
	}
	
	/*
	 * Left->Parent-Right
	 */
	private void printInOrder(BSTNode root) {
		// Traverse left sub tree first
		if (root.getLeft() != null) {
			printInOrder(root.getLeft());
		}		
		//Print the value
		System.out.println(root.getData());

		// Traverse the right sub tree
		if (root.getRight() != null) {
			printInOrder(root.getRight());
		}
	}
	
	/*
	 * Parent->Left->Right
	 */
	private void printPreOrder(BSTNode root) {
		//Print the value
		System.out.println(root.getData());

		// Traverse left sub tree first
		if (root.getLeft() != null) {
			printInOrder(root.getLeft());
		}

		// Traverse the right sub tree
		if (root.getRight() != null) {
			printInOrder(root.getRight());
		}
	}
	
	/*
	 * Left->Right->Parent
	 */
	private void printPostOrder(BSTNode root) {
		// Traverse left sub tree first
		if (root.getLeft() != null) {
			printInOrder(root.getLeft());
		}

		// Traverse the right sub tree
		if (root.getRight() != null) {
			printInOrder(root.getRight());
		}
		//Print the value
		System.out.println(root.getData());
	}
	
	/*
	 * Left->Right->Parent
	 */
	private void printLevelOrder() {
		final Queue<BSTNode> queue = new LinkedList<BSTNode>();
		queue.add(mRoot); //enqueue
	}
	
	private void printLevelOrder(Queue<BSTNode> queue) {				
		if (queue.isEmpty()) 
			return; //queue is empty
		
		final BSTNode node = queue.poll(); // dequeue
		
		//Print the node value
		System.out.println(node.getData());
		
		// Put current node children in to queue
		if (node.getLeft() != null) {
			queue.add(node.getLeft()); //enqueue	
		}		
		if (node.getRight() != null) {
			queue.add(node.getRight()); //enqueue	
		}		
		//Repeat for all the levels
		printLevelOrder(queue);		
	}
		
	private int height(BSTNode root) {
		if (root == null)
			return 0;
		
		int lHeight = height(root.getLeft());
		int rHeight = height(root.getRight());		
		return(lHeight > rHeight ? lHeight: rHeight) + 1;
	}
	
	private int size(BSTNode root) {		
		if (root == null)
			return 0;		
		int lSize = size(root.getLeft());
		int rSize = size(root.getRight());
		return (lSize + rSize +1);
	}
	
	private void printLeaves(BSTNode root) {		
		if (root.getLeft() == null && root.getRight() == null) {
			System.out.println(root.getData());
			return;
		}
		
		if (root.getLeft() != null) {
			printLeaves(root.getLeft());
		}		
		if (root.getRight() != null) {
			printLeaves(root.getRight());
		}		
	}
	
	private void getPathList(BSTNode root, ArrayList<ArrayList<Integer>> pathList, ArrayList<Integer> list) {
		final ArrayList<Integer> newList;
		if (list != null) {
			newList = new ArrayList<Integer>(list); // create new list and copy data from old list	
		} else {
			newList = new ArrayList<Integer>(); // create new empty list
		}		
		newList.add(root.getData()); // add new entry in to the list
		
		if (root.getLeft() != null) {
			getPathList(root.getLeft(), pathList, newList);
		}		
		if (root.getRight() != null) {
			getPathList(root.getRight(), pathList, newList);
		}		
		if (root.getLeft() == null && root.getRight() == null) {
			pathList.add(newList);
		}
	}
}
