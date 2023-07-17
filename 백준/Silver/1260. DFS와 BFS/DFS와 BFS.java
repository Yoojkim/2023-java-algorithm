import java.util.*;
import java.io.*;

class Graph{
    int n;
    LinkedList<Integer> graph[];

    public Graph(int n){
        this.n=n;
        graph=new LinkedList[n+1];
        for(int i=0;i<n+1;i++){
            graph[i]=new LinkedList();
        }
    }

    void addEdge(int v, int w){
        
        //차례대로 맞는 곳에 넣기 
        LinkedList<Integer> tempDist=graph[v];
        
        int idx;
        for(idx=0;idx<tempDist.size();idx++){
            if(tempDist.get(idx)>w) break;
        }
        
        tempDist.add(idx,w);
    }

    void dfs(int v){
        boolean [] visited=new boolean[n+1];

        dfsUtil(v, visited);
    }

    void dfsUtil(int v, boolean[] visited){
        System.out.print(v+" ");
        visited[v]=true;
        Iterator<Integer> it=graph[v].listIterator();
        while(it.hasNext()){
            int n=it.next();

            if(!visited[n]){
                dfsUtil(n,visited);
            }
        }
    }

    void bfs(int v){
        boolean [] visited=new boolean[n+1];
        LinkedList<Integer> queue=new LinkedList();
        queue.addLast(v);
        visited[v]=true;
        System.out.print(v+" ");

        while(queue.size()!=0){
            int n=queue.poll();
            Iterator<Integer> it=graph[n].listIterator();

            while(it.hasNext()){
                int gn=it.next();

                if(!visited[gn]){
                    visited[gn]=true;
                    queue.add(gn);
                    System.out.print(gn+" ");
                }

            }

        }

        //queue
        Iterator<Integer> qit=queue.listIterator();
        while(qit.hasNext()){
            int qn=qit.next();
            System.out.print(v+" ");
            visited[qn]=true;

            Iterator<Integer> it=graph[qn].listIterator();
            while(it.hasNext()){
                int n=it.next();
                if(!visited[n])
                    queue.addLast(n);
            }
        }
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        //본격적 수행 하는 부분

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int v=Integer.parseInt(st.nextToken());
        Graph graph=new Graph(n);

        for(int i=0;i<m;i++){
            StringTokenizer graphSt=new StringTokenizer(bf.readLine());
            int m1=Integer.parseInt(graphSt.nextToken());
            int m2=Integer.parseInt(graphSt.nextToken());
            graph.addEdge(m1, m2);
            graph.addEdge(m2, m1);
        }

        graph.dfs(v);
        System.out.println();
        graph.bfs(v);

    }
}