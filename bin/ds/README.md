
# Data Structures

## Definition

Data structures provides a way to manage/organize/store collections of data so that it can be used efficiently.
## Useful References
https://en.wikipedia.org/wiki/List_of_data_structures

http://www.studytonight.com/data-structures/introduction-to-data-structures

https://www.cs.usfca.edu/~galles/visualization/Algorithms.html

## Data Structures in Java

Java programming language provides a data structures framework called "Java Collections Framework"

## Java Collections Framework

1. Arrays (Static Array)
2. List - Linear data structure
   ..* Array List (Dynamic Array)
     * Linked List
     * Skip List      
  3. Set
     * Hash Set
     * Linked Hash Set
     * Tree Set
     
  4. Map
      * Hash Map
      * Linked Hash Map
      * Tree Map
      
  5. Queue - Queue is a FIFO data structure
  
  6. Hash - http://stackoverflow.com/questions/2974597/hash-table-vs-hash-list-vs-hash-tree
      * Hash Table (a.k.a Hash Map) - Key, Value Pair, It's very generic and useful version of Hash
      * Hash List - Hash List is a list of hash codes calculated on various chunks of data.
  			   	   - E.g.: you split a file in many parts and you calculate a hash code for each part, then you store all of them in a list. Then you can use that list to verify integrity of the data
   - Hash Tree - Hash Tree is similar to a hash list but instead of having a list of hashes you have got a tree, 
                 so every node in the tree is a hash code that is calculated on its children. 
                 Of course leaves will be the data from which you start calculating the hash codes.
          
7) Stack - Stack is a LIFO data structure - http://www.studytonight.com/data-structures/stack-data-structure

8) Tree - Non linear data structure
   a) Binary Tree 
       - In a binary tree, each node has no more than two children, referred to as left and right
       - It's unordered tree        
      		
   b) Binary Search Tree  
    - BST is a "Ordered Binary Tree" in which 'left' child contains a value less than its parent's value and
      the right child contains a value greater than its parent value.
    - It's 'OK' to add 'duplicate' elements however ..?    
    - BST is a "Unbalanced" Binary Tree
        
   c) Red Black Tree
    - RBT is a "Self-balanced Binary Search Tree"    
    
    
9) Heap 
   - Heap is a specialized "Tree" based data structure which satisfies the "Heap" property i.e. 
   - "Heap" Property - The value stored in the parent node is either greater than or equal to or less than or equal to the values stored
      in the children's node.
         
   - Heap is classified in to,
       - "Max-Heap"- Each child of a node has a value less than or equal to it's parent node's value
       - "Min-Heap"- Each child of a node has a value greater than or equal to it's parent node's value
              
   - Heap data structure is implemented as "Complete Binary Tree" called "Binary Heap"
    
   - "Binary Heap" is a "Heap" based data structure which is implemented as Complete Binary Tree  
      
   - "Complete Binary Tree" - All levels of the tree, except possibly the last one (deepest) are fully filled (with two children)
   							- http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
  		
10) Graphs - Non linear data structure

11) Heap
   - Binomial Heap
   - Fibonacci Heap




