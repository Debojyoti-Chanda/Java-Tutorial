import java.util.*;

public class ArraysTut {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // int[] arr = new int[n];
        // for (int i = 0; i < n; i++) {
        // arr[i] = sc.nextInt();
        // }
        // System.out.println("Duplicate Elements : " + findDuplicateElements(arr));
        // findPairsInSum(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 9);
        // findCommonElements(new int[] { 1, 2, 3, 7 }, new int[] { 2, 3, 4, 5 }, new
        // int[] { 3, 4, 5, 6, 7 });
        // System.out.println(Arrays.toString(removeDuplicateInPlaceFromSortedArray(new
        // int[] { 1, 1, 2, 2, 3 })));
        // System.out.println(Arrays.toString(rotateArrayByN(new int[] { 1, 2, 3, 4, 5
        // },2)));
        // System.out.println(Arrays.toString(zeroAtEnd(new int[]{0,1,0,3,4,0,0,5})));
        // System.out.println(unionOfTwoSortedArray(new int[]{1,3,5,5,6},new
        // int[]{1,1,2,4}));
        // System.out.println(intersectionTwoSortedArray(new int[]{2,2,2},new
        // int[]{2,2,2}));
        // System.out.println(maxConsecutiveOne(new int[]{0,1,0,1,1,1}));
        // System.out.println(hasArrayTwoCandidates(new int[]{ 1, 2, 5, 6, 7}, 4));
        // rearrange(new int[] { 9, 4, -2, -1, 5, 0, -5, -3 , 2 , 7 }, 10);
        // System.out.println(nextPermutation(3,new int[]{2,1,3}));
        // System.out.println(leaders(new int[]{16,17,4,3,5,2},6));
        // System.out.println(findLongestConseqSubseq(new int[]{0,1,1,2,8,9,10,11},8));
        // int[][] array = { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1,
        // 1 } };
        // makeZeros(array);
        // comb(2, 2);
        // System.out.println(findTriplets(new int[]{-2,-2,-2,-1,-1,-1,0,0,0,2,2,2,2}));
        // ArrayList<ArrayList<Integer>> a = fourSum(new int[]
        // {1000000000,1000000000,1000000000,1000000000}, -294967296);
        // System.out.println(a);
        // System.out.println(maxSubarrayXOR(new int[] { 1, 2, 3, 4 }));
        int[][] array = { { 1, 3 }, { 2, 4 }, { 2, 3 }, { 6, 8 }, { 8, 10 } };
        System.out.println(overlappedInterval(array));
        // test(null);
        // sc.close();
    }

    public static ArrayList<Integer> findDuplicateElements(int[] arr) {

        ArrayList<Integer> a = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
                a.add(arr[i]);
            }
        }
        return a;
    }

    public static void findPairsInSum(int[] arr, int sum) {
        Arrays.sort(arr);
        // ArrayList<int[]> arrayListOfArrays = new ArrayList<>();
        // ArrayList<Integer> a = new ArrayList<Integer>();
        // for (int i : arr) {
        // a.add(i);
        // }
        // for (int i = 0; i <= arr.length / 2; i++) {
        // int rem = sum - arr[i];
        // if (a.contains(rem)) {
        // arrayListOfArrays.add(new int[] { rem, arr[i] });
        // System.out.println(rem + " ---" + arr[i]);
        // }
        // ;
        // }
        // ----------------------------Optimization----------------

        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            if (arr[low] + arr[high] > sum) {
                high--;
            } else if (arr[low] + arr[high] < sum) {
                low++;
            } else if (arr[low] + arr[high] == sum) {
                System.out.println(arr[low] + " -- " + arr[high]);
                low++;
                high--;
            }
        }
    }

    public static void findCommonElements(int[] arr1, int[] arr2, int[] arr3) {
        // Integer[] integerArray1 =
        // Arrays.stream(arr1).boxed().toArray(Integer[]::new);
        // Integer[] integerArray2 =
        // Arrays.stream(arr2).boxed().toArray(Integer[]::new);
        // Integer[] integerArray3 =
        // Arrays.stream(arr3).boxed().toArray(Integer[]::new);
        // HashSet<Integer> set1 = new HashSet<>(Arrays.asList(integerArray1));
        // HashSet<Integer> set2 = new HashSet<>(Arrays.asList(integerArray2));
        // HashSet<Integer> set3 = new HashSet<>(Arrays.asList(integerArray3));
        // set1.retainAll(set2); // Intersection
        // set1.retainAll(set3); // Intersection
        // System.out.println(set1);

        // --------------------------Optimization ------------
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Arrays.sort(arr3);
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                System.out.print(arr1[i] + " ");
                i++;
                j++;
                k++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else if (arr2[j] > arr3[k]) {
                k++;
            } else { // arr3[k] > arr1[i]
                i++;
            }
        }
    }

    public static int largest(int arr[], int n) {
        // Largest Element in Array O(n)
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static long[] minAnd2ndMin(long a[], long n) {
        // https://www.geeksforgeeks.org/problems/find-the-smallest-and-second-smallest-element-in-an-array3226/1
        long min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        long secMin = Long.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if ((a[i] != min) && (a[i] < secMin)) {
                secMin = a[i];
            }
        }
        if (secMin == Long.MAX_VALUE) {
            return new long[] { -1 };
        }
        return new long[] { min, secMin };
    }

    public static int[] removeDuplicateInPlaceFromSortedArray(int[] arr) {
        // [1,1,2,2,3] -> [1,2,3, -,-] O(n)
        int tempIdx = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == arr[i]) {
                continue;
            } else {
                arr[tempIdx] = arr[i];
                tempIdx++;
            }
        }
        return arr;
    }

    public static int[] rotateArrayByN(int[] arr, int n) {
        // counter-clockwise n=1 [1,2,3,4,5] -> [2,3,4,5,1]
        // Brute force O(n^2)
        n = n % arr.length; // eg: n=6 -> n= 1
        // for (int j = 0; j < n; j++) {
        // int temp = arr[0];
        // for (int i = 0; i < arr.length - 1; i++) {
        // arr[i] = arr[i + 1];
        // }
        // arr[arr.length - 1] = temp;
        // }
        // Optimized - 1 O(n) and space O(No Of Rotations)
        // int[] a = new int[n];
        // for (int i = 0; i < a.length; i++) {
        // a[i] = arr[i];
        // }
        // for (int i = n; i < arr.length; i++) {
        // arr[i - n] = arr[i];
        // }
        // for (int i = 0; i < n; i++) {
        // arr[arr.length - n + i] = a[i];
        // }
        // Optimization - 2
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
        for (int i = 0; i < (arr.length - n) / 2; i++) {
            int temp = arr[i + n];
            arr[i + n] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }

    public static int[] zeroAtEnd(int[] arr) {
        // [1,2,0,3]
        // int zeroPtr = -1;
        // for (int i = 0; i < arr.length; i++) {
        // if (arr[i] == 0) {
        // zeroPtr = i;
        // for (int j = i + 1; j < arr.length; j++) {
        // if (arr[j] != 0) {
        // int temp = arr[j];
        // arr[j] = arr[zeroPtr];
        // arr[zeroPtr] = temp;
        // break;
        // }
        // }
        // zeroPtr++;
        // }

        // }
        // -------------------------------- Optimization -------------------
        int j = 0;
        int no_zero = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[j] = arr[i];
                j++;
            } else {
                no_zero++;
            }
        }
        for (int i = 0; i < no_zero; i++) {
            arr[arr.length - 1 - i] = 0;
        }

        return arr;
    }

    public static ArrayList<Integer> unionOfTwoSortedArray(int[] a, int[] b) {
        int i = 0, j = 0;
        int prevVal = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                if (prevVal != a[i]) {
                    arr.add(a[i]);
                    prevVal = a[i];
                }
                i++;
                j++;
            } else if (a[i] < b[j]) {
                if (prevVal != a[i]) {
                    arr.add(a[i]);
                    prevVal = a[i];
                }
                i++;
            } else if (a[i] > b[j]) {
                if (prevVal != b[j]) {
                    arr.add(b[j]);
                    prevVal = b[j];
                }
                j++;
            }
        }
        if (i < a.length) {
            while (i < a.length) {
                if (prevVal != a[i]) {
                    arr.add(a[i]);
                    prevVal = a[i];
                }
                i++;
            }
        } else if (j < b.length) {
            while (j < b.length) {
                if (prevVal != b[j]) {
                    arr.add(b[j]);
                    prevVal = b[j];
                }
                j++;
            }
        }
        return arr;
    }

    public static ArrayList<Integer> intersectionTwoSortedArray(int a[], int[] b) {
        int i = 0, j = 0;
        int prevVal = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                if (prevVal != a[i]) {
                    arr.add(a[i]);
                    prevVal = a[i];
                }
                i++;
                j++;
            } else if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            }
        }

        return arr;
    }

    public static int missingNumber(int[] arr) {
        // Solution 1 hashing
        // int[] a = new int[arr.length + 2];
        // for (int i = 0; i < arr.length; i++) {
        // a[arr[i]] = 1;
        // }
        // for (int i = 1; i < a.length; i++) {
        // if (a[i] == 0) {
        // return i;
        // }
        // }
        // return -1;
        // Solution 2 sum
        int n = arr.length + 1;
        int sum = 0;
        int rem = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += i + 1;
            rem += arr[i];
        }
        return sum + n - rem;
    }

    public static int maxConsecutiveOne(int[] arr) {
        int countOne = 0;
        int prevCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                countOne++;
                prevCount = Math.max(prevCount, countOne);
            } else {
                prevCount = Math.max(prevCount, countOne);
                countOne = 0;
            }
        }
        return prevCount;
    }

    public static int longestSubArraywithSumK(int[] arr, int k) {
        // https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
        // Optimal for integers
        // int sum = 0;
        // int len = 0;
        // HashMap<Integer, Integer> map = new HashMap<>();
        // for (int i = 0; i < arr.length; i++) {
        // sum += arr[i];
        // if (sum == k) {
        // len = Math.max(i+1, len);
        // }
        // if (map.containsKey(sum - k)) {
        // len = Math.max(len, i - map.get(sum - k));
        // }
        // if (!map.containsKey(sum)) {
        // map.put(sum, i);
        // }
        // }
        // return len;
        // -----------------------------Optimization ---------------- 2 pointer approach
        // Optimal if the array contains only whole numbers
        int backPtr = 0;
        int frontPtr = 0;
        int sum = arr[0];
        int len = 0;
        while (frontPtr < arr.length) {
            while (backPtr <= frontPtr && sum > k) {
                sum -= arr[backPtr];
                backPtr++;
            }
            if (sum == k) {
                len = Math.max(len, frontPtr - backPtr + 1);
            }
            frontPtr++;
            if (frontPtr < arr.length) {
                sum += arr[frontPtr];
            }
        }
        return len;

    }

    public static boolean hasArrayTwoCandidates(int arr[], int x) {
        // https://www.geeksforgeeks.org/problems/key-pair5616/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article

        // HashMap<Integer,Integer> map = new HashMap<>();
        // for (int i = 0; i < arr.length; i++) {
        // if (map.get(arr[i]) == null) {
        // map.put(arr[i], 1);
        // } else {
        // map.put(arr[i], map.get(arr[i])+1);
        // }

        // }
        // for (int i = 0; i < arr.length; i++) {
        // if(map.containsKey(x-arr[i])){
        // if(arr[i] == x-arr[i] && map.get(x-arr[i]) == 1){
        // return false;
        // }else{
        // return true;
        // }
        // }
        // }
        // return false;

        // Optimizaton -------- O(nlogn + n)
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < x) {
                left++;
            } else if (arr[left] + arr[right] > x) {
                right--;
            } else {
                return true;
            }
        }
        return false;

    }

    public static int[] sortArrayOf012(int[] arr) {
        // https://www.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s4231/1
        // most optimal solution ---> Dutch national Flag Algorithm
        // 0-> [0,low-1] 1-> [low..mid-1] 2 -> [high+1,n-1];
        int low = 0, high = arr.length - 1, mid = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[mid] == 0) {
                // swap arr[low],arr[mid]
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else if (arr[mid] == 2) {
                // swap arr[high],arr[mid]
                int temp = arr[high];
                arr[high] = arr[mid];
                arr[mid] = temp;
                high--;
            }
        }
        return arr;

    }

    public static int majorityElement(int[] a) {
        // https://www.geeksforgeeks.org/problems/majority-element-1587115620/1
        // Most Optimized Moore's Voting Algorithm
        // your code here
        int size = a.length;
        if (size == 1) {
            return a[0];
        }
        int ele = 0;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (count == 0) {
                ele = a[i];
            }
            if (ele == a[i]) {
                count++;
            } else {
                count--;
            }
        }

        if (count >= 1 && size % 2 == 0) {
            return ele;
        } else if (count > 1 && size % 2 != 0) {
            return ele;
        } else if (count == 1 && size % 2 != 0) {
            count--;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == ele) {
                    count++;
                }
            }
            if (count > size / 2) {
                return ele;
            }
        }
        return -1;
    }

    public static int maxSubarraySum(int arr[]) {
        // https://www.geeksforgeeks.org/problems/kadanes-algorithm-1587115620/1
        // Optimized Solution - Kadanes Algorithm
        int sum = 0;
        int sumMax = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (sum < 0) {
                sum = 0;
            }
            sum += arr[i];
            sumMax = Math.max(sumMax, sum);
        }
        return sumMax;
    }

    public static void rearrange(int arr[], int n) {
        // Alternate positive and negative numbers -
        // https://www.geeksforgeeks.org/problems/array-of-alternate-ve-and-ve-nos1401/1
        // 9, 4, -2, -1, 5, 0, -5, -3,
        // int[] a = new int[n];
        // int pos = 0;
        // int neg = 1;
        // for (int i = 0; i < n; i++) {
        // if (arr[i] >= 0) {
        // a[pos] = arr[i];
        // pos += 2;
        // } else {
        // a[neg] = arr[i];
        // neg += 2;
        // }
        // }
        // arr = a;
        ArrayList<Integer> list1 = new ArrayList<>(); // pos
        ArrayList<Integer> list2 = new ArrayList<>(); // neg

        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                list2.add(arr[i]);
            } else {
                list1.add(arr[i]);
            }
        }
        int sz = Math.min(list1.size(), list2.size());
        for (int i = 0; i < sz; i++) {
            arr[i * 2] = list1.get(i); // 0,2,4,...
            arr[i * 2 + 1] = list2.get(i); // 1,3,5...
        }
        ArrayList<Integer> list = new ArrayList<>();
        if (list1.size() > list2.size()) {
            list = list1;
            for (int i = sz * 2, j = sz; i < n; i++, j++) {
                arr[i] = list.get(j);
            }
        } else if (list1.size() < list2.size()) {
            list = list2;

            for (int i = sz * 2, j = sz; i < n; i++, j++) {
                arr[i] = list.get(j);
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    public static List<Integer> nextPermutation(int N, int arr[]) {
        // code here
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(arr[i]);
        }
        int index = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (list.get(i) < list.get(i + 1)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Collections.reverse(list);
            return list;
        }
        for (int i = N - 1; i > index; i--) {
            if (list.get(i) > list.get(index)) {
                Collections.swap(list, i, index);
                break;
            }
        }
        List<Integer> sublist = list.subList(index + 1, N);
        Collections.reverse(sublist);

        return list;
    }

    static ArrayList<Integer> leaders(int arr[], int n) {
        // Your code here
        ArrayList<Integer> a = new ArrayList<>();
        a.add(arr[n - 1]);
        int max = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[max]) {
                max = i;
                a.add(arr[i]);
            }
        }
        Collections.reverse(a);
        return a;
    }

    public static int findLongestConseqSubseq(int arr[], int N) {
        // Longest consecutive subsequence ->
        // https://www.geeksforgeeks.org/problems/longest-consecutive-subsequence2449/1
        // {2,6,1,9,4,5,3} = 6 , {1,9,3,10,4,20,2} = 4
        // Arrays.sort(arr);
        // int countCurr = 0;
        // int lastSmallest = Integer.MIN_VALUE;
        // int longest = 1;
        // for (int i = 0; i < arr.length; i++) {
        // if (arr[i] == lastSmallest) {
        // continue;
        // } else if (arr[i] != lastSmallest + 1) {
        // lastSmallest = arr[i];
        // countCurr = 1;
        // } else if (arr[i] == lastSmallest + 1) {
        // lastSmallest = arr[i];
        // countCurr++;
        // if (countCurr > longest) {
        // longest = countCurr;
        // }
        // }
        // }
        // return longest;

        // -----------------Optimization -----------------------
        HashSet<Integer> a = new HashSet<>();
        for (int i : arr) {
            a.add(i);
        }
        int maxLen = 0;
        for (Integer i : a) {
            if (a.contains(i - 1)) {
                continue;
            } else { // !a.contains(i - 1)
                int j = 1;
                while (a.contains(i + j)) {
                    j++;
                }
                if (j > maxLen) {
                    maxLen = j;
                }
            }
        }
        return maxLen;
    }

    public static void MakeZeros(int[][] matrix) {
        // https://www.geeksforgeeks.org/problems/make-zeroes4042/1

        int r = matrix.length;
        int c = matrix[0].length;
        int[][] mat = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) {
                    int sum = 0;
                    if (i > 0) {
                        sum += matrix[i - 1][j];
                        mat[i - 1][j] = -1;
                    }
                    if (i < r - 1) {
                        sum += matrix[i + 1][j];
                        mat[i + 1][j] = -1;
                    }
                    if (j > 0) {
                        sum += matrix[i][j - 1];
                        mat[i][j - 1] = -1;
                    }
                    if (j < c - 1) {
                        sum += matrix[i][j + 1];
                        mat[i][j + 1] = -1;
                    }

                    mat[i][j] = sum;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (mat[i][j] == -1)
                    matrix[i][j] = 0;
                if (mat[i][j] != 0 && mat[i][j] != -1)
                    matrix[i][j] = mat[i][j];
            }
        }
    }

    public static void makeZeros(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        boolean allRow = false;
        boolean allCol = false;
        // Iterating top row
        for (int i = 0; i < c; i++) {
            if (matrix[0][i] == 0) {
                allRow = true;
                break;
            }
        }
        // Iterating 1st column
        for (int i = 0; i < r; i++) {
            if (matrix[i][0] == 0) {
                allCol = true;
                break;
            }
        }
        System.out.println(allRow + " AllRows " + allCol + " AllCols");
        System.out.println(Arrays.deepToString(matrix) + "   Initial Array");
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix) + "   1st row and 1st col modified");
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix) + "  Elements inside ");
        if (allRow) {
            for (int i = 0; i < r; i++) {
                matrix[0][i] = 0;
            }
        }
        if (allCol) {
            for (int i = 0; i < r; i++) {
                matrix[i][0] = 0;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void rotateby90(int matrix[][], int n) {
        // https://www.geeksforgeeks.org/problems/rotate-by-90-degree-1587115621/1 --
        // anticlockwise
        // int[][] mat = new int[n][n];
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < n; j++) {
        // mat[n - 1 - j][i] = matrix[i][j];
        // }
        // }
        // matrix = mat;
        // ---------------------------- Optimization ---------------------------
        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // reverse the columns
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n / 2; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public static ArrayList<Integer> spirallyTraverse(int matrix[][], int r, int c) {
        // https://www.geeksforgeeks.org/problems/spirally-traversing-a-matrix-1587115621/1
        ArrayList<Integer> arr = new ArrayList<>();
        int top = 0;
        int bottom = r - 1;
        int left = 0;
        int right = c - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                arr.add(matrix[top][i]);
            }
            top++;
            for (int j = top; j <= bottom; j++) {
                arr.add(matrix[j][right]);
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    arr.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int j = bottom; j >= top; j--) {
                    arr.add(matrix[j][left]);
                }
                left++;
            }
        }

        return arr;
    }

    public static int findSubArraySum(int arr[], int N, int k) {
        // https://www.geeksforgeeks.org/problems/subarrays-with-sum-k/1
        int preSum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            preSum += arr[i];
            if (map.containsKey(preSum - k)) {
                count = count + map.get(preSum - k);
            }

            if (map.containsKey(preSum)) {
                map.put(preSum, map.get(preSum) + 1);
            } else {
                map.put(preSum, 1);
            }
        }
        return count;
    }

    public static ArrayList<Long> nthRowOfPascalTriangle(int n) {
        ArrayList<Long> arr = new ArrayList<>();
        n = n - 1;
        for (int r = 0; r < n; r++) {
            long fac = comb(n, r);
            arr.add(fac);
        }
        return arr;
    }

    public static long comb(int n, int r) {
        long fac = 1;
        for (int i = 1; i <= r; i++) {
            fac = fac * (n - i + 1);
            fac = fac / i;
        }
        return fac;
    }

    public static List<Integer> majorityElement2(int[] nums) {
        // Given an integer array of size n, find all elements that appear more than ⌊
        // n/3 ⌋ times.
        ArrayList<Integer> arr = new ArrayList<>();
        int ele1 = 0;
        int count1 = 0;
        int ele2 = Integer.MIN_VALUE;
        int count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count1 == 0 && ele2 != nums[i]) {
                ele1 = nums[i];
                count1++;
            } else if (count2 == 0 && ele1 != nums[i]) {
                ele2 = nums[i];
                count2++;
            } else if (ele1 == nums[i]) {
                count1++;
            } else if (ele2 == nums[i]) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ele1) {
                count1++;
            }
            if (nums[i] == ele2) {
                count2++;
            }
        }
        if (count1 > Math.floor(nums.length / 3)) {
            arr.add(ele1);
        }
        if (count2 > Math.floor(nums.length / 3)) {
            arr.add(ele2);
        }
        return arr;
    }

    public static void test(int[] arr) {
        // Integer[] otArr = { 12, 33, 44, 55, 66, 77 };
        // ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(otArr));
        int a = 1000000000;
        System.out.println(-3 * a - 294967296);
    }

    public static HashSet<ArrayList<Integer>> findTriplets(int arr[]) {
        // https://www.geeksforgeeks.org/problems/find-triplets-with-zero-sum/1
        // O(n^2)
        int n = arr.length;
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        // for (int i = 0; i < n; i++) {
        // HashMap<Integer, Integer> hash = new HashMap<>();
        // for (int j = i + 1; j < n; j++) {
        // if (hash.containsKey(-(arr[i] + arr[j]))) {
        // ArrayList<Integer> a = new ArrayList<>();
        // a.add(arr[i]);
        // a.add(arr[j]);
        // int rem = -arr[i] - arr[j];
        // a.add(rem);
        // Collections.sort(a);
        // set.add(a);
        // }
        // hash.put(arr[j], j);
        // }
        // }
        // --------------------------------------- Optimization-----------------
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            int k = n - 1;
            for (int j = i + 1; j < n; j++) {
                if (j >= k) {
                    break;
                }
                if (arr[i] + arr[k] + arr[j] < 0) {
                    // continue;
                } else if (arr[i] + arr[k] + arr[j] > 0) {
                    j--;
                    k--;
                } else { // arr[i] + arr[k] + arr[j] == 0
                    ArrayList<Integer> a = new ArrayList<>();
                    a.add(arr[i]);
                    a.add(arr[j]);
                    a.add(arr[k]);
                    set.add(a);
                }

            }
        }
        return set;
    }

    public static ArrayList<ArrayList<Integer>> fourSum(int[] arr, int t) {
        // https://www.geeksforgeeks.org/problems/find-all-four-sum-numbers1732/1
        // Find All Four Sum Numbers
        int n = arr.length;
        HashSet<ArrayList<Integer>> aoa = new HashSet<>();
        // for (int i = 0; i < n; i++) {
        // for (int j = i + 1; j < n; j++) {
        // HashSet<Integer> set = new HashSet<>();
        // for (int k = j + 1; k < n; k++) {
        // if (set.contains(t - arr[i] - arr[j] - arr[k])) {
        // ArrayList<Integer> a = new ArrayList<>();
        // a.add(arr[i]);
        // a.add(arr[j]);
        // a.add(arr[k]);
        // Collections.sort(a);
        // aoa.add(a);
        // }
        // set.add(arr[k]);
        // }
        // }
        // }
        // Conversion
        // ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        // for (ArrayList<Integer> list : aoa) {
        // arrayList.add(new ArrayList<>(list));
        // }

        // -----------------------------------Optimization ---------------------------
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1])
                    continue;
                int l = n - 1;
                for (int k = j + 1; k < n; k++) {
                    if (k >= l) {
                        break;
                    }
                    long sum = arr[i] % 1000000007;
                    sum = sum + arr[j] % 1000000007;
                    sum = sum + arr[k] % 1000000007;
                    sum = sum + arr[l] % 1000000007;
                    if (sum < t) {
                        // continue
                    } else if (sum > t) {
                        k--;
                        l--;
                    } else { // arr[i] + arr[k] + arr[j] arr[l] == t
                        ArrayList<Integer> a = new ArrayList<>();
                        a.add(arr[i]);
                        a.add(arr[j]);
                        a.add(arr[k]);
                        a.add(arr[l]);
                        aoa.add(a);
                    }
                }
            }
        }
        // Conversion
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        for (ArrayList<Integer> list : aoa) {
            arrayList.add(new ArrayList<>(list));
        }
        return arrayList;

    }

    public static int maxSubarrayXOR(int arr[]) {
        // https://www.geeksforgeeks.org/problems/maximum-xor-subarray--141631/1
        // Brute Force
        int n = arr.length;
        // int max = Arrays.stream(arr).max().getAsInt();
        // for (int i = 0; i < n; i++) {
        // int val = arr[i];
        // for (int j = i+1; j < n; j++) {
        // val = val ^ arr[j];
        // max = Math.max(val, max);
        // }
        // }
        // return max;
        // ------------------------------------------Optimization
        // -----------------------------------
        HashMap<Integer, Integer> map = new HashMap<>();
        int xorSum = 0;
        for (int i = 0; i < n; i++) {
            xorSum = xorSum ^ arr[i];
            map.put(xorSum, i);
        }
        // a ^ b ^ c = sum
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : map.keySet()) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        if ((max ^ min) > max) {
            return max ^ min;
        } else {
            return max;
        }
    }

    public static int[][] overlappedInterval(int[][] Intervals) {
        // https://www.geeksforgeeks.org/problems/overlapping-intervals--170633/1
        // Arrays.sort(Intervals, (a, b) -> Integer.compare(a[0], b[0])); // increasing
        // order
        // Sort based on first column and then second column
        Arrays.sort(Intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // Compare first column
                int compareFirst = Integer.compare(o1[0], o2[0]);
                if (compareFirst != 0) {
                    return compareFirst;
                } else {
                    // If first column elements are equal, compare second column
                    return Integer.compare(o1[1], o2[1]);
                }
            }
        });
        List<List<Integer>> aoa = new ArrayList<>();
        int n = Intervals.length;
        for (int i = 0; i < n; i++) {
            // if the current interval does not
            // lie in the last interval:
            if (aoa.isEmpty() || Intervals[i][0] > aoa.get(aoa.size() - 1).get(1)) {
                aoa.add(Arrays.asList(Intervals[i][0], Intervals[i][1]));
            } // if the current interval
              // lies in the last interval:
            else {
                aoa.get(aoa.size() - 1).set(1,
                        Math.max(aoa.get(aoa.size() - 1).get(1), Intervals[i][1]));
            }
        }
        int numRows = aoa.size();
        int numCols = aoa.get(0).size(); // Assuming all inner lists have the same size

        int[][] matrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = aoa.get(i);
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = row.get(j);
            }
        }

        return matrix;
    }
    public static void merge(long arr1[], long arr2[], int n, int m) 
    {
        // Merge Without Extra Space  -> https://www.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1
        // int j=0;
        // for(int i=0;i<n;i++){
        //     if(arr1[i]>arr2[j]){
        //         long temp = arr1[i];
        //         arr1[i] = arr2[j];
        //         arr2[j] = temp;
        //         Arrays.sort(arr2);
        //     }
        // }

        //---------------------------------- Optimization 
        int left = 0;   // for arr2
        int right = n - 1;   //for arr1
        while (right >= 0 && left <= m - 1) {
            if (arr2[left] < arr1[right]) {
                long temp = arr1[right];
                arr1[right] = arr2[left];
                arr2[left] = temp;
                left++;
                right--;
            } else {
                break;
            }
        }
        Arrays.sort(arr2);
        Arrays.sort(arr1);
    }

}
