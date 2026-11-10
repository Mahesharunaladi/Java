class Solution {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        long[] finish = new long[n];

        int[] order = new int[n];
        int idx = 0;

        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 0;

        while (top > 0) {
            int node = stack[--top];
            order[idx++] = node;

            for (int child : graph[node]) {
                stack[top++] = child;
            }
        }

        for (int i = idx - 1; i >= 0; i--) {
            int node = order[i];

            if (graph[node].isEmpty()) {
                finish[node] = baseTime[node];
            } else {
                long earliest = Long.MAX_VALUE;
                long latest = Long.MIN_VALUE;

                for (int child : graph[node]) {
                    earliest = Math.min(earliest, finish[child]);
                    latest = Math.max(latest, finish[child]);
                }

                long ownDuration = (latest - earliest) + baseTime[node];
                finish[node] = latest + ownDuration;
            }
        }

        return finish[0];
    }
}