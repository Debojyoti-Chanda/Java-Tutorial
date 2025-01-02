import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
        // int[][] mat = {
        // { 2, 1, 1 },
        // { 0, 1, 1 },
        // { 1, 0, 1 }
        // };
        // System.out.println(orangesRotting(mat));
        // int[][] mat ={{1,0},{0,1}};
        // System.out.println(isPossible(2,2,mat));
        // System.out.println(findOrder(new String[] {"dhhid", "dahi", "cedg", "fg",
        // "gdah", "i", "gbdei", "hbgf", "e", "ddde"},10,9));
        // int[][] matrix = {
        // { 0, 1, 2 },
        // { 0, 4, 1 },
        // { 4, 5, 4 },
        // { 4, 2, 2 },
        // { 1, 2, 3 },
        // { 2, 3, 6 },
        // { 5, 3, 1 }
        // };
        // System.out.println(Arrays.toString(shortestPath(6, 7, matrix)));
        int[][] matrix = {
                { 0, 1 },
                { 0, 3 },
                { 3, 4 },
                { 4, 5 },
                { 5, 6 },
                { 1, 2 },
                { 2, 6 },
                { 6, 7 },
                { 7, 8 },
                { 6, 8 }
        };
        System.out.println(Arrays.toString(shortestPath(matrix, 9, 10, 0)));
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

    public static void dfsHelperIsland(int x, int y, int bx, int by, int n, int m, int[][] grid, int[][] vis,
            ArrayList<String> arr) {
        vis[x][y] = 1;
        arr.add((x - bx) + "," + (y - by));

        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (checkValid(nx, ny, n, m) && vis[nx][ny] == 0 && grid[nx][ny] == 1) {
                dfsHelperIsland(nx, ny, bx, by, n, m, grid, vis, arr);
            }
        }
    }

    public static boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        // https://www.geeksforgeeks.org/problems/bipartite-graph/1
        Queue<Integer> queue = new LinkedList<>();
        int[] vis = new int[V];
        int[] colored = new int[V];
        for (int j = 0; j < vis.length; j++) {
            if (vis[j] == 0) {
                queue.add(j);
                vis[j] = 1;
                colored[j] = 1;
                while (!queue.isEmpty()) {
                    int node = queue.remove();
                    ArrayList<Integer> to_nodes = adj.get(node);
                    for (int i = 0; i < to_nodes.size(); i++) {
                        int to_node = to_nodes.get(i);
                        if (vis[to_node] == 0) {
                            queue.add(to_node);
                            vis[to_node] = 1;
                            colored[to_node] = (colored[node] == 0) ? 1 : 0;
                        } else if (vis[to_node] == 1 && colored[to_node] == colored[node]) {
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }

    public static boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
        int[] vis = new int[V];
        int[] pathvis = new int[V];
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                if (dfsisCyclic(adj, vis, pathvis, i) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfsisCyclic(ArrayList<ArrayList<Integer>> adj, int[] vis, int[] pathvis, int node) {
        ArrayList<Integer> arr = adj.get(node);
        vis[node] = 1;
        pathvis[node] = 1;
        for (int i : arr) {
            if (vis[i] == 0) {
                boolean val = dfsisCyclic(adj, vis, pathvis, i);
                if (val == true) {
                    return true;
                }
            } else if (vis[i] == 1 && pathvis[i] == 1) {
                return true;
            }
        }
        pathvis[node] = 0;
        return false;
    }

    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        // add your code here
        int[] vis = new int[V];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfsHelperTopo(i, vis, stk, adj);
            }
        }
        int j = 0;
        int[] res = new int[stk.size()];
        while (!stk.isEmpty()) {
            res[j] = stk.pop();
            j++;
        }
        return res;
    }

    public static void dfsHelperTopo(int i, int[] vis, Stack<Integer> stk, ArrayList<ArrayList<Integer>> adj) {
        vis[i] = 1;
        ArrayList<Integer> edges = adj.get(i);
        for (int e : edges) {
            if (vis[e] == 0) {
                dfsHelperTopo(e, vis, stk, adj);
            }
        }
        stk.push(i);
    }

    public static int[] topoSort1(int V, ArrayList<ArrayList<Integer>> adj) {
        // Kahn Algorithm for topological sort
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            ArrayList<Integer> edges = adj.get(i);
            for (int e : edges) {
                indegree[e] += 1;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        int[] res = new int[V];
        int j = 0;
        while (!que.isEmpty()) {
            int node = que.remove();
            res[j] = node;
            j++;
            ArrayList<Integer> edges = adj.get(node);
            for (int e : edges) {
                indegree[e] -= 1;
                if (indegree[e] == 0) {
                    que.add(e);
                }
            }
        }
        return res;
    }

    public static boolean isCyclic1(int V, ArrayList<ArrayList<Integer>> adj) {
        // detection of a cycle in directed graph - using Kahn Algorithm
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            ArrayList<Integer> edges = adj.get(i);
            for (int e : edges) {
                indegree[e] += 1;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        // int[] res = new int[V];
        int count = 0;
        // int j = 0;
        while (!que.isEmpty()) {
            int node = que.remove();
            // res[j] = node;
            count++;
            // j++;
            ArrayList<Integer> edges = adj.get(node);
            for (int e : edges) {
                indegree[e] -= 1;
                if (indegree[e] == 0) {
                    que.add(e);
                }
            }
        }
        if (count != V) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPossible(int N, int P, int[][] prerequisites) {
        // Your Code goes here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int r = 0; r < P; r++) {
            adj.get(prerequisites[r][0]).add(prerequisites[r][1]);
        }
        int V = N;
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            ArrayList<Integer> edges = adj.get(i);
            for (int e : edges) {
                indegree[e] += 1;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        // int[] res = new int[V];
        int count = 0;
        // int j = 0;
        while (!que.isEmpty()) {
            int node = que.remove();
            // res[j] = node;
            count++;
            // j++;
            ArrayList<Integer> edges = adj.get(node);
            for (int e : edges) {
                indegree[e] -= 1;
                if (indegree[e] == 0) {
                    que.add(e);
                }
            }
        }
        if (count != V) {
            return false;
        } else {
            return true;
        }
    }

    public static List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        // Eventual Safe States - BFS - Topological Sort
        List<List<Integer>> adjRev = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjRev.add(new ArrayList<>());
        }
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int ed : adj.get(i)) {
                adjRev.get(ed).add(i);
                indegree[i]++;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        List<Integer> ls = new ArrayList<>();
        while (!que.isEmpty()) {
            int node = que.remove();
            ls.add(node);
            for (int ed : adjRev.get(node)) {
                indegree[ed]--;
                if (indegree[ed] == 0) {
                    que.add(ed);
                }
            }
        }
        Collections.sort(ls);
        return ls;
    }

    public static String findOrder(String[] dict, int N, int K) {
        // Alien Dictionary - https://www.geeksforgeeks.org/problems/alien-dictionary/1
        // ArrayList<ArrayList<Character>> arr = new ArrayList<>();
        // for (int i = 0; i < K; i++) {
        // arr.add(new ArrayList<>());
        // }
        // int[] indegree = new int[K];
        // for (int i = 1; i < N; i++) {
        // String st1 = dict[i - 1];
        // String st2 = dict[i];
        // int len = Math.min(st1.length(), st2.length());
        // for (int j = 0; j < len; j++) {
        // if (st1.charAt(j) != st2.charAt(j)) {
        // arr.get(st1.charAt(j) - 'a').add(st2.charAt(j));
        // indegree[st2.charAt(j) - 'a']++;
        // break;
        // }
        // }
        // }
        // Queue<Integer> que = new LinkedList<>();
        // for (int i = 0; i < K; i++) {
        // if (indegree[i] == 0) {
        // que.add(i);
        // }
        // }
        // String res = "";
        // while (!que.isEmpty()) {
        // int node = que.remove();
        // char c = (char)(node + (int)'a');
        // res = res + c;
        // ArrayList<Character> edges = arr.get(node);
        // for (char e : edges) {
        // indegree[e - 'a'] -= 1;
        // if (indegree[e - 'a'] == 0) {
        // que.add(e - 'a');
        // }
        // }
        // }
        // return res;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            int len = Math.min(s1.length(), s2.length());
            for (int ptr = 0; ptr < len; ptr++) {
                if (s1.charAt(ptr) != s2.charAt(ptr)) {
                    adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }

        int[] t = topoSort1(K, adj);
        List<Integer> topo = Arrays.stream(t).boxed().toList();
        String ans = "";
        for (int it : topo) {
            ans = ans + (char) (it + (int) ('a'));
        }

        return ans;
    }

    static class Pair {
        int first, second;

        Pair(int _first, int _second) {
            this.first = _first;
            this.second = _second;
        }
    }

    public static void dfsHelperTopo1(int i, int[] vis, Stack<Integer> stk, ArrayList<ArrayList<Pair>> adj) {
        vis[i] = 1;
        ArrayList<Pair> edges = adj.get(i);
        for (Pair e : edges) {
            if (vis[e.first] == 0) {
                dfsHelperTopo1(e.first, vis, stk, adj);
            }
        }
        stk.push(i);
    }

    public static int[] shortestPath(int N, int M, int[][] edges) {
        // Shortest path in Directed Acyclic Graph
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
            dist[i] = 9999;
        }

        for (int i = 0; i < M; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int d = edges[i][2];
            adj.get(from).add(new Pair(to, d));
        }
        // Apply dfs topological sort and get the stack
        int[] vis = new int[N];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) {
                dfsHelperTopo1(i, vis, stk, adj);
            }
        }
        while (stk.peek() != 0) {
            dist[stk.peek()] = -1;
            stk.pop();
        }
        dist[0] = 0;
        while (!stk.isEmpty()) {
            int node = stk.pop();
            ArrayList<Pair> ed = adj.get(node);
            for (Pair pair : ed) {
                int newDist;
                newDist = dist[node] + pair.second;
                if (newDist < dist[pair.first]) {
                    dist[pair.first] = newDist;
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == 9999) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    public static int[] shortestPath(int[][] edges, int n, int m, int src) {
        // Shortest path in Undirected Graph having unit weight of the edges
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            dist[i] = 9999;
        }
        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        dist[src] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while (!q.isEmpty()) {
            int node = q.remove();
            ArrayList<Integer> ed = adj.get(node);
            for (int e : ed) {
                int newdist = dist[node] + 1;
                if (newdist < dist[e]) {
                    dist[e] = newdist;
                    q.add(e);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] == 9999) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    static class Pair1 {
        String first;
        int second;

        Pair1(String first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        // Code here
        // Creating a queue ds of type {word,transitions to reach ‘word’}.
        Queue<Pair1> q = new LinkedList<>();

        // BFS traversal with pushing values in queue
        // when after a transformation, a word is found in wordList.
        q.add(new Pair1(startWord, 1));

        // Push all values of wordList into a set
        // to make deletion from it easier and in less time complexity.
        Set<String> st = new HashSet<String>();
        int len = wordList.length;
        for (int i = 0; i < len; i++) {
            st.add(wordList[i]);
        }
        st.remove(startWord);
        while (!q.isEmpty()) {
            String word = q.peek().first;
            int steps = q.peek().second;
            q.remove();

            // we return the steps as soon as
            // the first occurence of targetWord is found.
            if (word.equals(targetWord) == true)
                return steps;

            // Now, replace each character of ‘word’ with char
            // from a-z then check if ‘word’ exists in wordList.
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    // check if it exists in the set and push it in the queue.
                    if (st.contains(replacedWord) == true) {
                        st.remove(replacedWord);
                        q.add(new Pair1(replacedWord, steps + 1));
                    }
                }

            }
        }
        // If there is no transformation sequence possible
        return 0;
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void dijkstra(List<List<Node>> adj, int source) {
        //  graphs +ve weighted graphs
        int V = adj.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            for (Node neighbor : adj.get(current.vertex)) {
                int newDist = dist[current.vertex] + neighbor.distance;
                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;
                    pq.add(new Node(neighbor.vertex, newDist));
                }
            }
        }

        // Print distances
        for (int i = 0; i < V; i++) {
            System.out.println("Distance from " + source + " to " + i + " is " + dist[i]);
        }
    }
    public static List<Integer> dijkstra(List<List<Node>> adj, int source , int destination) {
        //  graphs +ve weighted graphs nodes from 0 to n-1
        int V = adj.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        int[] parent = new int[V];
        Arrays.fill(parent, -1);  // Initialize parent array
        parent[source] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            for (Node neighbor : adj.get(current.vertex)) {
                int newDist = dist[current.vertex] + neighbor.distance;
                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;
                    parent[neighbor.vertex] = current.vertex;
                    pq.add(new Node(neighbor.vertex, newDist));
                }
            }
        }

        // Store the final path in the ‘path’ array.
        List<Integer> path = new ArrayList<>();
        if (dist[destination] == Integer.MAX_VALUE) {
            path.add(-1); 
            return path; 
        }
        // Construct the path from destination to source
        for (int ptr = destination; ptr != -1; ptr = parent[ptr]) {
            path.add(ptr);
        }
        Collections.reverse(path);
        return path;
    }
}
