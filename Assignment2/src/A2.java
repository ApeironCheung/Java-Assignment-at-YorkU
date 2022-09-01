/*************************************************************
* Heap sort and shell sort worked by: 						 *
*      Pak Hang CHEUNG(218428953) 							 *
*  and Ho Chun CHAN   (217486846)							 *
* Reference: 												 *
* lecture slide 09 Priority Queues.pdf p.37-39				 *
*https://www.geeksforgeeks.org/shellsort/ by Rajat Mishra	 *
* https://algs4.cs.princeton.edu/22mergesort/Merge.java.html *
*************************************************************/
public class A2{	
	/*************************************
	*helper functions for different sorts*
	*************************************/
	//swap elements
	private static void swap(int i, int j){
		Comparable temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	//compare elements
	private static boolean less(Comparable i, Comparable j) {return i.compareTo(j) < 0;}
	//print array to view before sort and after sort
	private static void toString(int[] a) {
    	for(int i: a) {
    		System.out.print(i + " ");
    	}
		System.out.println("");
    }
	private static void toString(Object[] a) {
    	for(Object i: a) {
    		System.out.print(i + " ");
    	}
		System.out.println("");
    }
	//converters to fit arrays into sort methods
	private static Comparable[] intToComp(int[]a) {
    	Comparable[]b = new Integer[a.length];
		for(int i = 0; i < a.length; i++) {
			b[i] = (Integer)a[i];
		}
		return b;
	}
	private static Comparable[] objToComp(Object[]a) {
    	Comparable[]b = new Comparable[a.length];
		for(int i = 0; i < a.length; i++) {
			b[i] = (Integer)a[i];
		}
		return b;
	}
	//converters to export sorted arrays 
	private static int[] compToInt(Comparable[] b, int[]a) {
		for(int i = 0; i < a.length; i++) {
			a[i] = (int)b[i];
		}		
		return a;
	}
	private static Object[] compToObj(Comparable[] b, Object[]a) {
		for(int i = 0; i < a.length; i++) {
			a[i] = b[i];
		}		
		return a;
	}
	/**************
	*heapSort here*
	***************/
		private static Comparable[] heap;//the array for sorting elements
		private static int size;//current number of elements in heap		
		
		//indexing: parent, left child, right child
		private static int parent(int j){return(j-1)/2;}
		private static int left(int j){return j*2 +1;}
		private static int right(int j){return j*2 +2;}
		
		//helper function for indexing
		private static boolean hasleft(int j){return left(j) < size;}
		private static boolean hasright(int j){return right(j) < size;}		
		//check and swap elements in heap tree to maintain validity
		private static void upheap(int j){
			while(j>0){
				int p = parent(j);
				if(less(heap[p], heap[j])) break; 
				swap(j,p);
				j = p;
			}
		}
		private static void downheap(int j){
			while(hasleft(j)){
				int leftIndex = left(j);
				int smallChildIndex = leftIndex;
				if(hasright(j)){
					int rightIndex = right(j);
					if(less(heap[rightIndex], heap[leftIndex])){
						smallChildIndex = rightIndex;
					}
				}
				if(less(heap[j], heap[smallChildIndex])){break; }//If j is smaller than small Child Index, J is smaller than both child, no need to continue downheap
				swap(j, smallChildIndex);
				j = smallChildIndex;
			}
		}
		//add elements from array to heap
		private static Comparable insert(Comparable key) throws IllegalAccessException{
			heap[size] = key;
			upheap(size);
			size += 1;
			return key;
		}
		//export elements from heap to array
		private static Comparable removeMin(){
			if(size == 0) return null;
			Comparable answer = heap[0];
			swap(0, size- 1);
			heap[size - 1] = null;
			size -= 1;
			downheap(0);
			return answer;
		}
		//main drivers of heapSort
	public static void heapSort(Object[] a) throws IllegalAccessException{
		//heapify
		heap = new Comparable[a.length];
		size = 0;
		for(int i = 0; i < a.length; i++){
			insert((Comparable) a[i]);
		}
		//unheap
		for(int i = 0; i < a.length; i++){
			a[i] = removeMin();	
		}
	}
	public static void heapSort(int[] a) throws IllegalAccessException{
		Comparable[] b = intToComp(a);
		heapSort(b);
		a = compToInt(b, a);
	}
	/************
	 * shellSort*
	 ************/
	//main driver of shellSort
    public static void shellSort(Object[] a) {
    	Comparable[] b = objToComp(a);
		  for (int gap = a.length/3; gap > 0; gap /= 3)
			  for(int index = gap; index< b.length; index++) {
				  Comparable temp = b[index];
				  int j;
				  for (j = index; j >= gap && less(temp, b[j - gap]); j -= gap)
	                  b[j] = b[j - gap];
				  b[j] = temp;		
			  }
		a = compToObj(b, a);
    }
    //change primitive type to reference type to call main driver
    public static void shellSort(int[] a) {
    	Comparable[] b = intToComp(a);
		shellSort(b);
		a = compToInt(b, a);
    }
    //merge sort: copy from https://algs4.cs.princeton.edu/22mergesort/Merge.java.html
    //Copyright 2000¡V2019, Robert Sedgewick and Kevin Wayne.
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);
    }
    private static void mergeSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    public static void mergeSort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        mergeSort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }
    public static void mergeSort(Object[] a) {
       	Comparable[] b = objToComp(a);
       	mergeSort(b);
   		a = compToObj(b, a);
    }
        public static void mergeSort(int[] a) {
        Comparable[] b = intToComp(a);
       	mergeSort(b);
       	a = compToInt(b, a);
    }
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k]; 
        }
        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                    index[k] = aux[j++];
            else if (j > hi)                     index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else                                 index[k] = aux[i++];
        }
    }
	public static void main (String[] arcs) throws IllegalAccessException{
		//tested here
		long startTime = System.nanoTime();
		System.currentTimeMillis();
		int ARRAYSIZE = 10000000;
		int array[] = new int[ARRAYSIZE];
//		Integer array[] = new Integer[ARRAYSIZE];
		for(int k = 0; k< array.length; k++) {
			array[k] = (int) (Math.random() * ARRAYSIZE);
		}
//		toString(array);
		shellSort(array);
//		toString(array);
		long endTime = System.nanoTime();
		System.out.print((startTime - endTime)/1000000 + " ms");
	}
}