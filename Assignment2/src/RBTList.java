/***********************************************************************************
* Assignment 3 RBTList 															   *
* worked by: Pak Hang CHEUNG(218428953) and Ho Chun CHAN (217486846)		   	   *			     *
* Reference: 												 					   *
* https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/RedBlackBST.java.html *
************************************************************************************/
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class RBTList<E> implements List<E>{

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root; // root of the BST

    // BST helper node data type
    private class Node {
        private E element;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(E element, boolean color, int size) {
            this.element = element;
            this.color = color;
            this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public RBTList() {
    	root = new Node(null, BLACK, 1);    
    }

   /***************************************************************************
    *  Node helper methods.
    ***************************************************************************/
    // is node x red; false if x is null
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    } 
    /**
     * Returns the number of index-value pairs in this symbol table.
     */
    public int size() {//return the size of the whole list
        return size(root);
    }

   /***************************************************************************
    *  Standard BST search.
    ***************************************************************************/

    public E get(int index) {
        return get(root, index);
    }

    // value associated with the given index in subtree rooted at x; null if no such index
    private E get(Node x, int index) {    
    	if(x == null) return null;
    	int leftSize;
    	if (x.left != null)leftSize = x.left.size;//use size of subtree to calculate rank
    	else leftSize = 0;
        if      (leftSize > index) return get(x.left,  index);
        else if (leftSize < index) return get(x.right, index - leftSize - 1); 
        else                      return x.element;
    }

   //Does this symbol table contain the given index?
    private boolean contains(int index) {
        return get(index) != null;
    }

   /***************************************************************************
    *  Red-black tree insertion.
    ***************************************************************************/

    public boolean add(E element) {
    	if(element == null)throw new NoSuchElementException("null element");
    	add(root.size, element);//add at the last, root.size = list size
    	return true;
    }
    
    public void add(int index, E element) {
    	if (index < 0 || index > root.size)throw new IndexOutOfBoundsException();
        if (element == null) {
            remove(index);
            return;
        }
        int rank = root.size;//rank of the root, == root.size if root.right is null
        if (root.right != null)rank = root.size - root.right.size;//right == null && left != null would not happen since every index bigger than the previous one
        root = add(root, index, element, rank -1);//tree rank start from 1 and list index start from 0
        root.color = BLACK;
    }

    // insert the index-value pair in the subtree rooted at h
    private Node add(Node h, int index, E element, int rank) { 
        if (h == null) return new Node(element, RED, 1);
       
    	int rightLeft = 0;//h.right.left.size = 0 if it is null
		if (h.right != null) if(h.right.left != null) rightLeft = h.right.left.size;
    	if (index > rank) h.right = add(h.right, index, element, rank + 1 + rightLeft); 
        else if (index < rank) {
        	int leftRight = 0;//h.left.right.size = 0 if it is null
        	if(h.left != null) if(h.left.right != null)leftRight = h.left.right.size;
        	h.left = add(h.left,  index, element, rank - 1 - leftRight);
        }
        else  {
        	h.right = add(h.right, index + 1, h.element, rank + 1 + rightLeft);
        	h.element = element;
        }

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;//update the size of each node processed recusively
        
        return h;
    }

   /***************************************************************************
    *  Red-black tree deletion.
    ***************************************************************************/
   
    public void clear() {
    	clear(root);
    }
    private void clear(Node x) {
    	x.left = null;
    	x.right = null;
    	x.size = 1;
    	x.element = null;
    	x.color = BLACK;
    }
    
    // delete the index-value pair with the minimum index rooted at h
    private Node deleteMin(Node h) { 
        if (h.left == null)//reach the leaf
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))// both left child and left grandchild are black
            h = moveRedLeft(h);//ensure the route from root to leaves pass through same number of black nodes

        h.left = deleteMin(h.left);//recursive call
        return balance(h);//call rotations to balance
    }


    /**
     * Removes the specified index and its associated value from this symbol table     
     * (if the index is in this symbol table).    
     *
     * @param  index the index
     * @throws IllegalArgumentException if {@code index} is {@code null}
     */
    public E remove(int index) { 
        if (!contains(index)) return null;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        
        int rank = root.size;
        if(root.right != null)rank = root.size - root.right.size;
        root = remove(root, index + 1, rank);
        if (root != null) root.color = BLACK;
        return root.element;
    }

    // delete the index-value pair with the given index rooted at h
    private Node remove(Node h, int index, int rank) { 
        if (index < rank)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            int leftRight = 0;
            if(h.left != null) if(h.left.right != null) leftRight = h.left.right.size;
            h.left = remove(h.left, index, rank - 1 - leftRight);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (index == rank && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (index == rank) {
                Node x = min(h.right);//next of h is min of h.right's subtree
                h.element = x.element;//replace h.element with next one
                h.right = deleteMin(h.right);//delete the next one instead since it is leaf
            }
            else {
            	int rightleft = 0;
            	if(h.right != null) if(h.right.left != null) rightleft = h.right.left.size;
            	h.right = remove(h.right, index, rank + 1 + rightleft);
            }
        }
        return balance(h);
    }

   /***************************************************************************
    *  Red-black tree helper functions.
    ***************************************************************************/

    // make a left-leaning link lean to the right
    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);
        // assert (h != null) && isRed(h.left) &&  !isRed(h.right);  // for insertion only
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        // assert (h != null) && isRed(h.right) && !isRed(h.left);  // for insertion only
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {

        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }


   /***************************************************************************
    *  Ordered symbol table methods.
    ***************************************************************************/

    /**
     * Returns the smallest index in the symbol table.
     */
    // the smallest Node in subtree rooted at x
    private Node min(Node x) { 
        // assert x != null;
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 


    public String toString() {
    	return inOrder(root, "", min(root)) + "\n";	    	
    }    
    private String inOrder(Node node, String result, Node begin)  //in order traversal
    {  
    	if(node.left != null) result = inOrder(node.left, result, begin);
    	if(node != begin) result += node.element + " ";//the element zero is null and will not be printed
    	if(node.right != null) result = inOrder(node.right, result, begin);
    	return result;
    }    
    
    public boolean addAll(Collection<? extends E> c) {
    	throw new UnsupportedOperationException();
    	}
    
    public boolean 	addAll(int index, Collection<? extends E> c) {
    	throw new UnsupportedOperationException();
    	}
    
    public boolean contains(Object o) {
    	throw new UnsupportedOperationException();
    	}

    public boolean containsAll(Collection<?> c) {
    	throw new UnsupportedOperationException();
    	}
    
    public boolean equals(Object o) {
    	throw new UnsupportedOperationException();
    	}
    public boolean isEmpty() {
    	throw new UnsupportedOperationException();
    }
    public int hashCode() {
    	throw new UnsupportedOperationException();
    	}
    
    public int indexOf(Object o) {
    	throw new UnsupportedOperationException();
    	}
    
    public Iterator<E> iterator(){
    	throw new UnsupportedOperationException();
    	}
    
    public int lastIndexOf(Object o){
    	throw new UnsupportedOperationException();
    	}
    
    public ListIterator<E> listIterator(){
    	throw new UnsupportedOperationException();
    	}
    
    public ListIterator<E> listIterator(int index){
    	throw new UnsupportedOperationException();
    	}  
    public boolean remove(Object o){
    	throw new UnsupportedOperationException();
    	}
    
    public boolean removeAll(Collection<?> c){
    	throw new UnsupportedOperationException();
    	}    
    public boolean retainAll(Collection<?> c){
    	throw new UnsupportedOperationException();
    	}
    
    public E set(int index, E element){
    	throw new UnsupportedOperationException();
    	}
     
    public List<E> subList(int fromIndex, int toIndex){
    	throw new UnsupportedOperationException();
    	}
    
    public Object[] toArray(){
    	throw new UnsupportedOperationException();
    	}
    
    public <T> T[] toArray(T[] a){
    	throw new UnsupportedOperationException();
    	}

    public static void main(String[] args) { 
    	RBTList<Integer> testcase1 = new RBTList<Integer>();//test add() works well
    	for(Integer i = 1; i < 20; i++) {
    		testcase1.add(i);
    	}
    	System.out.println(testcase1.toString());
    	testcase1.remove(7);    	
    	System.out.println(testcase1.toString());
    	testcase1.remove(5);    	
    	System.out.println(testcase1.toString());
    }
}


