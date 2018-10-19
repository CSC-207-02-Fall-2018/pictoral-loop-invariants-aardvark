package labwithcili;

/**
 * This class implements two approaches to solve the Dutch National Flag Problem.
 * Approach A maintains loop invariant A and approach D maintains the loop invariant D.
 * @author Stefan Ilic and Andrew Park
 *
 */
public class DutchNatFlag {
	
	/* defines color constants */
	enum color { 
		red, white, blue 
	};
	
	/**
	 * Swaps two color elements in the array.
	 * @param a the array.
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
	public void swap(color a[], int i, int j) {
		color temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	}
	
	/**
	 * Sorts the array in the order of colors of the Dutch National Flag
	 * (moving left to right, red then white then blue) with 
	 * Loop Invariant A
	 * @param a the array
	 * Preconditions:
	 * 	a must be be initialized.
	 * 	every element of array must be either red, blue, or white.
	 * Postconditions:
	 *  Assuming there are red, white, and blue elements in the array:
	 *   No white or blue elements precede red elements
	 *   No blue elements precede white elements
	 *   Only one red element is adjacent to a white element, 
	 *    the rest are adjacent only to other red elements
	 *   Only one white element is adjacent to a red element and
	 *    only one white element is adjacent to a blue element,
	 *    the rest are adjacent only to other white elements
	 *   Only one blue element is adjacent to a white element,
	 *    the rest are adjacent only to other blue elements
	 *  If there are no elements of a specific color in the array,
	 *    the rules above for the other colors that do not depend on the missing color
	 *    still apply (the other colors are still clustered and ordered according to color)
	 *  If a color that is neither red, nor white, nor blue is in the array, an error is printed
	 */
	public void approachA (color a[]) {
		int w1 = 0;
		int b1 = 0;
		int u1 = 0;
		
		while (u1 < a.length) {
			if (a[u1] == color.blue) {
				u1++;
			}
			else if (a[u1] == color.white) {
				swap(a, u1, b1);
				u1++;
				b1++;
			}
			else if (a[u1] == color.red) {
				swap(a, u1, w1);
				if (b1 != w1) {
					swap(a, u1, b1);
				}
				u1++;
				b1++;
				w1++;
			}
			else {
				System.err.println("Not a valid color");
			}
			
		}
	}
	
	/**
	 * Sorts the array in the order of colors of the Dutch National Flag
	 * (moving left to right, red then white then blue) with 
	 * Loop Invariant D
	 * @param a a the array
	 * Preconditions:
	 * 	a must be be initialized.
	 * 	every element of array must be either red, blue, or white.
	 * Postconditions:
	 *  Assuming there are red, white, and blue elements in the array:
	 *   No white or blue elements precede red elements
	 *   No blue elements precede white elements
	 *   Only one red element is adjacent to a white element, 
	 *    the rest are adjacent only to other red elements
	 *   Only one white element is adjacent to a red element and
	 *    only one white element is adjacent to a blue element,
	 *    the rest are adjacent only to other white elements
	 *   Only one blue element is adjacent to a white element,
	 *    the rest are adjacent only to other blue elements
	 *  If there are no elements of a specific color in the array,
	 *    the rules above for the other colors that do not depend on the missing color
	 *    still apply (the other colors are still clustered and ordered according to color)
	 *  If a color that is neither red, nor white, nor blue is in the array, an error is printed
	 */
	public void approachD (color a[]) {
		int u1 = a.length - 1;
		int r1 = a.length - 1;
		int w1 = a.length - 1;
		
		while (u1 >= 0) {
			if (a[u1] == color.red) {
				u1--;
			}
			else if (a[u1] == color.white) {
				swap(a, u1, r1);
				u1--;
				r1--;
			}
			else if (a[u1] == color.blue) {
				swap(a, u1, w1);
				if (r1 != w1) {
					swap(a, u1, r1);
				}
				u1--;
				r1--;
				w1--;
			}
			else {
				System.err.println("Not a valid color");
			}
		}
	}
	
	/**
	 * sets up an array with mixed red, blue, and white elements and
	 * orders them to look like the Dutch National Flag using one of 
	 * the approaches (used primarily for testing)
	 */
	public static void main (String arg[]) {
		final int size = 9; /* number of elements in an array */
		DutchNatFlag ob = new DutchNatFlag();
		
		color clrs[] = new color [size];
		int i;
		/* One way of mixing the colors */
		for (i = 0; i < size; i++) {
			if ( (i %  3) == 0) {
				clrs[i] = color.red;
			}
			else if ((i % 3) - 1 == 0) {
				clrs[i] = color.blue;
			}
			else {
				clrs[i] = color.white;
			}
		}
		
		
		/* Another way of mixing the colors 
		 * (all the permutations of the color groups can be created
		 *  with this code)
		 */
		/*for (i = 0; i < size; i++) {
			if (i < size/3) {
				clrs[i] = color.white;
			}
			else if (i > size - size/3) {
				clrs[i] = color.red;
			}
			else {
				clrs[i] = color.blue;
			}
		} */
		
		/* printing original array */
		for (i = 0; i < size; i++) {
			System.out.print(clrs[i] + " ");
		}	
		System.out.println();
		
		/* sorting */
		ob.approachA(clrs);
		//ob.approachD(clrs);
		
		/* printing sorted array */
		for (i = 0; i < size; i++) {
			System.out.print(clrs[i] + " ");
		}	
	}
}
