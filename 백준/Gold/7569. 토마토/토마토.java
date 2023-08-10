import java.util.*;
import java.io.*;

class Graph{
    int h;
    int n;
    int m;
    int[][][] graph;
    LinkedList<int[]> tomatos;
    static int[][] displacement={
            {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}, {-1, 0, 0}, {1, 0, 0}
    };

    public Graph(int h, int n, int m, int[][][] graph, LinkedList<int[]> tomatos){
        this.h=h;
        this.n=n;
        this.m=m;
        this.graph=graph;
        this.tomatos=tomatos;
    }

    int bfs(){
        boolean[][][] visited=new boolean[h][n][m];

        while (tomatos.size()!=0){
            int[] tomato=tomatos.poll();
            int tomatoCnt=graph[tomato[0]][tomato[1]][tomato[2]];

            for(int[] dp: displacement){
                int newI=tomato[0]+dp[0];
                int newJ=tomato[1]+dp[1];
                int newK=tomato[2]+dp[2];

                if(checkPoint(newI, newJ, newK)){
                    //방문
                    graph[newI][newJ][newK] = tomatoCnt + 1;
                    int[] newPoint={newI, newJ, newK};
                    tomatos.add(newPoint);
                }
            }
        }
        
        return result();
    }

    boolean checkPoint(int i, int j, int k){
        int[] sizes={h, n, m};
        int[] points={i, j, k};

        for(int p=0;p<3;p++){
            if(points[p]<0 || points[p]>=sizes[p])
                return false;
        }
        
        if(graph[i][j][k]!=0)
            return false;
        
        return true;
    }

    int result(){
        int max=0;

        for(int i=0;i<h;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<m;k++){
                    if(graph[i][j][k]==0)
                        return -1;
                    if(graph[i][j][k]>max)
                        max=graph[i][j][k];
                }
            }
        }

        if(max==1)
            return 0;

        return max-1;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int m=Integer.parseInt(st.nextToken());
        int n=Integer.parseInt(st.nextToken());
        int h=Integer.parseInt(st.nextToken());

        int [][][] arr=new int[h][n][m];
        LinkedList<int[]> tomatos=new LinkedList();
        for(int i=0;i<h;i++){
            for(int j=0;j<n;j++){
                st=new StringTokenizer(br.readLine());
                for(int k=0;k<m;k++){
                    int value=Integer.parseInt(st.nextToken());
                    arr[i][j][k]=value;
                    if(value==1){
                        int[] point={i, j, k};
                        tomatos.add(point);
                    }
                }
            }
        }

        Graph graph=new Graph(h, n, m, arr, tomatos);
        System.out.print(graph.bfs());
    }
}
