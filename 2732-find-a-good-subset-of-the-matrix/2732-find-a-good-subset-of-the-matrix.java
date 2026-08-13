class Solution {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        List<Integer> res = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            boolean allZero = true;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                res.add(i);
                return res;
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int ii = i + 1; ii < m; ii++) {
                boolean allZero = true;
                for (int j = 0; j < n; j++) {
                    if ((grid[i][j] & grid[ii][j]) > 0) {
                        allZero = false;
                        break;
                    }
                }
                if (allZero) {
                    res.add(i);
                    res.add(ii);
                    return res;
                }
            }
        }
        return res;
    }
}