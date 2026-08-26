class Solution {
    public int hIndex(int[] citations) 
    {
        int l=0;
        int h=citations.length-1;
        int n=citations.length;
        int ans=0;
        while(l<=h)
        {
            int mid=l+(h-l)/2;
            if(citations[mid]>=n-mid)
            {
                ans=n-mid;
                h=mid-1;
            }
            else
            {
                l=mid+1;
            }
        }
        return ans;
    }
}