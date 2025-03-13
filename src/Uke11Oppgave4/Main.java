package Uke11Oppgave4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/*
 * binary search class for array of Integer
 * methods are made member functions to mimic how
 * HashSet.contains() is called
 */

class IntegerArrayBinarySearch{
	protected Integer[] data;
	protected int size;
	
	public IntegerArrayBinarySearch(Integer[] arr) {
		data = arr;
		size = arr.length;
	}
	
	public boolean contains(Integer val) {
		int left = 0;
		int right = size - 1;
		int mid = 0;
		Integer data[] = this.data;		
		
		while (left <= right) {
			mid = (left + right) / 2;
			
			if (data[mid].equals(val)) {
				return true;
			}
			
			if (data[mid].compareTo(val) < 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return false;
	}	
}

public class Main {	
	static Set<Integer> intHashSet;
	static IntegerArrayBinarySearch intBinSearch;
	static Integer[] randValueArray;

	
	/*
	 * convert integer to snake string
	 */
	
	public static String int2SnakeString(int number) {
		String s = String.valueOf(number);
		return s.replaceAll("\\B(?=(?:\\d{3})+(?!\\d))", "_");
	}
	
	
	/*
	 * initialize array and set with same random unique values
	 */
	
	public static void init(int numElements, int numElementsToSearchFor) {
		// use reference to Integer[] since intBinSearch is not indexable
		Integer intArray[] = new Integer[numElements];
		
		// create arrays & sets
		intBinSearch = new IntegerArrayBinarySearch(intArray);
		intHashSet = new HashSet<Integer>();
		randValueArray = new Integer[numElementsToSearchFor];
		
		// populate set & array
		int tall = 376; // Her kan vi bruke eit vilkårleg tal
		for (int i = 0; i < numElements; i++){
			//legg tall til i HashSet og tabell			
			intArray[i] = tall; //legg til i tabell
			intHashSet.add(tall);
			
			tall = (tall + 45713) % 1000000; // Sjå nedanfor om 45713
		}
		
		// sort array
		Arrays.sort(intArray);
		
		// generate random values from array
		for (int i = 0; i < numElementsToSearchFor; i++) {
			int randIndex = (int) (Math.random() * numElements);
			randValueArray[i] = intArray[randIndex];
		}
		
		//System.out.println("initialized, num array vals: " + intArray.length + ", num set vals: " + intHashSet.size() + ", num search vals: " + randValueArray.length);
	}
	
	
	
	/*
	 * actual tests, count values to ensure its not optimized away
     * and so we can test for correctness
	 */
	
	public static int findCountHashSetEntries(Integer[] needleArray) {
		int found = 0;
		int n = needleArray.length;
		for (int i = 0; i < n; i++) {
			if (intHashSet.contains(needleArray[i])) {
				found++;
			}
		}
		return found;
	}
	
	public static int findCountBinarySearch(Integer[] needleArray) {
		int found = 0;
		int n = needleArray.length;
		for (int i = 0; i < n; i++) {
			if (intBinSearch.contains(needleArray[i])) {
				found++;
			}
		}
		return found;
	}
	
	
	
	/*
	 * 
	 */
	
	static public void performTests(int numTestIterations, int numElements, int numSearchElements) {
		System.out.println(
			"# " 
			+ int2SnakeString(numTestIterations) 
			+ " iterations of (find " + int2SnakeString(numSearchElements) + " elements"
			+ " amongst " + int2SnakeString(numElements) + " elements)"
			);
		
		// intitialize set/array/values to search for
		init(numElements, numSearchElements);
			
		// primve algorithms for JVM so its optimized
		// before we run the actual tests
		for(int i=0; i<numTestIterations; i++) {
			findCountHashSetEntries(randValueArray);
			findCountBinarySearch(randValueArray);			
		}
		
		// setup variables & test
		int found = 0;
		long hashSetTime;
		long binarySearchTime;
		
		{
			// compute time to search for NUM_SEARCH_ELEMENTS with Set.contains
			long t1 = System.currentTimeMillis();
			for(int i=0; i<numTestIterations; i++) {
				found = findCountHashSetEntries(randValueArray);
			}
			long t2 = System.currentTimeMillis();
			hashSetTime = t2 - t1;

			// print time and number of found elements, found should be equal to numSearchElements			
			System.out.println("HashSet: " + (hashSetTime) + " ms, found: " + found);				
		}

		{
			// compute time to search for NUM_SEARCH_ELEMENTS with binary search
			long t1 = System.currentTimeMillis();
			for(int i=0; i<numTestIterations; i++) {			
				found = findCountBinarySearch(randValueArray);
			}
			long t2 = System.currentTimeMillis();
			binarySearchTime = t2 - t1;
			
			// print time and number of found elements, found should be equal to numSearchElements
			System.out.println("BinarySearch: " + (binarySearchTime) + " ms, found: " + found);				
		}	
		
		// print runtime ratio
		if (hashSetTime != 0 && binarySearchTime != 0) {
			System.out.println("HashSet is " + String.format("%.1f", binarySearchTime/(double)hashSetTime).replace(",", ".") + " times faster than BinarySearch");
		}
		
		System.out.println();
	}
		
	
	
	/*
	 * main function
	 */
	
	static public void main(String[] args) {
		// pattern: 
		// performTests(numTests, numElementsInSetOrArray, numElementsToSearchFor;
		
		performTests(100, 			100_000, 	10_000);
		performTests(10_000, 		1000, 		1000);
		performTests(100_000, 		100, 		100);
		performTests(1_000_000, 	5, 			5);
		performTests(100_000_00, 	2, 			2);
		performTests(100_000_00, 	1, 			1);
	}
}
