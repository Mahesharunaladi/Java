class Solution {

    private static boolean allStars(int j, String pattern){
        for(int t = 1; t <= j; t++){
            if(pattern.charAt(t-1) != '*'){
                return false;
            }
        }

        return true;
    }

    private static boolean recursive(int i, int j, String text, String pattern){

        if(i < 0 && j < 0){
            return true;
        }

        if(i >= 0 && j < 0){    
            return false;
        }

        if(i < 0 && j >= 0){
            return allStars(j, pattern);
        }

        if(text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?'){
            return recursive(i-1, j-1, text, pattern);
        }

        if(pattern.charAt(j) == '*'){
            return recursive(i-1, j, text, pattern) || recursive(i, j-1, text, pattern);
        }

        return false;
    }

    private static boolean memoization(int i, int j, String text, String pattern, int[][] dp) {

        if (i == 0 && j == 0) {
            return true;
        }

        if (i > 0 && j == 0) {
            return false;
        }

        if(i == 0 && j > 0){
            return allStars(j, pattern);
        }

        if (dp[i][j] != - 1) {
            return dp[i][j] == 1;
        }

        if (text.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?') {
            dp[i][j] = memoization(i - 1, j - 1, text, pattern, dp) ? 1 : 0;
        }

        else if (pattern.charAt(j-1) == '*') {
            dp[i][j] = (memoization(i - 1, j, text, pattern, dp) || memoization(i, j - 1, text, pattern, dp)) ? 1 : 0;
        }
        else {
            dp[i][j] = 0;
        }

        return dp[i][j] == 1;
    }

    private boolean tabulation(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for(int i = 1; i <= n; i++){
            dp[i][0] = false;
        }

       for (int j = 1; j <= m; j++) {
            if (pattern.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            } else {
                dp[0][j] = false;
            }
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(text.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j - 1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if (pattern.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else{
                    dp[i][j] = false;
                }
            }
        }
        
        return dp[n][m];

    }
    
    private boolean ultimateSpaceOptimization(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        boolean[] prev = new boolean[m + 1];
        prev[0] = true;

       for (int j = 1; j <= m; j++) {
            if (pattern.charAt(j - 1) == '*') {
                prev[j] = prev[j - 1];
            } else {
                prev[j] = false;
            }
        }
        
        for(int i = 1; i <= n; i++){
            boolean[] curr = new boolean[m + 1];
            for(int j = 1; j <= m; j++){
                if(text.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j - 1) == '?'){
                    curr[j] = prev[j-1];
                } else if (pattern.charAt(j-1) == '*') {
                    curr[j] = prev[j] || curr[j-1];
                }else{
                    curr[j] = false;
                }
            }

            prev = curr;
        }
        
        return prev[m];

    }

    public boolean isMatch(String text, String pattern) {
        
        int n = text.length();
        int m = pattern.length();

        int[][] dp = new int[n+1][m+1];
        for(int[] temp : dp){
            Arrays.fill(temp, -1);
        }
        return ultimateSpaceOptimization(text, pattern);
    }
}