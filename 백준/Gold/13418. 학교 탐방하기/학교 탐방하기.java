import java.util.*;
import java.io.*;

class Road{
    int dest;
    int cost;

    public Road(int dest, int cost){
        this.dest = dest;
        this.cost = cost;
    }
}

public class Main{
    static int N;
    static int M;
    static List<Road>[] roads;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        for(int i=0;i<=N;i++){
            roads[i] = new ArrayList<>();
        }

        for(int i=0;i<=M;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            c = (c==0)?1:0; //오르막이면 1, 내리막이면 0
            roads[a].add(new Road(b, c));
            roads[b].add(new Road(a, c));
        }

        //best
        Comparator<Road> bestComparator = (r1, r2)->Integer.compare(r1.cost, r2.cost);
        int bestResult = getCost(bestComparator);


        //worst
        Comparator<Road> worstComparator = (r1, r2) -> Integer.compare(r2.cost, r1.cost);
        int worstResult = getCost(worstComparator);

        System.out.print(worstResult*worstResult - bestResult*bestResult);
    }

    private static int getCost(Comparator comparator) {
        PriorityQueue<Road> queue = new PriorityQueue<>(comparator);

        boolean[] visited = new boolean[N + 1];
        visited[0]=true;
        queue.addAll(roads[0]);

        int result = 0;
        while (!queue.isEmpty()) {
            Road now = queue.poll();

            if(visited[now.dest]) {
                continue;
            }

            result+=now.cost;
            visited[now.dest] = true;

            for(Road road:roads[now.dest]){
                if(visited[road.dest]){
                    continue;
                }

                queue.add(road);
            }
        }

        return result;
    }
}