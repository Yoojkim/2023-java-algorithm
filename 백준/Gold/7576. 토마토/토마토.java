import java.util.*;
import java.io.*;

class Point{
    int x;
    int y;
    
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}
public class Main{
    static int[][] dps = {
        {0, -1}, {0, 1}, {-1, 0}, {1, 0}
    };
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[][] tomatos = new int[N][M];//0: 안익토, 1:익토, -1:빈칸
        Queue<Point> queue = new LinkedList<>();
        
        
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int tomato= Integer.parseInt(st.nextToken());
                tomatos[i][j] = tomato;
                
                if(tomato == 1){
                    queue.add(new Point(i, j));
                }
            }
        }
        
        //bfs
        int days = 0;
        while(!queue.isEmpty()){
            //queue 인접 공간 다시 queue에 넣기
            Queue<Point> nextQueue = new LinkedList<>();
            
            for(Point p:queue){
                for(int[] dp:dps){
                    int nextX = p.x+dp[0];
                    int nextY = p.y+dp[1];
                    
                    if(nextX < 0 || nextX >= N || nextY <0 || nextY >=M){
                        continue;
                    }
                    
                    if(tomatos[nextX][nextY] != 0){ 
                        continue;
                    }
                    
                    tomatos[nextX][nextY] = 1;
                    nextQueue.add(new Point(nextX, nextY));
                }
            }
            
            if(!nextQueue.isEmpty()){
                days++;
            }
            
            queue = nextQueue;
        }
        
        //토마토가 모두 익지 못하는 순간 -> -1
        for(int i = 0;i<N;i++){
            for(int j=0;j<M;j++){
                if(tomatos[i][j] == 0){
                    System.out.print(-1);
                    
                    return;
                }
            }
        }
        
        
        System.out.print(days);
    }
}