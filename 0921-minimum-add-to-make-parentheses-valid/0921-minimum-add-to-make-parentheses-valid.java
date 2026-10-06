class Solution {
    public int minAddToMakeValid(String s) {
        int opening = 0;
        int res = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                opening++;
            } else {
                if (opening == 0) {
                    res++;
                } else {
                    opening--;
                }
            }
        }
        return res + opening;
    }
}