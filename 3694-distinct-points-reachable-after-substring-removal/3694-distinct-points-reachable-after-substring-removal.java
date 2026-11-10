class Solution {
    private void countStep(char c, int[] coordinate, int mul) {
        switch(c) {
            case 'U':
                coordinate[1] += 1 * mul;
                break;
            case 'L':
                coordinate[0] += -1 * mul;
                break;
            case 'R':
                coordinate[0] += 1 * mul;
                break;
            case 'D':
                coordinate[1] += -1 * mul;
                break;
        }
    }
    public int distinctPoints(String s, int k) {
        int n = s.length();

        if(n == k) return 1;

        int[] allSteps = new int[2];
        int[][] delta = new int[n - k + 1][2];
        for(int i=0; i<k; i++) {
            countStep(s.charAt(i), allSteps, 1);
            delta[0][0] = allSteps[0];
            delta[0][1] = allSteps[1];
        }
        for(int i=1; i<=n-k; i++) {
            int j = i+k-1;
            char c = s.charAt(j);
            countStep(c, delta[i], 1);
            allSteps[0] += delta[i][0];
            allSteps[1] += delta[i][1];
            countStep(s.charAt(i-1), delta[i], -1);
            delta[i][0] += delta[i-1][0];
            delta[i][1] += delta[i-1][1];
        }

        int ans = 0;

        Map<Integer, Set<Integer>> finalCoords = new HashMap<>();
        for(int i=0; i<=n-k; i++) {
            int X = allSteps[0] - delta[i][0];
            int Y = allSteps[1] - delta[i][1];
            finalCoords.putIfAbsent(X, new HashSet<>());
            if(finalCoords.get(X).add(Y)) ans++;
        }
        return ans;
    }
}