import java.util.*;
import java.io.*;

class Point implements Comparable<Point>{
    int x;
    int y;
    int dist;
    
    public Point(int x, int y, int dist){
        this.x=x;
        this.y=y;
        this.dist=dist;
    }
    
    public int compareTo(Point p){
        return Integer.compare(this.dist, p.dist);
    }
}

class Main{
    
    //상하좌우
    static int[][] dps = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int cnt=1;
        while(true){
            int N = Integer.parseInt(br.readLine());
            
            if(N==0){
                break;
            }
            
            int[][] fields = new int[N][N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            
            for(int j=0;j<N;j++){
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }
            
            find(N, cnt, fields);
            cnt++;
        }
    }
    
    static void find(int N, int cnt, int[][] fields){
            boolean[][] visited = new boolean[N][N];
        
        Point start = new Point(0, 0, fields[0][0]);
        PriorityQueue<Point> queue = new PriorityQueue();
        queue.add(start);
        
        while(!queue.isEmpty()){
            Point point = queue.poll();
            
            if(point.x == N-1 && point.y == N-1){
                System.out.println(String.format("Problem %d: %d", cnt, point.dist));
                
                return;
            }
            
            for(int[] dp:dps){
                int newX = point.x + dp[0];
                int newY = point.y + dp[1];
                
                if(newX < 0 || newX == N || newY <0 || newY == N){
                    continue;
                }
                
                if(visited[newX][newY]){
                    continue;
                }
                
                visited[newX][newY] = true;
                queue.add(new Point(newX, newY, point.dist+fields[newX][newY]));
            }
            
        }
    }
}