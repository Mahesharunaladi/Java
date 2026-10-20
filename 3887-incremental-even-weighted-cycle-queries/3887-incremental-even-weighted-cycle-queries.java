class Solution {
    public int numberOfEdgesAdded(int n, int[][] edges) {
        DisjointSet dj=new DisjointSet();
        for(int i=0;i<=n;i++){
            dj.createSet(i);
        }
        int ans=0;
        for(int[]e:edges){
            int u=e[0];
            int v=e[1];
            int w=e[2];
            if(dj.find(u)!=dj.find(v)){
                dj.union(u,v,w);
                ans++;
            }
            else{
                // int sum=dj.getCost(u)+w;
                // if(sum%2!=0){
                //     continue;
                // } 
                int p = dj.getParity(u) ^ dj.getParity(v);

            if(p != w){
                continue;
             }
                else{
                    dj.union(u,v,w);
                    ans++;
                }

            }
        }
        // int ans=0;
        //     for(int i=0;i<n;i++){
        //         if(dj.find(i)==i){
        //           ans+=dj.getEdge(i);
        //         }
        //     }
        return ans;
    }
    public class DisjointSet{
        public class Node{
            int val;
            int rank;
            Node parent;
            int parity;
            int edge;
            int cost;
        }
        HashMap<Integer,Node>map=new HashMap<>();
        public void createSet(int v){
            Node nn=new Node();
            nn.val=v;
            nn.rank=1;
            nn.parent=nn;
            nn.edge=0;
            nn.cost=0;
            nn.parity=0;
            map.put(v,nn);
        }
        public int find(int v){
            Node nn=map.get(v);
            return find(nn).val;
        }
        public Node find(Node nn){
            if(nn==nn.parent){
                return nn;

            }
            // Node rn=find(nn.parent);
            // rn.parity^=nn.parent.parity;

             Node p = nn.parent;
              Node rn = find(p);

              nn.parity ^= p.parity;
            nn.parent=rn;
            return rn;
        }
        public void union(int v1,int v2,int w){
            Node n1=map.get(v1);
            Node n2=map.get(v2);
            Node rn1=find(n1);
            Node rn2=find(n2);
            int p1 = n1.parity;
            int p2 = n2.parity;
            if(rn1==rn2){

                return ;
            }
            if(rn1.rank>rn2.rank){
                rn2.parent=rn1;

                rn2.parity = p1 ^ p2 ^ w;
            }
            else if(rn2.rank>rn1.rank){
                rn1.parent=rn2;

                rn1.parity=p1^p2^w;
                
            }
            else{
                rn2.parent=rn1;

                rn2.parity=p1^p2^w;
                rn1.rank++;
            }
        }
        public int getCost(int u){
            Node nn=map.get(u);
            return find(nn).cost;
        }
        public int getEdge(int v){
            Node nn=map.get(v);
            return find(nn).edge;
        }
        public int getParity(int u){
        Node nn = map.get(u);
          find(nn);
         return nn.parity;
     }
    }
}