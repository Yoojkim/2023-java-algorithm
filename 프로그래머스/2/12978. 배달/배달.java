import java.util.*;

class Road implements Comparable<Road>{
    int start;
    int end;
    int weight;
    
    public Road(int start, int end, int weight){
        this.start = start;
        this.end=end;
        this.weight= weight;
    }
    
    public int compareTo(Road road){
        return Integer.compare(this.weight, road.weight);
    }
}

class Solution {
    public int solution(int N, int[][] road, int K) {
        
        ArrayList<Road>[] towns = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            towns[i] = new ArrayList<>();
        }
        
        for(int[] r:road){
            int a = r[0];
            int b = r[1];
            int c = r[2];
            
            towns[a].add(new Road(a, b, c));
            towns[b].add(new Road(b, a, c));
        }
        
        int[] res = new int[N+1];
        boolean[] visited = new boolean[N+1];
        //1에서 시작
        PriorityQueue<Road> queue = new PriorityQueue<>();
        
        queue.add(new Road(1, 1, 0));
        while(!queue.isEmpty()){
            Road queueRoad = queue.poll();
            
            if(visited[queueRoad.end]){
                continue;
            }
            
            res[queueRoad.end] = queueRoad.weight;
            visited[queueRoad.end] = true;
            
            for(Road newRoad:towns[queueRoad.end]){
                queue.add(new Road(1, newRoad.end, queueRoad.weight+newRoad.weight));
            }
        }
        
        int cnt=0;
        for(int i=1;i<=N;i++){
            if(res[i] > K){
                continue;
            }
            
            cnt++;
        }
        
        return cnt;
    }
}