class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];

        int startRow = 0;
        int startCol = 0;
        int endRow = n - 1;
        int endCol = n - 1;

        int num = 1;

        while (startRow <= endRow && startCol <= endCol) {

            for (int j = startCol; j <= endCol; j++) {
                ans[startRow][j] = num++;
            }

            for (int i = startRow + 1; i <= endRow; i++) {
                ans[i][endCol] = num++;
            }

            if (startRow < endRow) {
                for (int j = endCol - 1; j >= startCol; j--) {
                    ans[endRow][j] = num++;
               }
            }

            if (startCol < endCol) {
                for (int i = endRow - 1; i >= startRow + 1; i--) {
                    ans[i][startCol] = num++;
                }
            }

            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }

        return ans;
    }
}