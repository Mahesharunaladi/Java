class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> tMp = new HashMap<>();
        HashMap<Character, Integer> sMp = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            tMp.put(ch, tMp.getOrDefault(ch, 0) + 1);
        }

        int required = tMp.size();
        int formed = 0;

        int l = 0;
        int resLeft = 0;
        int resRight = 0;
        int minLen = Integer.MAX_VALUE;

        for (int r = 0; r < s.length(); r++) {

            char ch = s.charAt(r);

            if (tMp.containsKey(ch)) {
                sMp.put(ch, sMp.getOrDefault(ch, 0) + 1);

                if (sMp.get(ch).intValue() == tMp.get(ch).intValue()) {
                    formed++;
                }
            }

            while (formed == required) {

                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    resLeft = l;
                    resRight = r;
                }

                char leftChar = s.charAt(l);

                if (tMp.containsKey(leftChar)) {

                    sMp.put(leftChar, sMp.get(leftChar) - 1);

                    if (sMp.get(leftChar) < tMp.get(leftChar)) {
                        formed--;
                    }
                }

                l++;
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(resLeft, resRight + 1);
    }
}