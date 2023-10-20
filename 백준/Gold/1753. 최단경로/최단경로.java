import java.util.*;
import java.io.*;

class Point implements Comparable<Point>{
    int v;
    int cost;
    
    public Point(int v, int cost){
        this.v=v;
        this.cost=cost;
    }
    
    public int compareTo(Point p){
        //cost 오름차순
        return this.cost-p.cost;
    }
}
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V=Integer.parseInt(st.nextToken());
        int E=Integer.parseInt(st.nextToken());
        
        int start=Integer.parseInt(br.readLine());
        
        //정답 배열 
        int[] dist=new int[V+1];
        //인접 리스트의 리스트 
        List<LinkedList<Point>> points=new ArrayList<>();
        for(int i=0;i<=V;i++){
            dist[i]=dist[i]=Integer.MAX_VALUE;
            points.add(new LinkedList<Point>());
        }
        
        //단방향
        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            
            points.get(u).addLast(new Point(v, w));
        }
        
        PriorityQueue<Point> queue=new PriorityQueue();
        boolean[] visited=new boolean[V+1];
        
        //시작 위해 start, 0 
        queue.add(new Point(start, 0));
        
        while(!queue.isEmpty()){
            Point now=queue.poll();
            
            if(visited[now.v])
                continue;
            
            //여기서 로직상 불일치 있을 확률 ... 
            dist[now.v]=now.cost;
            visited[now.v]=true;
            
            for(Point p:points.get(now.v)){
                //v 인접 정점
                if(dist[p.v]>dist[now.v]+p.cost)
                    queue.add(new Point(p.v, dist[now.v]+p.cost));
            }
        }
        
        StringBuilder sb= new StringBuilder();
        for(int i=1;i<=V;i++){
            if(dist[i]==Integer.MAX_VALUE)
                sb.append("INF");
            else
                sb.append(dist[i]);
            
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
}