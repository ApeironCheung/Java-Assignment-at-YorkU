public class Part2A extends A2{
	static int a[];
	static Object b[];
	static long[]intHeapSortTimeCount = new long[7];
	static long[]objHeapSortTimeCount = new long[7];
	static long[]intShellSortTimeCount = new long[7];
	static long[]objShellSortTimeCount = new long[7];
	static long[]intMergeSortTimeCount = new long[7];
	static long[]objMergeSortTimeCount = new long[7];
	
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
		long startTime = System.nanoTime()/1000000;
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
		long endTime = System.nanoTime()/1000000;
		return startTime - endTime;
	}
	private static long testCaseTime(int sort, Object[] array) throws IllegalAccessException {//1 heapSort 1shellSort 3mergeSort
		Object[] copy = new Object[array.length];
		for(int i = 0; i < array.length; i++) {
			copy[i] = array[i];
		}		
		long startTime = System.nanoTime()/1000000;
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
		long endTime = System.nanoTime()/1000000;
		return endTime - startTime;
	}
	private static void timeCountArrayFilling() throws IllegalAccessException {
		int size = 10;
		for(int i = 0; i < 7; i++) {
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
	private static void printRunningTime(long[] timeCount) {
		int size = 10;
		for(int i = 0; i < timeCount.length; i++) {
			System.out.print(timeCount[i] + "ms \t");
			size *= 10;
		}
		System.out.println("");
	}
	public static void main (String args[]) throws IllegalAccessException {
		timeCountArrayFilling();
		System.out.println("n: \t\t 10 \t 100 \t 1K \t 10K \t 100K \t 1M \t 10M");
		System.out.print("heapSort(int)\t");
		printRunningTime(intHeapSortTimeCount);
		System.out.print("heapSort(obj)\t");
		printRunningTime(objHeapSortTimeCount);
		System.out.print("shellSort(int)\t");
		printRunningTime(intShellSortTimeCount);
		System.out.print("shellSort(obj)\t");
		printRunningTime(objShellSortTimeCount);
		System.out.print("mergeSort(int)\t");
		printRunningTime(intMergeSortTimeCount);
		System.out.print("mergeSort(obj)\t");
		printRunningTime(objMergeSortTimeCount);
		//3. print test case time arrays into table
	}
}
