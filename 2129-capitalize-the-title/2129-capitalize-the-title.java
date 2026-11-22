class Solution {
    public String capitalizeTitle(String title) {
        StringBuilder sb = new StringBuilder();
        int n = title.length();
        int i = 0;
        while (i < n) {
            int start = i;
            while (i < n && title.charAt(i) != ' ') {
                i++;
            }
            int wordLength = i - start;
            for (int j = start; j < i; j++) {
                if (j == start && wordLength > 2) {
                    sb.append(Character.toUpperCase(title.charAt(j)));
                } else {
                    sb.append(Character.toLowerCase(title.charAt(j)));
                }
            }
            if (i < n && title.charAt(i) == ' ') {
                sb.append(' ');
                i++;
            }
        }

        return sb.toString();
    }
}