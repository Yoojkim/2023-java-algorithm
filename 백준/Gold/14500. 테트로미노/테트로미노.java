import java.util.*;
import java.io.*;

class Point{
    int i;
    int j;
    
    public Point(int i, int j){
        this.i=i;
        this.j=j;
    }
}

public class Main{
    static int max = 0;
    static int N, M;
    static int[][] dps = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    static int[][] fields;
    static boolean[][] visited;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        fields = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        visited = new boolean[N][M];
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                visited[i][j] = true;
                Stack<Point> points = new Stack<>();
                points.push(new Point(i, j));
                
                calculate(i, j, 1, fields[i][j], points);
                
                points.pop();
                visited[i][j] = false;
            }
        }
        
        System.out.print(max);
    }
    
    static void calculate(int i, int j, int step, int sum, Stack<Point> points){
        if(step == 4){
            if(max < sum){
                max = sum;
            }
            
            return;
        }
        
        if(points.size()>1){
        	Point p1 = points.pop();
            Point p2 = points.peek();
            
            calculate(p2.i, p2.j, step, sum, points);
            
            points.push(p1);
        }
        
        for(int[] dp:dps){
            int newI = dp[0]+i;
            int newJ = dp[1]+j;
            
            if(newI<0 || newI>=N || newJ<0 || newJ>=M){
                continue;
            }
            
            if(visited[newI][newJ]){
                continue;
            }
            
            visited[newI][newJ] = true;
            points.push(new Point(newI, newJ));
            
            calculate(newI, newJ, step+1, sum+fields[newI][newJ], points);
            
            visited[newI][newJ] = false;
            points.pop();
        }
    }
    
}