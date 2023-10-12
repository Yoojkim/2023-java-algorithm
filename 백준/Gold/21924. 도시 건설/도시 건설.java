import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
    int a;
    int b;
    int cost;

    public Edge(int a, int b, int cost){
        this.a=a;
        this.b=b;
        this.cost=cost;
    }

    public int compareTo(Edge e){
        return this.cost-e.cost;
    }
}

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nums=br.readLine().split(" ");
        int n=Integer.parseInt(nums[0]); int m=Integer.parseInt(nums[1]);

        PriorityQueue<Edge> queue=new PriorityQueue();
        long edgeSum=0;
        long mstSum=0;
        int mstCnt=0;

        StringTokenizer st;
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int cost=Integer.parseInt(st.nextToken());

            queue.add(new Edge(a, b, cost));
            edgeSum+=cost;
        }

        int[] parents =new int[n+1];
        for(int i=0;i<=n;i++){
            parents[i]=i;
        }

        while(!queue.isEmpty()){
            Edge nowEdge=queue.poll();

            if(checkParent(parents, nowEdge.a, nowEdge.b))
                continue;

            //연결
            union(parents, nowEdge.a, nowEdge.b);

            //후처리
            mstSum+=nowEdge.cost;
            mstCnt++;
        }

        long ans=-1;
        if(mstCnt==n-1)
            ans=edgeSum-mstSum;

        System.out.print(ans);
    }

    private static boolean checkParent(int[] parents, int a, int b){
        if(getParent(parents, a) == getParent(parents, b))
            return true;

        return false;
    }

    private static int getParent(int[] parents, int child){
        if(parents[child]==child){
            return child;
        }

        return getParent(parents, parents[child]);
    }

    //최적화 x
    private static void union(int[] parents, int a, int b){
        //부모 node
        if(parents[a]==a){
            parents[a]=b;

            return;
        }
        
        int parent=parents[a];
        parents[a]=b;
        union(parents, parent, b);
    }
}