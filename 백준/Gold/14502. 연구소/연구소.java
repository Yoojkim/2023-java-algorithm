import java.util.*;
import java.io.*;

//아이디어: backTracking으로 3개 세우는 경우 64*63*62
//3개 다 세운 경우 -> 이때 안전영역 bfs로 계산

class Point{
    int i;
    int j;

    public Point(int i, int j){
        this.i=i;
        this.j=j;
    }
}

public class Main{
    static int N;
    static int M;
    static int[][] fields;
    static int max = Integer.MIN_VALUE;
    static int[][] dps = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    }; //상하좌우

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

        backTracking(0, 0, 0);
        System.out.print(max);
    }

    static void backTracking(int i, int j, int count){
        if(count == 3){
            int safeArea = bfs();
            max = Math.max(max, safeArea);

            return;
        }

        if(i>=N || j>=M){
            return;
        }

        int nextI, nextJ;
        if(j==M-1){
            nextI = i+1;
            nextJ = 0;
        } else {
            nextI = i;
            nextJ = j+1;
        }

        //이번 칸에 벽을 세울 수 없다.
        if(fields[i][j] !=0){
            backTracking(nextI, nextJ, count);
        } else {
            //세울 수 있는 경우

            //1. 세운다
            fields[i][j] = 1;
            backTracking(nextI, nextJ, count+1);

            //2. 안 세우고 넘어간다
            fields[i][j] = 0;
            backTracking(nextI, nextJ, count);
        }
    }

    static int bfs(){
        Queue<Point> virus = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(fields[i][j] == 2){
                    virus.add(new Point(i, j));
                }
            }
        }

        while(!virus.isEmpty()){
            for(int i=0;i<virus.size();i++){
                Point p = virus.poll();

                for(int[] dp:dps){
                    int newI = dp[0]+p.i;
                    int newJ = dp[1]+p.j;

                    if(newI < 0 || newI >= N || newJ <0 || newJ >=M){
                        continue;
                    }

                    if(fields[newI][newJ] != 0){
                        continue;
                    }

                    if(visited[newI][newJ]){
                        continue;
                    }

                    visited[newI][newJ] = true;
                    virus.add(new Point(newI, newJ));
                }
            }
        }

        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(fields[i][j] == 0 && !visited[i][j]){
                    cnt++;
                }
            }
        }

        return cnt;
    }
}