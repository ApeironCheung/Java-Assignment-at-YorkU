

public class testing {
	private static <T extends Comparable<T>> void heapify(T[] heap, int size, int i) {
        int largest = i;
        final int left = 2 * i + 1;
        final int right = 2 * i + 2;
        
        for (int candidate : new int[]{ left, right })
            if (candidate < size && heap[candidate].compareTo(heap[i]) > 0)
                largest = candidate;
        
        if (largest == i)
            return;
        
        // largest is not i
        T tmp = heap[i];
        heap[i] = heap[largest];
        heap[largest] = tmp;
        
        // recursively apply heapify on the affected subtree
        heapify(heap, size, largest);
    }
    
    // max heap
    // 1. a complete binary tree (all nodes should be filled top-to-bottom and left-to-right
    // 2. each node should be no less than the value of its children
    private static <T extends Comparable<T>> void heapSortA(T[] heap) {
        for (int i = heap.length / 2 - 1; i >= 0; --i)
            heapify(heap, heap.length, i);

        // the heap size is array.length
        // extract the maximum into heap[size] (out of the heap) 
        for (int size = heap.length - 1; size >= 0; --size) {
            T max = heap[0];
            heap[0] = heap[size];
            heap[size] = max;
            
            heapify(heap, size, 0);
        }
        // when the heap size becomes 0, the array is sorted in non-descending order
    }
	public static void heapSort(Object[] a) throws IllegalAccessException{
		heapSortA(a);
		}
		for(int i = 0; i < a.length; i++){
			a[i] = removeMin();
		}
	}
	public static void heapSort(int[] a) throws IllegalAccessException{
		Integer[]b = new Integer[a.length];
		for(int i = 0; i < a.length; i++) {
			b[i] = (Integer)a[i];
		}
		heapSort(b);
		for(int i = 0; i < a.length; i++) {
			a[i] = (int)b[i];
		}			
	}
    
	public static void main (String[] arcs) throws IllegalAccessException{
		int i = (int)(Math.random() * 100);
		int array[] = new int[i];
		for(int index = 0; index < i; index++) {
			array[index] = (int)(Math.random() * i -1);
			System.out.print(array[index] + " ;");
		}
		System.out.println("");
		heapSort(array);
		for(int index = 0; index < array.length; index++) {
			System.out.print(array[index] + " ;");
		}
	}
}
