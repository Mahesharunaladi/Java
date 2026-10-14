class Solution {
    public long minimumTime(int[] d, int[] r) {
        long d1 = d[0], d2 = d[1];
        long r1 = r[0], r2 = r[1];
        long lcm = lcm(r1, r2);

        long low = 1;
        long high = (d1 + d2) * 2L + 10;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (isPossible(mid, d1, d2, r1, r2, lcm)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isPossible(long T, long d1, long d2, long r1, long r2, long lcm) {
        long bothRecharge = T / lcm;
        long only1 = (T / r2) - bothRecharge;
        long only2 = (T / r1) - bothRecharge;
        long shared = T - (T / r1) - (T / r2) + bothRecharge;

        long rem1 = Math.max(0L, d1 - only1);
        long rem2 = Math.max(0L, d2 - only2);

        return rem1 + rem2 <= shared;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}