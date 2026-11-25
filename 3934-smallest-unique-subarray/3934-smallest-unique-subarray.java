class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] sa = buildSuffixArray(nums, n, 100001);
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) rank[sa[i]] = i;
        int[] lcp = new int[n + 1];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) h++;
                lcp[rank[i]] = h;
                if (h > 0) h--;
            } else {
                h = 0;
            }
        }

        int ans = n;
        for (int r = 0; r < n; r++) {
            int prev = lcp[r];
            int nxt = (r + 1 < n) ? lcp[r + 1] : 0;
            int need = Math.max(prev, nxt) + 1;
            if (sa[r] + need <= n) ans = Math.min(ans, need);
        }
        return ans;
    }
    private int[] buildSuffixArray(int[] s, int n, int m) {
        int[] sa = new int[n], sa2 = new int[n];
        int[] rnk = new int[n], rnk2 = new int[n];
        int[] cnt = new int[Math.max(m, n) + 1];

        for (int i = 0; i < n; i++) cnt[s[i]]++;
        for (int i = 1; i < m; i++) cnt[i] += cnt[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[--cnt[s[i]]] = i;

        int classes = 1;
        rnk[sa[0]] = 0;
        for (int i = 1; i < n; i++) {
            if (s[sa[i]] != s[sa[i - 1]]) classes++;
            rnk[sa[i]] = classes - 1;
        }

        for (int k = 1; k < n && classes < n; k <<= 1) {
            int p = 0;
            for (int i = n - k; i < n; i++) sa2[p++] = i;
            for (int j = 0; j < n; j++) if (sa[j] >= k) sa2[p++] = sa[j] - k;

            Arrays.fill(cnt, 0, classes, 0);
            for (int i = 0; i < n; i++) cnt[rnk[i]]++;
            for (int i = 1; i < classes; i++) cnt[i] += cnt[i - 1];
            for (int j = n - 1; j >= 0; j--) sa[--cnt[rnk[sa2[j]]]] = sa2[j];

            rnk2[sa[0]] = 0;
            classes = 1;
            for (int j = 1; j < n; j++) {
                int a = sa[j - 1], b = sa[j];
                int a2 = a + k < n ? rnk[a + k] : -1;
                int b2 = b + k < n ? rnk[b + k] : -1;
                if (rnk[a] != rnk[b] || a2 != b2) classes++;
                rnk2[b] = classes - 1;
            }
            int[] t = rnk; rnk = rnk2; rnk2 = t;
        }
        return sa;
    }
}