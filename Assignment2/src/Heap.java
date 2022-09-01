public class Heap {

    // This class should not be instantiated.
    private Heap() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    protected static void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

   /***************************************************************************
    * Helper functions to restore the heap invariant.
    ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

   /***************************************************************************
    * Helper functions for comparisons and swaps.
    * Indices are "off-by-one" to support 1-based indexing.
    ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // print array   
    public static void heapSort(Object[] a) {
		Comparable[] b = new Comparable[a.length];
		for(int i = 0; i < a.length; i++){
			b[i] = (Comparable)a[i];
		}
		Heap.sort(b);
		for(int i = 0; i < a.length; i++){
			a[i] = b[i];
		}
    }
    public static void heapSort(int[] a) {
    	Comparable[]b = new Integer[a.length];
		for(int i = 0; i < a.length; i++) {
			b[i] = (Integer)a[i];
		}
		Heap.sort(b);
		for(int i = 0; i < a.length; i++) {
			a[i] = (int)b[i];
		}			
    }
    protected static void toString(int[] a) {
    	for(int i: a) {
    		System.out.print(i + " ");}
    		System.out.println("");	
    }
    protected static void toString(Object[] a) {
    	for(Object i: a) {
    		System.out.print(i + " ");
    		}  	
    	System.out.println("");
    }
    public static void main(String[] args) {
    	int i = (int)(Math.random() * 100);
		int array[] = new int[i];
		for(int index = 0; index < array.length; index++) {
			array[index] = (int)(Math.random() * 100);
		}
		Heap.toString(array);
		Heap.heapSort(array);
		Heap.toString(array);
    }
}