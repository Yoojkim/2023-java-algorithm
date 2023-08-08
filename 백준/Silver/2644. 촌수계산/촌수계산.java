import java.util.*;
import java.io.*;

class Graph{
    int n;
    LinkedList<Integer>[] graph;
    
    public Graph(int n){
        this.n=n;
        
        graph=new LinkedList[n+1];
        for(int i=0;i<n+1;i++){
            graph[i]=new LinkedList<Integer>();
        }
    }
    
    void addEdge(int n, int m){
        //양방향 
        LinkedList<Integer> nGraph=graph[n];
        nGraph.add(m);
        
        LinkedList<Integer> mGraph=graph[m];
        mGraph.add(n);
    }
    
    //return: 촌 수 
    int bfs(int start, int target){
        //start에서 시작해서 target 찾을 때까지 -> 없으면 -1 
        LinkedList<Integer> queue=new LinkedList();
        int[] visited=new int[n+1]; //0으로 초기화
        
        queue.add(start);
        //visited[start]=1;
        
        while(queue.size()!=0){
            int point=queue.poll();
            LinkedList<Integer> pointGraph=graph[point];
            Iterator<Integer> it= pointGraph.listIterator();
            
            int pointCnt=visited[point];
            
            boolean quit=false;
            while(it.hasNext()){
                int next=it.next();
                
                if(visited[next]==0){
                    //여기서 방문처리를 해야 중복으로 들어가지 않음 
                    queue.addLast(next);
                    visited[next]=pointCnt+1;
                    
                    if(next==target){
                        quit=true;
                    }
                }
            }
            
            if(quit)
                break;
            
        }
        
        return visited[target]==0?-1:visited[target];
    }
 }

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        Graph graph=new Graph(n);
        
        StringTokenizer st= new StringTokenizer(br.readLine());
        int start=Integer.parseInt(st.nextToken());
        int target=Integer.parseInt(st.nextToken());
        
        int inputN=Integer.parseInt(br.readLine());
        for(int i=0;i<inputN;i++){
            st=new StringTokenizer(br.readLine());
            graph.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        int result=graph.bfs(start, target);
        System.out.println(result);
    }
}