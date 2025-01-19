import java.io.*;
import java.util.*;

class Point{
    int i;
    int j;

    public Point(int i, int j){
        this.i=i;
        this.j=j;
    }
}

public class Main {
    static int N =5;
    static int groupSize= 7;
    static int min = 4;
    static char[][] fields = new char[N][N];
    static int[][] dps = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    static int cnt = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<N;i++) {
            fields[i] = br.readLine().toCharArray();
        }

        backTracking(0, 0, new Stack<>(), 0);

        System.out.print(cnt);
    }

    static void backTracking(int i, int j, Stack<Point> group, int sCnt){
        if(group.size()==groupSize){
            countSuccess(group, sCnt);

            return;
        }

        if(j==N){
            backTracking(i+1, 0, group, sCnt);

            return;
        }

        if(i==N || j==N){
            return;
        }

        //포함
        group.push(new Point(i, j));
        backTracking(i, j+1, group, sCnt+((fields[i][j]=='S')?1:0));
        group.pop();

        //미포함
        backTracking(i, j+1, group, sCnt);
    }

    static void countSuccess(Stack<Point> group, int sCnt){
        if(sCnt <4){
            return;
        }

        int finalCnt = 0;
        Point start = group.peek();

        boolean[][] visited = new boolean[N][N];
        for(Point p:group) {
            visited[p.i][p.j] = true;
        }

       Queue<Point> queue = new LinkedList<>();
       queue.add(start);
       visited[start.i][start.j] = false;
       while(!queue.isEmpty()){
           Point p = queue.poll();
           finalCnt++;

           for(int[] dp:dps){
               int newI = p.i+dp[0];
               int newJ = p.j+dp[1];

               if(newI<0 || newI>=N || newJ<0 || newJ>=N){
                   continue;
               }

               if(!visited[newI][newJ]){
                   continue;
               }

               visited[newI][newJ] = false;
               queue.add(new Point(newI, newJ));
           }
       }

       if(finalCnt==groupSize){
           cnt++;
       }
    }
}
