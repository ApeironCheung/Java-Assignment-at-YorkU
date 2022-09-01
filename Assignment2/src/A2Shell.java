public class A2Shell {
	private static boolean less(Comparable i, Comparable j) {return i.compareTo(j) < 0;}
	private static Comparable[] hsort(Comparable[] arr, int index, int gap) {
		if(index >= arr.length) {
			return arr;
		}
			  Comparable temp = arr[index];
			  int j;
			  for (j = index; j >= gap && less(temp, arr[j - gap]); j -= gap)
                  arr[j] = arr[j - gap];
			  arr[j] = temp;
		return hsort(arr, index + 1, gap);
	}
    public static void shellSort(Object[] a) {
    	Comparable[] b = objToComp(a);
		  for (int gap = a.length/2; gap > 0; gap /= 2)
			  hsort(b, gap, gap); 		
		a = compToObj(b, a);
    }
    public static void shellSort(int[] a) {
    	Comparable[] b = intToComp(a);
		shellSort(b);
		a = compToInt(b, a);
    }
    //helper function
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
    private static void toString(int[] a) {
    	for(int i: a) {
    		System.out.print(i + " ");}
    		System.out.println("");	
    }
    private static void toString(Object[] a) {
    	for(Object i: a) {
    		System.out.print(i + " ");
    		}  	
    	System.out.println("");
    }
    public static void main(String[] args) {
    	int i = 7;
		int array[] = new int[i];
		for(int index = 0; index < array.length; index++) {
			array[index] = (int)(Math.random() * 100);
		}
		toString(array);
		shellSort(array);
		toString(array);
    }
}
