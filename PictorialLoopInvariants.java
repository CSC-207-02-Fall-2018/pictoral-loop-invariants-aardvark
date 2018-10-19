package labwithcili;

/**
 * This class implements partition, select, median, and quicksort. 
 * 	Partition organizes the array such that all the elements that are greater than the pivot 
 * follow the pivot while all of the elements which are less than the pivot proceed the pivot.
 * 	Select determines the index of the kth smallest element in the array. 
 * 	Median determines the median of the numbers in the array.
 * 	QuickSort sorts the elements in the array in a ascending order according to the quicksorting algorithm.  
 * @author Stefan Ilic and Andrew Park
 *
 */
public class PictorialLoopInvariants {
	
	/**
	 * Swaps two int elements in the array.
	 * @param a the array
	 * @param i index of one of the elements to be swapped.
	 * @param j index of the other element to be swapped.
	 * Preconditions: 
	 * 	a must be initialized.
	 *  0 <= i < a.length
	 *  0 <= j < a.length
	 * Postconditions: 
	 * 	the index of the element that was formerly at i is now at j
	 * 	the index of the element that was formerly at j is now at i
	 */
	public void swap(int a[], int i, int j) {
		int temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	}
	
	/**
	 * 	This function rearranges the elements of a between a[left] and a[right], 
	 * so that one array element has moved to the place it would belong in a sorted array.
	 * @param a the array.
	 * @param left the first element of the array to be partitioned.
	 * @param right the last element of the array to be partitioned. 
	 * @return r_spot the position of the array element that has moved to the place 
	 * 		   			it would belong in a sorted array.
	 * Preconditions:
	 * 	a must be initialized.
	 * 	0 <= left < a.length
	 *  0 <= right < a.length
	 * Postconditions:
	 * 	For an arbitrary element a[i] such that i < r_spot, a[i] <= a[r_spot]
	 *  For an arbitrary element a[i] such that i > r_spot, a[i] >= a[r_spot]
	 */
	public int partition(int a[], int left, int right) {
		// first element of the array that we are partitioning after the pivot 
		int l_spot = left + 1;
		// last element of the array that we are partitioning 
		int r_spot = right;

		while (l_spot <= r_spot) {
			if (a[r_spot] > a[left]) {
				r_spot--;
			}
			else if (a[l_spot] < a[left]) {
				l_spot++;
			}
			else {
				swap(a, l_spot, r_spot);
				l_spot++;
				r_spot--;
			}
		}
		swap(a, left, r_spot); //move the pivot into the middle
		return r_spot;
	}
	
	/**
	 * It determines the index of the kth smallest element in the array. 
	 * @param a the array.
	 * @param k the index + 1 of the element that we desire (i.e. the kth smallest element).
	 * @return middle the index of the kth smallest element in the array.
	 * Preconditions:
	 * 	a must be initialized.
	 * 	0 < k <= a.length
	 * Postconditions: 
	 * 	There are k-1 elements in the array that are less than or equal to a[middle]
	 */
	public int select(int a[], int k) {
		return selectHelper(a, k, 0, a.length - 1);
	}
	
	/**
	 * helper (kernel) procedure for select that does what select does but also
	 * keeps track of the interval being considered (See documentation for what select does).
	 * @param a the array.
	 * @param k the index + 1 of the element that we desire (i.e. the kth smallest element).
	 * @param left first element of the interval in which the kth smallest element may be found.
	 * @param right last element of the interval in which the kth smallest element may be found.
	 * @return middle the index of the kth smallest element in the array.
	 * Preconditions:
	 * 	a must be initialized.
	 * 	0 < k <= a.length
	 * 	0 <= left < a.length
	 *  0 <= right < a.length
	 * Postconditions:
	 * 	There are k-1 elements in the array that are less than or equal to a[middle]	
	 */
	public int selectHelper (int a[], int k, int left, int right) {
		int middle = partition(a, left, right);
		if (middle > k - 1) {
			return selectHelper(a, k, left, middle - 1); //recurse on the left
		} 
		else if (middle < k - 1) {
			return selectHelper(a, k, middle + 1, right); //recurse on the right
		}
		else {
			return middle;
		}
	}
	
	/**
	 * It determines the median of the numbers in the array.
	 * @param a the array.
	 * @return median the median of the numbers in the array.
	 * Preconditions:
	 * 	a must be initialized.
	 * Postconditions:
	 * 	The number of elements in the array that are greater than or equal to
	 * 	median is equal to the number of elements in the array that are
	 * 	less than or equal to median.
	 *	If the array has an even number of elements, median is the average of
	 *	 two of the elements in the array.
	 *	If the array has an odd number of elements, median is one of the elements
	 *	 in the array.
	 */
	public double median(int a[]) {
		int i = select(a, ((a.length - 1) / 2) + 1);
		if (a.length % 2 == 0) { //if there are an even number of elements
			return (a[i] + a[i+1]) / 2.0;
		}
		else { //if there are an odd number of elements
			return a[i]; 
		}
	}
	
	/**
	 * It sorts the elements in the array in a ascending order according to the quick sorting algorithm.  
	 * @param a the array.
	 * Preconditions:
	 * 	a must be initialized.
	 * Postconditions:
	 * 	For every index i and j (where 0 <= i < a.length and 0 <= i < a.length and j > i),
	 *   a[i] <= a[j]
	 */
    public void quickSort (int a[]) {
        quicksortKernel (a, 0, a.length-1);
     }	
    
    /**
     * helper (kernel) procedure for quickSort that does what quickSort does but also
	 * keeps track of the interval being considered (See documentation for what quickSort does).
     * @param a the array.
     * @param left first element of the interval that should be sorted
     * @param right last element of the interval that should be sorted
     * Preconditions:
     * 	a must be initialized.
     * 	0 <= left < a.length 
     *  0 <= right < a.length 
     * Postconditions:
     *   For every index i and j (where 0 <= i < a.length and 0 <= i < a.length and j > i),
	 *   a[i] <= a[j]
     */
    public void quicksortKernel (int a[], int left, int right) {
    	//System.out.println("quicksortKernel!");
    	int middle;
    	//System.out.println("left: " +left+ "right: " + right);
		
		if (left < right) {
			middle = partition(a, left, right);
			// recurse over (sort) the left side
			quicksortKernel(a, left, middle - 1);
			// recurse over (sort) the right side
			quicksortKernel(a, middle + 1, right);
		}
    }
	
    /**
     * contains code for testing all of the methods in this class
     */
	public static void main(String[] args) {
		PictorialLoopInvariants ob = new PictorialLoopInvariants();
		int [] arr = {-23, 1, -125, 1, -34, 88, 967, 0, -9999};
		
		/* print inputted array*/
		System.out.print("inputted array: ");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		/* testing partition */
		int left = 4;
		int right = 3;
		
		int output = ob.partition(arr, left, right);
		System.out.println("Partition: " + output);
		
		System.out.print("parititioned array: ");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		/* Testing select */
		int k = 3;
		int kthSmallestPos = ob.select(arr, k);
		System.out.println(k + "th smallest: " + arr[kthSmallestPos]);
		
		/* Testing median */
		double median = ob.median(arr);
		System.out.println("median: " + median); 
		
		/* Testing quicksort*/
		ob.quickSort(arr);
		System.out.print("quicksorted array: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
