import java.util.*;
import java.io.*;

class Graph{
    int n;
    int m;
    int[][] graph;
    static int[][] displacement={
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public Graph(int n, int m, int[][] graph){
        this.n=n;
        this.m=m;
        this.graph=graph;
    }

    //1년 후
    void yearLater(){
        boolean[][] visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(graph[i][j]==0 && !visited[i][j]){
                    //상하좌우
                    for(int[]dp:displacement){
                        int newI=i+dp[0];
                        int newJ=j+dp[1];

                        if(newI>=0 && newI<n && newJ>=0 && newJ<m && graph[newI][newJ]>0) {
                            graph[newI][newJ] = graph[newI][newJ] - 1;

                            //원래 빙하가 있던 위치가 0이 되더라도 얼음을 녹이지 않게 하기 위해
                            visited[newI][newJ]=true;
                        }
                    }
                }
            }
        }
    }

    int dfs(){
        int year=0;
        while(true){
            int cnt=0;
            boolean[][] visited=new boolean[n][m];

            //1년 후
            yearLater();
            year++;

            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(graph[i][j]>0 && !visited[i][j]){
                        dfsUtil(i, j, visited);
                        cnt++;
                    }
                }
            }

            if(cnt>1)
                return year;

            if(cnt==0)
                return 0;
        }
    }

    void dfsUtil(int i, int j, boolean[][] visited){
        visited[i][j]=true;

        for(int[] dp:displacement){
            int newI=i+dp[0];
            int newJ=j+dp[1];

            if(newI>=0 && newI<n && newJ>=0 && newJ<m && !visited[newI][newJ] && graph[newI][newJ]>0)
                dfsUtil(newI, newJ, visited);
        }
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        int[][] arr=new int[n][m];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int val=Integer.parseInt(st.nextToken());
                arr[i][j]=val;
            }
        }

        Graph graph=new Graph(n, m, arr);
        System.out.print(graph.dfs());
    }
}