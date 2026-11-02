class Solution {
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ct = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    if ((j > 0 && grid[i][j - 1] == 0) || j == 0)
                        ct++;

                    if ((i > 0 && grid[i - 1][j] == 0) || i == 0)
                        ct++;

                    if ((j < m - 1 && grid[i][j + 1] == 0) || j == m - 1)
                        ct++;

                    if ((i < n - 1 && grid[i + 1][j] == 0) || i == n - 1)
                        ct++;
                }
            }
        }
        return ct;
    }
}
