class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart=newInterval[0];
        int newEnd=newInterval[1];
        List<int[]> list = new ArrayList<int[]>();
        int i=0;

        while(i<intervals.length && intervals[i][1]<newStart){
            list.add(new int[]{intervals[i][0],intervals[i][1]});
            i++;
        }

        while(i<intervals.length && intervals[i][0]<=newEnd){
            newStart = Math.min(newStart, intervals[i][0]);
            newEnd = Math.max(newEnd, intervals[i][1]);
            i++;
        }
        list.add(new int[]{newStart,newEnd});
        while(i<intervals.length){
            list.add(new int[]{intervals[i][0],intervals[i][1]});
            i++;
        }
        


        return list.toArray(new int[list.size()][]);
        
    }
}