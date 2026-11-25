class Solution {
    public int minimumSwaps(int[] nums) {
        int minSwaps = 0;
        int zeroesCount  = 0;
        for(int num : nums){
            if(num == 0) zeroesCount++;
        }
        if(zeroesCount == 0) return 0;

        for(int i = nums.length - zeroesCount; i < nums.length; i++){
            if(nums[i] != 0){
                minSwaps++;
            }
        }
        return minSwaps;
    }
}