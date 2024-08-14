import java.util.*;

public class StringTut {
    public static void main(String[] args) {
        // System.out.println(removeOuter("()"));
        // System.out.println(areIsomorphic("abc", "xyx"));
        // System.out.println(frequencySort("treeAA"));
        // System.out.println(maxDepth("((5+2)(c+e)((a)))"));
        // System.out.println(atoi("3a45"));
        // System.out.println(most_k_chars("abcd",3));
        // System.out.println(longestPalindrome("aadfaamm"));
        // System.out.println(expandFromCenter("aba", 0, 0));
        // System.out.println(reverseWords("i.like.this.program.very.much"));
        // System.out.println(beautySum("aaac"));
        System.out.println(exact_k_chars("abcdaabb",3));

    }

    public static String removeOuter(String s) {
        // https://www.geeksforgeeks.org/problems/outermost-parentheses/1
        String[] arr = s.split("");
        int count = 0;
        String val = "";
        for (String a : arr) {
            if (a.equals("(")) {
                count++;
                if (count > 1) {
                    val = val + a;
                }
            } else {
                if (count > 1) {
                    val = val + a;
                }
                count--;
            }
        }
        return val;
    }

    public static int compare(String a, String b) {
        int k = 0;
        int count = 0;
        while (k < a.length() && k < b.length()) {
            if (a.charAt(k) == b.charAt(k))
                count++;
            else
                return count;
            k++;
        }
        return count;
    }

    public static String longestCommonPrefix(String arr[], int n) {
        // code here
        if (n == 1)
            return arr[0];
        String a = "";
        int count = 0, ans = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            count = compare(arr[0], arr[i + 1]);
            if (count == 0)
                return "-1";

            if (count < ans)
                ans = count;
        }
        for (int i = 0; i < ans; i++) {
            a += arr[0].charAt(i);
        }
        return a;
    }

    public static boolean areIsomorphic(String str1, String str2) {
        // Your code here
        if (str1.length() != str2.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            if (map.containsKey(str1.charAt(i)) && map.get(str1.charAt(i)) == str2.charAt(i)) {
                continue;
            } else if (!map.containsKey(str1.charAt(i))) {
                map.put(str1.charAt(i), str2.charAt(i));
            } else {
                return false;
            }
        }
        HashMap<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            if (map2.containsKey(str2.charAt(i)) && map2.get(str2.charAt(i)) == str1.charAt(i)) {
                continue;
            } else if (!map2.containsKey(str2.charAt(i))) {
                map2.put(str2.charAt(i), str1.charAt(i));
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean areRotations(String s1, String s2) {
        // Your code here
        // for (int i = 0; i < s1.length(); i++) {
        // String sub = s1.substring(0, i + 1);
        // String rotated = s1.substring(i + 1, s1.length()) + sub;
        // if (rotated.equals(s2)) {
        // return true;
        // }
        // }
        // return false;
        // optimized
        String temp = s1 + s1;
        if (temp.contains(s2)) {
            return true;
        } else {
            return false;
        }
    }

    class Pair {
        char ch;
        int count;

        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static String frequencySort(String s) {
        // LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        // for (int i = 0; i < s.length(); i++) {
        // if (map.containsKey(s.charAt(i))) {
        // map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        // } else {
        // map.put(s.charAt(i), 1);
        // }
        // }
        // // Convert LinkedHashMap to a List of entries
        // List<Map.Entry<Character, Integer>> entryList = new
        // ArrayList<>(map.entrySet());

        // // Sort the List based on the values using a custom comparator
        // Collections.sort(entryList, new Comparator<Map.Entry<Character, Integer>>() {
        // @Override
        // public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character,
        // Integer> e2) {
        // // Compare values (e1.getValue() and e2.getValue()) for sorting
        // return - e1.getValue().compareTo(e2.getValue());
        // }
        // });

        // // Create a new LinkedHashMap to store the sorted entries
        // LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
        // for (Map.Entry<Character, Integer> entry : entryList) {
        // sortedMap.put(entry.getKey(), entry.getValue());
        // }

        // StringBuilder sb = new StringBuilder();
        // for (Character ch : sortedMap.keySet()) {
        // int count = sortedMap.get(ch);
        // while (count-- > 0) {
        // sb.append(ch);
        // }
        // }
        // return sb.toString();

        // ------------------------
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        // we have to sort it character wise
        List<Character> sort_ch = new ArrayList<>(map.keySet());
        sort_ch.sort((a, b) -> map.get(b) - map.get(a));

        StringBuilder ans = new StringBuilder();
        for (char c : sort_ch) {
            int freq = map.get(c);
            for (int i = 0; i < freq; i++) {
                ans.append(c);
            }
        }
        return ans.toString();

    }

    public static int maxDepth(String s) {
        // Maximum Nesting Depth of the Parentheses -
        // https://www.geeksforgeeks.org/problems/maximum-nesting-depth-of-the-parentheses/1
        int openCount = 0;
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openCount++;
            } else if (s.charAt(i) == ')') {
                openCount--;
            }
            maxCount = Math.max(maxCount, openCount);
        }
        return maxCount;
    }

    public static int getVal(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static int romanToDecimal(String str) {
        // https://www.geeksforgeeks.org/problems/roman-number-to-integer3201/1 -- Roman
        // Number to Integer
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i + 1 < str.length() && getVal(ch) < getVal(str.charAt(i + 1))) {
                sum = sum - getVal(ch);
            } else {
                sum += getVal(ch);
            }
        }
        return sum;

    }

    public static int atoi(String s) {
        int i;
        boolean isPositive = true;
        if (s.charAt(0) == '-') {
            i = 1;
            isPositive = false;
        } else {
            i = 0;
        }
        int num = 0;
        for (; i < s.length(); i++) {
            int number = s.charAt(i) - '0';
            if (number >= 0 && number <= 9) {
                num = (10 * num) + number;
            } else {
                return -1;
            }

        }
        if (!isPositive) {
            return (-1 * num);
        } else {
            return num;
        }
    }

    public static long most_k_chars(String S, int K) {
        // https://www.geeksforgeeks.org/problems/count-number-of-substrings4528/1
        // Given a string of lowercase alphabets, count all possible substrings (not
        // necessarily distinct) that have exactly k distinct characters.
        // Brute Force
        // int length = S.length();
        // long count = 0;
        // // Generate all substrings
        // for (int start = 0; start < length; start++) {
        // for (int end = start + 1; end <= length; end++) {
        // String substring = S.substring(start, end);
        // Set<Character> st = new HashSet<>();
        // for (int i = 0; i < substring.length(); i++) {
        // st.add(substring.charAt(i));
        // }
        // if (st.size() == K) {
        // count++;
        // }
        // }
        // }
        // return count;
        // Optimization 1 - O (n^2)
        // The idea is to count all the subarrays with at most K distinct characters and
        // then subtract all the subarrays with atmost K – 1 characters. That leaves us
        // with count of subarrays with exactly K distinct characters.
        if (S.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int num = 0, left = 0;
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
            while (map.size() > K) {
                map.put(S.charAt(left), map.getOrDefault(S.charAt(left), 0) - 1);
                if (map.get(S.charAt(left)) == 0) {
                    map.remove(S.charAt(left));
                }
                left++;
            }
            num += i - left + 1;
        }
        return num;
        // Optimization 2 -- Sliding window approch O(n)

    }

    public static long exact_k_chars(String s, int k) {
        return most_k_chars(s, k) - most_k_chars(s, k - 1);
    }

    public static String longestPalindrome(String s) {
        // https://www.geeksforgeeks.org/problems/longest-palindrome-in-a-string1956/1
        // --
        // -------------- Brute Force
        // int max = Integer.MIN_VALUE;
        // String val = "";
        // for(int i=0;i<S.length();i++){
        // for(int j=i;j<S.length();j++){
        // if(checkPalindrome(S.substring(i,j)) && S.substring(i,j).length() > max){
        // max = S.substring(i,j).length();
        // val = S.substring(i,j);
        // }
        // }
        // }
        // return val;
        // ----- Optimized 1 ----
        if (s.length() <= 1) {
            return s;
        }
        String maxStr = s.substring(0, 1);
        for (int i = 0; i < s.length() - 1; i++) {
            String odd = expandFromCenter(s, i, i);
            String even = expandFromCenter(s, i, i + 1);
            if (odd.length() > maxStr.length()) {
                maxStr = odd;
            }
            if (even.length() > maxStr.length()) {
                maxStr = even;
            }
        }
        return maxStr;
    }

    public static String expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public static boolean checkPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static String reverseWords(String S) {
        // your code here

        String[] arr = S.split("\\.");
        for (int i = 0; i < arr.length; i++) {
            String str = arr[i];
            StringBuilder reversed = new StringBuilder();
            for (int j = str.length() - 1; j >= 0; j--) {
                reversed.append(str.charAt(j));
            }
            arr[i] = reversed.toString();
        }
        String val = String.join(".", arr);
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == '.') {
                val = val + ".";
            } else {
                break;
            }
        }
        return val;
    }

    public static int beautySum(String s) {
        // https://www.geeksforgeeks.org/problems/sum-of-beauty-of-all-substrings-1662962118/1
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int j = i; j < s.length(); j++) {
                map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
                int least = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (Integer k : map.values()) {
                    if (least > k) {
                        least = k;
                    }
                    if (max < k) {
                        max = k;
                    }
                }
                sum += (max - least);
            }
        }
        return sum;
        // Optimized
        
        
    }

}
