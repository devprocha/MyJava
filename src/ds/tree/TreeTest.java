package ds.tree;

import ds.tree.BinarySearchTree.TraversalType;

public class TreeTest {
	
	public static void Test() {
		System.out.println("[Tree]Test Started..");
		TestBST();
		TestBST2();
		System.out.println("[Tree]Test Exited");
	}
	
	private static void TestBST() {
		final BinarySearchTree bst = new BinarySearchTree();
		bst.insert(100);
		bst.insert(50);
		bst.insert(200);
		
		bst.insert(30);
		bst.insert(60);
		bst.insert(300);
		bst.insert(150);
		
		bst.printPaths();
		
		//bst.traverse(TraversalType.INORDER);
		//bst.traverse(TraversalType.PREORDER);
		//bst.traverse(TraversalType.POSTORDER);
		//bst.traverse(TraversalType.LEVELORDER);
		
//		bst.traverse(TraversalType.INORDER);
//		bst.totalNodes();
//		bst.remove(200);		
//		bst.traverse(TraversalType.INORDER);
//		bst.totalNodes();
		
		//bst.contains(60);
		//bst.contains(160);
		
		//bst.height();
		
		//bst.getMax();
		//bst.getMin();
		
		final BinarySearchTree bst2 = new BinarySearchTree();
		bst2.insert(100);
		bst2.insert(50);
		bst2.insert(200);
		
		final BinarySearchTree bst3 = new BinarySearchTree();
		
		boolean subtree = bst3.isSubTree(bst.root(), bst2.root());
		System.out.println("isSubTree(): " + subtree);		
	}
	
	private static void TestBST2() {
		final BinarySearchTree2 bst = new BinarySearchTree2();
		bst.insert(100);
		bst.insert(50);
		bst.insert(200);
		
		bst.insert(30);
		bst.insert(60);
		bst.insert(300);
		bst.insert(150);
		
		bst.printPaths();
		
	}
}
