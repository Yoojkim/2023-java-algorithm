import java.util.*;
import java.io.*;

class Graph{
    int [] visited;
    static LinkedList<Integer> queue=new LinkedList();
    int start;
    int target;
    
    public Graph(int start, int target){
        this.start=start;
        this.target=target;
        
        //방문여부 range 문제
        visited=new int[100000+1]; 
    }
    
    //start -> target
    int bfs(){
        queue.add(start);
        visited[start]=visited[start]+1;
        
        while(queue.size()!=0){
            int now=queue.poll();
            int cnt=visited[now];
            
            boolean find=false;
            if(now-1>=0 && now-1 <100000+1 && visited[now-1]==0)
                find=visit(now-1, cnt)||find?true:false;
            if(now+1>=0 && now+1 <100000+1 && visited[now+1]==0)
                find=visit(now+1, cnt)||find?true:false;
            if(now*2>=0 && now*2 <100000+1 && visited[now*2]==0)
                find=visit(now*2, cnt)||find?true:false;
            
            if(find)
                return cnt; //처음에 1 증가시킨 채로 시작해서 -1 개념으로 cnt 그대로 반환
        }
        
        return visited[target]-1;
    }
    
    //방문처리
    boolean visit(int next, int cnt){
        queue.add(next);
        visited[next]=cnt+1;
        
        if(next==target)
            return true;
        
        return false;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        
        Graph graph=new Graph(n, k);
        System.out.print(graph.bfs());
    }
}