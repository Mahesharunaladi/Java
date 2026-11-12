class Solution {
    public int distance(char s1, char s2) {
        int diff = Math.abs(s1 - s2);
        return Math.min(diff, 26 - diff);
    }
    public String getSmallestString(String s, int k) {
        char[] result = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (distance(s.charAt(i), c) <= k) {
                    result[i] = c;
                    k -= distance(s.charAt(i), c);
                    break;
                }
            }
        }
        return new String(result);
        
    }
}