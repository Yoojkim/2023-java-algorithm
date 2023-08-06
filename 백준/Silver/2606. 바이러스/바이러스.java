import java.util.*;
import java.io.*;

//순서, 양방향 고려하지 않아도 됨

class Graph{
    int n;
    LinkedList<Integer>[] graph;
    
    public Graph(int n){
        this.n=n;
        this.graph=new LinkedList[n+1];
        
        for(int i=0;i<n+1;i++){
            graph[i]=new LinkedList<>();
        }
        
    }
    
    void addEdge(int v, int m){
        LinkedList<Integer> nowGraph=graph[v];
        nowGraph.add(m);
    }
    
    int bfs(){
        int cnt=0;int start=1;
        boolean[] visited=new boolean[n+1];
        LinkedList<Integer> queue=new LinkedList();
        
        //start 방문
        visited[start]=true;
        queue.add(start);
        
        while(queue.size()!=0){
            int now=queue.poll();
            Iterator<Integer> it=graph[now].listIterator();
            
            while(it.hasNext()){
                int next=it.next();
                
                if(!visited[next]){
                    cnt++;
                    queue.add(next);
                    visited[next]=true;
                }
            }
        }
        
        return cnt;
    }
}

//메인 클래스
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st=new StringTokenizer(bf.readLine());
        int n=Integer.parseInt(st.nextToken());
        
        st=new StringTokenizer(bf.readLine());
        int m=Integer.parseInt(st.nextToken());
        
        Graph graph=new Graph(n);
        
        for(int i=0;i<m;i++){
            st=new StringTokenizer(bf.readLine());
            
            int e1=Integer.parseInt(st.nextToken());
            int e2=Integer.parseInt(st.nextToken());
            
            graph.addEdge(e1, e2);
            graph.addEdge(e2, e1);
        }
        
        System.out.println(graph.bfs());
    }
}