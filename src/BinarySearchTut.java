import java.util.*;

public class BinarySearchTut {
    public static void main(String[] args) {
        // System.out.println(bsRecursive(new int[] { 1, 2, 3, 4, 5 }, 5, 0, 4));
        // System.out.println(findFloor(new long[]{1,3,5,7,9,11},6,0));
        // System.out.println(searchInsertK(new int[]{1,3,5,7,9,11}, 6, 8));
        // ArrayList<Integer> a = find(new int[] { 1, 5, 5, 4 }, 4, 5);

        // System.out.println(search(new int[] { 1, 0, 1, 1, 1 }, 0, 4, 0));
        // System.out.println(findMin(new int[]{4,5,1,2,3},5));
        // System.out.println(findKRotation(new int[]{1,2,3,4,5},5));
        // System.out.println(findOnce(new int[]{1,1,2,2,3,3,4,50,50,65,65},11));
        // System.out.println(peakElement(new int[]{1,2,3},3));
        // System.out.println(floorSqrt(10));
        // System.out.println(NthRoot(3,126));
        // System.out.println(Solve(5, new int[] { 30, 11, 23, 4, 20 }, 6));
        // System.out.println(solve(3,2,new int[]{1, 10, 3, 10, 2}));
        // System.out.println(smallestDivisor(new int[]{1,2,5,9},6));
        // System.out.println(leastWeightCapacity(new int[]{1,2,1},3,2));
        // System.out.println(KthMissingElement(new int[]{32,59,77},3,1));
        // System.out.println(solveAggresiveCows(5,3,new int[]{10,1,2,7,5}));
        // System.out.println(findSmallestMaxDist(new int[] { 1, 2, 3, 4, 5 }, 4));
        // System.out.println(medianOfArrays(1, 4, new int[] { 900 }, new int[] { 5, 8,
        // 10, 20 }));
        // System.out.println(kthElement(new int[] { 5, 8, 9, 11, 11 },
        // new int[] { 4, 6, 8, 9, 11, 13 }, 5, 6, 10));
        int[][] arr = { { 18, 21, 27, 38, 55, 67 } };
        // System.out.println(rowWithMax1s(arr, 4, 4));
        // System.out.println(matSearch(arr, 1, 6, 55));
        int[][] mat = {
            {41, 8, 2, 48, 18},
            {16, 15, 9, 7, 44},
            {48, 35, 6, 38, 28},
            {3, 2, 14, 15, 33},
            {39, 36, 13, 46, 42}
        };
        System.out.println(Arrays.toString(findPeakGrid(mat)));
    }

    public static int bs(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = ((left + right) / 2);
            if (arr[mid] == k) {
                return mid;
            } else if (k > arr[mid]) {
                left = mid + 1;
            } else { // k < arr[mid]
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int bsRecursive(int[] arr, int k, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = ((left + right) / 2);
        if (k > arr[mid]) {
            return bsRecursive(arr, k, mid + 1, right);
        } else if (k < arr[mid]) {
            return bsRecursive(arr, k, left, mid - 1);
        } else {
            return mid;
        }

    }

    public static int findFloor(long arr[], int n, long x) {
        // https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1
        // arr[i] <= x
        int ans = -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = ((left + right) / 2);
            if (arr[mid] == x) {
                return mid;
            } else if (x > arr[mid]) {
                ans = mid;
                left = mid + 1;
            } else { // x < arr[mid]
                right = mid - 1;
            }
        }
        return ans;
    }

    public static int searchInsertK(int arr[], int N, int x) {
        // Search insert position of K in a sorted array
        // arr[i] >= x
        int ans = arr.length;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = ((left + right) / 2);
            if (arr[mid] == x) {
                return mid;
            } else if (x > arr[mid]) {
                left = mid + 1;
            } else { // x < arr[mid]
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }

    public static ArrayList<Integer> find(int arr[], int n, int x) {
        // First and last occurrences of x --
        // https://www.geeksforgeeks.org/problems/first-and-last-occurrences-of-x3116/1
        // find the first and last occurrences of an element x in the given array.
        ArrayList<Integer> a = new ArrayList<>();
        // int left = 0;
        // int right = arr.length - 1;
        // while (left <= right) {
        // int mid = ((left + right) / 2);
        // if (arr[mid] == x) {
        // // return mid;
        // int j = 0;
        // while (mid - j >= 0 && arr[mid - j] == x) {
        // j++;
        // }
        // a.add(mid - j + 1);
        // int i = 0;
        // while (mid + i < n && arr[mid + i] == x) {
        // i++;
        // }
        // a.add(mid + i-1);
        // return a;
        // } else if (x > arr[mid]) {
        // left = mid + 1;
        // } else { // x <= arr[mid]
        // right = mid - 1;
        // }
        // }
        // a.add(-1);
        // a.add(-1);
        // return a;
        // ------------------------------- Optomization ---------------------
        // lower bound
        // upper bound
        // if (lower_bound == n || arr[lower_bound] != x) return {-1,-1}
        // return {lower_bound , upper_bound -1}
        // ----------------------------------------------Optimization 2
        // -----------------------------
        int left = 0;
        int right = arr.length - 1;
        // for min index
        int minIndex = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = ((left + right) / 2);
            if (arr[mid] == x) {
                minIndex = Math.min(minIndex, mid);
                right = mid - 1;
            } else if (x > arr[mid]) {
                left = mid + 1;
            } else { // x < arr[mid]
                right = mid - 1;
            }
        }
        left = 0;
        right = arr.length - 1;
        int maxIndex = Integer.MIN_VALUE;
        while (left <= right) {
            int mid = ((left + right) / 2);
            if (arr[mid] == x) {
                maxIndex = Math.max(maxIndex, mid);
                left = mid + 1;
            } else if (x > arr[mid]) {
                left = mid + 1;
            } else { // x < arr[mid]
                right = mid - 1;
            }
        }
        if (minIndex == Integer.MAX_VALUE && maxIndex == Integer.MIN_VALUE) {
            a.add(-1);
            a.add(-1);
            return a;
        } else {
            a.add(minIndex);
            a.add(maxIndex);
            return a;
        }
    }

    public static int search(int A[], int l, int h, int key) {
        // l: The starting index
        // h: The ending index, you have to search the key in this range
        // Search in a Rotated Array -->
        // https://www.geeksforgeeks.org/problems/search-in-a-rotated-array4618/1
        while (l <= h) {
            int mid = (l + h) / 2;
            if (A[mid] == key) {
                return mid;
            }
            if (A[l] <= A[mid] && !(key >= A[l] && key < A[mid])) {
                l = mid + 1;
                continue;
            }
            if (A[l] <= A[mid] && (key >= A[l] && key < A[mid])) {
                h = mid - 1;
                continue;
            }
            if (A[mid] <= A[h] && !(key > A[mid] && key <= A[h])) {
                h = mid - 1;
                continue;
            }
            if (A[mid] <= A[h] && (key > A[mid] && key <= A[h])) {
                l = mid + 1;
                continue;
            }
        }
        return -1;
    }

    public static boolean Search(int N, int[] A, int key) {
        // Search in Rotated Array 2 (duplicates) -->
        // https://www.geeksforgeeks.org/problems/search-in-rotated-array-2/1
        int l = 0;
        int h = A.length - 1;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (A[mid] == key) {
                return true;
            }
            if (A[l] == A[mid] && A[mid] == A[h]) {
                l = l + 1;
                h = h - 1;
                continue;
            }
            if (A[l] <= A[mid]) {
                if ((key >= A[l] && key < A[mid])) {
                    h = mid - 1;
                    continue;
                } else { // !(key >= A[l] && key < A[mid])
                    l = mid + 1;
                    continue;
                }
            }
            if (A[mid] <= A[h]) {
                if ((key > A[mid] && key <= A[h])) {
                    l = mid + 1;
                    continue;
                } else { // !(key > A[mid] && key <= A[h])
                    h = mid - 1;
                    continue;
                }
            }

        }
        return false;
    }

    public static int findMin(int A[], int n) {
        // 9 10 1 2 3 4 5 // 3 4 5 9 10 1 2
        int l = 0;
        int h = A.length - 1;
        int minEle = Integer.MAX_VALUE;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (A[l] <= A[mid]) {
                minEle = Math.min(minEle, A[l]);
                l = mid + 1;
                continue;
            }
            if (A[mid] <= A[h]) {
                minEle = Math.min(minEle, A[mid]);
                h = mid - 1;
                continue;
            }
        }
        return minEle;

    }

    public static int findKRotation(int arr[], int n) {
        // 5,1,2,3,4 = 1 // 4,5,1,2,3 = 2 // 3,4,5,1,2 = 3 // 2,3,4,5,1 = 4 // 1,2,3,4,5
        // = 0
        // https://www.geeksforgeeks.org/problems/rotation4723/1 --- Find out how many
        // times the array has been rotated
        int l = 0;
        int h = arr.length - 1;
        while (l <= h) {
            int mid = (l + h) / 2;
            // left is sorted
            if (mid + 1 < n && arr[mid] > arr[mid + 1]) {
                return mid + 1;
            }
            if (arr[l] <= arr[mid]) {
                l = mid + 1;
                continue;
            }
            if (arr[mid] <= arr[h]) {
                h = mid - 1;
                continue;
            }
        }
        return 0;
    }

    public static int findOnce(int arr[], int n) {
        // Complete this function
        // 1,1,2,3,3 // 1,1,2,2,3 // 1,2,2,3,3
        if (n == 1)
            return arr[0];
        if (arr[0] != arr[1])
            return arr[0];
        if (arr[n - 1] != arr[n - 2]) {
            return arr[n - 1];
        }
        int l = 1;
        int h = arr.length - 2;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]) {
                return arr[mid];
            }
            if (mid % 2 != 0 && arr[mid] == arr[mid - 1]) { // odd index
                l = mid + 1;
                continue;
            }
            if (mid % 2 != 0 && arr[mid] == arr[mid + 1]) {
                h = mid - 1;
                continue;
            }
        }
        return 0;
    }

    public static int peakElement(int[] arr, int n) {
        // add code here.
        int l = 1;
        int h = arr.length - 2;
        if (n == 1)
            return 0;
        if (arr[0] >= arr[1])
            return 0;
        if (arr[n - 1] >= arr[n - 2]) {
            return n - 1;
        }
        while (l <= h) {
            int mid = (l + h) / 2;
            if (arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1]) {
                return mid;
            }
            if (arr[mid - 1] <= arr[mid]) {
                l = mid + 1;
                continue;
            }
            if (arr[mid] >= arr[mid + 1]) {
                h = mid - 1;
                continue;
            }
        }
        return -1;
    }

    public static long floorSqrt(long x) {
        // https://www.geeksforgeeks.org/problems/square-root/1

        long low = 1;
        long high = x;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid > x) {
                high = mid - 1;
            } else { // mid*mid <= x
                low = mid + 1;
            }
        }
        return high;
    }

    public static int NthRoot(int n, int m) {
        // code here
        // int low = 1;
        // int high = m;
        // while(low<= high){
        // int mid = low + (high - low) / 2;
        // long val = 1;
        // for(int i=0;i<n;i++){
        // val = (val * mid)%1000000007;
        // }
        // if( val > m){
        // high = mid-1;
        // }else{ //mid*mid <= m
        // low = mid +1;
        // }
        // }
        // return high;
        int low = 1;
        int high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long val = 1;
            for (int i = 0; i < n; i++) {
                val = (val * mid) % 1000000007;
            }
            if (val == m) {
                return mid;
            } else if (val > m) {
                high = mid - 1;
            } else { // mid*mid < m
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int Solve(int N, int[] piles, int H) {
        // https://www.geeksforgeeks.org/problems/koko-eating-bananas/1
        // Given N piles of bananas, the ith pile has piles[i] bananas and H hours time
        // until guards return (N <= H). Find the minimum (S) bananas to eat per hour
        // such that Koko can eat all the bananas within H hours. Each hour, Koko
        // chooses some pile of bananas and eats S bananas from that pile. If the pile
        // has less than S bananas, then she consumes all of them, and wont eat any more
        // bananas during that hour.
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = 0;
            for (int i = 0; i < N; i++) {
                // val += Math.ceilDiv(piles[i],mid); // banana / bananas to eat per hour =
                // hours needed
                val += (int) Math.ceil((double) piles[i] / mid);
            }
            if (val > H) {
                low = mid + 1;
            } else { // val <= H
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int solve(int M, int K, int[] bloomDay) {
        // Minimum days to make M bouquets ----
        // https://www.geeksforgeeks.org/problems/minimum-days-to-make-m-bouquets/1
        // To make one bouquet we need K adjacent flowers from the garden. Here the
        // garden consists of N different flowers, the ith flower will bloom in the
        // bloomDay[i]. Each flower can be used inside only one bouquets. We have to
        // find the minimum number of days need to wait to make M bouquets from the
        // garden. If we cannot make m bouquets, then return -1.
        int low = Arrays.stream(bloomDay).min().getAsInt();
        int high = Arrays.stream(bloomDay).max().getAsInt();
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            int bouqC = 0;
            for (int i = 0; i < bloomDay.length; i++) {
                if (bloomDay[i] <= mid) {
                    count++;
                    if (count == K) {
                        bouqC++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
            // if (bouqC == M) {
            // return mid;
            // } else
            if (bouqC < M) {
                low = mid + 1;
            } else { // bouqC >= M
                ans = mid;
                high = mid - 1;
            }

        }
        return ans;
    }

    public static int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = Arrays.stream(nums).max().getAsInt();
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += (int) Math.ceil((double) nums[i] / mid);
            }
            // System.out.println(sum);
            if (sum > threshold) { // denominator should increase to reduse the sum value
                low = mid + 1;
            } else { // sum <= threshold
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int leastWeightCapacity(int[] arr, int N, int D) {
        // https://www.geeksforgeeks.org/problems/capacity-to-ship-packages-within-d-days/1
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = 0;
            int countD = 0;
            for (int i = 0; i < arr.length; i++) {
                if ((sum + arr[i]) <= mid) {
                    sum += arr[i];
                } else {
                    sum = arr[i];
                    countD++;
                }
            }
            countD++;
            if (countD > D) { // increase capacity
                low = mid + 1;
            } else { // countD <= D
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int KthMissingElement(int a[], int n, int k) {
        // https://leetcode.com/problems/kth-missing-positive-number/submissions/1233266400/
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int noOfMissing = a[mid] - (mid + 1);
            if (noOfMissing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k;
    }

    public static int solveAggresiveCows(int n, int k, int[] stalls) {
        // Aggressive Cows -- https://www.geeksforgeeks.org/problems/aggressive-cows/0
        // Brute Force
        Arrays.sort(stalls);
        // for (int i = 1; i < (stalls[n - 1] - stalls[0]); i++) {
        // if (canWePlaceCows(stalls, i, k) == true) {
        // continue;
        // } else {
        // return i - 1;
        // }
        // }
        // return -1;
        // Optimized Technique
        int low = 1;
        int high = stalls[n - 1] - stalls[0];
        int ans = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canWePlaceCows(stalls, mid, k) == false) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }

    public static boolean canWePlaceCows(int[] stalls, int minSpace, int k) {
        int lastSpace = 0;
        k--;
        for (int i = 1; i < stalls.length; i++) {
            if (k > 0 && (stalls[i] - stalls[lastSpace]) >= minSpace) {
                lastSpace = i;
                k--;
            }
        }
        if (k == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int findPages(int[] A, int N, int M) {
        // Allocate minimum number of pages --
        // https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
        // Brute Force
        int low = Arrays.stream(A).max().getAsInt();
        int high = Arrays.stream(A).sum();
        // for (int i = low; i < high; i++) {
        // int countSt = studentCount(A, i);
        // if (countSt == M) {
        // return i;
        // }
        // }
        // return -1;
        // optimization
        if (M > N) {
            return -1;
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int countSt = studentCount(A, mid);
            if (countSt > M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static int studentCount(int[] A, int maxPages) {
        int studentCount = 1;
        int pagesStudent = 0;
        for (int i = 0; i < A.length; i++) {
            if (pagesStudent + A[i] <= maxPages) {
                pagesStudent += A[i];
            } else {
                studentCount++;
                pagesStudent = A[i];
            }
        }
        return studentCount;
    }

    public static long minTime(int[] arr, int n, int k) {
        // The Painter's Partition Problem-II --
        // https://www.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
        long low = Arrays.stream(arr).max().getAsInt();
        long high = Arrays.stream(arr).sum();
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long numPainters = numberOfPainters(arr, mid);
            if ((int) numPainters > k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static long numberOfPainters(int[] arr, long maxTime) {
        long numberOfPainters = 1;
        long unitOfBoard = 0;
        for (int i = 0; i < arr.length; i++) {
            if (unitOfBoard + arr[i] <= maxTime) {
                unitOfBoard += arr[i];
            } else {
                numberOfPainters++;
                unitOfBoard = arr[i];
            }
        }
        return numberOfPainters;
    }

    public static double findSmallestMaxDist(int stations[], int K) {
        // Minimize Max Distance to Gas Station --
        // https://www.geeksforgeeks.org/problems/minimize-max-distance-to-gas-station/1
        // station - [1,13,17,23] ____ howMany- [_,1,0,0] -> [_,1,0,1];
        // --------------------- Brute Force -------
        Arrays.sort(stations);
        int[] howMany = new int[stations.length];
        for (int i = 1; i <= K; i++) {
            int maxVal = -1;
            int maxIndex = -1;
            for (int j = 1; j < howMany.length; j++) {
                int diff = stations[j] - stations[j - 1];
                int sectionLength = (diff / (howMany[j] + 1));
                if (maxVal < sectionLength) {
                    maxVal = sectionLength;
                    maxIndex = j;
                }
            }
            howMany[maxIndex] += 1;
        }
        double maxAns = -1;
        for (int i = 1; i < howMany.length; i++) {
            double dist = (double) (stations[i] - stations[i - 1]) / (howMany[i] + 1);
            maxAns = Math.max(maxAns, dist);
        }
        return maxAns;
        //
    }

    public static double medianOfArrays(int n, int m, int a[], int b[]) {
        // https://www.geeksforgeeks.org/problems/median-of-2-sorted-arrays-of-different-sizes/1

        // --------------------- brute force -----------------
        // int i = 0;
        // int j = 0;
        // int num1 = (n + m) / 2;
        // int count = 0;
        // int num2 = num1 - 1;
        // int first = 0;
        // int second = 0;
        // while (i < n && j < m) {
        // if (a[i] < b[j]) {
        // if (count == num1) {
        // first = a[i];
        // }
        // if (count == num2) {
        // second = a[i];
        // }
        // i++;
        // count++;
        // continue;
        // }
        // if (a[i] > b[j]) {
        // if (count == num1) {
        // first = b[j];
        // }
        // if (count == num2) {
        // second = b[j];
        // }
        // j++;
        // count++;
        // continue;
        // }
        // }
        // while (i < n) {
        // if (count == num1)
        // first = a[i];
        // if (count == num2)
        // second = a[i];
        // i++;
        // count++;
        // }
        // while (j < m) {
        // if (count == num1)
        // first = b[j];
        // if (count == num2)
        // second = b[j];
        // j++;
        // count++;
        // }
        // if ((n + m) % 2 == 0) {
        // double val = (double) (first + second) / 2;
        // return val;
        // } else {
        // double val = (double) (first);
        // return val;
        // }

        // ----------------------- optimized -----------------
        if (m < n)
            return medianOfArrays(m, n, b, a);

        int total = (int) (n + m + 1) / 2;
        int low = 0;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = total - mid1;
            // calculate l1, l2, r1 and r2;
            int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n) ? a[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < m) ? b[mid2] : Integer.MAX_VALUE;
            if (l1 <= r2 && l2 <= r1) {
                if ((n + m) % 2 == 1) {
                    return Math.max(l1, l2);
                } else {
                    double val = (double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                    return val;
                }
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        return 0;

    }

    public static long kthElement(int arr1[], int arr2[], int n, int m, int k) {
        int low = 0;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = k - mid1;
            if (mid2 < 0) {
                high = mid1 - 1;
                continue;
            }
            if (mid2 > m) {
                low = mid1 + 1;
                continue;
            }
            int l1 = (mid1 > 0) ? arr1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? arr2[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n) ? arr1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < m) ? arr2[mid2] : Integer.MAX_VALUE;
            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        return 0;
    }

    public static int rowWithMax1s(int arr[][], int n, int m) {
        // https://www.geeksforgeeks.org/problems/row-with-max-1s0023/1
        // Row with max 1s
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < arr.length; i++) {
            int ans = Integer.MAX_VALUE;
            int left = 0;
            int right = arr[0].length - 1;
            while (left <= right) {
                int mid = ((left + right) / 2);
                if (0 < arr[i][mid]) { // 1
                    ans = mid;
                    right = mid - 1;
                } else { // 0 >= arr[mid] //0
                    left = mid + 1;
                }
            }
            // min = ans;
            if (ans < min) {
                minIdx = i;
                min = ans;
            }
        }
        return minIdx;
    }

    public static int matSearch(int mat[][], int N, int M, int X) {
        // https://www.geeksforgeeks.org/problems/search-in-a-matrix17201720/1 ---
        // Search in a 2D matrix
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][0] <= X && X <= mat[i][M - 1]) {
                // binary search
                int low = 0;
                int high = M - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (mat[i][mid] == X) {
                        return 1;
                    } else if (mat[i][mid] > X) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        }
        return 0;
    }

    public static int[] findPeakGrid(int[][] mat) {
        // https://leetcode.com/problems/find-a-peak-element-ii/description/ ---
        // int[][] mat = {
        //     {41, 8, 2, 48, 18},
        //     {16, 15, 9, 7, 44},
        //     {48, 35, 6, 38, 28},
        //     {3, 2, 14, 15, 33},
        //     {39, 36, 13, 46, 42}
        // };
        int m = mat.length; // row
        int n = mat[0].length; // column
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // column
            int max = 0;
            int maxIdx = -1;
            for (int i = 0; i < m; i++) {
                if (mat[i][mid] > max) {
                    max = mat[i][mid];
                    maxIdx = i;
                }
            }
            int left = (mid - 1) < 0 ? -1 : mat[maxIdx][mid - 1];
            int right = (mid + 1) >= n ? -1 : mat[maxIdx][mid + 1];
            if (mat[maxIdx][mid] > left && mat[maxIdx][mid] > right) {
                return new int[] { maxIdx, mid };
            } else if (mat[maxIdx][mid] < left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{0,0};
    }
}
