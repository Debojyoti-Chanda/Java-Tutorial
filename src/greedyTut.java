import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class greedyTut {
    public static void main(String[] args) throws NumberFormatException, IOException {
        // System.out.println(Arrays.toString(minPartition(43).toArray()));
        // System.out.println(lemonadeChange(5, new int[] { 5, 5, 10, 10, 20 }));
        // System.out.println(minJumps(new int[] { 1, 0, 0, 0 }, 4));
        // System.out.println(findPlatform(new int[]{900, 940, 950, 1100, 1500, 1800},new int[]{910, 1200, 1120, 1130, 1900, 2000},6));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //testcases
		int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String inputLine[] = br.readLine().trim().split(" ");

            //size of array
            int n = Integer.parseInt(inputLine[0]);
            Job[] arr = new Job[n];
            inputLine = br.readLine().trim().split(" ");

            //adding id, deadline, profit
            for (int i = 0, k = 0; i < n; i++) {
                arr[i] = new Job(Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]),
                        Integer.parseInt(inputLine[k++]));
            }

            //function call
            int[] res = JobScheduling(arr, n);
            System.out.println(res[0] + " " + res[1]);
        }

    }

    public static int maxChildren(int N, int M, int greed[], int sz[]) {
        // Assign Cookies -- https://www.geeksforgeeks.org/problems/assign-cookies/0
        Arrays.sort(greed);
        Arrays.sort(sz);
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < greed.length && j < sz.length) {
            if (greed[i] <= sz[j]) {
                i++;
                j++;
                count++;
            } else {
                j++;
            }

        }
        return count;
    }

    class Item {
        int value, weight;

        Item(int x, int y) {
            this.value = x;
            this.weight = y;
        }
    }

    public static double fractionalKnapsack(int w, Item arr[], int n) {

        Arrays.sort(arr, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                double r1 = (double) a.value / a.weight;
                double r2 = (double) b.value / b.weight;
                return Double.compare(r2, r1);
            }
        });
        double finalValue = 0.0;
        int currentWeight = 0;
        for (int i = 0; i < n; i++) {
            if (currentWeight + arr[i].weight <= w) {
                currentWeight += arr[i].weight;
                finalValue += arr[i].value;
            } else {
                int remainingWeight = w - currentWeight;
                finalValue += remainingWeight * ((double) arr[i].value / arr[i].weight);
                break;
            }
        }
        return finalValue;
    }

    public static List<Integer> minPartition(int N) {
        // code here
        int[] coins = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000 };
        List<Integer> ar = new ArrayList<>();
        int sum = N;
        for (int i = coins.length - 1; i >= 0; i--) {
            while (sum >= coins[i]) {
                sum -= coins[i];
                ar.add(coins[i]);
            }
            if (sum == 0) {
                break;
            }
        }
        return ar;
    }

    public static boolean lemonadeChange(int N, int bills[]) {
        // code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] vals = { 5, 10, 20 };
        for (int i = 0; i < bills.length; i++) {
            map.put(bills[i], map.getOrDefault(bills[i], 0) + 1);

            if (bills[i] > 5) {
                int rem = bills[i] - 5;

                for (int j = vals.length - 1; j >= 0; j--) {
                    while (rem >= vals[j]) {
                        if (map.containsKey(vals[j])) {
                            rem -= vals[j];
                            map.put(vals[j], map.get(vals[j]) - 1);
                            if (map.get(vals[j]) == 0) {
                                map.remove(vals[j]);

                            }
                        } else {
                            break;
                        }
                    }
                }
                if (rem != 0) {
                    return false;
                }

            }

        }
        return true;
    }

    public static int maxMeetings(int start[], int end[], int n) {
        // add your code here
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = start[i];
            arr[i][1] = end[i];
        }
        // Sorting the array
        Arrays.sort(arr, (a, b) -> {
            int result = Double.compare(a[1], b[1]);
            return result;
        });
        int count = 1;
        int freeTime = arr[0][1];
        for (int i = 1; i < n; i++) {
            if (arr[i][0] > freeTime) {
                count++;
                freeTime = arr[i][1];
            }
        }
        return count;

    }

    public static int canReach(int[] A, int N) {
        // Jump Game -- https://www.geeksforgeeks.org/problems/jump-game/1
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i > maxIndex)
                return 0;
            maxIndex = Math.max(i + A[i], maxIndex);
        }
        if (maxIndex >= N - 1) {
            return 1;
        } else {
            return 0;
        }
    }

    // public static int minJumps(int[] A, int N) {
    //     // Minimum Jumps -- https://www.geeksforgeeks.org/problems/minimum-number-of-jumps-1587115620/1
    //     int start = 0;
    //     int end = N - 1;
    //     int val = helperMinJumps(start,end,A);
    //     if(val==Integer.MAX_VALUE){
    //          return -1;    
    //     }
    //     return val;
    // }

    // public static int helperMinJumps(int start, int end,int[] A) {
    //     if(A[0]==0) return -1;
    //     if (start >= end) {
    //         return 0;
    //     }
    //     if (A[start] == 0) {
    //         return Integer.MAX_VALUE;
    //     }
    //     int minVal = Integer.MAX_VALUE ;
    //     for (int i = A[start]; i > 0; i--) {
    //         int val = helperMinJumps(start + i, end, A);
    //         if(val!=Integer.MAX_VALUE){
    //          minVal = Math.min(minVal, val+1);
    //         }
    //         
    //     }
    //     return minVal;
    // }
    static int minJumps(int[] A, int N) {
        // your code here
        int start = 0;
        int end = N - 1;
        int[] memo = new int[A.length];
        Arrays.fill(memo, -1);
        int val = helperMinJumps(start, end, A, memo);
        return val;
    }

    public static int helperMinJumps(int start, int end, int[] A, int[] memo) {
        if (start == end) {
            return 0;
        }
        if (memo[start] != -1)
            return memo[start];

        int minVal = Integer.MAX_VALUE;
        for (int i = A[start]; i > 0; i--) {
            if ((start + i) <= end) {
                int val = helperMinJumps(start + i, end, A, memo);
                minVal = Math.min(minVal, val + 1);
            }
        }
        return memo[start] = minVal;
    }
    static class Pair {
        int val;
        char ch;
        public Pair(int val, char ch){
            this.val = val;
            this.ch = ch;
        }
    }

    static int findPlatform(int arr[], int dep[], int n) {
        // Minimum Platforms -- https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
        Pair[] pr = new Pair[2 * n];
        for (int i = 0; i < n; i++) {
            pr[2 * i] = new Pair(arr[i], 'A');
            pr[2 * i + 1] = new Pair(dep[i], 'D');
        }
        // Debugging: Print the Pair array to ensure it is populated correctly
        // for (Pair p : pr) {
        //     System.out.println(p.val + " " + p.ch);
        // }
        Arrays.sort(pr, new Comparator<Pair>() {
            public int compare(Pair o1, Pair o2) {
                if (o1.val != o2.val) {
                    return Integer.compare(o1.val, o2.val);
                } else {
                    return Character.compare(o1.ch, o2.ch); // Ensure 'D' comes before 'A' if times are the same
                }
            }
        });
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (pr[i].ch == 'A') {
                count++;
            } else {
                count--;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;

    }

    static class Job {
        int id, profit, deadline;

        Job(int x, int y, int z) {
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }

    public static int[] JobScheduling(Job arr[], int n) {
        // Job Sequencing Problem -- https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
        Arrays.sort(arr, new Comparator<Job>() {
            public int compare(Job o1, Job o2) {
                return Integer.compare(o2.profit, o1.profit);
            }
        });
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].deadline > maxDeadline) {
                maxDeadline = arr[i].deadline;
            }
        }
        int[] a = new int[maxDeadline + 1];
        Arrays.fill(a, -1);
        int profit = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int lastdate = arr[i].deadline;
            while (lastdate > 0 && a[lastdate] != -1) {
                lastdate--;
            }
            if (lastdate > 0 && a[lastdate] == -1) {
                a[lastdate] = arr[i].id;
                profit += arr[i].profit;
                count++;
            }
        }
        return new int[] { count, profit };
    }

    public static int solve(int bt[]) {
        // Shortest Job first -- https://www.geeksforgeeks.org/problems/shortest-job-first/1
        Arrays.sort(bt);
        int totalSum = 0;
        int sum = 0;
        for (int i = 0; i < bt.length; i++) {
            if (i == 0) {
                sum += bt[i];
                continue;
            }
            totalSum += sum;
            sum += bt[i];
        }
        return (int) totalSum / bt.length;
    }
    public static int pageFaults(int N, int C, int pages[]){
        // code here
        ArrayList<Integer> arr = new ArrayList<>(C);
        int fcount =0;
        for (int i = 0; i < N; i++) {
            if (!arr.contains(pages[i])) {
                if (arr.size() == C) {
                    arr.remove(0);
                }
                arr.add(pages[i]);
                fcount++;
            } else {
                int idx = arr.indexOf(pages[i]);
                arr.remove(idx);
                arr.add(pages[i]);
            }
        }
        return fcount;
    }
}
    

