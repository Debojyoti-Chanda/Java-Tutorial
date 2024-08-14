import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HeapsTut {
    public static void main(String[] args) {
        int[] arr = { 18, 44, 12, 27, 4, 19, 42, 8, 34, 15, 38, 5, 16, 47, 1, 6, 32, 40, 10, 33, 22 };
        // convertMinToMaxHeap(4, arr);
        // System.out.println(findKthLargest(arr,5));
        System.out.println(isKSortedArray(arr, arr.length, 18));
    }

    static void convertMinToMaxHeap(int N, int arr[]) {
        // code here
        int[] temp = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                temp[0] = arr[i];
                continue;
            }
            temp[i] = arr[i];
            int idx = i;
            while (temp[parent(idx)] < temp[idx]) {
                int tmp = temp[parent(idx)];
                temp[parent(idx)] = temp[idx];
                temp[idx] = tmp;
                idx = parent(idx);
            }
        }
        for (int i = 0; i < N; i++) {
            arr[i] = temp[i];
        }
    }

    public static int parent(int pos) {
        return (pos - 1) / 2;
    }

    public static int findKthLargest(int[] nums, int k) {
        // 215. Kth Largest Element in an Array --
        // https://leetcode.com/problems/kth-largest-element-in-an-array/description/
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }
        int val = 0;
        while (k > 0) {
            val = pq.remove();
            k--;
        }
        return val;
    }

    public static String isKSortedArray(int arr[], int n, int k) {
        // code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if (!(Math.abs(i - map.get(arr[i])) <= 2)) {
                return "No";
            }
        }
        return "Yes";
    }

    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
        // Write your code here.
        ArrayList<Integer> a = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                pq.add(arr[i][j]);
            }
        }
        while (pq.size() > 0) {
            a.add(pq.remove());
        }
        return a;
    }

    public static int[] replaceWithRank(int arr[], int N) {
        // code here
        int[] temp = arr.clone();
        Arrays.sort(temp);
        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < temp.length; i++) {
            if (map.containsKey(temp[i])) {
                continue;
            }
            map.put(temp[i], rank);
            rank++;
        }
        int[] val = new int[N];
        for (int i = 0; i < N; i++) {
            val[i] = map.get(arr[i]);
        }
        return val;

    }

    public static int leastInterval(int N, int K, char tasks[]) {
        // code here
        int fr[] = new int[26];
        for (char c : tasks) {
            fr[c - 'A']++;
        }
        Arrays.sort(fr);
        int maxfr = fr[25];
        // (maxfr - 1) is total number of internal spaces between the max character
        // k is number of blanks between each spaces
        // ideal is total internal gaps
        int ideal = (maxfr - 1) * K;
        // decrease the number of empty spaces
        for (int i = 24; i >= 0; i--) {
            ideal -= Math.min(maxfr - 1, fr[i]);
        }
        return N + Math.max(0, ideal);
    }

    public static boolean isStraightHand(int N, int groupSize, int hand[]) {
        // code here
        if (hand.length % groupSize != 0) {
            return false;
        }

        Map<Integer, Integer> count = new HashMap<>();
        for (int n : hand) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> minH = new PriorityQueue<>(count.keySet());
        while (!minH.isEmpty()) {
            int first = minH.peek();
            for (int i = first; i < first + groupSize; i++) {
                if (!count.containsKey(i)) {
                    return false;
                }
                count.put(i, count.get(i) - 1);
                if (count.get(i) == 0) {
                    if (i != minH.peek()) {
                        return false;
                    }
                    minH.poll();
                }
            }
        }
        return true;
    }

}

class MergeKSortedArrays {

    // Helper class to store elements with their array and index information
    static class ArrayElement implements Comparable<ArrayElement> {
        int value; // Value of the element
        int arrayIndex; // Index of the array from which the element is taken
        int elementIndex; // Index of the element in its array

        ArrayElement(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        // Overriding compareTo method to sort elements by their value
        @Override
        public int compareTo(ArrayElement other) {
            return Integer.compare(this.value, other.value);
        }
    }

    public static int[] mergeKSortedArrays(int[][] arrays) {
        int k = arrays.length;
        PriorityQueue<ArrayElement> minHeap = new PriorityQueue<>();
        int totalElements = k * k;
        int[] result = new int[totalElements];
        int resultIndex = 0;

        // Add the first element of each array to the min-heap
        for (int i = 0; i < k; i++) {
            if (arrays[i].length > 0) {
                minHeap.add(new ArrayElement(arrays[i][0], i, 0));
            }
        }

        // Extract the minimum element from the heap and add the next element from the
        // same array to the heap
        while (!minHeap.isEmpty()) {
            ArrayElement minElement = minHeap.poll();
            result[resultIndex++] = minElement.value;
            int nextElementIndex = minElement.elementIndex + 1;

            if (nextElementIndex < arrays[minElement.arrayIndex].length) {
                minHeap.add(new ArrayElement(arrays[minElement.arrayIndex][nextElementIndex], minElement.arrayIndex,
                        nextElementIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        int[] mergedArray = mergeKSortedArrays(arrays);

        for (int value : mergedArray) {
            System.out.print(value + " ");
        }
    }
}
