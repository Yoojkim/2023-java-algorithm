import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Integer>[] roads = new List[N+1]; //todo: 명확한 기준 확인
        for(int i=1;i<N+1;i++){
            roads[i] = new ArrayList();
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            roads[a].add(b);
        }

        int[] dist = new int[N+1];
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[]a, int[]b)-> Integer.compare(a[1], b[1]));
        dist[X] = 0;
        for(int destination:roads[X]){
            queue.add(new int[]{destination, 1});
        }

        while(!queue.isEmpty()){
            int[] minRoad = queue.poll();

            int dest = minRoad[0];
            int cost = minRoad[1];

            if(dist[dest] != 0){
                continue;
            }

            dist[dest] = cost;
            for(int destDest : roads[dest]){
                queue.add(new int[]{destDest, cost+1});
            }
        }

        StringJoiner sj = new StringJoiner("\n");
        for(int i=1;i<N+1;i++){
            if(i==X){
                continue;
            }

            if(dist[i] != K){
                continue;
            }

            sj.add(Integer.toString(i));
        }

        if(sj.length()==0){
            System.out.print(-1);
            return;
        }

        System.out.print(sj);
    }
}