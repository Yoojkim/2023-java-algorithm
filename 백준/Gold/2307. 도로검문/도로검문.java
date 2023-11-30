import java.util.*;
import java.io.*;

class Road implements Comparable<Road>{
    int start;
    int end;
    int cost;

    public Road(int start, int end, int cost){
        this.start=start;
        this.end=end;
        this.cost=cost;
    }

    public int compareTo(Road r){
        return Integer.compare(this.cost, r.cost);
    }
}

class Main{
    private static List<Road>[] roads;
    private static int N;

    //result
    private static List<Integer> sequence = new ArrayList<>();
    private static int mstSum;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        for(int i=0;i<=N;i++){
            roads[i] = new ArrayList<Road>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int roadCost = Integer.parseInt(st.nextToken());

            roads[startNode].add(new Road(startNode, endNode, roadCost));
            roads[endNode].add(new Road(endNode, startNode, roadCost));
        }

        getMstSequence();
        System.out.print(getMaxWait());
    }

    private static void getMstSequence(){
        boolean[] visited = new boolean[N+1];
        int[] seq = new int[N+1];
        PriorityQueue<Road> queue = new PriorityQueue<>();

        queue.add(new Road(1, 1, 0));
        while(!queue.isEmpty()){
            Road nowRoad = queue.poll();

            int prevNode = nowRoad.start;
            int nextNode = nowRoad.end;
            int roadCost = nowRoad.cost;

            if(visited[nextNode]){
                continue;
            }

            visited[nextNode] = true;
            seq[nextNode] = prevNode;

            if(nextNode == N){
                mstSum = roadCost;
            }

            for(Road road:roads[nextNode]){
                if(visited[road.end]){
                    continue;
                }

                queue.add(new Road(road.start, road.end, roadCost+road.cost));
            }
        }

        int last = N;
        while(last != 1){
            sequence.add(last);
            last = seq[last];
        }

        sequence.add(1);
        Collections.reverse(sequence);
    }

    private static int getMaxWait(){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<sequence.size()-1;i++){
            int cost = calculateCost(sequence.get(i), sequence.get(i+1));

            if(cost == -1){
                return -1;
            }

            if(cost>max){
                max = cost;
            }
        }

        return max-mstSum;
    }

    private static int calculateCost(int start, int end){
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.add(new Road(1, 1, 0));

        int result = -1;
        while(!queue.isEmpty()){
            Road nowRoad = queue.poll();

            int nextNode = nowRoad.end;
            int roadCost = nowRoad.cost;

            if(visited[nextNode]){
                continue;
            }

            if(nextNode == N){
                result = roadCost;

                break;
            }

            visited[nextNode] = true;
            for(Road road:roads[nextNode]){
                if(visited[road.end]){
                    continue;
                }

                if(road.start == start && road.end == end){
                    continue;
                }

                queue.add(new Road(road.start, road.end, roadCost+road.cost));
            }
        }

        return result;
    }
}