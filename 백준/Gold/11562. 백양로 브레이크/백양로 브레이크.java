import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 초기화: 큰 값으로 채워서 비교 가능하게 함
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE); // 충분히 큰 값으로 초기화
            dist[i][i] = 0; // 자기 자신으로의 경로는 0
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if (b == 0) {
                dist[u][v] = 0; // 단방향 도로의 경우 변환 없음
                dist[v][u] = 1; // 반대로 가기 위해선 변환이 필요
            } else {
                dist[u][v] = 0; // 양방향 도로의 경우 변환 없음
                dist[v][u] = 0;
            }
        }

        // 플로이드 와샬 알고리즘 실행
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE){
                        continue;
                    }
                    
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 쿼리 처리
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        
        StringJoiner sj = new StringJoiner("\n");
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            sj.add(Integer.toString(dist[s][e]));
        }
        
        System.out.print(sj);
    }
}
