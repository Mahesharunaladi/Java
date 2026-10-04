class Solution {
    Double[][][] grid;
    int[][] dirs = {{2, 1}, {1, 2}, {-2, 1}, {-1, 2}, {2, -1}, {1, -2}, {-2, -1}, {-1, -2}};

    public double knightProbability(int n, int k, int row, int column) {
        grid = new Double[n][n][k + 1];

        return findTotalProb(row, column, n, k);
    }

    double findTotalProb(int i, int j, int n, int k){
        if(i < 0 || j < 0 || i >= n || j >= n) return 0;
        if(k == 0) return 1.0;
        if(grid[i][j][k] != null) return grid[i][j][k];
        double prob = 0.0;
        for(int[] dir : dirs){
            prob += findTotalProb(i + dir[0], j + dir[1], n, k - 1) / 8.0;
        }
        grid[i][j][k] = prob;
        return prob;
    }
}