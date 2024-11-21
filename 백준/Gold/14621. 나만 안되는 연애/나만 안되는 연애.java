import java.util.*;
import java.io.*;

class Road implements Comparable<Road>{
    int u;
    int v;
    int d;

    Road(int u, int v, int d){
        this.u=u;
        this.v=v;
        this.d=d;
    }

    public int compareTo(Road r){
        return Integer.compare(this.d, r.d);
    }
}

class Main{
    static int N;
    static int M;
    static boolean[] isFemale;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] sizes = br.readLine().split(" ");
        N = Integer.parseInt(sizes[0]);
        M = Integer.parseInt(sizes[1]);

        isFemale = new boolean[N+1];
        String[] schools = br.readLine().split(" ");
        for(int i=1;i<=N;i++){
            String school = schools[i-1];
            if(school.equals("W")){
                isFemale[i] = true;
            }
        }

        PriorityQueue<Road> queue = new PriorityQueue<>();
        while(M-->0){
            String[] road = br.readLine().split(" ");
            queue.add(new Road(Integer.parseInt(road[0]), Integer.parseInt(road[1]), Integer.parseInt(road[2])));
        }

        int[] parents = new int[N+1];
        for(int i=1;i<=N;i++){
            parents[i] = i;
        }

        int sum = 0;
        int cnt = 0;
        while(!queue.isEmpty()){
            Road road = queue.poll();

            if(isFemale[road.u]==isFemale[road.v]){
                continue;
            }

            int uParent = getParent(parents, road.u);
            int vParent = getParent(parents, road.v);

            if(uParent == vParent){
                continue;
            }

            sum+=road.d;
            cnt++;
            //통합 (부모 작은 쪽으로 )
            int min = Math.min(uParent, vParent);
            int max = Math.max(uParent, vParent);

            parents[max]=min;
        }

        if(cnt != N-1){
            System.out.print("-1");
        } else {
            System.out.print(sum);
        }


    }

    static int getParent(int[] parents, int node){
        while(parents[node] != node){
            node = parents[node];
        }

        return node;
    }
}