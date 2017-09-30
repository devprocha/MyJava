Hashing
-------
- Hashing is a technique in which items are stored in a data structure (like array) such a way that it always provides constant time search operation - O(1).
- Hashing makes use of special function called "Hash Function" which generates the integer value (called 'Hash code/key/index') from the given input (called 'key' )which determines the position/location of the given
  item to be stored in a table called "Hash Table" (array).     
 
 For more info - http://interactivepython.org/runestone/static/pythonds/SortSearch/Hashing.html
 
1) Hash Table(HT):- A hash table is a simple 'array' in which items are stored/retrieved using a 'Hashing' technique. 
   
2) Hash table Size:- Size of the Hash Table, Prime number is preferable,
	a) Avoids frequent collisions (generates different hash values for similar keys)  
    b) Distributes item(s) evenly/uniformly in a HT 
    c) Probes all free spots/cells
	
3) Load Factor = Total no.of elements in HT/ HT Size
	a) Chaining - 0.75f
	b) Linear Probing - 0.5f
	c) Quadratic Probing - 0.5f
	d) Double Hashing - 0.5f
	
4) Hash Function:- 
   - Function which generates the hash code/key (array index)
   - Good Hash Function, 
      a) Avoids frequent collisions (generates different hash values for similar keys)  
      b) Distributes item(s) evenly/uniformly in a HT 
      c) Probes all free spots/cells 
   
5) Hash Code/Key:- Array index
 
6) Collision:- More than one key represents same hash code/key

7) Collision Resolution Techniques:- hi(x) = (h(x) + f(i))% HT size    
	a) Separate Chaining (Open Hashing)
		- Forms chain (linked list) from the point of collision		
		- Disadvantage - Remaining free slots(if any) are not utilized instead creates new node (linked list node)
		- f(i) = 0    
	b) Linear Probing (Close Hashing (Open Addressing) ) 
	    - Finds/probes free slot in a HT by moving 'one'spot from the point of collision hence it's called as "Linear"	    
		- f(i) = i where 'i' is no.of attempts   
		- Disadvantage - Forms "Primary Clustering" which reduces the performance (takes longer time to search for free spot) 
		- How to fix Primary Clustering? by using "Quadratic Probing" technique    
	c) Quadratic Probing(Close Hashing (Open Addressing) ) 
	    - Finds/probes free slot in a HT by moving i*i spot(s) from the point of collision so that elements are distributed evenly 
	    - Probes more widely separated cells, instead of those adjacent to the primary hash site
	    - HT size must be always Prime number and LF = 0.5f else probe sequences do not probe all locations in the table	 
		- f(i) = i*i where 'i' is no.of attempts (1, 4, 9, 16, 25 so on)
		- Disadvantage 
			- Forms "Secondary Clustering"
			- Probe sequences do not probe all locations in the table
						
	d) Double Hashing (Close Hashing (Open Addressing))
	   - Double hashing uses two hash functions, one to find the initial location to place the key and a second to determine the size of the jumps in the probe sequence in case of collision
	   - Second hash function h2(x) is used to calculate free spot after collision
	   - h2(x) = R - (x % R) where is 'R' is 'prime number' which is less than the HT size
	   - f(i) = i*h2(x)
	   - Disadvantage - ?? 
	
8) "Primary Clustering" 
	- "Caused by Linear Probing"
	- Items are not distributed evenly fairly in HT, at some point items are densely accumulated at some portion of HT (forming clusters)

9) "Secondary Clustering" 
	- "Caused by Quadratic Probing"
	- Items are placed/distributed far away from the point of collision x, x+1, x+4, x+9, x+16 so on this causes not probing of all free cells 
	- Because elements that hash to the same hash key will always probe the same alternative cells

10) Rehashing - When table gets too full
    - Build another table about twice as big,
    - Compute new hash value for each nondeleted element, insert elements in new table.

11) References 
 - http://stackoverflow.com/questions/730620/how-does-a-hash-table-work
 - http://www.cs.cmu.edu/afs/cs/academic/class/15210-f13/www/lectures/lecture24.pdf
 - http://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
 - http://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable
 - http://faculty.cs.niu.edu/~freedman/340/340notes/340hash.htm
 - http://stackoverflow.com/questions/9124331/meaning-of-open-hashing-and-closed-hashing
 - http://stackoverflow.com/questions/3980117/hash-table-why-size-should-be-prime
 - https://cathyatseneca.gitbooks.io/data-structures-and-algorithms/content/tables/quadratic_probing_and_double_hashing.html 
 - http://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap
 - http://www.cs.rmit.edu.au/online/blackboard/chapter/05/documents/contribute/chapter/05/linear-probing.html
 - http://stackoverflow.com/questions/27742285/what-is-primary-and-secondary-clustering-in-hash 
 - http://www.cs.rmit.edu.au/online/blackboard/chapter/05/documents/contribute/chapter/05/linear-probing.html
 - https://www.youtube.com/watch?v=nEcUS90C4fo
 - https://www.youtube.com/watch?v=CwM-Cxilk4g

 
 

