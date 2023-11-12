import java.util.*;
import java.io.*;

class Main {

    private static int N;
    private static int X;
    private static int INF = Integer.MAX_VALUE;

    //graph
    private static int[][] graph;


    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start][end] = cost;
        }

        calMaxCost();
        System.out.println(max);
    }

    private static void calMaxCost() {
        for (int i = 1; i <= N; i++) {
            if (i == X) {
                continue;
            }

            int goCost = dijkstra(i, X);
            int returnCost = dijkstra(X, i);

            if (goCost + returnCost > max) {
                max = goCost + returnCost;
            }
        }
    }


    private static int dijkstra(int start, int end) {
        int dist[] = new int[N + 1]; //현재 start에서 각 마을까지 가는 최단 시간
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> queue = new PriorityQueue<>((d1, d2) -> Integer.compare(d1[1], d2[1]));

        dist[start] = 0;
        int[] node = {start, 0};
        queue.add(node);

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowVillage = now[0];
            int nowDist = now[1];

            if (dist[nowVillage] > nowDist || nowDist >= dist[end]) continue;

            for (int i = 1; i <= N; i++) {
                if (graph[nowVillage][i] == 0) continue;

                int newDist = nowDist + graph[nowVillage][i];
                if (dist[i] <= newDist) {
                    continue;
                }

                dist[i] = newDist;
                int[] newNode = {i, newDist};
                queue.add(newNode);
            }
        }

        return dist[end];
    }
}