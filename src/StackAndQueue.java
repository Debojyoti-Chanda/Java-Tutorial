import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackAndQueue {
    public static void main(String[] args) {
        // System.out.println(infixToPostfix("(a-(b^c))+(d)"));
        // System.out
        // .println(Arrays.toString(nextGreaterElement(new int[] { 6, 8, 0, 1, 3 }, new
        // int[] { 6, 8, 0, 1, 3 })));
        // System.out.println(sumSubarrayMins(new int[] { 3, 1, 2, 4 }, 4));
        // System.out.println(asteroidCollision(6, new int[] { 1, -7, -3, -12, 1, -8 }));
        // System.out.println(asteroidCollision(new int[] { -2, -2, 1, -2 }));
    }

    public static String infixToPostfix(String exp) {
        // Your code here
        Stack<Character> stk = new Stack<>();
        String st = "";
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('^', 3);
        map.put('*', 2);
        map.put('/', 2);
        map.put('+', 1);
        map.put('-', 1);
        for (int i = 0; i < exp.length(); i++) {
            char isym = exp.charAt(i);
            if (Character.isLetterOrDigit(isym)) {
                st = st + isym;
            } else if (stk.size() == 0 || (stk.peek() == '(' && isym != ')')) {
                stk.push(isym);
            } else if (isym == '(') {
                stk.push(isym);
            } else if (isym == ')') {
                char sym = stk.pop();
                while (sym != '(') {
                    st = st + sym;
                    sym = stk.pop();
                }
            } else if (map.get(isym) > map.get(stk.peek())) {
                stk.push(isym);
            } else if (map.get(isym) <= map.get(stk.peek())) {
                char sym = stk.pop();
                st = st + sym;
                i--;
            }
        }
        while (stk.size() > 0) {
            st = st + stk.pop();
        }
        return st;
    }

    public static String preToInfix(String pre_exp) {
        // https://www.geeksforgeeks.org/problems/prefix-to-infix-conversion/1
        Stack<String> st = new Stack<>();
        for (int i = pre_exp.length() - 1; i >= 0; i--) { // difference
            String ch = pre_exp.substring(i, i + 1);
            char sym = pre_exp.charAt(i);
            if (Character.isLetterOrDigit(sym)) {
                st.push(ch);
            } else if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("^")) {
                String ele1 = st.pop();
                String ele2 = st.pop();
                String str = "(" + ele1 + ch + ele2 + ")"; // difference
                st.push(str);
            }
        }
        return st.pop();
    }

    public static String postToInfix(String exp) {
        // https://www.geeksforgeeks.org/problems/postfix-to-infix-conversion/1
        Stack<String> st = new Stack<>();
        for (int i = 0; i < exp.length(); i++) { // difference
            String ch = exp.substring(i, i + 1);
            char sym = exp.charAt(i);
            if (Character.isLetterOrDigit(sym)) {
                st.push(ch);
            } else if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("^")) {
                String ele1 = st.pop();
                String ele2 = st.pop();
                String str = "(" + ele2 + ch + ele1 + ")"; // difference
                st.push(str);
            }
        }
        return st.pop();
    }

    public static String preToPost(String pre_exp) {
        // https://www.geeksforgeeks.org/problems/prefix-to-postfix-conversion/1
        Stack<String> st = new Stack<>();
        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            String ch = pre_exp.substring(i, i + 1);
            char sym = pre_exp.charAt(i);
            if (Character.isLetterOrDigit(sym)) {
                st.push(ch);
            } else if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("^")) {
                String ele1 = st.pop();
                String ele2 = st.pop();
                String str = ele1 + ele2 + ch; // difference
                st.push(str);
            }
        }
        return st.pop();
    }

    public static String postToPre(String post_exp) {
        // https://www.geeksforgeeks.org/problems/postfix-to-prefix-conversion/0
        Stack<String> st = new Stack<>();
        for (int i = 0; i < post_exp.length(); i++) {
            String ch = post_exp.substring(i, i + 1);
            char sym = post_exp.charAt(i);
            if (Character.isLetterOrDigit(sym)) {
                st.push(ch);
            } else if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("^")) {
                String ele1 = st.pop();
                String ele2 = st.pop();
                String str = ch + ele2 + ele1; // difference
                st.push(str);
            }
        }
        return st.pop();
    }

    public static long[] nextLargerElement(long[] arr, int n) {
        // Next Greater Element --
        // https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1
        Stack<Long> stk = new Stack<>();
        long[] val = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            if (stk.empty()) {
                val[i] = -1;
                stk.push(arr[i]);
            } else if (stk.peek() > arr[i]) {
                val[i] = stk.peek();
                stk.push(arr[i]);
            } else if (stk.peek() <= arr[i]) {
                while (stk.peek() <= arr[i]) {
                    stk.pop();
                    if (stk.empty()) {
                        break;
                    }
                }
                if (stk.empty()) {
                    val[i] = -1;
                } else {
                    val[i] = stk.peek();
                }
                stk.push(arr[i]);
            }
        }
        return val;
    }

    public static int[] nextLargerElement1(int[] arr, int n) {
        // when the array is circular in nature --
        // https://www.geeksforgeeks.org/problems/next-greater-element-2/1
        Stack<Integer> stk = new Stack<>();
        int[] val = new int[n];
        for (int i = 2 * n - 1; i >= 0; i--) {
            if (stk.empty()) {
                val[i % n] = -1;
                stk.push(arr[i % n]);
            } else if (stk.peek() > arr[i % n]) {
                val[i % n] = stk.peek();
                stk.push(arr[i % n]);
            } else if (stk.peek() <= arr[i % n]) {
                while (stk.peek() <= arr[i % n]) {
                    stk.pop();
                    if (stk.empty()) {
                        break;
                    }
                }
                if (stk.empty()) {
                    val[i % n] = -1;
                } else {
                    val[i % n] = stk.peek();
                }
                stk.push(arr[i % n]);
            }
        }
        return val;
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stk = new Stack<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : nums2) {
            while (!stk.isEmpty() && stk.peek() < v) {
                m.put(stk.pop(), v);
            }
            stk.push(v);
        }
        int n = nums1.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = m.getOrDefault(nums1[i], -1);
        }
        return ans;
    }

    public static int[] count_NGEs(int N, int arr[], int queries, int indices[]) {
        // Brute Force -- (N^2 * queries)
        int[] count = new int[queries];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < queries; j++) {
                if (i == indices[j]) {
                    int temp = i;
                    int k = i + 1;
                    while (k < N) {
                        if (arr[k++] > arr[temp]) {
                            count[j]++;
                        }
                    }

                }
            }
        }
        return count;
    }

    public static long trappingWater(int arr[], int n) {
        // Your code here
        // Maximum left array bar -- prefix max.
        int leftbar[] = new int[n];
        leftbar[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftbar[i] = Math.max(arr[i], leftbar[i - 1]);
        }

        // Maximum right array bar -- sufix max
        int rightbar[] = new int[n];
        rightbar[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightbar[i] = Math.max(arr[i], rightbar[i + 1]);
        }
        long trapped = 0;
        for (int i = 0; i < n; i++) {
            long water = Math.min(leftbar[i], rightbar[i]);
            trapped += water - arr[i];
        }
        return trapped;
    }

    public static long trappingWater1(int arr[], int n) {
        // 2 pointer approach
        int i = 0;
        int leftMax = arr[i];
        long sum = 0;
        int j = n - 1;
        int rightMax = arr[j];

        while (i < j) {
            if (leftMax <= rightMax) {
                sum += leftMax - arr[i];
                i++;
                leftMax = Math.max(leftMax, arr[i]);
            } else {
                sum += rightMax - arr[j];
                j--;
                rightMax = Math.max(rightMax, arr[j]);
            }
        }
        return sum;
    }

    static class pair {
        int value, n_larger;

        public pair(int value, int n_larger) {
            this.value = value;
            this.n_larger = n_larger;
        }
    }

    static int sumSubarrayMins(int A[], int n) {
        int[] left = new int[n];
        // the length of strictly larger numbers on the left of A[i]
        int[] right = new int[n];
        // the length of strictly larger numbers on the right of A[i]

        Stack<pair> s1 = new Stack<pair>();
        Stack<pair> s2 = new Stack<pair>();

        // getting number of element strictly larger
        // than A[i] on Left.
        for (int i = 0; i < n; ++i) {
            int cnt = 1;

            // get elements from stack until element
            // greater than A[i] found
            while (!s1.isEmpty() &&
                    (s1.peek().value) > A[i]) {
                cnt += s1.peek().n_larger;
                s1.pop();
            }

            s1.push(new pair(A[i], cnt));
            left[i] = cnt;
        }

        // getting number of element larger
        // than A[i] on Right.
        for (int i = n - 1; i >= 0; --i) {
            int cnt = 1;

            // get elements from stack until element
            // greater or equal to A[i] found
            while (!s2.isEmpty() &&
                    (s2.peek().value) >= A[i]) {
                cnt += s2.peek().n_larger;
                s2.pop();
            }

            s2.push(new pair(A[i], cnt));
            right[i] = cnt;
        }

        int result = 0;

        // calculating required resultult
        for (int i = 0; i < n; ++i)
            result = (result + A[i] * left[i] *
                    right[i]);

        return result;
    }

    //     public static int[] asteroidCollision(int n, int[] arr) {
    //         // code here
    //         Stack<Integer> st = new Stack<>();

    //         for (int i = 0; i < n; i++) {
    //             if (st.isEmpty() || arr[i] > 0) {
    //                 st.add(arr[i]);
    //             } else if (arr[i] < 0 && st.peek() < 0) {
    //                 st.add(arr[i]);
    //             } else {
    //                 boolean same = false;
    //                 while (!st.isEmpty()) {
    //                     //stack is larger
    //                     if (st.peek() < 0 && arr[i] < 0) {
    //                         st.add(arr[i]);
    //                         break;
    //                     }
    //                     if (st.peek() > Math.abs(arr[i])) {
    //                         break;
    //                     } else if (st.peek() < Math.abs(arr[i])) {//arr[i] is larger
    //                         st.pop();
    //                     } else if (st.peek() == Math.abs(arr[i])) {//both are same
    //                         st.pop();
    //                         same = true;
    //                         break;
    //                     }
    //                 }
    //                 if (st.isEmpty() && same == false) {
    //                     st.add(arr[i]);
    //                 }
    //             }
    //         }
    //         int size = st.size();
    //         int[] array = new int[size];

    //         for (int i = 0; i < size; i++) {
    //             array[i] = st.get(i);
    //         }
    //         return array;
    //     }
    // }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        int n = asteroids.length;
        for (int i = 0; i < n; i++) {
            if (st.isEmpty() || asteroids[i] > 0) {
                st.add(asteroids[i]);
            } else { // asteroids[i] < 0 && !st.isEmpty()
                if (st.peek() > 0) {
                    while (!st.isEmpty() && Math.abs(st.peek()) < Math.abs(asteroids[i])) {
                        st.pop();
                    }
                    if (!st.isEmpty() && Math.abs(st.peek()) == Math.abs(asteroids[i])) {
                        st.pop();
                        continue;
                    } else if (!st.isEmpty() && Math.abs(st.peek()) > Math.abs(asteroids[i])) {
                        continue;
                    }
                }

                st.add(asteroids[i]);
            }
        }
        int size = st.size();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = st.get(i);
        }
        return array;
    }

}

// Code tobe updated in LMS
// 501310062410
