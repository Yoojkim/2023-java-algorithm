import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int[][] fields;
    static boolean[][] visited;
    static int[][] memory;
    static int[][] dps = {
            {-1, 0}, {1, 0}, {0, -1}, {0,1}
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fields = new int[N][M];
        visited = new boolean[N][M];
        memory = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                fields[i][j] = Integer.parseInt(st.nextToken());
            }

            Arrays.fill(memory[i], -1);
        }

        int res = DFS(0, 0);

        System.out.print(res);
    }

    //todo: 메모이제이션 활용
    static int DFS(int i, int j){
        if(i==N-1 && j==M-1){
            return 1;
        }

        if(memory[i][j] != -1){
            return memory[i][j];
        }

        visited[i][j] = true;
        int innerCnt = 0;
        for(int[] dp:dps){
            int newI = i+dp[0];
            int newJ = j+dp[1];

            if(newI <0 || newI>=N || newJ<0 ||newJ>=M){
                continue;
            }

            if(visited[newI][newJ]){
                continue;
            }

            if(fields[newI][newJ]>=fields[i][j]){
                continue;
            }

            if(memory[newI][newJ] != -1){
                innerCnt+=memory[newI][newJ];
                continue;
            }

            innerCnt+=DFS(newI, newJ);
        }

        memory[i][j] = innerCnt;
        visited[i][j]=false;

        return memory[i][j];
    }
}