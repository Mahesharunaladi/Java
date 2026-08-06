class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        char[] chArr = s.toCharArray();
        int n = chArr.length;

        if(chArr[n-1] != '0') return false;

        Queue<Integer> queue = new ArrayDeque<>();
        int reached = 0;
        queue.offer(0);

        while(!queue.isEmpty()) {
            int curr = queue.poll();

            int l = Math.max(reached+1, curr + minJump);
            int r = Math.min(curr + maxJump, n-1);

            for(int i = l; i <= r; i++) {
                if(chArr[i] == '0') {
                    if(i == n-1) return true;
                    queue.offer(i);
                }
            }
            reached = Math.max(reached, Math.max(r, l));
        }

        return false;
    }
}