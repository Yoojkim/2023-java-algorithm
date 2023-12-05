import java.util.*;
import java.io.*;

class Bus implements Comparable<Bus> {
    int start;
    int stop;
    int cost;

    public Bus(int start, int stop, int cost) {
        this.start = start;
        this.stop = stop;
        this.cost = cost;
    }

    public int compareTo(Bus bus) {
        return this.cost - bus.cost;
    }
}

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Bus>[] cities = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            cities[i] = new LinkedList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int stop = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Bus bus = new Bus(start, stop, cost);
            cities[start].add(bus);
        }

        StringJoiner sj = new StringJoiner("\n");
        for (int i = 1; i <= N; i++) {
            sj.add(getMstResult(cities, i));
        }

        System.out.print(sj);
    }

    private static String getMstResult(List<Bus>[] cities, int start) {
        PriorityQueue<Bus> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        long[] minCost = new long[N + 1];

        queue.add(new Bus(start, start, 0));
        while (!queue.isEmpty()) {
            Bus nowBus = queue.poll();

            if (visited[nowBus.stop]) {
                continue;
            }

            minCost[nowBus.stop] = nowBus.cost;
            visited[nowBus.stop] = true;

            for(Bus nextBus:cities[nowBus.stop]){
                if(visited[nextBus.stop]){
                    continue;
                }

                queue.add(new Bus(nextBus.start, nextBus.stop, nextBus.cost+ nowBus.cost));
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for(int i=1;i<=N;i++){
            sj.add(Long.toString(minCost[i]));
        }

        return sj.toString();
    }
}