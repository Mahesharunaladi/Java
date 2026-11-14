import java.util.*;

class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;
        int[] answer = new int[n];
        ArrayList<Integer>[] tree1 = new ArrayList[n];
        ArrayList<Integer>[] tree2 = new ArrayList[m];

        for (int i = 0; i < n; i++) tree1[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) tree2[i] = new ArrayList<>();

        for (int[] edge : edges1) {
            tree1[edge[0]].add(edge[1]);
            tree1[edge[1]].add(edge[0]);
        }

        for (int[] edge : edges2) {
            tree2[edge[0]].add(edge[1]);
            tree2[edge[1]].add(edge[0]);
        }

        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            visited[i] = true;
            temp[i] = dfs(tree1, i, k, visited);
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            boolean[] visited = new boolean[m];
            visited[i] = true;
            max = Math.max(max, dfs(tree2, i, k - 1, visited));
        }

        for (int i = 0; i < n; i++) {
            answer[i] = temp[i] + max;
        }

        return answer;
    }

    public int dfs(ArrayList<Integer>[] graph, int src, int k, boolean[] visited) {
        if (k < 0) return 0;
        int cnt = 1;
        for (int nbr : graph[src]) {
            if (!visited[nbr]) {
                visited[nbr] = true;
                cnt += dfs(graph, nbr, k - 1, visited);
            }
        }
        return cnt;
    }
}