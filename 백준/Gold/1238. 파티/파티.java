import java.util.*;
import java.io.*;

class Graph {
    List<Node> nodes;

    public Graph(int n) {
        nodes = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            nodes.add(new Node());
        }
    }
}

class Node {
    List<Edge> edges;

    public Node() {
        edges = new ArrayList<>();
    }

}

class Edge{

    int end;
    int cost;

    public Edge(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }
}

class Main {
    private static int N;
    private static int X;
    private static int INF = Integer.MAX_VALUE;

    private static Graph graph;
    private static Graph reverseGraph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new Graph(N+1);
        reverseGraph = new Graph(N+1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.nodes.get(start).edges.add(new Edge(end, cost));
            reverseGraph.nodes.get(end).edges.add(new Edge(start, cost));
        }

        int res = getMaxResult();
        System.out.print(res);
    }

    private static int getMaxResult(){
        int max = Integer.MIN_VALUE;

        // x -> n (return)
        int[] dist = dijkstra(X, graph);

        // n -> x (go)
        int[] reverseDist = dijkstra(X, reverseGraph);

        for(int i=1;i<=N;i++){
            if(i==X){
                continue;
            }

            int nodeCost = dist[i]+reverseDist[i];

            if(max<nodeCost){
                max=nodeCost;
            }
        }

        return max;
    }

    private static int[] dijkstra(int start, Graph graph) {
        int dist[] = new int[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> queue = new PriorityQueue<>((d1, d2) -> Integer.compare(d1[1], d2[1]));

        dist[start] = 0;
        queue.add(new int[]{start, 0});
        boolean[] visited = new boolean[N + 1];

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int nextTown = now[0];
            int nowDist = now[1];

            if (visited[nextTown])
                continue;

            visited[nextTown] = true;
            dist[nextTown] = nowDist;

            //nextTown 값을 통해 계산하는 마을들과의 새로운 값들
            for(Edge nextTownEdge:graph.nodes.get(nextTown).edges){
                int newTownIdx = nextTownEdge.end;
                if(visited[newTownIdx]){
                    continue;
                }

                int newDist = nowDist + nextTownEdge.cost;
                if(dist[newTownIdx] <= newDist){
                    continue;
                }

                queue.add(new int[]{newTownIdx, newDist});
            }

        }

        return dist;
    }
}