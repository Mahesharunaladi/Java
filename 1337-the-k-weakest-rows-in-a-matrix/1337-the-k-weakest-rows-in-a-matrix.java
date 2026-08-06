class Pair{
    int row;
    int sold;
    public Pair(int row,int sold){
        this.row=row;
        this.sold=sold;
    }

}
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] arr=new int[k];
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->{
            if(a.sold!=b.sold){
               return  Integer.compare(a.sold,b.sold);

            }
            else{
                return Integer.compare(a.row,b.row);
            }
       });
        for(int i=0;i<mat.length;i++){
            int cnt=0;
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==1){
                    cnt++;
                }
            }
            pq.add(new Pair(i,cnt));
        }
        for(int i=0;i<k;i++){
            Pair curr=pq.poll();
            arr[i]=curr.row;
        }
        return arr;
    }
}