import java.util.ArrayList;

public class SortingTut {
    public static void main(String[] args) {
        mergeSort(new int[]{4,1,3,9,7}, 0, 4);
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
            //swap a[i] and a[minIdx]
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
        ArrayList<Integer> a = new ArrayList<>();
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
        int i = start;
        int j = end;
        int pivot = arr[start];
        while (i < j) {
            if (arr[i] < pivot && i < end) {
                i++;
            }
            if (arr[j] > pivot && j > start) {
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }
        arr[start] = arr[i];
        arr[i] = pivot;
        return i;
    }
}
