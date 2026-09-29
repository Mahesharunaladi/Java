class Solution {
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private long gcd(long a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, (int) (a % b));
    }

    public long maxScore(int[] nums) {
        int n = nums.length;

        // edge
        if (n == 1) {
            return (long) nums[0] * nums[0];
        }

        int tgcd = nums[0];
        long tlcm = nums[0];
        for (int i = 1; i < nums.length; i++) {
            tgcd = gcd(tgcd, nums[i]);
            tlcm = (tlcm * nums[i]) / gcd(tlcm, nums[i]);
        }

        long maxScore = tgcd * tlcm;
        for (int i = 0; i < n; i++) {
            int ngcd = 0;
            long nlcm = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    ngcd = ngcd == 0 ? nums[j] : gcd(ngcd, nums[j]);
                    nlcm = (nlcm * nums[j]) / gcd(nlcm, nums[j]);
                }
            }

            maxScore = Math.max(maxScore, ngcd * nlcm);
        }
        return maxScore;
    }
}