import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GraphsTut {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>();

        // Add the nested lists to the listOfLists
        listOfLists.add(new ArrayList<>(Arrays.asList(1, 0, 1)));
        listOfLists.add(new ArrayList<>(Arrays.asList(0, 1, 0)));
        listOfLists.add(new ArrayList<>(Arrays.asList(1, 0, 1)));
        // listOfLists.add(new ArrayList<>(Arrays.asList(0)));
        // listOfLists.add(new ArrayList<>(Arrays.asList(2)));
        // System.out.println(numProvinces(listOfLists, 3));
        int[][] mat = {
                { 2, 1, 1 },
                { 0, 1, 1 },
                { 1, 0, 1 }
        };
        System.out.println(orangesRotting(mat));
    }

    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // O(2*E+V)
        int[] vis = new int[V];
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        queue.add(0);
        res.add(0);
        vis[0] = 1;
        while (!queue.isEmpty()) {
            ArrayList<Integer> ls = adj.get(queue.remove());
            for (int i : ls) {
                if (vis[i] == 0) {
                    queue.add(i);
                    res.add(i);
                    vis[i] = 1;
                }
            }
        }
        return res;
    }

    public static ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int[] vis = new int[V];
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0);
        vis[0] = 1;
        dfshelper(vis, 0, adj, res);
        return res;
    }

    public static void dfshelper(int[] vis, int index, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> res) {
        if (index >= vis.length) {
            return;
        }
        for (int i : adj.get(index)) {
            if (vis[i] == 0) {
                vis[i] = 1;
                res.add(i);
                dfshelper(vis, i, adj, res);
            }
        }
    }

    // static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
    // // https://www.geeksforgeeks.org/problems/number-of-provinces/1
    // int[] vis = new int[V];
    // int count = 0;
    // for (int i = 0; i < V; i++) {
    // if (vis[i] == 0) {
    // bfs(vis, adj, i);
    // count++;
    // }
    // }
    // return count;
    // }

    // public static void bfs(int[] vis, ArrayList<ArrayList<Integer>> adj, int ind)
    // {
    // Queue<Integer> queue = new LinkedList<>();
    // queue.add(ind);
    // vis[ind] = 1;
    // while (!queue.isEmpty()) {
    // int lastNode = queue.remove();
    // ArrayList<Integer> ls = adj.get(lastNode);
    // for (int i = 0; i < ls.size(); i++) {
    // if (ls.get(i) == 1 && vis[i] == 0) {
    // queue.add(i);
    // vis[i] = 1;
    // }
    // }
    // }
    // }
    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] vis = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int bfsCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] { i, j, 0 });
                    vis[i][j] = 1; // visited
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] t = queue.remove();
            int x = t[0];
            int y = t[1];
            int count = t[2];
            bfsCount = Math.max(count, bfsCount);
            if (checkValid(x, y + 1, m, n) && vis[x][y + 1] == 0 && grid[x][y + 1] == 1) { // right
                vis[x][y + 1] = 1;
                queue.add(new int[] { x, y + 1, count + 1 });
            }
            if (checkValid(x + 1, y, m, n) && vis[x + 1][y] == 0 && grid[x + 1][y] == 1) { // down
                vis[x + 1][y] = 1;
                queue.add(new int[] { x + 1, y, count + 1 });
            }
            if (checkValid(x - 1, y, m, n) && vis[x - 1][y] == 0 && grid[x - 1][y] != 0) { // left
                vis[x - 1][y] = 1;
                queue.add(new int[] { x - 1, y, count + 1 });
            }
            if (checkValid(x, y - 1, m, n) && vis[x][y - 1] == 0 && grid[x][y - 1] != 0) { // up
                vis[x][y - 1] = 1;
                queue.add(new int[] { x, y - 1, count + 1 });
            }
        }
        boolean left = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && vis[i][j] == 0) {
                    left = true;
                }
            }
        }
        if (left) {
            return -1;
        } else {
            return bfsCount;
        }
    }

    public static boolean checkValid(int x, int y, int m, int n) {
        if (x >= 0 && x < m && y >= 0 && y < n) {
            return true;
        } else {
            return false;
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // Code here
        int n = image.length;
        int m = image[0].length;
        // int[][] res = new int[n][m];
        int initialColor = image[sr][sc];
        int[][] vis = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { sr, sc });
        vis[sr][sc] = 1;
        image[sr][sc] = newColor;

        while (!queue.isEmpty()) {
            int[] t = queue.remove();
            int x = t[0];
            int y = t[1];
            if (checkValid(x, y + 1, n, m) && vis[x][y + 1] == 0 && image[x][y + 1] == initialColor) {
                image[x][y + 1] = newColor;
                vis[x][y + 1] = 1;
                queue.add(new int[] { x, y + 1 });
            }
            if (checkValid(x, y - 1, n, m) && vis[x][y - 1] == 0 && image[x][y - 1] == initialColor) {
                image[x][y - 1] = newColor;
                vis[x][y - 1] = 1;
                queue.add(new int[] { x, y - 1 });
            }
            if (checkValid(x + 1, y, n, m) && vis[x + 1][y] == 0 && image[x + 1][y] == initialColor) {
                image[x + 1][y] = newColor;
                vis[x + 1][y] = 1;
                queue.add(new int[] { x + 1, y });
            }
            if (checkValid(x - 1, y, n, m) && vis[x - 1][y] == 0 && image[x - 1][y] == initialColor) {
                image[x - 1][y] = newColor;
                vis[x - 1][y] = 1;
                queue.add(new int[] { x - 1, y });
            }
        }

        return image;
    }

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int[] vis = new int[V];

        // Check for cycle in each component of the graph
        for (int start = 0; start < V; start++) {
            if (vis[start] == 0) {
                if (bfsCheckCycle(start, adj, vis)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean bfsCheckCycle(int start, ArrayList<ArrayList<Integer>> adj, int[] vis) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { start, -1 });
        vis[start] = 1;

        while (!queue.isEmpty()) {
            int[] t = queue.remove();
            int node = t[0];
            int prevNode = t[1];
            ArrayList<Integer> edg = adj.get(node);

            for (int i : edg) {
                if (vis[i] == 0) {
                    queue.add(new int[] { i, node });
                    vis[i] = 1;
                } else if (vis[i] == 1 && i != prevNode) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfsCheckCycle(int start, ArrayList<ArrayList<Integer>> adj, int[] vis, int prev) {
        ArrayList<Integer> list = adj.get(start);
        vis[start] = 1;
        for (int i = 0; i < list.size(); i++) {
            if (vis[list.get(i)] == 0) {
                if (dfsCheckCycle(list.get(i), adj, vis, start)) {
                    return true;
                }
            } else if (vis[list.get(i)] == 1 && list.get(i) != prev) {
                return true;
            }
        }
        return false;
    }

    public int[][] updateMatrix(int[][] mat) {
        // https://leetcode.com/problems/01-matrix/
        Queue<int[]> queue = new LinkedList<>();
        int n = mat.length;
        int m = mat[0].length;
        int[][] vis = new int[n][m];
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[] { i, j, 0 });
                    vis[i][j] = 1;
                    res[i][j] = 0;
                }

            }
        }
        while (!queue.isEmpty()) {
            int[] t = queue.remove();
            int x = t[0];
            int y = t[1];
            int dist = t[2];
            if (checkValid(x + 1, y, n, m) && vis[x + 1][y] == 0 && mat[x + 1][y] == 1) {
                queue.add(new int[] { x + 1, y, dist + 1 });
                res[x + 1][y] = dist + 1;
                vis[x + 1][y] = 1;
            }
            if (checkValid(x - 1, y, n, m) && vis[x - 1][y] == 0 && mat[x - 1][y] == 1) {
                queue.add(new int[] { x - 1, y, dist + 1 });
                res[x - 1][y] = dist + 1;
                vis[x - 1][y] = 1;
            }
            if (checkValid(x, y + 1, n, m) && vis[x][y + 1] == 0 && mat[x][y + 1] == 1) {
                queue.add(new int[] { x, y + 1, dist + 1 });
                res[x][y + 1] = dist + 1;
                vis[x][y + 1] = 1;
            }
            if (checkValid(x, y - 1, n, m) && vis[x][y - 1] == 0 && mat[x][y - 1] == 1) {
                queue.add(new int[] { x, y - 1, dist + 1 });
                res[x][y - 1] = dist + 1;
                vis[x][y - 1] = 1;
            }
        }
        return res;
    }

    public static char[][] fill(int n, int m, char a[][]) {
        // code here
        Queue<int[]> queue = new LinkedList<>();
        int[][] vis = new int[n][m];
        char[][] res = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = 'X';
            }
        }
        for (int i = 0; i < m; i++) {
            if (a[0][i] == 'O') {
                queue.add(new int[] { 0, i });
                vis[0][i] = 1;
                res[0][i] = 'O';
            }
            if (a[n - 1][i] == 'O') {
                queue.add(new int[] { n - 1, i });
                vis[n - 1][i] = 1;
                res[n - 1][i] = 'O';
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i][0] == 'O') {
                queue.add(new int[] { i, 0 });
                vis[i][0] = 1;
                res[i][0] = 'O';
            }
            if (a[i][m - 1] == 'O') {
                queue.add(new int[] { i, m - 1 });
                vis[i][m - 1] = 1;
                res[i][m - 1] = 'O';
            }
        }
        while (!queue.isEmpty()) {
            int[] t = queue.remove();
            int x = t[0];
            int y = t[1];
            if (checkValid(x + 1, y, n, m) && vis[x + 1][y] == 0 && a[x + 1][y] == 'O') {
                queue.add(new int[] { x + 1, y });
                res[x + 1][y] = 'O';
                vis[x + 1][y] = 1;
            }
            if (checkValid(x - 1, y, n, m) && vis[x - 1][y] == 0 && a[x - 1][y] == 'O') {
                queue.add(new int[] { x - 1, y });
                res[x - 1][y] = 'O';
                vis[x - 1][y] = 1;
            }
            if (checkValid(x, y + 1, n, m) && vis[x][y + 1] == 0 && a[x][y + 1] == 'O') {
                queue.add(new int[] { x, y + 1 });
                res[x][y + 1] = 'O';
                vis[x][y + 1] = 1;
            }
            if (checkValid(x, y - 1, n, m) && vis[x][y - 1] == 0 && a[x][y - 1] == 'O') {
                queue.add(new int[] { x, y - 1 });
                res[x][y - 1] = 'O';
                vis[x][y - 1] = 1;
            }
        }
        return res;
    }

    public static int countDistinctIslands(int[][] grid) {
        // https://www.geeksforgeeks.org/problems/number-of-distinct-islands/0
          int n = grid.length;
          int m = grid[0].length;
          int[][] vis = new int[n][m];
          HashSet<ArrayList<String>> set = new HashSet<>();
  
          for (int i = 0; i < n; i++) {
              for (int j = 0; j < m; j++) {
                  if (grid[i][j] == 1 && vis[i][j] == 0) {
                      ArrayList<String> arr = new ArrayList<>();
                      dfsHelperIsland(i, j, i, j, n, m, grid, vis, arr);
                      set.add(arr);
                  }
              }
          }
  
          return set.size();
      }
      public static void dfsHelperIsland(int x, int y, int bx, int by, int n, int m, int[][] grid, int[][] vis, ArrayList<String> arr) {
          vis[x][y] = 1;
          arr.add((x - bx) + "," + (y - by));
  
          int[] dx = {1, -1, 0, 0};
          int[] dy = {0, 0, 1, -1};
  
          for (int i = 0; i < 4; i++) {
              int nx = x + dx[i];
              int ny = y + dy[i];
              if (checkValid(nx, ny, n, m) && vis[nx][ny] == 0 && grid[nx][ny] == 1) {
                  dfsHelperIsland(nx, ny, bx, by, n, m, grid, vis, arr);
              }
          }
      }
}
