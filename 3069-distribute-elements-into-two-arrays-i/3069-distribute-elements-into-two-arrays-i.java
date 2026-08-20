class Solution {

    public int[] resultArray(int[] nums) {

        int n = nums.length;

        int arr1[] = new int[n];
        int arr2[] = new int[n];

        int index1 = 0;
        int index2 = 0;

        arr1[index1++] = nums[0];
        arr2[index2++] = nums[1];

        for (int i = 2; i < n; i++) {

            if (arr1[index1 - 1] > arr2[index2 - 1])
                arr1[index1++] = nums[i];
            else
                arr2[index2++] = nums[i];
        }

        int[] result = new int[n];

        System.arraycopy(arr1, 0, result, 0, index1);
        System.arraycopy(arr2, 0, result, index1, index2);

        return result;
    }
}