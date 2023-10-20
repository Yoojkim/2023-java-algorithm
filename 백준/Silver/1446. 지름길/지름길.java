import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost){
        this.start=start;
        this.end=end;
        this.cost=cost;
    }

    public int compareTo(Edge e){
        return this.end-e.end;
    }
}
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int D=Integer.parseInt(st.nextToken());

        int[] dist=new int[D+1];
        for(int i=0;i<=D;i++){
            dist[i]=i;
        }

        PriorityQueue<Edge> queue=new PriorityQueue<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            queue.add(new Edge(start, end, cost));
        }

        while(!queue.isEmpty()){
            Edge e=queue.poll();

            if(e.end>D)
                continue;

            if(dist[e.end]<=dist[e.start]+e.cost)
                continue;

            dist[e.end]=dist[e.start]+e.cost;

            //end 이후 초기화
            for(int j=1;j<=(D-e.end);j++){
                if(dist[e.end+j]<=dist[e.end]+j)
                    break;

                dist[e.end+j]=dist[e.end]+j;
            }
        }

        System.out.print(dist[D]);
    }
}