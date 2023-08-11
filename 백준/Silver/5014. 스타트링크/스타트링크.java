import java.util.*;
import java.io.*;

class Graph{
    int f; //총 층수
    int s; //현재 층수
    int g; //목표 층수
    int u; //위로 가는 층수
    int d; //밑으로 가는 층수 
    
    public Graph(int f, int s, int g, int u, int d){
        this.f=f;
        this.s=s;
        this.g=g;
        this.u=u;
        this.d=d;
    }
    
    //0: 실패 
    int bfs(){
        LinkedList<Integer> queue=new LinkedList();
        int[] visited=new int[f+1];
        
        visited[s]=visited[s]+1;
        queue.add(s);
        
        while(queue.size()!=0){
            int now=queue.poll();
            int cnt=visited[now];
            
            if(now+u<=f && visited[now+u]==0){
                queue.add(now+u);
                visited[now+u]=cnt+1;
                
                if (now+u==g)
                    return cnt;
            }
            
            if(now-d>=1 && visited[now-d]==0){
                queue.add(now-d);
                visited[now-d]=cnt+1;
                
                if (now-d==g)
                    return cnt;
            }
        }
        
        return visited[g]-1;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        int f=Integer.parseInt(st.nextToken());
        int s=Integer.parseInt(st.nextToken());
        int g=Integer.parseInt(st.nextToken());
        int u=Integer.parseInt(st.nextToken());
        int d=Integer.parseInt(st.nextToken());
    
        Graph graph=new Graph(f, s, g, u, d);
        
        int res=graph.bfs();
        if(res<0){
            System.out.print("use the stairs");
        }else{
            System.out.print(res);
        }
    }
}