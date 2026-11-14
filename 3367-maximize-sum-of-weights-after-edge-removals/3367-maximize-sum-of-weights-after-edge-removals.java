class Solution {

    public record Pair(int left, int right) {
    }

    public record LongPair(long left, long right) {
    }

    public static long maximizeSumOfWeights(int[][] edges, int k) {
        int numOfNodes = edges.length + 1; 
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i < numOfNodes; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(new Pair(edge[1], edge[2]));
            adjList.get(edge[1]).add(new Pair(edge[0], edge[2]));
        }

        long[][] dp = new long[numOfNodes][2];

        for (long[] row : dp) {
            Arrays.fill(row, -1L);
        }

        return dfs(0, 0, -1, adjList, k, dp);
    }


    public static long dfs(int currentNode, int keepParent, int parent, List<List<Pair>> adjList, int k, long[][] dp) {

        if (dp[currentNode][keepParent] != -1) {
            return dp[currentNode][keepParent];
        }
        int numOfNeighbours = adjList.get(currentNode).size();
        int edgesToRemove = Math.max(0, numOfNeighbours - k - keepParent);

        List<LongPair> children = new ArrayList<>();

        for (Pair neigh : adjList.get(currentNode)) {
            int child = neigh.left();
            int weight = neigh.right();

            if (child != parent) {
                long takeParent = dfs(child, 0, currentNode, adjList, k, dp) + weight;
                long dontTakeParent = dfs(child, 1, currentNode, adjList, k, dp);
                children.add(new LongPair(takeParent, dontTakeParent));
            }
        }
        children.sort(Comparator.comparingLong(a -> a.left() - a.right()));

        long answer = 0;
        for (int i = 0; i < children.size();i++){
            if(i< edgesToRemove){
                answer+= children.get(i).right();
            }
            else{
                answer+=Math.max(children.get(i).left(), children.get(i).right());
            }
        }
        
        dp[currentNode][keepParent] = answer;
        return answer;
    }
}