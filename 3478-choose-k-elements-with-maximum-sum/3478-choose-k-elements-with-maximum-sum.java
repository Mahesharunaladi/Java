class Solution {
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int l = nums1.length;
        Integer[] idx = new Integer[l];
        for(int i=0;i<l;i++)
            idx[i] = i;
        Arrays.sort(idx , new Comparator<Integer>() {
            @Override
            public int compare(Integer i , Integer j){
                return nums1[i] - nums1[j];
            }
        });
        int[] temp =new int[l];
        for(int i=0;i<l;i++)
            temp[i] = nums1[idx[i]];
        for(int i=0;i<l;i++)
            nums1[i] = temp[i];
        for(int i=0;i<l;i++)
            temp[i] = nums2[idx[i]];
        for(int i=0;i<l;i++)
            nums2[i] = temp[i];
        long[] ans = new long[nums1.length];
        long sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y)->(x - y));
        int size = 0;
        for(int i = 0;i<l;){
            int j=i;
            long new_sum = sum;

            for(j = i;j<l && nums1[j]==nums1[i];j++){
                ans[idx[j]] = sum;


                if(size < k){
                    new_sum += nums2[j];
                    pq.add(nums2[j]);
                    size++;
                }
                else{

                    if(nums2[j] > pq.peek()){
                        new_sum -= pq.remove();
                        new_sum += nums2[j];
                        pq.add(nums2[j]);
                    }
                }
            }

            i = j;

            sum = new_sum;
        }

        return ans;
    }
}