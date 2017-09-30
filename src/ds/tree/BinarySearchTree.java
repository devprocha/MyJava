package ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/* BST is a Ordered Binary Tree
	* 1) Data Structure: Resizable "Array"
	* 2) Access: "Random Access" based on "index"
	* 3) Iteration Order - Yes (Insertion order)
	* 4) Allows Duplicates - Yes 
	* 5) Allows "Null" entries - Yes (one or more)
	* 6) Thread Safe - No (Throws ConcurrentModificationException)
	* 7) Fail-Fast or Fail-Safe - Fail-Fast
	* 8) Big-O Analysis
	* |------------------------------------|
	* |	 Insert | Delete  | Get  | Find	   |
	* |---------|---------|------|---------|
	* |  O(n) 	|  O(n)   | O(1) | O(n)    |
	* |---------|---------|------|---------|
	* Insert -  Insertion of new element in between the elements requires shifting of following elements
	* Delete - Deletion of element in between the elements requires shifting of following elements
	* http://stackoverflow.com/questions/13288854/arraylist-constant-time-and-linear-time-access    
*/
public class BinarySearchTree {
	
	public enum TraversalType {
		INORDER,
		PREORDER,
		POSTORDER,
		LEVELORDER
	}
	
	public class Node {
		int  mData;
		Node mLeft;
		Node mRight;
		
		Node (int data) {
			mData = data;					
		}	
				
		public Node insert (int data) {			
			if (data < mData) { 				
				if (mLeft == null) {
					mLeft = new Node (data);
					return mLeft;
				} else {
					return mLeft.insert(data);
				}				
			} else if (data > mData) { 				
				if (mRight == null) {
					mRight = new Node (data);
					return mRight;
				} else {
					return mRight.insert(data);
				}
			} else { // data == mData
				System.out.println("Duplicates are not allowed: " + data);
			}
			return null;
		}
		
		/*
		 * Leaves - Easy
		 * Node with only one child - Easy
		 * Node with two child - Hard
		 * Root Node - Tricky 
		 */
		public Node remove(int data) {
			if (data < mData) { 	
				if (mLeft != null) {
					mLeft = mLeft.remove(data);				
				}
			} else if (data > mData) {	
				if (mRight != null) {
					mRight = mRight.remove(data);				
				}
			} else { // found data == mData				
				// Case 1: Leaf Node
				if (mLeft == null && mRight == null) {
					return null;
				}
				// Case 2 : One Child
				else if (mLeft == null || mRight == null) {
					if (mLeft == null) return mRight;
					if (mRight == null) return mLeft;
				}
				// Case 3 : Two children
				else  {
					mData = mRight.findMin(); // Find min node in the right subtree and set it to current node
					mRight = mRight.remove(mData); // remove the min returned by findMin() from right subtree
				}
			}
			return this; // root node is not altered return same root node
		}
			
		public boolean contains(int data) {			
			if (data == mData) {
				return true;
			}		
			if (data < mData) {				
				if (mLeft == null) {
					return false;
				} else {
					return mLeft.contains(data);
				}				
			} else  { // data > mData				
				if (mRight == null) {
					return false;
				} else {
					return mRight.contains(data);
				}
			}
		}
		
		/*
		 * Left->Parent->Right
		 * 1. Traverse the left subtree, i.e., call Inorder(left-subtree)
   		 * 2. Visit the root.
   		 * 3. Traverse the right subtree, i.e., call Inorder(right-subtree)
		 */
		public void printInOrder() {			
			if (this.mLeft != null) {
				this.mLeft.printInOrder();
			}			
			
			System.out.println(this.mData);
			
			if (this.mRight != null) {
				this.mRight.printInOrder();
			}			
		}
		
		/*
		 * Parent->Left->Right
		 * 1. Visit the root.
   		 * 2. Traverse the left subtree, i.e., call Preorder(left-subtree)
         * 3. Traverse the right subtree, i.e., call Preorder(right-subtree) 
		 */
		public void printPreOrder() {			
			System.out.println(mData);
			if (mLeft != null) {
				mLeft.printPreOrder();
			}
			if (mRight != null) {
				mRight.printPreOrder();
			}			
		}
		
		/*
		 * Left->Right->Parent
		 * 1. Traverse the left subtree, i.e., call Inorder(left-subtree)
   		 * 2. Traverse the right subtree, i.e., call Inorder(right-subtree)
   		 * 3. Visit the root.
		 */
		public void printPostOrder() {		
			if (mLeft != null) {
				mLeft.printPostOrder();
			}			
			if (mRight != null) {
				mRight.printPostOrder();
			}
			System.out.println(mData);
		}
		
		/*
		 * http://www.geeksforgeeks.org/level-order-tree-traversal/
		 */
		public void printLevelOrder() {
			// Create empty queue
			final Queue<Node> queue = new LinkedList<Node>();			
			// Enqueue root node in to empty queue
			queue.add(this); 
			
			// Traverse all the nodes in each level
			Node node;
			while (!queue.isEmpty()) {				
				// 1.Dequeue
				node = queue.poll();				
				// 2.Print data
				System.out.println(node.mData);				
				// 3.Enqueue child nodes
				if (node.mLeft != null) {
					queue.add(node.mLeft);
				}
				if (node.mRight != null) {
				    queue.add(node.mRight);
				}
			}			
		}
		
		public void printLeaves() {
			if (mLeft != null) {
				mLeft.printLeaves();
			}
			if (mRight != null) {
				mRight.printLeaves();
			}
			if (mLeft == null && mRight == null) {
				System.out.println(mData);
			}		
		}
		
		public int totalLeavesOrPaths(int count) {
			if (mLeft != null) {
				mLeft.totalLeavesOrPaths(count);
			}
			if (mRight != null) {
				mRight.totalLeavesOrPaths(count);
			}
			if (mLeft == null && mRight == null) {
				count = count + 1;
			}
			return count;
		}	
		
		public void printPaths(String path) {
			if (path == "") {
				path = path + mData;	
			} else {
				path = path + "-->" + mData;
			}
			
			if (mLeft != null) {
				mLeft.printPaths(path);
			}
			if (mRight != null) {
				mRight.printPaths(path);
			}
			if (mLeft == null && mRight == null) {
				System.out.println(path);
			}
		}
		
		public int totalNodes(int count) {			
			if (mLeft != null) {
				count = mLeft.totalNodes(count);
			}
			if (mRight != null) {
				count = mRight.totalNodes(count);
			}
			return count + 1;
		}
				
		public int findMin() {
			if (mLeft == null) {
				return mData;
			} else {
				return mLeft.findMin();
			}			
		}
		
		public int findMax() {
			if (mRight == null) {
				return mData;
			} else {
				return mRight.findMax();
			}
		}
	}
	
	private Node mRoot;	
	public boolean insert (int data) {
		if (mRoot == null) {
			mRoot = new Node(data);
			return true;
		}
		return insert(mRoot, data) != null;		
	}	
	
	private Node insert (Node root, int data) {
		if (root == null) {		
			return null;
		}
		
		if (data < root.mData) {
			if (root.mLeft == null) {
				root.mLeft = new Node(data);
				return root.mLeft;
			} else {
				return insert(root.mLeft, data);	
			}
			
		} else if (data > root.mData) {
			if (root.mRight == null) {
				root.mRight = new Node(data);
				return root.mRight;
			} else {
				return insert(root.mRight, data);	
			}			
		} else {
			System.out.println("Duplicates are not allowed: " + data);
		}		
		return null;		
	}
	
	public boolean remove(int data) {
		if (mRoot == null) {			
			return false;
		}
		boolean removed = mRoot.remove(data) != null;
		System.out.println("remove(): " + removed);
		return removed;
	}
	
	public boolean contains(int data) {
		if (mRoot == null) {			
			return false;
		}
		boolean contains = mRoot.contains(data);
		System.out.println("contains(): " + contains);
		return contains;
	}
	
	public int height() {
		if (mRoot == null) {			
			return -1;
		}		
		if (mRoot.mLeft == null && mRoot.mRight == null) {
			return 0; // only root node
		}
		return height(mRoot);
	}
	
	public int height(Node root) {
		if (root == null) {			
			return 0;
		}
		int lHeight = height(root.mLeft); // left subtree
		int rHeight = height(root.mRight); // right subtree
		
		if (lHeight > rHeight) {
			return lHeight + 1;
		} else {
			return rHeight + 1;
		}
	}
	
	public int depth() {
		return 0;
	}
	
	public int getMax() {
		if (mRoot == null) {
			return -1;
		}
		int max = mRoot.findMax();
		System.out.println("getMax(): " + max);
		return max;		
	}
	
	public int getMin() {
		if (mRoot == null) {			
			return -1;
		}
		int min = mRoot.findMin();
		System.out.println("getMin(): " + min);
		return min;	
	}
	
	public void traverse(TraversalType type) {
		if (mRoot == null) {			
			return;
		}
		switch (type) {
		case INORDER:
			System.out.println("In Order Traversal Start:");
			mRoot.printInOrder();
			System.out.println("In Order Traversal End:");
			break;
		case PREORDER:
			System.out.println("Pre Order Traversal Start:");
			mRoot.printPreOrder();
			System.out.println("Pre Order Traversal End:");
			break;
		case POSTORDER:
			System.out.println("Post Order Traversal Start:");
			mRoot.printPostOrder();
			System.out.println("Post Order Traversal End:");
			break;
		case LEVELORDER:
			System.out.println("Level Order Traversal Start:");
			mRoot.printLevelOrder();
			System.out.println("Level Order Traversal End:");
			break;			
		}			
	}
	
	public int totalNodes() {
		if (mRoot == null) {			
			return 0;
		}
		int nodes = mRoot.totalNodes(0);
		System.out.println("totalNodes(): " + nodes);
		return nodes;	
	}
	
	public static boolean isBinarySearchTree (Node node) {
		if (node == null) {
			return false; // root node can't be null
		}
		
		if (node.mLeft == null  && node.mRight == null) { // leaf node - recursion exit point
			return true;
		}
		
		// Left subtree
		final Node leftNode = node.mLeft;
		if (leftNode != null) {			
			if (leftNode.mData < node.mData) { 
				boolean isBST = isBinarySearchTree(leftNode);
				if (isBST == false) {
					return false;
				}
			} else {
				return false;
			}
		}
		
		// Right subtree
		final Node rightNode = node.mRight;
		if (rightNode != null) {			
			if (rightNode.mData > node.mData) { 
				boolean isBST = isBinarySearchTree(rightNode);
				if (isBST == false) {
					return false;
				}
			} else {
				return false;
			}		
		}	
		return true;
	}
	
	public static boolean isSame (Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		}		
		if (node1 == null || node2 == null) {
			return false;
		}		
		if (node1.mData == node2.mData) {
			if (isSame(node1.mLeft, node2.mLeft)) {
				if (isSame(node1.mRight, node2.mRight)) {
					return true;
				}			
			}
		}
		return false;
	}
	
	public static boolean isLeftRightSubTreeSame(Node node) {
		if (node == null) {
			return false;
		}
		if (node.mLeft == null && node.mRight == null) {
			return true;
		}
		if (node.mLeft == null || node.mRight == null) {
			return false;
		}		
		return isSame(node.mLeft, node.mRight);
	}
	
	public static boolean isSubTree(Node treeNode, Node subtreeNode) {
		if (subtreeNode == null) {
			return true;
		}
		
		if (treeNode == null) {
			return false;
		}
		
		if (isSame(treeNode, subtreeNode)) {
			return true;
		}		
		return isSubTree(treeNode.mLeft, subtreeNode) ||
			   isSubTree(treeNode.mRight, subtreeNode);
	}
	
	public static boolean isMirror(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		}		
		if (node1 == null || node2 == null) {
			return false;
		}		
		if (node1.mData == node2.mData) {
			if (isSame(node1.mLeft, node2.mRight)) {
				if (isSame(node1.mRight, node2.mLeft)) {
					return true;
				}			
			}
		}
		return false;
	}
	
	/*
	 * https://www.youtube.com/watch?v=8t4KCaDB108
	 */
	public static boolean isSymmetricTree(Node node) {
		if (node == null) {
			return false; // root node can't be null
		}		
		if (node.mLeft == null  && node.mRight == null) { // leaf node - recursion exit point
			return true;
		}		
		if (node.mLeft == null  || node.mRight == null) {
			return false;
		}		
		return isMirror (node.mLeft, node.mRight);
	}
	
	public Node root() {		
		return mRoot;
	}	
	
	public void printPaths() {		
		mRoot.printPaths("");
	}
	
	private int findMax(ArrayList<Integer> arrList) {	
		int max = 0;
		for (int i: arrList) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}
}
