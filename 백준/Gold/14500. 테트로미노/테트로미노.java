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
                calculate(i, j, 1, fields[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.print(max);
    }

    static void calculate(int i, int j, int step, int sum){
        if(step == 4){
            if(max < sum){
                max = sum;
            }

            return;
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

            //available
            if(step == 2){
                visited[newI][newJ] = true;
                calculate(i, j, step+1, sum+fields[newI][newJ]);
                visited[newI][newJ] = false;
            }

            visited[newI][newJ] = true;
            calculate(newI, newJ, step+1, sum+fields[newI][newJ]);
            visited[newI][newJ] = false;
        }
    }

}