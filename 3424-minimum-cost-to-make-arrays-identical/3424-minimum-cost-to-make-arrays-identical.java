class Solution {
    public long minCost(int[] arr, int[] brr, long k) {
        int len = arr.length;
        
        long raw = 0;
        for (int i = 0; i < len; i++) {
            raw += Math.abs((long) arr[i] - brr[i]);
        }
        
        int[] tmp = arr.clone();
        int[] dst = brr.clone();
        
        Arrays.sort(tmp);
        Arrays.sort(dst);
        
        long srt = k;
        for (int i = 0; i < len; i++) {
            srt += Math.abs((long) tmp[i] - dst[i]);
        }
        
        return Math.min(raw, srt);
    }
}