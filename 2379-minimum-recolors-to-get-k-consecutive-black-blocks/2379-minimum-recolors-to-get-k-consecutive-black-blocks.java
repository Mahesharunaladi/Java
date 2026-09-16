class Solution {
    public int minimumRecolors(String a, int k) {
        int m,w=0;
        for(int i=0;i<k;i++){
            if(a.charAt(i)=='W'){
                w+=1;
            }
        }
        m=w;
        for(int i=k;i<a.length();i++){
            if(a.charAt(i)=='W'){
                w+=1;
            }
            if(a.charAt(i-k)=='W'){
                w-=1;
            }
            m=Math.min(m,w);
        }
        return m;
    }
}