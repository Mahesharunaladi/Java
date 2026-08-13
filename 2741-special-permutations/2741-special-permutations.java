import java.util.Arrays;

class Solution {
    int mod = 1000000007;

    int sb(int x) {
        int ans = 0;
        while (x > 0) {
            if ((x & 1) == 1)
                ans++;
            x = x >> 1;
        }
        return ans;
    }

    long[][] dp = new long[1 << 14][15];

    int func(int idx, int[] nums, int mask, int prev) {
        if (idx == nums.length)
            return (sb(mask) == nums.length) ? 1 : 0;

        long way1 = 0;

        if (dp[mask][prev + 1] != -1)
            return (int) dp[mask][prev + 1];

        if (prev == -1) {
            for (int i = 0; i < nums.length; ++i) {
                if ((mask & (1 << i)) != 0)
                    continue;
                else {
                    int newMask = mask | (1 << i);
                    way1 = (way1 + func(idx + 1, nums, newMask, i)) % mod;
                }
            }
        } else {
            for (int i = 0; i < nums.length; ++i) {
                if ((mask & (1 << i)) != 0)
                    continue;
                else {
                    if (nums[i] % nums[prev] == 0 || nums[prev] % nums[i] == 0) {
                        int newMask = mask | (1 << i);
                        way1 = (way1 + func(idx + 1, nums, newMask, i)) % mod;
                    }
                }
            }
        }

        return (int) (dp[mask][prev + 1] = way1 % mod);
    }

    int specialPerm(int[] nums) {
        for (long[] row : dp)
            Arrays.fill(row, -1);

        int mask = 0;
        return func(0, nums, mask, -1) % mod;
    }
}