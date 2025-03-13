package Uke10Oppgave5;

import java.util.Arrays;
import java.util.Stack;

class QuickSortUsingStackImpl{
	static class Node{
		public int left;
		public int right;

		public Node(int l, int r) {
			left = l;
			right = r;
		}
	}
	
    private static int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high]; // Choosing the last element as the pivot
        int i = low - 1; 

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) { 
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
	public static void sort(Integer[] arr) {
		if(arr == null || arr.length <= 1)
			return;
		
		Stack<Node> stack = new Stack<Node>();
		stack.push(new Node(0, arr.length - 1));
		
		while(!stack.isEmpty()) {
			// get next workload
			Node currentWork = stack.pop();
			
			// done with this work
			if (currentWork.left >= currentWork.right) {
				continue;
			}
			
			// partition
			int pivot = partition(arr, currentWork.left, currentWork.right);
			
			// push new work
			stack.push(new Node(currentWork.left, pivot - 1));
			stack.push(new Node(pivot + 1, currentWork.right));
		}
	}
}

public class QuickSortUsingStack {

	public static void main(String[] args) {			
		System.out.println("QuickSortUsingStack, variations are being sorted");
		
		{
			Integer arr[] = {3, 2, 1, 4, 5};	
			QuickSortUsingStackImpl.sort(arr);		
			System.out.println(Arrays.toString(arr));
		}
		
		{
			Integer arr[] = {1, 5, 3, 2, 4};	
			QuickSortUsingStackImpl.sort(arr);		
			System.out.println(Arrays.toString(arr));
		}		
	}

}
