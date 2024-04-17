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
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] values = br.readLine().split(" ");
        int N = Integer.parseInt(values[0]); int M = Integer.parseInt(values[1]);
        
        int[][] maze = new int[N][M];
        
        for(int i=0;i<N;i++){
            char[] row = br.readLine().toCharArray();
            for(int j=0;j<M;j++){
                maze[i][j]=row[j]-'0';
            }
        }
        
        Queue<Point> queue = new LinkedList<>();
        int turn=0;
        queue.add(new Point(0, 0));
        while(!queue.isEmpty()){
            turn++;
            
            Queue<Point> newQueue = new LinkedList<>();
            while(!queue.isEmpty()){
                Point now = queue.poll();
                
                if(now.x == N-1 && now.y == M-1){
                    System.out.println(turn);
                    
                    return;
                }
                
                maze[now.x][now.y] = 2;
                
                for(int[] dp:dps){
                    int newX = now.x+dp[0];
                    int newY = now.y+dp[1];
                    
                    if(newX<0 || newX>=N || newY<0 || newY>=M){
                        continue;
                    }
                    
                   if(maze[newX][newY]!=1){
                       continue;
                   }
                    
                   maze[newX][newY] = 2;
                   newQueue.add(new Point(newX, newY));
                }
            }
            
            queue = newQueue;
        }
    }
}