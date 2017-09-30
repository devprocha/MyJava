**Tree Data Structure :** 

**Introduction:** The Tree data structure represents the 'hierarchical' tree structure, consisting of set of ***linked nodes***.

https://www.youtube.com/watch?v=oSWTXtMglKE&index=3&list=PLE_MvscCKqIzdWMBSZC2-D8DKNK_TARxq

https://www.youtube.com/watch?v=qH6yxkw0u78&index=25&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P

https://en.wikipedia.org/wiki/Tree_(data_structure)

**Why/when to Use Tree Data Structure?**
1. Quick Search, Insertion, Deletion
2. Hierarchical
    a. File System 
    b. Organizational Structure
    c. Networking

** Terminology**
http://btechsmartclass.com/DS/U3_T1.html
http://typeocaml.com/2014/11/26/height-depth-and-level-of-a-tree/
https://en.wikipedia.org/wiki/Tree_(data_structure)#Terminology

**1. Depth** - Depth of a "Node X" (always referred to Node) is total no.of edges from "Root Node" to given Node "X"
             - Depth of a "Root Node" is always "0"
         
**2. Height** - Height of "Node X" is a total no.of edges from given "Node X" to "Leaf" Node in a "Longest Path"
              - Height of "Tree" is a total no.of edges from "Root Node" to "Leaf" Node in a "Longest Path"
              - Height of "Root Node" = Height of "Tree"
              - Height of Tree with only "Root Node" is always "0"
          
**3. Level** - Level 0 (Root Node), Level 1, Level 2 and so on...
 
**4. Leaf Node** - Node with no children
**5. Left/Right Subtree** - Tree from Left/Right child node of root node to all leaf nodes
**6. Total no.of "Edges"** = Total no. of nodes - 1
          
** Types of Tree Data Structure**

**1. Binary Tree**  
   1. Binary Tree is a specialized tree structure, in which each node has no more than two children referred as left and right child node.
   2. Binary Tree is a most widely/commonly used tree data structure
 
 ** Types of Binary Tree**
   **Balanced/Height-Balanced Tree**
   
     ..* In Balanced tree the height difference between the left and right subtree's of each node is no more than 
         one.

   **Perfect Balanced/Perfect Height-Balanced Tree**
   
     ..* In Perfect Balanced tree the height of the left and right subtree's of each node is same (height difference
         is zero).

   **Self-Balancing Tree**
    
    ..* Tree which automatically maintains it's height "balanced" in the event of item insertions and deletions.
 
   **Full/Proper/Strict Binary Tree**
   
    ..* Each node contains either zero or two child nodes. 
  
   **Complete Binary Tree**
   
    ..* In Complete Binary Tree, each level except for the last level is completely filled, and all nodes in the last
        level are as far left as possible. 
  	` 
   **Perfect Binary Tree**
   
    ..* In Perfect Binary Tree, all nodes are completely filled and all leaves have the same depth or same level.
    ..* Perfect Binary Tree is also a Complete  Binary Tree
   
   **Ordered Binary Tree** - https://www.youtube.com/watch?v=8t4KCaDB108
    
    
   **Symmetric Binary Tree**
   
    ..* In Ordered Binary Tree, subtrees are arranged in a particular order (w.r.t parent's node).
    ..* http://cs.lmu.edu/~ray/notes/orderedtrees/
    
   **Binary Tree Traversal**
   
   			(A)
   			/\
   		   /  \	
         (B)  (C)
     
     https://www.youtube.com/watch?v=9RHO6jU--GU&index=32&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P
     https://www.youtube.com/watch?v=gm8DUJJhmY4&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=34
     https://www.youtube.com/watch?v=86g8jAQug04&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=33
              
   **1. In Order Traversal**
       
      - Is a Breadth-First Traversal
      - Left->Parent->Right (B->A->C)
      - Allows nodes to be printed in order
      
   **2. Pre Order Traversal**
    
      - Is a Breadth-First Traversal
      - Parent->Left->Right (A->B->C)
      
   **3. Post Order Traversal**
   
      - Is a Breadth-First Traversal
      - Left->Right->Parent (B->C->A)
      
   **4. Level Order Traversal** 
     
      - Is a Depth-First Traversal
      - Nodes are visited at each level
      - A (Level 0)-->B, C (Level 1) and so on until Level 'n'
      - http://www.geeksforgeeks.org/level-order-tree-traversal/
        
**2. Binary Search Tree**
  
    ..* BST is a "Ordered Binary Tree" in which 'left' child contains a value less than its parent's value and
        the right child contains a value greater than its parent value.        
    ..* It's 'OK' to add 'duplicate' elements however while removing node not sure which node to delete        
    ..* BST is a "Unbalanced" Binary Tree
    ..* Big O Analysis -
       ----------------------------------
      | Operation  | Average     | Worst |
      | -----------|-------------|-------|
      | Insertion  | O(log (n))  | O(n)  |
      | Deletion   | O(log (n))  | O(n)  |
      | Search     | O(log (n))  | O(n)  |
      ------------------------------------
     ..* References -
       http://pages.cs.wisc.edu/~siff/CS367/Notes/bsts.html
           
**3. Red Black Tree**

     ..* RBT is a "Self- Balanced Binary Search Tree" in which tree's height is automatically "balanced" in the
         event of insertion, deletion
     ..* Properties of RBT,
     		..*1. Node is either Red or Black 
     		..*2. Root node and NULL descendants (leaf node's children are always NULL) are always Black
     		..*3. Red Parent Node can't have Red Child Node
     		
      | Deletion   | O(log (n))  | O(log (n))   |
      | Search     | O(log (n))  | O(log (n))   |
      -------------------------------------------
     ..* How to fix RBT -   
         https://www.youtube.com/watch?v=tJ7niBAhDQI&index=8&t=86s&list=PLE_MvscCKqIzdWMBSZC2-D8DKNK_TARxq
     1. Uncle is Red - Re-Coloring
        * Change GP color to Red
        * Change Parent and Uncle Color to Black
        * Repeat recursively from GP until Root node
     2. Uncle is Black - Tree Rotation
        Case 1: Parent is Left, Child is Right [OR] Parent is Right, Child is Left
             * Tree rotation through parent
             * Node becomes Parent, Parent becomes Left/Right child of node
        Case 2: Parent is Left, Child is Left [OR] Parent is Right, Child is Right
             * Tree rotation through Grand parent
             * Parent moves to top (GP position) and GP become left/right child of parent
             * Switch GP color and Parent Color             

**4. AVL Tree**

		https://www.hackerrank.com/challenges/self-balancing-tree
		
     ..* AVLT is a "Self- Balanced Binary Search Tree" in which tree's height is automatically "balanced" in the 
         event of insertion, deletion
     ..* Properties of AVLT,
     		..1. At any given node, the height difference between left subtree and right subtree is either -1, 0, +1
     		       		
     ..*	https://www.cs.usfca.edu/~galles/visualization/AVLtree.html
     ..* Big O Analysis - n is Total no.of elements in a tree
      ------------------------------------------
      | Operation  | Average     | Worst        |
      | -----------|-------------|--------------|
      | Insertion  | O(log (n))  | O(log (n))   |
      | Deletion   | O(log (n))  | O(log (n))   |
      | Search     | O(log (n))  | O(log (n))   |
      -------------------------------------------
     ..* How to fix AVLT -   
         https://www.youtube.com/watch?v=msU79oNPRJc&list=PLE_MvscCKqIzdWMBSZC2-D8DKNK_TARxq&index=9
     Case 1. Parent-Node is LL (OR) RR
        * Single tree rotation by "Grand parent"
     Case 2. Parent-Node is LR (OR) RL
        * Double tree rotation
          * First Rotation - First Convert to Case(1) - Left/Right rotation by "Parent" for LR/RL respectively
          * Second Rotation - Follow above Case (1)            
    
**5. Heap**
         	     	   
    * Heap is a specialized "Tree" based data structure which additionally satisfies the "Heap" property
    * "Heap" Property - The value stored in the parent node is either greater than or equal to or less than or
       equal to the values stored in the children's node.             
    * Heap data structure is classified in to,
       - "Max-Heap"- Each child of a node has a value less than or equal to it's parent node's value
       - "Min-Heap"- Each child of a node has a value greater than or equal to it's parent node's value              
    * Widely, "Heap" data structure is implemented as "Binary Heap"     
    * "Binary Heap" is a specialized "Complete Binary Tree" data structure which additionally satisfies the
      "Heap" property    
    * References
      https://www.youtube.com/watch?v=c1TpLRyQJ4w&index=10&list=PLE_MvscCKqIzdWMBSZC2-D8DKNK_TARxq&t=16s
      https://www.youtube.com/watch?v=ijfPvX2qYOQ&index=11&list=PLE_MvscCKqIzdWMBSZC2-D8DKNK_TARxq&t=164s
      https://www.youtube.com/watch?v=fJORlbOGm9Y&index=12&list=PLE_MvscCKqIzdWMBSZC2-D8DKNK_TARxq      
      http://quiz.geeksforgeeks.org/binary-heap/     
   							
10) Graphs - Non linear data structure

11) Heap
   - Binomial Heap
   - Fibonacci Heap




