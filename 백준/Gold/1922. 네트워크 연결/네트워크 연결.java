import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
    int node;
    int cost;
    
    public Edge(int node, int cost){
        this.node=node;
        this.cost=cost;
    }
    
    public int compareTo(Edge e){
        return this.cost-e.cost;
    }
}

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        
        LinkedList<Edge>[] nears=new LinkedList[n+1];
        for(int i=0;i<=n;i++){
            nears[i]=new LinkedList();
        }
        
        for(int i=0;i<m;i++){
            String[] nums=br.readLine().split(" ");
            int node1=Integer.parseInt(nums[0]);
            int node2=Integer.parseInt(nums[1]);
            int cost=Integer.parseInt(nums[2]);
            
            nears[node1].add(new Edge(node2, cost));
            nears[node2].add(new Edge(node1, cost));
        }
        
        int sum=0; int cnt=0;
        boolean[] visited=new boolean[n+1];
        PriorityQueue<Edge> queue=new PriorityQueue();
        queue.add(new Edge(1, 0));
        while(true){
            Edge now=queue.poll();
            
            if(visited[now.node])
                continue;
            
            //현재 now에 해당하는 node 방문
            cnt++;
            sum+=now.cost;
            visited[now.node]=true;
            
            if(cnt==n)
                break;
            
            for(Edge near:nears[now.node]){
                if(visited[near.node])
                    continue;
                queue.add(near);
            }
        }
        
        System.out.print(sum);
    }
}