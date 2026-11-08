class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] colCounts = new int[n];
        int[] lastServerInRow = new int[m];
        int communicableServers = 0;
        
        
        for (int i = 0; i < m; i++) {
            int serversInRow = 0;
            int lastCol = -1;
            
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    serversInRow++;
                    colCounts[j]++;
                    lastCol = j; 
                }
            }
            
            if (serversInRow > 1) {
                
                communicableServers += serversInRow;
                lastServerInRow[i] = -1; 
            } else {
               
                lastServerInRow[i] = lastCol;
            }
        }
        
       
        for (int i = 0; i < m; i++) {
            int targetCol = lastServerInRow[i];
           
            if (targetCol != -1 && colCounts[targetCol] > 1) {
                communicableServers++;
            }
        }
        
        return communicableServers;
    }
}