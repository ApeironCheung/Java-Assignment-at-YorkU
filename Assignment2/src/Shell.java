public class Shell {
    private Shell() { }
    private static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n/3) h = 3*h + 1; 
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h); 
            h /= 3;
        }
        assert isSorted(a);
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }
    public static void shellSort(Object[] a) {
		Comparable[] b = new Comparable[a.length];
		for(int i = 0; i < a.length; i++){
			b[i] = (Comparable)a[i];
		}
		Shell.sort(b);
		for(int i = 0; i < a.length; i++){
			a[i] = b[i];
		}
    }
    public static void shellSort(int[] a) {
    	Comparable[]b = new Integer[a.length];
		for(int i = 0; i < a.length; i++) {
			b[i] = (Integer)a[i];
		}
		Shell.sort(b);
		for(int i = 0; i < a.length; i++) {
			a[i] = (int)b[i];
		}			
    }
    // print array 
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
    	int i = (int)(Math.random() * 100);
		int array[] = new int[i];
		for(int index = 0; index < array.length; index++) {
			array[index] = (int)(Math.random() * 100);
		}
		Shell.toString(array);
		Shell.shellSort(array);
		Shell.toString(array);
    }
}

