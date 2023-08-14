import java.util.*;
import java.io.*;

class Graph{
    int n;
    int[][] graph;
    int max=-1; 
    int height;
    static int[][] displacement={
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    public Graph(int n, int[][] graph, int height){
        this.n=n;
        this.graph=graph;
        this.height=height;
    }
    
    int dfs(){
        //1~ 가장 높은 높이까지
        for(int i=0;i<=height;i++){
            boolean[][] visited=new boolean[n][n];
            int heightCnt=0;
                
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if(graph[j][k]>i && !visited[j][k]){
                        dfsUtil(j, k, visited, i);
                        heightCnt++;
                    }
                }
            }
            
            if(max<heightCnt)
                max=heightCnt;
        }
        
        return max;
    }
    
    void dfsUtil(int j, int k, boolean[][] visited, int i){
        visited[j][k]=true;
        
        //상하좌우 
        for(int[] dp:displacement){
            int newJ=j+dp[0];
            int newK=k+dp[1];
            
            if(newJ>=0 && newJ<n && newK>=0 && newK<n && !visited[newJ][newK] && graph[newJ][newK]>i)
                dfsUtil(newJ, newK, visited, i);
                
        }
        
    }  
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        
        int[][] arr=new int[n][n];
        int max=-1;
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int val=Integer.parseInt(st.nextToken());
                arr[i][j]=val;
                
                if(max<val)
                    max=val;
            }
        }
        
        Graph graph=new Graph(n, arr, max);
        System.out.print(graph.dfs());
    }
}