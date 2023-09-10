import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
    int home;
    int cost;

    public Edge(int home, int cost){
        this.home=home;
        this.cost=cost;
    }

    //cost 오름차순
    public int compareTo(Edge e){
        return this.cost-e.cost;
    }
}

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums= br.readLine().split(" ");

        int n=Integer.parseInt(nums[0]); int m=Integer.parseInt(nums[1]);
        PriorityQueue<Edge>[] queues=new PriorityQueue[n+1];

        for(int i=0;i<=n;i++){
            queues[i]=new PriorityQueue<Edge>();
        }

        for(int i=0;i<m;i++){
            nums= br.readLine().split(" ");
            int h1=Integer.parseInt(nums[0]); int h2=Integer.parseInt(nums[1]);
            int cost=Integer.parseInt(nums[2]);

            //양방향
            PriorityQueue<Edge> queue1=queues[h1];
            PriorityQueue<Edge> queue2=queues[h2];

            queue1.add(new Edge(h2, cost));
            queue2.add(new Edge(h1, cost));
        }

        //본격적 계산
        int max=Integer.MIN_VALUE; int sum=0; int cnt=0; //~n-1까지
        boolean[] visited=new boolean[n+1];
        PriorityQueue<Edge> candidates=new PriorityQueue();

        candidates.add(new Edge(1, 0));
        while(true){
            Edge now=candidates.poll();
            
            if(visited[now.home])
                continue;
            
            sum+=now.cost;
            if(max<now.cost)
                max=now.cost;
            cnt++;
            visited[now.home]=true;

            if(cnt==n)
                break;

            PriorityQueue<Edge> nexts=queues[now.home];

            while(!nexts.isEmpty()){
                Edge edge=nexts.poll();

                if(!visited[edge.home])
                    candidates.add(edge);
            }
        }

        System.out.print(sum-max);
    }
}