import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    static int[][] maps;
    static int N, M;

    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int map = Integer.parseInt(st.nextToken());
                maps[i][j] = map;

                if(map == 1){
                    cnt++;
                }

            }
        }

        int sec = 0;
        while(cnt > 0){
            boolean[][] visited= new boolean[N][M];
            int[][] cntMap = new int[N][M];
            findCheese(0, 0, visited, cntMap);

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(cntMap[i][j] >= 2){
                        maps[i][j] = 0;
                        cnt--;
                    }
                }
            }
            sec++;
        }

        System.out.print(sec);
    }

    static void findCheese(int i, int j, boolean[][] visited, int[][] cntMap){
        visited[i][j] = true;

        for(int[] dir:dirs){
            int newI = dir[0]+i;
            int newJ = dir[1]+j;

            if(newI < 0 || newI >= N || newJ < 0 || newJ >= M){
                continue;
            }

            if(visited[newI][newJ]){
                continue;
            }

            if(maps[newI][newJ] == 1){
                cntMap[newI][newJ] ++;
            } else {
                findCheese(newI, newJ, visited, cntMap);
            }
        }
    }
}
