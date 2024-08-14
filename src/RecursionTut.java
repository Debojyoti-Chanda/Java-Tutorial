import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RecursionTut {
    public static void main(String[] args) {
        // System.out.println(atoi("123"));
        // System.out.println(power(7, 7));
        // Stack<Integer> stk = new Stack<>();
        // stk.push(1);
        // stk.push(2);
        // stk.push(3);
        // reverse(stk);
        // String values = Arrays.toString(stk.toArray());
        // System.out.println(values);
        // List<String> ls = AllPossibleStrings("aa");
        // System.out.println(Arrays.toString(ls.toArray()));
        // System.out.println(checkSubsequenceSum(5, new int[] { 1, 7, 2, 9, 10 }, 6));
        // ArrayList<Integer> A = new ArrayList<>();
        // A.add(7);
        // A.add(2);
        // A.add(6);
        // A.add(5);

        // ArrayList<ArrayList<Integer>> ara = combinationSum(A, 4);
        // for (int i = 0; i < ara.size(); i++) {
        // for (int j = 0; j < ara.get(i).size(); j++) {
        // System.out.print(ara.get(i).get(j) + " ");
        // }
        // System.out.println();
        // }
        // int[] num = { 1, 2, 5 };
        // List<List<Integer>> ara = CombinationSum2_1(num, 3, 7);
        // for (int i = 0; i < ara.size(); i++) {
        // for (int j = 0; j < ara.get(i).size(); j++) {
        // System.out.print(ara.get(i).get(j) + " ");
        // }
        // System.out.println();
        // }
        // ArrayList<Integer> arr = new ArrayList<>();
        // arr.add(2);
        // arr.add(3);
        // ArrayList<Integer> a = subsetSums(arr, arr.size());
        // for (int number : a) {
        // System.out.print(number + " ");
        // }
        // ArrayList<ArrayList<Integer>> a = combinationSum3(3,7);
        // for (int i = 0; i < a.size(); i++) {
        // for (int j = 0; j < a.get(i).size(); j++) {
        // System.out.print(a.get(i).get(j) + " ");
        // }
        // System.out.println();
        // }
        // List<String> ls = find_permutation("ABC");
        // for (String item : ls) {
        // System.out.println(item);
        // }
        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        System.out.println(exist(board, "ABCCED"));
    }

    public static int atoi(String s) {
        int n = s.length();
        int val;
        boolean isZero = true;
        String temp = s;
        if (s.charAt(0) == '-') {
            temp = s.substring(1, s.length());
        }
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != '0') {
                isZero = false;
                break;
            }
        }
        if (isZero) {
            return 0;
        }
        if (s.charAt(0) == '-') {
            n = n - 1;
            val = helper(s.substring(1, s.length()), n);
            val = -1 * val;
        } else {
            val = helper(s, n);
        }

        if (val == 0) {
            return -1;
        }
        return val;
    }

    public static int helper(String str, int n) {
        if (str == "" || !str.matches("^\\d*$")) {
            return 0;
        }
        if (n == 1) {
            return str.charAt(0) - '0';
        }
        return (10 * helper(str, n - 1) + str.charAt(n - 1) - '0');
    }

    public static long power(int N, int R) {
        // Power Of Numbers -
        // https://www.geeksforgeeks.org/problems/power-of-numbers-1587115620/1
        if (R == 0) {
            return 1;
        }
        long temp = power(N, R / 2) % 1000000007;
        long result = (temp * temp) % 1000000007;
        if (R % 2 == 1)
            result = (N * result) % 1000000007;
        return result;
    }

    public static int countGoodNumbers(long n) {
        int count = helper(n, 0);
        return count;
    }

    public static int helper(long n, long pt) {
        if (pt == n) {
            return 1;
        }
        if (pt % 2 == 0) {
            return 5 * helper(n, pt + 1);
        } else {
            return 4 * helper(n, pt + 1);
        }
    }

    public static Stack<Integer> sort(Stack<Integer> s) {
        // Sort a stack -- https://www.geeksforgeeks.org/problems/sort-a-stack/1
        fun(s);
        return s;
    }

    public static void insert_at_top(Stack<Integer> s, int x) {
        if (s.size() == 0)
            s.push(x);
        else if (s.peek() > x) {
            int y = s.peek();
            s.pop();
            insert_at_top(s, x);
            s.push(y);
        } else {
            s.push(x);
        }
    }

    public static void fun(Stack<Integer> s) {
        if (s.size() > 0) {
            int x = s.peek();
            s.pop();
            fun(s);
            insert_at_top(s, x);
        }
    }

    public static void reverse(Stack<Integer> s) {
        if (s.size() > 0) {
            Integer x = s.peek();
            s.pop();
            reverse(s);
            insertAtBottom(s, x);
        }
    }

    public static void insertAtBottom(Stack<Integer> s, Integer x) {
        if (s.isEmpty()) {
            s.push(x);
        } else {
            Integer y = s.peek();
            s.pop();
            insertAtBottom(s, x);
            s.push(y);
        }
    }

    public static List<String> generateBinaryStrings(int n) {
        // Generate all binary strings --
        // https://www.geeksforgeeks.org/problems/generate-all-binary-strings/1
        List<String> ls = new LinkedList<>();
        generateBinary("", ls, n);
        return ls;
    }

    public static void generateBinary(String s, List<String> ls, int n) {
        if (s.length() == n) {
            ls.add(s);
            return;
        }
        s = s + "0";
        generateBinary(s, ls, n);
        s = s.substring(0, s.length() - 1);
        // which do not contain consecutive 1s.
        if (s.length() != 0 && s.charAt(s.length() - 1) == '1') {
            return;
        }
        s = s + "1";
        generateBinary(s, ls, n);
    }

    public static List<String> AllParenthesis(int n) {
        // Generate Parentheses ---
        // https://www.geeksforgeeks.org/problems/generate-all-possible-parentheses/1
        List<String> ls = new LinkedList<>();
        generateParen(ls, n, 0, "", 0);
        return ls;
    }

    public static void generateParen(List<String> ls, int n, int countParen, String st, int count) {
        // count denotes each ( and ) ... if >0 more ( ... if <0 more ) .. if 0 balanced
        // .... countParen denotes each ()
        if (countParen > n)
            return;
        if (countParen == n && count == 0) {
            ls.add(st);
            return;
        }
        st = st + "(";
        count++;
        countParen++;
        if (count > countParen) {
            return;
        }
        generateParen(ls, n, countParen, st, count);
        st = st.substring(0, st.length() - 1);
        countParen--;
        count--;
        st = st + ")";
        count--;
        if (count < 0) {
            return;
        }
        generateParen(ls, n, countParen, st, count);
    }

    public static List<String> AllPossibleStrings(String s) {
        // Power Set -- https://www.geeksforgeeks.org/problems/power-set4302/1
        List<String> ls = new ArrayList<>();
        generatePowerSet(ls, 0, s, "");
        Collections.sort(ls);
        return ls;
    }

    public static void generatePowerSet(List<String> ls, int index, String s, String st) {
        if (index > s.length()) {
            return;
        }
        for (int i = index; i < s.length(); i++) {
            st = st + Character.toString(s.charAt(i));
            ls.add(st);
            generatePowerSet(ls, i + 1, s, st);
            st = st.substring(0, st.length() - 1);
        }
    }

    public static void generatePowerSet1(List<String> ls, int index, String s, String st) {
        if (index == s.length()) {
            ls.add(st);
            return;
        }
        generatePowerSet1(ls, index + 1, s, st + s.charAt(index));
        generatePowerSet1(ls, index + 1, s, st);
    }

    public static boolean checkSubsequenceSum(int N, int[] arr, int K) {
        // Check if there exists a subsequence with sum K --
        // https://www.geeksforgeeks.org/problems/check-if-there-exists-a-subsequence-with-sum-k/1
        boolean val = checkSumK1(arr, 0, K);
        return val;
    }

    public static boolean checkSumK(int[] arr, int index, int k, int sum) {
        if (sum == k) {
            return true;
        }
        if (index == arr.length && sum != k) {
            return false;
        }
        boolean val;
        sum = sum + arr[index];
        val = checkSumK(arr, index + 1, k, sum);
        sum = sum - arr[index];
        if (val == true) {
            return val;
        }
        val = checkSumK(arr, index + 1, k, sum);
        return val;
    }

    public static boolean checkSumK1(int[] arr, int index, int k) {
        // O(2^n)
        if (index > arr.length || k < 0) {
            return false;
        }
        boolean val = false;
        if (k == 0) {
            return true;
        } else {
            for (int i = index; i < arr.length; i++) {
                val = val || checkSumK1(arr, i + 1, k - arr[i]);
            }
        }
        return val;
    }

    public static int numSubseq(int[] nums, int target) {
        // 1498. Number of Subsequences That Satisfy the Given Sum Condition ---
        // https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/description/
        ArrayList<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<>();
        countSubseq(nums, aList, temp, 0, target);
        return aList.size();

    }

    public static void countSubseq(int[] nums, ArrayList<ArrayList<Integer>> aList, ArrayList<Integer> temp, int index,
            int target) {
        if (index == nums.length) {

            if (temp.size() != 0) {
                int value1 = Collections.min(temp) % 1000000007;
                int value2 = Collections.max(temp) % 1000000007;
                value1 = (value1 + value2) % 1000000007;
                if (value1 <= target) {
                    aList.add(temp);
                }
            }
            return;
        }
        temp.add(nums[index]);
        countSubseq(nums, aList, temp, index + 1, target);
        temp.remove(temp.size() - 1);
        countSubseq(nums, aList, temp, index + 1, target);
    }

    // public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer>
    // A, int B) {
    // // Combination Sum --
    // https://www.geeksforgeeks.org/problems/combination-sum-1587115620/1
    // ArrayList<ArrayList<Integer>> ala = new ArrayList<ArrayList<Integer>>();
    // ArrayList<Integer> temp = new ArrayList<>();
    // Collections.sort(A);
    // HashSet<ArrayList<Integer>> set = new HashSet<>();
    // combSum(ala,temp,B,A,0,set);
    // return ala;
    // }

    // public static void combSum(ArrayList<ArrayList<Integer>> ala,
    // ArrayList<Integer> temp, int B, ArrayList<Integer> A,
    // int index,HashSet<ArrayList<Integer>> set) {
    // if (B < 0) {
    // return;
    // }
    // if (B == 0) {
    // ArrayList<Integer> a = new ArrayList<>(temp);

    // addUniqueList(ala,set,a);
    // return;
    // }
    // for (int i = index; i < A.size(); i++) {
    // temp.add(A.get(i));
    // combSum(ala, temp, B - A.get(i), A, i,set);
    // temp.remove(temp.size() - 1);
    // combSum(ala, temp, B, A, i + 1,set);
    // }
    // }
    // public static void addUniqueList(ArrayList<ArrayList<Integer>> ala,
    // HashSet<ArrayList<Integer>> set, ArrayList<Integer> list) {
    // if (set.add(list)) { // set.add returns false if the list already exists in
    // the set
    // ala.add(list);
    // }
    // }
    public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        // add your code here
        HashSet<Integer> set = new HashSet<>(A);
        A.clear();
        A.addAll(set);
        Collections.sort(A);

        ArrayList<ArrayList<Integer>> listoflist = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        comb(0, B, A, listoflist, list);
        return listoflist;

    }

    public static void comb(int index, int target, ArrayList<Integer> A, ArrayList<ArrayList<Integer>> arr,
            ArrayList<Integer> list) {

        if (index >= A.size() || target < 0) {
            return;
        }
        if (index >= A.size() || target == 0) {
            arr.add(new ArrayList<Integer>(list));
            return;
        }
        list.add(A.get(index));
        comb(index, target - A.get(index), A, arr, list);
        list.remove(list.size() - 1);
        comb(index + 1, target, A, arr, list);
    }

    public static List<List<Integer>> CombinationSum2(int arr[], int n, int k) {
        // Combination Sum II --- Not Optimized
        // https://www.geeksforgeeks.org/problems/combination-sum-ii-1664263832/1
        HashSet<List<Integer>> lol = new HashSet<>();
        ArrayList<Integer> ls = new ArrayList<>();
        combSum2(lol, ls, arr, 0, k);
        // Convert HashSet to List
        List<List<Integer>> list = new ArrayList<>(lol);
        return list;
    }

    public static void combSum2(HashSet<List<Integer>> lol, ArrayList<Integer> ls, int arr[], int index, int k) {
        if (index == arr.length && k == 0) {
            lol.add(new ArrayList<Integer>(ls));
            return;
        }
        if (index >= arr.length || k < 0) {
            return;
        }
        ls.add(arr[index]);
        combSum2(lol, ls, arr, index + 1, k - arr[index]);
        ls.remove(ls.size() - 1);
        combSum2(lol, ls, arr, index + 1, k);
    }

    public static List<List<Integer>> CombinationSum2_1(int arr[], int n, int k) {
        // optimized --- Combination Sum II
        Arrays.sort(arr);
        List<List<Integer>> lol = new ArrayList<>();
        ArrayList<Integer> ls = new ArrayList<>();
        combSum2_1(lol, ls, arr, 0, k);
        return lol;
    }

    public static void combSum2_1(List<List<Integer>> lol, ArrayList<Integer> ls, int arr[], int index, int k) {
        if (index > arr.length || k < 0) {
            return;
        }
        if (k == 0) {
            lol.add(new ArrayList<>(ls));
            return;
        }
        int prev = -1;
        for (int i = index; i < arr.length; i++) {
            if (prev != arr[i]) {
                prev = arr[i];
                ls.add(arr[i]);
                combSum2_1(lol, ls, arr, i + 1, k - arr[i]);
                ls.remove(ls.size() - 1);
            } else {
                continue;
            }
        }
    }

    public static ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        // SubSet Sum 1
        ArrayList<Integer> a = new ArrayList<>();
        subSum(arr, a, 0, 0);
        return a;
    }

    public static void subSum(ArrayList<Integer> arr, ArrayList<Integer> a, int sum, int index) {
        if (index >= arr.size()) {
            a.add(sum);
            return;
        }
        // a.add(sum);
        sum = sum + arr.get(index);
        subSum(arr, a, sum, index + 1);
        sum = sum - arr.get(index);
        subSum(arr, a, sum, index + 1);
    }

    public static ArrayList<ArrayList<Integer>> printUniqueSubsets(int[] nums) {
        // SubSet Sum 2 -- https://www.geeksforgeeks.org/problems/subset-sum-ii/1 ---
        // time exceeded
        Arrays.sort(nums);
        HashSet<ArrayList<Integer>> lol = new HashSet<>();
        ArrayList<Integer> a = new ArrayList<>();
        subSum2(lol, a, nums, 0);
        ArrayList<ArrayList<Integer>> lol1 = new ArrayList<>(lol);
        return lol1;
    }

    public static void subSum2(HashSet<ArrayList<Integer>> lol, ArrayList<Integer> a, int[] nums, int index) {

        if (index == nums.length) {
            lol.add(new ArrayList<>(a));
            return;
        }
        a.add(nums[index]);
        subSum2(lol, a, nums, index + 1);
        a.remove(a.size() - 1);
        subSum2(lol, a, nums, index + 1);
    }

    public static ArrayList<ArrayList<Integer>> printUniqueSubsets1(int[] nums) {
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>> lol = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        subSum21(lol, a, nums, 0);
        lol.remove(lol.size() - 1);
        return lol;
    }

    public static void subSum21(ArrayList<ArrayList<Integer>> lol, ArrayList<Integer> a, int[] nums, int index) {
        lol.add(new ArrayList<>(a));
        int prev = -1;
        for (int i = index; i < nums.length; i++) {
            if (prev != nums[i]) {
                prev = nums[i];
                a.add(nums[i]);
                subSum21(lol, a, nums, index + 1);
                a.remove(a.size() - 1);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> combinationSum3(int K, int N) {
        ArrayList<ArrayList<Integer>> lol = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        combSum3(lol, a, K, N, 1);
        return lol;
    }

    public static void combSum3(ArrayList<ArrayList<Integer>> lol, ArrayList<Integer> a, int K, int N, int index) {
        if (N == 0 && K == 0) {
            lol.add(new ArrayList<>(a));
            return;
        }
        if (K < 0 || index > 10) {
            return;
        }

        for (int i = index; i < 10; i++) {
            a.add(i);
            combSum3(lol, a, K - 1, N - i, i + 1);
            a.remove(a.size() - 1);
        }
    }

    public static List<String> letterCombinations(String digits) {
        // Letter Combinations of a Phone Number --
        // https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
        List<String> ls = new ArrayList<>();
        HashMap<Integer, char[]> map = new HashMap<>();
        map.put(2, new char[] { 'a', 'b', 'c' });
        map.put(3, new char[] { 'd', 'e', 'f' });
        map.put(4, new char[] { 'g', 'h', 'i' });
        map.put(5, new char[] { 'j', 'k', 'l' });
        map.put(6, new char[] { 'm', 'n', 'o' });
        map.put(7, new char[] { 'p', 'q', 'r', 's' });
        map.put(8, new char[] { 't', 'u', 'v' });
        map.put(9, new char[] { 'w', 'x', 'y', 'z' });
        phNoComb(ls, map, "", digits, 0);
        if (digits.equals("")) {
            ls.remove(0);
        }
        return ls;
    }

    public static void phNoComb(List<String> ls, HashMap<Integer, char[]> map, String temp, String digits, int index) {
        if (index == digits.length()) {
            ls.add(temp);
            return;
        }
        char digitChar = digits.charAt(index);
        int digit = Character.getNumericValue(digitChar);
        // System.out.println(digit);
        char[] arr = map.get(digit);

        for (int i = 0; i < arr.length; i++) {
            temp = temp + arr[i];
            phNoComb(ls, map, temp, digits, index + 1);
            temp = temp.substring(0, temp.length() - 1);
        }
    }

    public List<List<String>> partition(String s) {
        var res = new ArrayList<List<String>>();
        dfs(s.toCharArray(), 0, res, new ArrayList<>());
        return res;
    }

    private void dfs(char[] s, int start, List<List<String>> res, List<String> cur) {
        if (s.length == start) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i < s.length; i++) {
            if (isP(s, start, i)) {
                cur.add(new String(s, start, i - start + 1));
                dfs(s, i + 1, res, cur);
                cur.removeLast();
            }
        }
    }

    private boolean isP(char[] s, int start, int end) {
        int len = end - start + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s[start + i] != s[start + len - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static List<String> find_permutation(String S) {
        // Code here
        // Convert the string to a character array
        char[] charArray = S.toCharArray();

        // Sort the character array in lexicographic order
        Arrays.sort(charArray);

        // Convert the sorted character array back to a string
        S = new String(charArray);
        HashSet<String> ds = new HashSet<>();
        boolean freq[] = new boolean[S.length()];
        recurPermute(S, ds, "", freq);
        List<String> list = new ArrayList<>(ds);
        return list;
    }

    public static void recurPermute(String S, HashSet<String> ds, String temp, boolean freq[]) {
        if (temp.length() == S.length()) {
            ds.add(temp);
            return;
        }
        for (int i = 0; i < S.length(); i++) {
            if (!freq[i]) {
                freq[i] = true;
                temp = temp + S.charAt(i);
                recurPermute(S, ds, temp, freq);
                temp = temp.substring(0, temp.length() - 1);
                freq[i] = false;
            }

        }
    }

    private void recurPermute(int index, int[] nums, List<List<Integer>> ans) {
        if (index == nums.length) {
            // copy the ds to ans
            List<Integer> ds = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                ds.add(nums[i]);
            }
            ans.add(new ArrayList<>(ds));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            recurPermute(index + 1, nums, ans);
            swap(i, index, nums);
        }
    }

    private void swap(int i, int j, int[] nums) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        recurPermute(0, nums, ans);
        return ans;
    }

    public static boolean exist(char[][] board, String word) {
        int rowLen = board.length;
        int colLen = board[0].length;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (searchWord(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchWord(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(index)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '*'; // Mark the cell as visited
        // Explore the four neighboring directions: right, down, left, up
        int[][] offsets = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int[] offset : offsets) {
            int newRow = i + offset[0];
            int newCol = j + offset[1];
            if (searchWord(board, word, newRow, newCol, index + 1)) {
                return true;
            }
        }
        board[i][j] = temp; // Restore the cell's original value
        return false;
    }

}