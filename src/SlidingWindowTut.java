import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class SlidingWindowTut {
    public static void main(String[] args) {
        // ArrayList<Integer> ar = new ArrayList<>();
        // ar.add(100);
        // ar.add(200);
        // ar.add(300);
        // ar.add(400);
        // System.out.println(maximumSumSubarray(2,ar,ar.size()));
        // System.out.println(smallestSubWithSum(new int[]{7,6,5,9,1},5,15));
        // System.out.println(longestUniqueSubsttr("geeksforgeeks"));
        // System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2));
        // System.out.println(totalFruits(11, new int[] { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3,
        // 4 }));
        // System.out.println(characterReplacement("AABCDE",2));
        // System.out.println(numberOfSubarrays(new int[]{1,0,1,0,1},5,2));
        System.out.println(numberOfSubarrays(new int[] { 1, 1, 2, 1, 1 }, 3));

    }

    public static long maximumSumSubarray(int K, ArrayList<Integer> Arr, int N) {
        // Max Sum Subarray of size K ---
        // https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
        int temp = K;
        long sum = 0;
        long currSum = 0;
        for (int i = 0; i < N; i++) {
            if (temp > 0) {
                currSum = currSum + Arr.get(i);
                temp--;
                if (temp == 0) {
                    sum = currSum;
                }
                continue;
            }
            currSum = currSum + Arr.get(i) - Arr.get(i - K);
            if (sum < currSum) {
                sum = currSum;
            }
        }
        return sum;
    }

    public static int smallestSubWithSum(int arr[], int n, int x) {
        // Smallest subarray with sum greater than x --
        // https://www.geeksforgeeks.org/problems/smallest-subarray-with-sum-greater-than-x5651/1
        int l = 0;
        int r = 1;
        int sum = arr[0];
        int minL = n + 1;
        while (r <= n) {
            if (minL == 1)
                return 1;
            if (sum > x) {
                minL = Math.min(minL, r - l);
                sum -= arr[l];
                l++;
            } else if (r == n) {
                break;
            } else { // r!=n && sum<x
                sum += arr[r];
                r++;
            }
        }
        return (minL == n + 1) ? 0 : minL;

    }

    public static int longestUniqueSubsttr(String S) {
        // Length of the longest substring --
        // https://www.geeksforgeeks.org/problems/length-of-the-longest-substring3036/1
        int maxLen = 1;
        HashSet<Character> set = new HashSet<>();
        set.add(S.charAt(0));
        int front = 1;
        int back = 0;
        while (front <= S.length()) {
            if (!set.contains(S.charAt(front))) {
                set.add(S.charAt(front));
                front++;
                maxLen = Math.max(maxLen, front - back);
            } else {
                while (S.charAt(back) != S.charAt(front)) {
                    set.remove(S.charAt(back));
                    back++;
                }
                back++;
                front++;
                maxLen = Math.max(maxLen, front - back);
            }
        }
        return maxLen;
    }

    public static int longestOnes(int[] nums, int k) {
        // Max Consecutive Ones III --
        // https://leetcode.com/problems/max-consecutive-ones-iii/description/
        int temp = k;
        int front = 0;
        int back = 0;
        Queue<Integer> lastZero = new LinkedList<>();

        int maxLen = 0;
        while (front < nums.length) {
            if (nums[front] == 1) {
                front++;
                maxLen = Math.max(maxLen, front - back);
            } else {
                lastZero.add(front);
                if (temp >= 0) {
                    temp--;
                    if (temp == -1) {
                        back = lastZero.remove() + 1;
                        temp++;
                    }
                    front++;
                    maxLen = Math.max(maxLen, front - back);
                }

            }
        }
        return maxLen;
    }

    public static int totalFruits(int N, int[] fruits) {
        // Fruit Into Baskets --
        // https://www.geeksforgeeks.org/problems/fruit-into-baskets-1663137462/1
        int front = 0;
        int back = 0;
        int maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // <Fruit_number ,index>
        while (front < N) {
            if (map.isEmpty()) {
                map.put(fruits[front], front);
                front++;
                maxLen = Math.max(maxLen, front - back);
                continue;
            }
            if (map.containsKey(fruits[front])) {
                map.put(fruits[front], front);
                front++;
                maxLen = Math.max(maxLen, front - back);
            } else { // !map.containsKey(fruits[front])
                map.put(fruits[front], front);
                if (map.size() > 2) {

                    int minIndex = Integer.MAX_VALUE;
                    int fruitNum = -1;

                    // Iterate over the entries in the map
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        // Update the smallest value if a smaller value is found
                        if (entry.getValue() < minIndex) {
                            minIndex = entry.getValue();
                            fruitNum = entry.getKey();
                        }
                    }
                    back = minIndex + 1;
                    map.remove(fruitNum);
                }
                front++;
                maxLen = Math.max(maxLen, front - back);
            }
        }
        return maxLen;
    }

    static int characterReplacement(String S, int K) {
        // Longest Repeating Character Replacement --
        // https://www.geeksforgeeks.org/problems/longest-repeating-character-replacement/1
        // brurte force
        // int maxVal = 0;
        // for (int i = 0; i < S.length(); i++) {
        // HashMap<Character, Integer> map = new HashMap<>();
        // for (int j = i; j < S.length(); j++) {
        // map.put(S.charAt(j), map.getOrDefault(S.charAt(j), 0) + 1);
        // int maxFreq = Integer.MIN_VALUE;
        // for (Map.Entry<Character, Integer> entry : map.entrySet()) {
        // // Update the smallest value if a smaller value is found
        // if (entry.getValue() > maxFreq) {
        // maxFreq = entry.getValue();
        // }
        // }
        // if (((j - i + 1) - maxFreq) <= K) {
        // maxVal = Math.max((j - i + 1), maxVal);
        // }
        // }
        // }
        // return maxVal;
        // sliding window 2 pointer approch
        int first = 0;
        int last = 0;
        int maxVal = 0;
        int maxFreq = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (first < S.length()) {
            map.put(S.charAt(first), map.getOrDefault(S.charAt(first), 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(S.charAt(first)));

            if ((first - last + 1) - maxFreq > K) {
                map.put(S.charAt(last), map.getOrDefault(S.charAt(last), 0) - 1);
                last++;
                // maxVal = Math.max(maxVal, first - last);
            }
            maxVal = Math.max(maxVal, first - last + 1);
            first++;

        }
        return maxVal;
    }

    public static int numberOfSubarrays(int arr[], int N, int target) {
        // Binary subarray with sum ---
        // https://www.geeksforgeeks.org/problems/binary-subarray-with-sum/1
        // Given a binary array arr of size N and an integer target, return the number
        // of non-empty subarrays with a sum equal to target. Note : A subarray is the
        // contiguous part of the array.
        int preSum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            preSum += arr[i];
            if (map.containsKey(preSum - target)) {
                count = count + map.get(preSum - target);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public static long countSubarray(int N, int A[], long L, long R) {
        // Count the number of subarrays --
        // https://www.geeksforgeeks.org/problems/count-the-number-of-subarrays/1
        // Find count of subarrays with sum <= R
        long val1 = solve(A, N, R);
        // Find count of subarrays with sum < L (which is same as sum <= L-1)
        long val2 = solve(A, N, L - 1);
        return val1 - val2;
    }

    public static long solve(int A[], int N, long k) {
        long count = 0;
        int first = 0;
        int last = 0;
        long sum = 0;
        while (first < N) {
            sum += A[first];
            while (sum > k) {
                sum -= A[last];
                last++;
            }
            count += (long) (first - last + 1);
            first++;
        }
        return count;
    }

    public static int findSubArraySum(int arr[], int N, int k) {
        // Subarrays with sum K --
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
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        // Count Number of Nice Subarrays --
        // https://leetcode.com/problems/count-number-of-nice-subarrays/description/
        // Given an array of integers nums and an integer k. A continuous subarray is
        // called nice if there are k odd numbers on it.
        // Return the number of nice sub-arrays.
        int preCount = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                preCount += 1;
            }
            if (map.containsKey(preCount - k)) {
                count = count + map.get(preCount - k);
            }
            map.put(preCount, map.getOrDefault(preCount, 0) + 1);
        }
        return count;
    }

    public static int countSubstring(String s) {
        // Count Substring -- https://www.geeksforgeeks.org/problems/count-substring/1
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', -1);
        map.put('b', -1);
        map.put('c', -1);
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
            int min = Collections.min(map.values());
            count += min + 1;
        }
        return count;
    }

    public static long maxScore(int cardPoints[], int N, int k) {
        // Maximum point you can obtain from cards -- https://www.geeksforgeeks.org/problems/maximum-point-you-can-obtain-from-cards/0 
        // HashMap<Integer, Integer> map1 = new HashMap<>();
        // HashMap<Integer, Integer> map2 = new HashMap<>();
        // int sum1 = 0;
        // int sum2 = 0;
        // for (int i = 0; i < k; i++) {
        //     sum1 += cardPoints[i];
        //     map1.put(i, sum1);
        //     sum2 += cardPoints[N - 1 - i];
        //     map2.put(i, sum2);
        // }
        // map1.put(-1, 0);
        // map2.put(-1, 0);
        // int max = 0;
        // for (int i = 0; i <= k; i++) {
        //     max = Math.max(max, map1.get(k - 1 - i) + map2.get(-1 + i));
        // }
        // return max;

        // 
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=cardPoints[i];
        }
        int curr=sum;
        for(int i=k-1;i>=0;i--){
            curr-=cardPoints[i];
            curr+=cardPoints[N-k+i];
            sum=Math.max(curr,sum);
        }
        return sum;
    
    }
}
