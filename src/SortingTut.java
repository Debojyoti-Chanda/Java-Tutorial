import java.util.ArrayList;
import java.util.Arrays;

public class SortingTut {
    public static void main(String[] args) {
        int[] a = { 3, 1, 2, 10, 6, 8, 1 };
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minValue = Integer.MAX_VALUE;
            int minIdx = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] <= minValue) {
                    minValue = arr[j];
                    minIdx = j;
                }
            }
            // swap a[i] and a[minIdx]
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean noSwap = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    noSwap = false;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (noSwap == true) {
                break;
            }
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j >= 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        ArrayList<Integer> a = new ArrayList<>(); // Extra Array
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                a.add(arr[i]);
                i++;
            } else {
                a.add(arr[j]);
                j++;
            }
        }
        while (i != mid + 1) {
            a.add(arr[i]);
            i++;
        }
        while (j != end + 1) {
            a.add(arr[j]);
            j++;
        }
        int m = 0;
        for (int k = start; k <= end; k++) {
            arr[k] = a.get(m);
            m++;
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int partition = partition(arr, start, end);
            quickSort(arr, start, partition - 1);
            quickSort(arr, partition + 1, end);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < end && arr[i] <= pivot) {
                i++;
            }
            while (j > start && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Place the pivot in its correct position
        arr[start] = arr[j];
        arr[j] = pivot;
        return j;
    }

    public static void countingSort(int[] arr, int max) { // O(n+k),where n is the number of elements and k is the range of input 
        int[] count = new int[max + 1];
        // int[] arr = {4, 2, 2, 8, 3, 3, 1};
        // Count the occurrences of each element  , [0,1,2,2,1,0,0,0,1] -> index[0,1,2,3,4,5,6,7,8]
        for (int num : arr) {
            count[num]++;
        }

        // Calculate the cumulative sum of the count array [0,1,3,5,6,6,6,6,7]
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Sort the array using the cumulative sum
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Copy the sorted elements back to the original array   [1,2,2,3,3,4,8]
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
    
}

// In-place sorting is a type of sorting algorithm that sorts the elements of a
// list or array without using any additional memory space proportional to the
// input size. This means that the algorithm modifies the input array directly,
// rearranging its elements within the original space.

// Examples of in-place sorting algorithms:

// Bubble Sort: Compares adjacent elements and swaps them if they're in the
// wrong order.  
// Selection Sort: Finds the minimum element in the unsorted part and swaps it
// with the first element.
// Insertion Sort: Inserts each element into its correct position in the sorted
// part of the array.  
// Quick Sort: Divides the array into two partitions and recursively sorts each
// partition.  
// Heap Sort: Uses a heap data structure to sort the elements.  

// Out-of-place sorting algorithms require additional memory space, typically
// proportional to the input size, to sort the elements. This extra space is
// used to create a temporary array or data structure to store intermediate
// results or to perform sorting operations without directly modifying the
// original input array.

// Merge Sort: This algorithm divides the input array into smaller subarrays,
// recursively sorts them, and then merges the sorted subarrays back together.
// To merge the subarrays efficiently, it often requires extra space to store
// the merged elements temporarily.  

// 1. Comparison-Based vs. Non-Comparison-Based:

// Comparison-Based: These algorithms compare elements to determine their
// relative order. Examples include Bubble Sort, Insertion Sort, Selection Sort,
// Merge Sort, Quick Sort, and Heap Sort.

// Non-Comparison-Based: These algorithms don't rely on pairwise comparisons.
// They exploit specific properties of the data, such as digit or radix
// representation. Examples include Counting Sort, Radix Sort, and Bucket Sort.

// 2. Stable vs. Unstable:

// Stable: These algorithms preserve the relative order of equal elements in the
// input array. Examples include Merge Sort, Insertion Sort, and Bubble Sort.
// Unstable: These algorithms may not preserve the relative order of equal
// elements. Examples include Quick Sort, Heap Sort, and Selection Sort.

// 3. Adaptive vs. Non-Adaptive:

// Adaptive: These algorithms take advantage of any existing order in the input
// data to reduce the number of comparisons and operations. Examples include
// Insertion Sort and Merge Sort.
// Non-Adaptive: These algorithms don't exploit any existing order in the input
// data. Examples include Bubble Sort, Selection Sort, and Quick Sort.

// 4. Online vs. Offline:

// Online: These algorithms can process input elements one at a time, without
// requiring the entire input to be available upfront. Examples include
// Insertion Sort and Heap Sort.
// Offline: These algorithms require the entire input to be available before the
// sorting process begins. Examples include Merge Sort and Quick Sort.

// 5. Internal vs. External:

// Internal: These algorithms can sort the entire data set within the main
// memory. Examples include most of the common sorting algorithms.
// External: These algorithms are designed to sort large data sets that don't
// fit entirely in main memory. They involve reading and writing data to
// secondary storage devices like disks.