import java.io.*;
import java.util.NoSuchElementException;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     * @throws Exception 
     */
    public AnyType findMin( ) 
    {
        if( isEmpty( ) )
        	
        	throw new UnderflowException( );
            
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     * @throws Exception 
     */
    public AnyType findMax( ) throws Exception
    {
        if( isEmpty( ) )
        	throw new UnderflowException( );
        return findMax( root ).element;
    }
    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() 
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
    
    //method to count the number of nodes recursively
    private int nodeCount(BinaryNode<AnyType> root) {
    	
    	//if the tree is empty, also the base case
    	if(root == null) {
    		
    		return 0;
    	} 
    	
    	//else, determine the number of nodes in the left and right of the tree using the height method
    	int total = 1 + nodeCount(root.left) + nodeCount(root.right);
    	return total;
    	
    }

    //method to determine if the BST is full
    private boolean isFull(BinaryNode<AnyType> root) {
    	
    	//if the tree is empty
    	if(root == null) {
    		
    		return true;
    	}
    	
    	//if the root is a leaf
    	if(root.left == null && root.right == null) {
    		
    		return true;
    	}
    	
    	//if the root has only 1 child
    	if((root.left == null && root.right != null) || (root.left != null && root.right == null)) {
    		
    		return false;
    	}
    	
    	//otherwise, check both the left and right of the tree recursively
    	boolean check = isFull(root.left) && isFull(root.right);
    	return check;
    }
    
    //method to determine if the structure of current tree matches another tree
    private boolean compareStructure(BinaryNode<AnyType> r1, BinaryNode<AnyType> r2) {
    	
    	//if both BST trees are empty
    	if(r1 == null && r2 == null) {
    		
    		return true;
    	}
    	
    	//if both BST trees are not empty, compare the trees based on each tree left and right side
    	if(r1 != null && r2 != null) {
    		
    		return compareStructure(r1.left, r2.left) && compareStructure(r1.right, r2.right);
    	}
    	//else
    	return false;
    }
    
    //method to determine if the current tree is identical to another tree
    private boolean equals(BinaryNode<AnyType> r1, BinaryNode<AnyType> r2) {
    	
    	//if both BST trees are empty
    	if(r1 == null && r2 == null) {
    		
    		return true;
    	}
    	
    	//if one BST tree is not empty, but the other is empty
    	if(r1 == null && r2 != null) {
    		
    		return false;
    		
    	} else if( r1 != null && r2 == null) {
    		
    		return false;
    		
    	}
    	
    	//if both BST trees are not empty, compare them by determining if their data is the same
    	//and their left and right sides are equal as well
    	if(r1.element == r2.element && equals(r1.left, r2.left) == true && equals(r1.right, r2.right) == true) {
    		
    		return true;
    		
    	//otherwise, the tree are not identical
    	} else {
    		
    		return false;
    	}
    }
    
    //method that copies the current tree and copies it to make a new copy of that tree
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> root) {
    	
    	//if the tree is empty
    	if(root == null) {
    		
    		return null;
    	}
    	
    	//make a copy of the tree to the new tree
    	BinaryNode<AnyType> copyNode = new BinaryNode<AnyType>(root.element);
    	copyNode.left = copy(root.left);
    	copyNode.right = copy(root.right);
    	return copyNode;
    }
    
    //method that copies the current tree and creates a new copy that is a mirror image of the tree
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> root) {
    	
    	//if the tree is empty
    	if(root == null) {
    		
    		return null;
    	}
    	
    	//create a BST tree that is a mirror from the original tree
    	//left side becomes right in new tree, and right becomes left
    	BinaryNode<AnyType> mirrorCopy = new BinaryNode<AnyType>(root.element);
    	mirrorCopy.right = mirror(root.left);
    	mirrorCopy.left = mirror(root.right);
    	return mirrorCopy;
    }
    
    //method that determines a tree is a mirror image of the current tree
    private boolean isMirror(BinaryNode<AnyType> r1, BinaryNode<AnyType> r2) {
    	
    	//if both trees are empty
    	if(r1 == null && r2 == null) {
    		
    		return true;
    	}
    	//if only one tree is empty, but the other is not
    	if((r1 == null || r2 == null)) {
    		
    		return false;
    	}
    	//else if both are not empty, determine if they are mirror images
    	if(r1.element == r2.element && isMirror(r1.right, r2.left) && isMirror(r1.left, r2.right)){
    		
    		return true;
    	}
    	return false;
    }
    
    //method that performs a single rotation on the node that is out of bounds to the right
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> root) {
    	
    	//if the tree is empty, do not do anything
    	if(root == null) {
    		
    		throw new NoSuchElementException();
    	}
    	
    	//if the tree contains only the root
    	if(root.left == null && root.right == null) {
    		
    		throw new NoSuchElementException();
    	}
    	
    	//else, rotate the tree to the right
    	BinaryNode<AnyType> current = root;
    	BinaryNode<AnyType> next = null;
    	BinaryNode<AnyType> t = null;
    	BinaryNode<AnyType> previous = null;
    	
    	//go through all the left nodes
    	while(current != null) {
    		
    		next = current.left;
    		
    		//swap nodes
    		//right of previous becomes current's left child
    		current.left = t;
    		
    		//current's right child
    		t = current.right;
    		
    		//swap nodes again
    		//current's right child is now previous
    		current.right = previous;
    		
    		previous = current;
    		current = next;
    	}
    	return previous;
    }
    
    //method that performs a single rotation on the node that is out of bounds, but to the left
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> root) {
    	
    	//if the tree is empty, then do nothing
    	if(root == null) {
    		
    		return root;
    	}
    	//if the tree only contains the root, then do nothing
    	if(root.left == null && root.right == null) {
    		
    		return root;
    	}
    	
    	//else, rotate the tree to the left
    	BinaryNode<AnyType> current = root;
    	BinaryNode<AnyType> next = null;
    	BinaryNode<AnyType> t = null;
    	BinaryNode<AnyType> previous = null;
    	
    	//go through all the left nodes
    	while(current != null) {
    		
    		next = current.right;
    		
    		//swap nodes
    		//right of previous becomes current's left child
    		current.right = t;
    		
    		//current's right child
    		t = current.left;
    		
    		//swap nodes again
    		//current's right child is now previous
    		current.left = previous;
    		
    		previous = current;
    		current = next;
    	}
    	return previous;
    }
    
    //method that prints the entire levels of the tree
    private void printLevels() {
    	
    	int height = height(root);
    	
    	for(int i = 1; i<height; i++) {
    	    	
    		//call the method to print each level
    		printLevel(root, i);
    		System.out.print(System.lineSeparator());
    	}
    }
    //method to help print one level of tree
    private void printLevel(BinaryNode<AnyType> root, int l) {
    	
    	//if the tree is empty, do nothing
    	if(root == null) {
    		
    		return;
    	}
    	if(l == 1) {
    		
    		//print the values at the level
    		System.out.println(root.element + " ");
    		
    	} else if(l > 1) {
    		
    		printLevel(root.left, l-1);
    		printLevel(root.right, l-1);
    	}
    }
      /** The tree root. */
    private BinaryNode<AnyType> root;

        // Test program
    public static void main( String [] args ) throws Exception 
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree();
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
        
        //check for the node count method
        BinaryNode<Integer> root = t.root;
        System.out.println(t.nodeCount(root));
        
        //check for the BST is full method
        System.out.println(t.isFull(root));
        
        //check for the BST is the same structure as another tree
        //create a new tree to compare to
        BinarySearchTree<Integer> example = new BinarySearchTree<>();
        int n = 20;
        for(int i = 0; i<n; i++) {
        	example.insert(i);
        }
        BinaryNode<Integer> root2 = example.root;
        System.out.println(t.compareStructure(root, root2));
        
        //check for the equals method
        System.out.println(t.equals(root, root2));
        
        //check for the copy method
        BinaryNode<Integer> newCopy = example.copy(root2);
        example.printTree(newCopy);
        
        //check for the mirror method
        BinaryNode<Integer> mirrorCopy = example.mirror(root2);
        example.printTree(mirrorCopy);
        
        //check for the if two trees are mirror method
        System.out.println(t.isMirror(root, root2));
        
        //check for the method to rotate right method
        BinaryNode<Integer> rotateRight = t.rotateRight(root);
        t.printTree(rotateRight);
        
        //check for the method to rotate to the left method
        BinaryNode<Integer> rotateLeft = t.rotateLeft(root);
        t.printTree(rotateLeft);
        
        //check for method to print all levels of the tree method
        t.printLevels();
        
    }
}