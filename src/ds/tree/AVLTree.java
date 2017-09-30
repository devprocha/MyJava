package ds.tree;

public class AVLTree {
	Node mRoot;
	
	public boolean insert (int data) {
		if (mRoot == null) {
			mRoot = new Node(data);
			return true;
		}		
		final Node node = insert(mRoot, data);
		if (node != null) {
			fixAVLTree(node);
			return true;
		}
		return false; 
	}
	
	private Node insert (Node root, int data) {
		if (data < root.getData()) {
			// left node
			if (root.getLeft() == null) {
				final Node node = new Node(data);
				node.setParent(root);
				root.setLeft(node);
				return node;
			} else {
				return insert(root.getLeft(), data);
			}
		} else {
			// left node
			if (root.getRight() == null) {
				final Node node = new Node(data);
				node.setParent(root);
				root.setRight(node);
				return node;
			} else {
				return insert(root.getRight(), data);			
			}
		}
	}
	
	private boolean insert (Node root, Node node) {
		if (node == null) {
			return false;
		}
		
		if (node.getData() < root.getData()) {
			// left node
			if (root.getLeft() == null) {
				node.setParent(root);
				root.setLeft((Node)node);
				return true;
			} else {
				return insert(root.getLeft(), node);
			}
		} else {
			// left node
			if (root.getRight() == null) {
				node.setParent(root);
				root.setRight((Node)node);
				return true;
			} else {
				return insert(root.getRight(), node);			
			}
		}
	}
	
	public boolean remove (int data) {
		if (mRoot == null) {
			return false;
		}		
		final Node node = remove(mRoot, data);
		if (node != null) {
			fixAVLTree(node);
			return true;
		}
		return false; 
	}
	
	private Node remove(Node root, int data) {
		if (data == root.getData()) {
			if (root.getRight() == null && root.getLeft() == null) { //no child nodes
				Node parent = ((Node)root).getParent();
				if (parent == null) { // root node
					mRoot = null;
					return root; // do nothing
				} else {
					if (parent.getLeft() == root) {
						parent.setLeft(null);
					} else {
						parent.setRight(null);
					}
					return root;
				}
			}
			if (root.getRight() != null) {
				Node right = (Node)root.getRight();
				right.setParent(((Node)root).getParent());
				if (right.getLeft() == null) {
					right.setLeft(root.getLeft());
					((Node)root.getLeft()).setParent(right);
					return root;
				} else {
					insert(right.getLeft(), root.getLeft());
					return root;
				}				
			} else { // only left child
				Node left = (Node)root.getLeft();
				left.setParent(((Node)root).getParent());
				return root;
			}
		}
		return null;
	}

	private void fixAVLTree(Node node) {
		// Check if tree is violated AVL tree property or not i.e. Load factor should be either -1, 0, +1 
		final int lf = getLoadFactor(node);
		//if (lf == -1 || lf == 0 || lf == 1) {
		if (lf >= -1 && lf <= 1) {
			// do nothing
			return;
		}		
		rotateTree(node);
	}
	
	private int getLoadFactor(Node root) {
		int heightLS = height(root.getLeft());
		int heightRS = height(root.getRight());		
		return (heightLS - heightRS);
	}
	
	private int height(Node root) {
		if (root == null) {			
			return 0;
		}
		int lHeight = height(root.getLeft()); // left subtree
		int rHeight = height(root.getRight()); // right subtree
		
		if (lHeight > rHeight) {
			return lHeight + 1;
		} else {
			return rHeight + 1;
		}
	}
	
	private void rotateTree(Node node) {		
		if (node == null) {
			return;
		}		
		final Node parent = node.getParent();
		if (parent == null) {
			return;
		}
		final Node gParent = parent.getParent();
		if (gParent == null) {
			return;
		}
		boolean rotateByParent = false;
		boolean rotateByGParent = false;
		boolean leftRotate = false;
		
		// Check for LL, RR, LR, RR case
		// if LL, RR --> Rotate by Grand parent
		// if LR, RL --> Rotate by Parent
		if (parent.getLeft() == node) {
			// node is left child of parent
			leftRotate = true;
			if (gParent.getLeft() == parent) {
				// parent is left child of grand parent - LL case
				rotateByGParent = true;
			} else {
				// parent is right child of grand parent  - LR case
				rotateByParent = true;
			}
		} else  {
			// node is right child of parent
			leftRotate = false;
			if (gParent.getRight() == parent) {
				// parent is right child of grand parent - RR case
				rotateByGParent = true;
			} else {
				// parent is left child of grand parent  - RL case
				rotateByParent = true;
			}
		}
		// First rotate tree by parent if it's required
		if (rotateByParent) {
			node = rotateTreeByParent(node, leftRotate);
		} 
		rotateTreeByGParent(node, leftRotate);
	}
	
	private Node rotateTreeByParent(Node node, boolean leftRotate) {
		if (node == null) {
			return null;
		}		
		final Node parent = node.getParent();
		if (parent == null) {
			return null;
		}
		final Node gParent = parent.getParent();
		if (gParent == null) {
			return null;
		}		
		// Rotate tree by parent
		if (leftRotate) {
			node.setParent(gParent);
			gParent.setLeft(node);
			node.setLeft(parent);		
		} else {
			node.setParent(gParent);
			gParent.setRight(node);
			node.setRight(parent);		
		}
		return parent;
	}
	
	private void rotateTreeByGParent(Node node, boolean leftRotate) {
		if (node == null) {
			return;
		}		
		final Node parent = node.getParent();
		if (parent == null) {
			return;
		}
		final Node gParent = parent.getParent();
		if (gParent == null) {
			return;
		}
		final Node ggParent = ((Node)gParent).getParent();			
		// Rotate tree by grand parent
		if (leftRotate) {
			parent.setParent(ggParent);
			if (ggParent != null) {
				ggParent.setRight(parent);
			}
			parent.setLeft(gParent);		
		} else {
			parent.setParent(ggParent);
			if (ggParent != null) {
				ggParent.setLeft(parent);
			}
			parent.setRight(gParent);		
		}
	}
}
