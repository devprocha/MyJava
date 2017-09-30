package ds.tree;

import ds.tree.RBNode.COLOR;

//http://cs.lmu.edu/~ray/notes/redblacktrees/

public class RedBlackTree {
	RBNode mRoot;
	
	public RBNode getRoot () {
		return mRoot;
	}
	public boolean insert(int data) {
		if (mRoot == null) {
			mRoot = new RBNode(data, COLOR.BLACK, null); // root node is always "Black"
			return true;
		}
		final RBNode node = insert(mRoot, data);
		if (node != null) {
			fixRBT(node);
		}
		return false;
	}
	
	public RBNode insert(RBNode root, int data) {
		if (data < root.getData()) {
			if (root.getLeft() == null) {
				final RBNode node = new RBNode(data, COLOR.RED, root);// newly inserted node is always "Red"
				root.setLeft(node);
				return node;
			} else {
				insert(root.getLeft(), data);
			}
		}else if (data > root.getData()) {
			if (root.getRight() == null) {
				final RBNode node = new RBNode(data, COLOR.RED, root);// newly inserted node is always "Red"
				root.setRight(node);
				return node;
			} else {
				insert(root.getRight(), data);
			}
		}
		return null;
	}
	
	private void fixRBT(RBNode node) {		
		if (node == null) {
			return;
		}		
		final RBNode parent = node.getParent();
		if (parent == null) { // root node
			if (node.getColor() == COLOR.RED) {
				node.setColor(COLOR.BLACK);	
			}
			return;
		}		
		if (node.getColor() == COLOR.RED && parent.getColor() == COLOR.RED) { // child-parent node can't be Red in color		
			if (isUncleRed (node)) { // Case 1 : Uncle is Red
				// Re color Parent, Uncle to Black and GP to Red
				recolorParentUncleGP(node);
			} else { // Case2 : Uncle is Black
				// Rotate tree
				rotateTree(node);			
			}
		}		
	}
	
	private void rotateTree(RBNode node) {
		if (node == null) {
			return;
		}		
		final RBNode parent = node.getParent();
		if (parent == null) {
			return;
		}
		final RBNode gParent = parent.getParent();
		if (gParent == null) {
			return;
		}		
		boolean rotateParent = false;
		boolean rotateGParent = false;
		boolean leftRotate = false;
		if (parent.getLeft() == node) {
			// node is left child of parent
			leftRotate = false; //rotate by right			
			if (gParent.getLeft() == parent) {
				// parent is left child of grand parent
				rotateGParent = true;
			} else {
				// parent is right child of grand parent
				rotateParent = true;
			}
		} else {
			// node is right child of parent
			leftRotate = true; //rotate by left
			if (gParent.getRight() == parent) {
				// parent is right child of grand parent
				rotateGParent = true;
			} else {
				// parent is left child of grand parent
				rotateParent = true;
			}	
		}
		if (rotateParent) {
			rotateTreeByParent(node, leftRotate);
		} else if (rotateGParent) {
			rotateTreeByGrandParent(node, leftRotate);
		}
	}
	
	private void rotateTreeByParent(RBNode node, boolean leftRotate) {
		if (node == null) {
			return;
		}
		// Re color parent to Black
		final RBNode parent = node.getParent();
		if (parent == null) {
			return;
		}
		final RBNode gParent = parent.getParent();
		if (gParent == null) {
			return;
		}
		
		// Move node to parent's spot
		node.setParent(gParent);
		if (leftRotate) {
			gParent.setLeft(node);
		} else {
			gParent.setRight(node);
		}
		
		// Make parent as left/right child of node
		if (leftRotate) {
			node.setLeft(parent);			
		} else {
			node.setRight(parent);
		}
		parent.setParent(node);
		// again rotate tree by grand parent
		rotateTreeByGrandParent(parent, !leftRotate);
	}
	
	private void rotateTreeByGrandParent(RBNode node, boolean leftRotate) {
		if (node == null) {
			return;
		}
		// Re color parent to Black
		final RBNode parent = node.getParent();
		if (parent == null) {
			return;
		}
		final RBNode gParent = parent.getParent();
		if (gParent == null) {
			return;
		}
		
		final RBNode ggParent = gParent.getParent();
		if (ggParent == null) {
			return;
		}
		
		// Move parent to grand parent's spot
		parent.setParent(ggParent);
		if (leftRotate) {
			ggParent.setLeft(parent);
		} else {
			ggParent.setRight(parent);
		}
		
		// Make grand parent as left/right child of node
		if (leftRotate) {
			parent.setLeft(gParent);			
		} else {
			parent.setRight(gParent);
		}
		gParent.setParent(parent);
		
		// switch color between parent and grand parent
		parent.setColor(COLOR.BLACK);
		gParent.setColor(COLOR.RED);
		
		fixRBT(parent); // repeat
	}
	
	private void recolorParentUncleGP(RBNode node) {
		if (node == null) {
			return;
		}
		// Re color parent to Black
		final RBNode parent = node.getParent();
		if (parent == null) { // Root node
			return;
		}
		if (parent.getColor() == COLOR.RED) {
			parent.setColor(COLOR.BLACK);
		}
		// Re color grand parent to Red
		final RBNode gParent = parent.getParent();
		if (gParent == null) {
			return;
		}
		if (gParent.getColor() == COLOR.BLACK) {
			if (gParent.getParent() != null) { // Check for Root node
				gParent.setColor(COLOR.RED);	
			}			
		}		
		// Re color Uncle to Black
		if (gParent.getLeft() == parent) {			
			// Uncle is right node
			gParent.getRight().setColor(COLOR.BLACK);
		} else if (gParent.getRight() == parent) {
			// Uncle is left node
			gParent.getLeft().setColor(COLOR.BLACK);
		}
		fixRBT(gParent); // recurse through GP		
	}
	
	private boolean isUncleRed(RBNode node) {
		if (node == null) {
			return false;
		}
		
		final RBNode parent = node.getParent();
		if (parent == null) {
			return false;
		}
		final RBNode gParent = parent.getParent();
		if (gParent == null) {
			return false;
		}
		if (gParent.getLeft() == null || gParent.getRight() == null) {
			// Uncle is not present	
			return false;
		}
		if (gParent.getLeft() == parent) {
			// Uncle is right node
			if (gParent.getRight().getColor() != COLOR.RED) {
				return false;
			}				
		} else if (gParent.getRight() == parent) {
			// Uncle is left node
			if (gParent.getLeft().getColor() != COLOR.RED) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isRedBlackTree(RBNode root, boolean isRoot) {
		 if (root == null) {
			 return false;
		 }
		 
		 if (root.getLeft() == null && root.getRight() == null) {
			 return true;
		 }
		 
		// 1. Root Node color can't be Red in color
		 if (isRoot && root.getColor() == COLOR.RED) {
			 return false; 
		 }
		 
		 // 2. Red Parent node can't contain Red children
		 boolean isParentChildRed = isParentChildRed(root);
		 if (isParentChildRed) {
			 return false;
		 }
		 
		 // 3. No.of Black node(s) in all the path should be same
		 boolean isBlackHeightBalanced = balancedBlackHeight(root) != -1;
		 if (!isBlackHeightBalanced) {
			 return false;
		 } 
		return true;
	}
	
	private int balancedBlackHeight(RBNode root) {		
		if (root == null) {
			return 1;
		}
		int lHeight = balancedBlackHeight(root.getLeft());
		if (lHeight == -1)
			return -1;
		
		int rHeight = balancedBlackHeight(root.getRight());
		if (rHeight == -1)
			return -1;
		
		
		if (lHeight != rHeight) {
			return -1;
		} else {
			return (root.getColor() == COLOR.BLACK ? lHeight + 1: lHeight);
		}
	}

	private int maxBlackHeight() {
		if (mRoot == null) {			
			return -1;
		}		
		if (mRoot.getLeft() == null && mRoot.getRight() == null) {
			return 0; // only root node
		}
		return maxBlackHeight(mRoot);
	}
	
	private int maxBlackHeight(RBNode root) {
		if (root == null) {			
			return 1;
		}
		int lHeight = maxBlackHeight(root.getLeft()); // left subtree
		int rHeight = maxBlackHeight(root.getRight()); // right subtree
		
		if (lHeight > rHeight) {
			return (root.getColor() == COLOR.BLACK ? lHeight + 1: lHeight);			
		} else {
			return (root.getColor() == COLOR.BLACK ? rHeight + 1: rHeight);	
		}
	}
	
	private boolean isParentChildRed(RBNode root) {
		 if (root == null) {
			 return false;
		 }
		 
		 if (root.getLeft() == null && root.getRight() == null) {
			 return false;
		 }		 
		 if (root.getColor() != COLOR.RED) {
			 boolean isRed = isParentChildRed(root.getLeft());
			 if (isRed) {
				 return true;
			 }
			 return isParentChildRed(root.getRight());			 
		 } else {
			// Red Parent node can't contain Red children
			 if (root.getLeft() != null && root.getLeft().getColor() == COLOR.RED) {
				 return true;				 
			 }
			 if (root.getRight() != null && root.getRight().getColor() == COLOR.RED) {
				 return true;
			 }
			 // Move to left child
			 boolean isRed = isParentChildRed(root.getLeft());
			 if (isRed) {
				 return true;
			 }			 
			 // Move to right child
			 return isParentChildRed(root.getRight());			 
		 } 
	}
	public RBNode getCousin(RBNode root, int data) {
		if (root == null) {
			return null; // TODO
		}		
		if (root.getLeft() == null && root.getRight() == null) {
			return null;
		}
		
		if (data < root.getData()) {
			if (root.getLeft() != null) {
				if (root.getLeft().getData() == data) {
					return root.getRight();// cousin is found
				}
				return getCousin(root.getLeft(), data);
			} else {
				// requested node is not found
				return null;
			}
		} else 	if (data > root.getData()) {
			if (root.getRight() != null) {
				if (root.getRight().getData() == data) {
					return root.getLeft(); // cousin is found
				}
				return getCousin(root.getRight(), data);
			} else {
				// requested node is not found
				return null;
			}
		}
		return null;
	}
}
