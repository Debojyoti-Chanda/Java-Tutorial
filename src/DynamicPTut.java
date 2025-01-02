import java.util.Arrays;
import java.util.List;

public class DynamicPTut {

    public static void main(String[] args) {
        int[] dp = new int[10];
        Arrays.fill(dp, -1);

    }

    public static int fibonacci_memo(int n, int[] dp) {
        if (n <= 1)
            return n;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = fibonacci_memo(n - 1, dp) + fibonacci_memo(n - 2, dp);
    }

    public static int fibonacci_tab(int n) {
        int prev2 = 0;
        int prev = 1;
        for (int i = 2; i <= n; i++) {
            int cur_i = prev2 + prev;
            prev2 = prev;
            prev = cur_i;
        }
        return prev;
    }

    public static int minimumEnergy(int arr[], int N) {
        // https://www.geeksforgeeks.org/problems/geek-jump/0
        int[] dp = new int[N];
        int res = greekJump_memo(N - 1, arr, dp);
        return res;
    }

    public static int greekJump(int ind, int[] arr) {
        if (ind == 0)
            return 0;
        int small = Integer.MAX_VALUE;
        int large = Integer.MAX_VALUE;
        if (ind - 1 >= 0) {
            small = greekJump(ind - 1, arr) + Math.abs(arr[ind] - arr[ind - 1]);
        }
        if (ind - 2 >= 0) {
            large = greekJump(ind - 2, arr) + Math.abs(arr[ind] - arr[ind - 2]);
        }
        return Math.min(small, large);
    }

    public static int greekJump_memo(int ind, int[] arr, int[] dp) {
        if (ind == 0)
            return 0;
        int small = Integer.MAX_VALUE;
        int large = Integer.MAX_VALUE;
        if (ind - 1 >= 0) {
            if (dp[ind - 1] == 0) {
                small = greekJump(ind - 1, arr) + Math.abs(arr[ind] - arr[ind - 1]);
            } else {
                small = dp[ind - 1] + Math.abs(arr[ind] - arr[ind - 1]);
            }

        }
        if (ind - 2 >= 0) {
            if (dp[ind - 2] == 0) {
                large = greekJump(ind - 2, arr) + Math.abs(arr[ind] - arr[ind - 2]);
            } else {
                large = dp[ind - 2] + Math.abs(arr[ind] - arr[ind - 2]);
            }
        }
        return dp[ind] = Math.min(small, large);
    }

    public static int greekJump_tab(int[] arr) {
        // int[] dp = new int[arr.length];
        // dp[0] = 0;
        // int small = Integer.MAX_VALUE;
        // int large = Integer.MAX_VALUE;
        // for (int ind = 1; ind < dp.length; ind++) {
        // if (ind - 1 >= 0) {
        // small = dp[ind - 1] + Math.abs(arr[ind] - arr[ind - 1]);
        // }
        // if (ind - 2 >= 0) {
        // large = dp[ind - 2] + Math.abs(arr[ind] - arr[ind - 2]);
        // }
        // dp[ind] = Math.min(small, large);
        // }
        // return dp[arr.length - 1];
        // int[] dp = new int[arr.length];
        int two = 0;
        int one = 0;
        int small = Integer.MAX_VALUE;
        int large = Integer.MAX_VALUE;
        for (int ind = 1; ind < arr.length; ind++) {
            if (ind - 1 >= 0) {
                small = one + Math.abs(arr[ind] - arr[ind - 1]);
            }
            if (ind - 2 >= 0) {
                large = two + Math.abs(arr[ind] - arr[ind - 2]);
            }
            two = one;
            one = Math.min(small, large);
        }
        return one;
    }

    public static int minimizeCost(int arr[], int k) {
        int[] dp = new int[arr.length];
        // Start at the first stone, no cost to stay there
        dp[0] = 0;
        for (int ind = 1; ind < arr.length; ind++) {
            int min = Integer.MAX_VALUE;
            // Check all possible stones Geek can jump from within the range [ind-k, ind-1]
            for (int j = 1; j <= k; j++) {
                if (ind - j >= 0) {
                    min = Math.min(min, dp[ind - j] + Math.abs(arr[ind] - arr[ind - j]));
                }
            }
            // Store the minimum cost to reach the current stone
            dp[ind] = min;
        }
        // The last element in dp array contains the minimum cost to reach the last
        // stone
        return dp[arr.length - 1];
    }

    public static int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        // int val = helper_house_robber(nums,0,dp);
        // return val;
        int n = nums.length;
        dp[n - 1] = nums[n - 1];
        for (int i = 2; i <= n; i++) {
            dp[n - i] = Math.max(nums[n - i] + dp[n - i + 2], dp[n - i + 1]);
        }
        return dp[0];
    }

    public static int helper_house_robber(int[] nums, int ind, int[] dp) {
        if (ind >= nums.length) {
            return 0;
        }
        int prev2;
        if (ind + 2 < nums.length && dp[ind + 2] != 0) {
            prev2 = dp[ind + 2];
        } else {
            prev2 = helper_house_robber(nums, ind + 2, dp);
        }
        int prev1;
        if (ind + 1 < nums.length && dp[ind + 1] != 0) {
            prev1 = dp[ind + 1];
        } else {
            prev1 = helper_house_robber(nums, ind + 1, dp);
        }
        int val = Math.max(nums[ind] + prev2, prev1);
        dp[ind] = val;
        return val;
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        int val1 = helper_house_robber2(Arrays.copyOfRange(nums, 0, n - 1));
        int val2 = helper_house_robber2(Arrays.copyOfRange(nums, 1, n));
        return Math.max(val1, val2);
    }

    public static int helper_house_robber2(int[] nums) {
        int[] dp = new int[nums.length + 1];
        int n = nums.length;
        if (n == 0)
            return 0;
        dp[n - 1] = nums[n - 1];
        for (int i = 2; i <= n; i++) {
            dp[n - i] = Math.max(nums[n - i] + dp[n - i + 2], dp[n - i + 1]);
        }
        return dp[0];
    }

    public int maximumPoints(int arr[][], int N) {
        // recursive way -> Geek's Training
        // https://www.geeksforgeeks.org/problems/geeks-training/1
        // int val = maximumPoints_helper(arr,N-1,-1);
        // return val;
        // Memoization
        // int[][] dp = new int[N][4];
        // for(int i=0;i<N;i++){
        // for(int j=0;j<4;j++){
        // dp[i][j] = -1;
        // }
        // }
        // int val = maximumPoints_helper(arr,N-1,3,dp);
        // return val;

        // Tabulation
        // Create a DP table with dimensions [N][4]
        int[][] dp = new int[N][4];

        // Base case: Fill for the first day
        dp[0][0] = Math.max(arr[0][1], arr[0][2]); // If previous activity was 0
        dp[0][1] = Math.max(arr[0][0], arr[0][2]); // If previous activity was 1
        dp[0][2] = Math.max(arr[0][0], arr[0][1]); // If previous activity was 2
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2])); // If no previous activity

        // Fill the DP table for subsequent days
        for (int day = 1; day < N; day++) {
            for (int prev = 0; prev < 4; prev++) { // Iterate over all possible previous activities
                dp[day][prev] = 0; // Initialize
                for (int activity = 0; activity < 3; activity++) { // Iterate over all current activities
                    if (activity != prev) { // Ensure the activity is not the same as the previous one
                        dp[day][prev] = Math.max(dp[day][prev], arr[day][activity] + dp[day - 1][activity]);
                    }
                }
            }
        }

        // The answer is the maximum points for the last day when no activity is
        // restricted
        return dp[N - 1][3];
    }

    public static int maximumPoints_helper(int arr[][], int N, int prev) {
        if (N < 0) {
            return 0;
        }
        int val = 0;
        for (int c = 0; c < 3; c++) {
            if (prev == c) {
                continue;
            }
            val = Math.max(val, arr[N][c] + maximumPoints_helper(arr, N - 1, c));
        }
        return val;
    }

    public static int maximumPoints_helper(int arr[][], int day, int prev_activity, int[][] dp) {
        if (day < 0) {
            return 0;
        }
        if (dp[day][prev_activity] != -1) {
            return dp[day][prev_activity];
        }
        int val = Integer.MIN_VALUE;
        for (int activity = 0; activity < 3; activity++) {
            if (prev_activity != activity) {
                val = Math.max(val, arr[day][activity] + maximumPoints_helper(arr, day - 1, activity, dp));
            }

        }
        dp[day][prev_activity] = val;
        return val;
    }

    public int uniquePaths(int m, int n) {
        // if(m==1 && n ==1){
        // return 1;
        // }
        // if(m<1 || n <1){
        // return 0;
        // }
        // int no_paths = uniquePaths(m-1,n) + uniquePaths(m,n-1);
        // return no_paths;
        // Memoization approach
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        int cnt_path = uniquePaths_helper(m - 1, n - 1, dp);
        return cnt_path;
    }

    public static int uniquePaths_helper(int m, int n, int[][] dp) {
        if (m == 0 && n == 0) {
            return 1;
        }
        if (m < 0 || n < 0) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int no_paths = uniquePaths_helper(m - 1, n, dp) + uniquePaths_helper(m, n - 1, dp);
        dp[m][n] = no_paths;
        return no_paths;
    }

    public static int uniquePaths2(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[m - 1][n - 1] = 1;
        int cnt_path = uniquePaths_helper(m - 1, n - 1, dp);
        return cnt_path;
    }

    public static int uniquePaths_helper2(int m, int n, int[][] dp) {
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int down = m - i + 1;
                int right = n - j + 1;
                dp[m - i][n - j] = dp[m - i + 1][n - j] + dp[m - i][n - j + 1];
            }
        }
        return dp[0][0];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        // int no_path = uniquePathsWithObstacles_helper(obstacleGrid,0,0);
        // Memoization approach
        // int[][] dp = new int[obstacleGrid.length+1][obstacleGrid[0].length+1];

        // int no_path = uniquePathsWithObstacles_helper(obstacleGrid,0,0,dp);
        // return no_path;
        // Tabulation approach
        int n = obstacleGrid.length; // no of rows 3
        int m = obstacleGrid[0].length; // no of columns 3
        if (obstacleGrid[n - 1][m - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][m + 1];
        dp[n - 1][m - 1] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (obstacleGrid[n - 1 - i][m - 1 - j] == 1) {
                    dp[n - 1 - i][m - 1 - j] = 0;
                    continue;
                }
                dp[n - 1 - i][m - 1 - j] = dp[n - 1 - i + 1][m - 1 - j] + dp[n - 1 - i][m - 1 - j + 1];
            }
        }
        return dp[0][0];
    }

    public static int uniquePathsWithObstacles_helper(int[][] obstacleGrid, int c, int r) {
        int tr = obstacleGrid.length;
        int tc = obstacleGrid[0].length;
        if (r == tr - 1 && c == tc - 1 && obstacleGrid[r][c] == 0) {
            return 1;
        }
        if (r > tr - 1 || c > tc - 1) {
            return 0;
        }
        if (obstacleGrid[r][c] == 1) {
            return 0;
        }
        int count = uniquePathsWithObstacles_helper(obstacleGrid, c + 1, r)
                + uniquePathsWithObstacles_helper(obstacleGrid, c, r + 1);

        return count;

    }

    public static int uniquePathsWithObstacles_helper(int[][] obstacleGrid, int c, int r, int[][] dp) {
        int tr = obstacleGrid.length;
        int tc = obstacleGrid[0].length;
        if (r == tr - 1 && c == tc - 1 && obstacleGrid[r][c] == 0) {
            return 1;
        }
        if (r > tr - 1 || c > tc - 1) {
            return 0;
        }
        if (obstacleGrid[r][c] == 1) {
            return 0;
        }
        if (dp[r][c] != 0) {
            return dp[r][c];
        }
        int count = uniquePathsWithObstacles_helper(obstacleGrid, c + 1, r, dp)
                + uniquePathsWithObstacles_helper(obstacleGrid, c, r + 1, dp);
        dp[r][c] = count;
        return count;
    }

    public int minPathSum(int[][] grid) {
        // int val = minPathSum_helper(grid,0,0);
        // return val;
        // Memoization Approch
        // int[][] dp = new int[grid.length][grid[0].length];
        // int val = minPathSum_helper(grid,0,0,dp);
        // return val;
        // Tabulation Approch
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[n - 1][m - 1] = grid[n - 1][m - 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    continue;
                dp[n - 1 - i][m - 1 - j] = grid[n - 1 - i][m - 1 - j]
                        + Math.min(dp[n - 1 - i + 1][m - 1 - j], dp[n - 1 - i][m - 1 - j + 1]);
            }
        }
        return dp[0][0];

    }

    public static int minPathSum_helper(int[][] grid, int r, int c) {
        int lr = grid.length;
        int lc = grid[0].length;
        if (r == lr - 1 && c == lc - 1) {
            return grid[r][c];
        }
        if (r > lr - 1 || c > lc - 1) {
            return Integer.MAX_VALUE;
        }
        int val = grid[r][c] + Math.min(minPathSum_helper(grid, r + 1, c), minPathSum_helper(grid, r, c + 1));
        return val;
    }

    public static int minPathSum_helper(int[][] grid, int r, int c, int[][] dp) {
        int lr = grid.length;
        int lc = grid[0].length;
        if (r == lr - 1 && c == lc - 1) {
            return grid[r][c];
        }
        if (r > lr - 1 || c > lc - 1) {
            return Integer.MAX_VALUE;
        }
        if (dp[r][c] != 0) {
            return dp[r][c];
        }
        int val = grid[r][c] + Math.min(minPathSum_helper(grid, r + 1, c, dp), minPathSum_helper(grid, r, c + 1, dp));
        dp[r][c] = val;
        return val;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        // int val = minimumTotal_helper(triangle,0,0);
        // return val;
        // Memoization Approch
        // int[][] dp = new int[triangle.size()][triangle.size()];
        // int val = minimumTotal_helper(triangle,0,0,dp);
        // return val;
        // tabulation approach
        int[][] dp = new int[triangle.size()][triangle.size()];
        for (int i = 0; i < triangle.size(); i++) {
            dp[triangle.size() - 1][i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int d = triangle.size() - 2; d >= 0; d--) {
            for (int p = d; p >= 0; p--) {
                dp[d][p] = triangle.get(d).get(p) + Math.min(dp[d + 1][p], dp[d + 1][p + 1]);
            }
        }
        return dp[0][0];
    }

    public static int minimumTotal_helper(List<List<Integer>> triangle, int d, int p) {
        int td = triangle.size();
        int tp = d + 1;
        if (d == td - 1) {
            return triangle.get(d).get(p);
        }
        int val = triangle.get(d).get(p)
                + Math.min(minimumTotal_helper(triangle, d + 1, p), minimumTotal_helper(triangle, d + 1, p + 1));
        return val;
    }

    public static int minimumTotal_helper(List<List<Integer>> triangle, int d, int p, int[][] dp) {
        int td = triangle.size();
        int tp = d + 1;
        if (d == td - 1) {
            return triangle.get(d).get(p);
        }
        if (dp[d][p] != 0) {
            return dp[d][p];
        }
        int val = triangle.get(d).get(p) + Math.min(minimumTotal_helper(triangle, d + 1, p, dp),
                minimumTotal_helper(triangle, d + 1, p + 1, dp));
        dp[d][p] = val;
        return val;
    }

    public int minFallingPathSum(int[][] matrix) {
        // int minSum = Integer.MAX_VALUE;
        // int n = matrix.length;
        // int m = matrix[0].length;
        // for(int i=0;i<n;i++){
        // minSum = Math.min(minFallingPathSum_helper(matrix,0,i),minSum);
        // }
        // return minSum;
        // Tabulation approach
        int minSum = Integer.MAX_VALUE;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m + 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m + 2; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            dp[n - 1][i + 1] = matrix[n - 1][i];
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                dp[n - i][j + 1] = matrix[n - i][j]
                        + Math.min(dp[n - i + 1][j], Math.min(dp[n - i + 1][j + 1], dp[n - i + 1][j + 2]));
            }
        }
        for (int i = 0; i < m + 2; i++) {
            minSum = Math.min(minSum, dp[0][0]);
        }
        return minSum;
    }

    public static int minFallingPathSum_helper(int[][] matrix, int r, int c) {
        int n = matrix.length;
        int m = matrix[0].length;
        if ((r < 0 || r >= n) || (c < 0 || c >= m)) {
            return Integer.MAX_VALUE;
        }
        if (r == n - 1) {
            return matrix[r][c];
        }
        int val = Math.min(minFallingPathSum_helper(matrix, r + 1, c - 1), minFallingPathSum_helper(matrix, r + 1, c));
        int val2 = Math.min(val, minFallingPathSum_helper(matrix, r + 1, c + 1));
        return matrix[r][c] + val2;
    }

    public int solve(int n, int m, int grid[][]) {
        // Code here
        // Start the helper function with both robots at the initial positions
        // return solve_helper(grid, 0, 0, m - 1);
        // Memoization Approch
        int[][][] dp = new int[n][m][m];
        return solve_helper(grid, 0, 0, m - 1, dp);
    }

    public static int solve_helper(int[][] grid, int row, int col1, int col2) {
        int n = grid.length;
        int m = grid[0].length;

        // Base case: if any robot goes out of bounds
        if (col1 < 0 || col2 < 0 || col1 >= m || col2 >= m) {
            return Integer.MIN_VALUE;
        }

        // Base case: when robots reach the last row
        if (row == n - 1) {
            if (col1 == col2) {
                // If both robots are in the same cell, pick chocolates once
                return grid[row][col1];
            } else {
                // Otherwise, pick chocolates from both cells
                return grid[row][col1] + grid[row][col2];
            }
        }

        // Recursive case: explore all paths for both robots
        int maxChocolates = Integer.MIN_VALUE;
        for (int d1 = -1; d1 <= 1; d1++) { // Robot 1 can move in 3 directions
            for (int d2 = -1; d2 <= 1; d2++) { // Robot 2 can move in 3 directions
                int newCol1 = col1 + d1;
                int newCol2 = col2 + d2;

                if (col1 == col2) {
                    // If both robots are in the same cell, pick chocolates once
                    maxChocolates = Math.max(maxChocolates,
                            grid[row][col1] + solve_helper(grid, row + 1, newCol1, newCol2));
                } else {
                    // Otherwise, pick chocolates from both cells
                    maxChocolates = Math.max(maxChocolates,
                            grid[row][col1] + grid[row][col2] + solve_helper(grid, row + 1, newCol1, newCol2));
                }
            }
        }

        return maxChocolates;

    }

    public static int solve_helper(int[][] grid, int row, int col1, int col2, int[][][] dp) {
        int n = grid.length;
        int m = grid[0].length;

        // Base case: if any robot goes out of bounds
        if (col1 < 0 || col2 < 0 || col1 >= m || col2 >= m) {
            return Integer.MIN_VALUE;
        }

        // Base case: when robots reach the last row
        if (row == n - 1) {
            if (col1 == col2) {
                // If both robots are in the same cell, pick chocolates once
                return grid[row][col1];
            } else {
                // Otherwise, pick chocolates from both cells
                return grid[row][col1] + grid[row][col2];
            }
        }

        if (dp[row][col1][col2] != 0) {
            return dp[row][col1][col2];
        }
        // Recursive case: explore all paths for both robots
        int maxChocolates = Integer.MIN_VALUE;
        for (int d1 = -1; d1 <= 1; d1++) { // Robot 1 can move in 3 directions
            for (int d2 = -1; d2 <= 1; d2++) { // Robot 2 can move in 3 directions
                int newCol1 = col1 + d1;
                int newCol2 = col2 + d2;

                if (col1 == col2) {
                    // If both robots are in the same cell, pick chocolates once
                    maxChocolates = Math.max(maxChocolates,
                            grid[row][col1] + solve_helper(grid, row + 1, newCol1, newCol2, dp));
                } else {
                    // Otherwise, pick chocolates from both cells
                    maxChocolates = Math.max(maxChocolates,
                            grid[row][col1] + grid[row][col2] + solve_helper(grid, row + 1, newCol1, newCol2, dp));
                }
            }
        }

        dp[row][col1][col2] = maxChocolates;
        return maxChocolates;

    }

    public static Boolean isSubsetSum(int arr[], int target) {
        // Create a memoization table initialized with null
        // Boolean[][] dp = new Boolean[target + 1][arr.length];
        // return isSubsetSumHelper(arr, target, 0, dp);
        // Tabulation Approch
        int n = arr.length;

        // Create a DP table
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Initialize the table
        // When target is 0, we can always achieve it by selecting no elements
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // When array is empty and target > 0, it's not possible to achieve the target
        for (int j = 1; j <= target; j++) {
            dp[0][j] = false;
        }

        // Fill the table
        for (int i = 1; i <= n; i++) { // Iterate over all elements
            for (int j = 1; j <= target; j++) { // Iterate over all possible targets
                if (arr[i - 1] <= j) {
                    // Include the current element or exclude it
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    // Exclude the current element
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // The answer is in the bottom-right corner of the table
        return dp[n][target];

    }

    public static boolean isSubsetSumHelper(int arr[], int target, int ind, Boolean[][] dp) {
        // Base case: If the target becomes 0, we've found a subset
        if (target == 0) {
            return true;
        }

        // Base case: If we've gone past the end of the array or target is negative
        if (ind >= arr.length || target < 0) {
            return false;
        }

        // Check if already computed
        if (dp[target][ind] != null) {
            return dp[target][ind];
        }

        // Recursive case:
        // Include the current element in the subset OR skip it
        boolean include = isSubsetSumHelper(arr, target - arr[ind], ind + 1, dp);
        boolean exclude = isSubsetSumHelper(arr, target, ind + 1, dp);

        // Memoize the result
        dp[target][ind] = include || exclude;
        return dp[target][ind];
    }

    public boolean canPartition(int[] nums) {
        // https://leetcode.com/problems/partition-equal-subset-sum/
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        Boolean[][] dp = new Boolean[target + 1][nums.length];
        boolean val = canPartition_helper(nums, target, 0, dp);
        return val;
    }

    public static boolean canPartition_helper(int[] arr, int target, int ind, Boolean[][] dp) {
        if (target == 0) {
            return true;
        }

        // Base case: If we've gone past the end of the array or target is negative
        if (ind >= arr.length || target < 0) {
            return false;
        }

        // Check if already computed
        if (dp[target][ind] != null) {
            return dp[target][ind];
        }

        // Recursive case:
        // Include the current element in the subset OR skip it
        boolean include = canPartition_helper(arr, target - arr[ind], ind + 1, dp);
        boolean exclude = canPartition_helper(arr, target, ind + 1, dp);

        // Memoize the result
        dp[target][ind] = include || exclude;
        return dp[target][ind];
    }

    public static int minimumDifference(int[] nums) {
        // https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/
        int tSum = 0;
        for (int i = 0; i < nums.length; i++) {
            tSum += nums[i];
        }
        int val = minDif_helper(nums, 0, 0, 0, tSum);
        return val;
    }

    public static int minDif_helper(int[] nums, int ind, int count, int subsetSum1, int tSum) {
        // Base case: When we've processed all elements
        if (ind == nums.length) {
            if (count == nums.length / 2) {
                int subsetSum2 = tSum - subsetSum1;
                return Math.abs(subsetSum1 - subsetSum2); // Absolute difference of two subsets
            }
            return Integer.MAX_VALUE; // Invalid subset
        }

        // Recursive case:
        // Option 1: Include nums[ind] in subsetSum1
        int include = Integer.MAX_VALUE;
        if (count < nums.length / 2) {
            include = minDif_helper(nums, ind + 1, count + 1, subsetSum1 + nums[ind], tSum);
        }

        // Option 2: Exclude nums[ind] from subsetSum1
        int exclude = minDif_helper(nums, ind + 1, count, subsetSum1, tSum);

        // Return the minimum difference between the two options
        return Math.min(include, exclude);
    }

    public int perfectSum(int[] nums, int target) {
        // https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1
        int[][] dp = new int[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int val = find(nums, target, 0, 0, dp);
        return val;
        // Tabulation approach
        // int n = nums.length;
        // int[][] dp = new int[n + 1][target + 1];

        // // Base case: If target is 0, there's one way to achieve it (empty subset).
        // for (int i = 0; i <= n; i++) {
        // dp[i][0] = 1;
        // }

        // // Bottom-up calculation.
        // for (int i = n - 1; i >= 0; i--) {
        // for (int j = 0; j <= target; j++) {
        // if (j >= nums[i]) {
        // dp[i][j] = dp[i + 1][j - nums[i]] + dp[i + 1][j];
        // } else {
        // dp[i][j] = dp[i + 1][j];
        // }
        // }
        // }

        // return dp[0][target];
    }

    public static int find(int[] nums, int target, int sum, int index, int[][] dp) {
        if (index == nums.length && sum == target) {
            return 1;
        }
        if (index == nums.length && sum != target) {
            return 0;
        }
        if (sum > target) {
            return 0;
        }
        if (dp[index][sum] != -1) {
            return dp[index][sum];
        }
        int a = find(nums, target, sum + nums[index], index + 1, dp);
        int b = find(nums, target, sum, index + 1, dp);
        dp[index][sum] = a + b;
        return a + b;
    }

    int countPartitions(int[] arr, int d) {
        // https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1
        // sum1 + sum2 = Tsum
        // sum1 - sum2 = d
        // sum1 = (Tsum+d)/2
        int Tsum = 0;
        for (int i = 0; i < arr.length; i++) {
            Tsum += arr[i];
        }
        if ((Tsum + d) % 2 != 0) {
            return 0;
        }
        int target = (Tsum + d) / 2;

        int val = perfectSum(arr, target);
        return val;
    }

    public static int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
    
        // Base case: If target is 0, there's one way to achieve it (empty subset).
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
    
        // Bottom-up calculation.
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i + 1][j - nums[i]] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
    }
    

}
