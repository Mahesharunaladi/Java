class Solution {
    public int minOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int i = nums.length - 1; i >= 0; --i) {
            int n = nums[i];
            if (set.contains(n)) {
                int len = i + 1;
                return (len / 3) + ((len % 3) != 0 ? 1 : 0);
            } 
            else set.add(n);
        }
        
        return 0;
    }
}