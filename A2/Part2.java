/*************************************************************
* Part2 test class worked by: 						         *
*      Pak Hang CHEUNG(218428953) 							 *
*  and Ho  Chun CHAN  (217486846)	                         *					
*                                                        	 *
*************************************************************/
public class Part2 extends A2{
	static int a[];
	static Object b[];
	final static int TimeCountArraySize = 6;
	static long[]intHeapSortTimeCount = new long[TimeCountArraySize];
	static long[]objHeapSortTimeCount = new long[TimeCountArraySize];
	static long[]intShellSortTimeCount = new long[TimeCountArraySize];
	static long[]objShellSortTimeCount = new long[TimeCountArraySize];
	static long[]intMergeSortTimeCount = new long[TimeCountArraySize];
	static long[]objMergeSortTimeCount = new long[TimeCountArraySize];
	
	private static Object[] objArrayCreation(int arraySize) {
		Integer array[] = new Integer[arraySize];
		for(int k = 0; k< array.length; k++) {
			array[k] = (Integer)a[k];
		}
		return array;
	}
	private static int[] intArrayCreation(int arraySize) {
		int array[] = new int[arraySize];
		for(int k = 0; k< array.length; k++) {
			array[k] = (int) (Math.random() * arraySize);
		}
		return array;
	}	
	private static long testCaseTime(int sort, int[] array) throws IllegalAccessException {//1 heapSort 1shellSort 3mergeSort
		int[] copy = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			copy[i] = array[i];
		}		
		long startTime = System.currentTimeMillis();
		switch(sort){
			case 1:
				heapSort(copy);
				break;
			case 2:
				shellSort(copy);
				break;
			case 3:
				mergeSort(copy);
				break;
			default:
				break;
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	private static long testCaseTime(int sort, Object[] array) throws IllegalAccessException {//1 heapSort 1shellSort 3mergeSort
		Object[] copy = new Object[array.length];
		for(int i = 0; i < array.length; i++) {
			copy[i] = array[i];
		}		
		long startTime = System.currentTimeMillis();
		switch(sort){
			case 1:
				heapSort(copy);
				break;
			case 2:
				shellSort(copy);
				break;
			case 3:
				mergeSort(copy);
				break;
			default:
				break;
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	private static void timeCountArrayFilling() throws IllegalAccessException {
		int size = 10;
		for(int i = 0; i < TimeCountArraySize; i++) {
			a = intArrayCreation(size);
			intHeapSortTimeCount[i] = testCaseTime(1, a);
			intShellSortTimeCount[i] = testCaseTime(2, a);
			intMergeSortTimeCount[i] = testCaseTime(3, a);
			b = objArrayCreation(size);
			objHeapSortTimeCount[i] = testCaseTime(1, b);
			objShellSortTimeCount[i] = testCaseTime(2, b);
			objMergeSortTimeCount[i] = testCaseTime(3, b);
			size *= 10;
		}
	}
	private static void printRunningTime() {
		int size = 10;
		String[] n = new String[]{"10  ", "100 ", "1k  ", "10k ", "100k", "1M  "};
		System.out.println("Sorts: \t\t heapSort(int)\t heapSort(obj)\t shellSort(int)\t shellSort(obj)\t mergeSort(int)\t mergeSort(obj)\t");
		for(int i = 0; i < TimeCountArraySize; i++) {
			System.out.print("n = " + n[i]);
			System.out.print("\t\t" + intHeapSortTimeCount[i] + "ms \t\t" + objHeapSortTimeCount[i] + "ms");
			System.out.print("\t\t" + intShellSortTimeCount[i] + "ms \t\t" + objShellSortTimeCount[i] + "ms");
			System.out.println("\t\t" + intMergeSortTimeCount[i] + "ms \t\t" + objMergeSortTimeCount[i] + "ms");
			size *= 10;
			
			if(size > 1000000) {
				break;
			}
		}
	}
	public static void main (String args[]) throws IllegalAccessException {
		timeCountArrayFilling();
		printRunningTime();
		
	}
}
