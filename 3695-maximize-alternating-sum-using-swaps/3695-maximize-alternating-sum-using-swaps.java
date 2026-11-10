import java.util.*;

class Solution {
    private static class DSU {
        int[] parent;
        int[] size;

        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
        
                if (size[rootI] < size[rootJ]) {
                    int temp = rootI;
                    rootI = rootJ;
                    rootJ = temp;
                }
                parent[rootJ] = rootI;
                size[rootI] += size[rootJ];
            }
        }
    }

    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int n = nums.length;
        DSU dsu = new DSU(n);

       
        for (int[] swap : swaps) {
            dsu.union(swap[0], swap[1]);
        }

       
        Map<Integer, List<Integer>> componentIndices = new HashMap<>();
        Map<Integer, List<Integer>> componentValues = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            componentIndices.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
            componentValues.computeIfAbsent(root, k -> new ArrayList<>()).add(nums[i]);
        }

        long totalMaxSum = 0;

        
        for (int root : componentIndices.keySet()) {
            List<Integer> indices = componentIndices.get(root);
            List<Integer> values = componentValues.get(root);

            
            int evenCount = 0;
            for (int index : indices) {
                if (index % 2 == 0) {
                    evenCount++;
                }
            }

           
            values.sort(Collections.reverseOrder());

            
            for (int i = 0; i < evenCount; i++) {
                totalMaxSum += values.get(i);
            }

            
            for (int i = evenCount; i < values.size(); i++) {
                totalMaxSum -= values.get(i);
            }
        }

        return totalMaxSum;
    }
}