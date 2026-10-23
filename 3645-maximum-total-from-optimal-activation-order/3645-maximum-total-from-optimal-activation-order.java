class Solution {
    public long maxTotal(int[] value, int[] limit) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]){
                return b[1] - a[1]; 
            } else {
                return a[0] - b[0]; 
            }
        }); 
        PriorityQueue<int[]> act = new PriorityQueue<>((a, b) -> a[0] - b[0]);   
        for (int i = 0; i < limit.length; i++){
            int[] toAdd = {limit[i], value[i]}; 
            pq.offer(toAdd); 
        }
        long res = 0; 
        int active = 0; 
        while (!pq.isEmpty()){
            if (active < pq.peek()[0]){
                res += pq.peek()[1];
                active++;
                act.offer(pq.poll()); 
                while (!pq.isEmpty() && active >= pq.peek()[0]){
                    pq.poll(); 
                }
                while (!act.isEmpty() && active >= act.peek()[0]){
                    active--; 
                    act.poll(); 
                }
            }
        }
        return res; 
    }
}