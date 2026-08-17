import java.util.*;

class Solution {
  
    private static final String[] KEYPAD = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        return helper("", digits);
    }

    private static ArrayList<String> helper(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }

        int digit = up.charAt(0) - '0';
        String letters = KEYPAD[digit]; 
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < letters.length(); i++) {
            char ch = letters.charAt(i);
            list.addAll(helper(p + ch, up.substring(1)));
        }

        return list;
    }
}