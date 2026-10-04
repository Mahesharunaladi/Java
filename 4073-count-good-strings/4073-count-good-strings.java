class Solution {

    static final long mod = 1000000007L;

    private long[] fib(long n) {
        if (n == 0) {
            return new long[] { 0, 1 };
        }

        long[] p = fib(n / 2);
        long a = p[0];
        long b = p[1];
        long c = (a * ((2 * b % mod - a + mod) % mod)) % mod;
        long d = (a * a % mod + b * b % mod) % mod;

        if (n % 2 == 0) {
            return new long[] { c, d };
        }

        return new long[] { d, (c + d) % mod };
    }

    public int countGoodStrings(long n) {
        long fibonacci = fib(n)[0];
        return (int) ((2L * fibonacci) % mod);
    }
}