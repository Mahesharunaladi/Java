class Solution {
    public int waysToSplitArray(int[] nums) {
        long right = 0;
        long left = 0;
        for(int i : nums){
             right += i;
        }
        int ans = 0;
       for(int i = 0; i < nums.length-1; i++){
          left += nums[i];
          right -= nums[i];
          if(left >= right){
            ans++;
          }
       } 
       return ans;
    }
}