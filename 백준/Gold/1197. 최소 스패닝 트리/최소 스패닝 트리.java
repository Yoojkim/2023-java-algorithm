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

    //오름차순 정렬
    public int compareTo(Edge edge){
        return this.cost-edge.cost;
    }
}
public class Main{
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        parents = new int[N+1];

        for(int i=1;i<=N;i++){
            parents[i] = i;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for(int i=0;i<M;i++){
            String[] edge = br.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            int cost = Integer.parseInt(edge[2]);

            queue.add(new Edge(a, b, cost));
        }

        long res = getMst(queue);
        System.out.println(res);
    }

    private static long getMst(PriorityQueue<Edge> queue){
        long sum = 0;

        while(!queue.isEmpty()){
            Edge edge = queue.poll();

            if(isCycle(edge)){
                continue;
            }

            sum+=edge.cost;
        }

        return sum;
    }

    private static boolean isCycle(Edge edge){
        int aParent = getParent(edge.a);
        int bParent = getParent(edge.b);

        if(aParent==bParent){
            return true;
        }

        //union
        if(aParent<bParent){
            parents[aParent] = bParent;
        } else{
            parents[bParent] = aParent;
        }
        
        return false;
    }

    private static int getParent(int node){
        if(node==parents[node]){
            return node;
        }

        return getParent(parents[node]);
    }
}