class Solution {
    public int firstUniqueEven(int[] nums) 
    {
        int[] t = new int[101];
        for(int i=0;i<nums.length;i++)
        {
            t[nums[i]]++;
        }
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]%2==0&&t[nums[i]]==1) return nums[i];
        }
        return -1;
    }

}