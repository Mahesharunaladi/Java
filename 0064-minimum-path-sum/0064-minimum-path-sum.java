class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        // Initialize the destination cell
        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        // Fill the DP table from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                // Destination cell is already initialized
                if (i == m - 1 && j == n - 1) {
                    continue;
                }

                // Both bottom and right cells are available
                if (isValid(i + 1, j, m, n) && isValid(i, j + 1, m, n)) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);

                // Only the bottom cell is available
                } else if (isValid(i + 1, j, m, n)) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];

                // Only the right cell is available
                } else {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    // Checks whether a cell is inside the grid
    private boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}