import java.io.*;
import java.util.*;

class Nears {

    LinkedList<Edge> nears;

    public Nears() {
        nears = new LinkedList<>();
    }

    public void addNears(Edge edge) {
        nears.add(edge);
    }
}

class Edge implements Comparable<Edge>{
    int point;
    int cost;

    public Edge(int point, int cost) {
        this.point = point;
        this.cost = cost;
    }

    public int compareTo(Edge e){
        return this.cost-e.cost;
    }
}

public class Main {

    static List<Nears> graph = new LinkedList<>();
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        for (int i = 0; i <= n; i++) {
            graph.add(new Nears());
        }

        StringTokenizer st;
        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).addNears(new Edge(b, c));
            graph.get(b).addNears(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        //1 -> p1 -> p2 -> n
        int result1=getResultMinDist(p1, p2);

        //1 -> p2 -> p1 -> n
        int result2=getResultMinDist(p2, p1);

        int result = (result1<result2)?result1:result2;

        System.out.println(result);
    }

    private static int getResultMinDist(int p1, int p2){
        int[][] sequences = {
            {1, p1}, {p1, p2}, {p2, n}
        };

        int sum=0;
        for(int[] seq:sequences){
            int dist = getMinDist(seq[0], seq[1]);

            if(dist==Integer.MAX_VALUE){
                return -1;
            }

            sum+=dist;
        }

        return sum;
    }

    private static int getMinDist(int p1, int p2) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        dist[p1]=0;
        queue.add(new Edge(p1, 0));

        while(!queue.isEmpty()){
            Edge now = queue.poll();

            if(dist[now.point]<now.cost) {
                continue;
            }

            dist[now.point]=now.cost;

            Nears nears = graph.get(now.point);
            for(Edge near:nears.nears){
                int newCost = now.cost+near.cost;

                if(newCost<dist[near.point]){
                    queue.add(new Edge(near.point, newCost));
                }
            }
        }

        return dist[p2];
    }
}