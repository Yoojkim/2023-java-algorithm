import java.util.*;

class Roads{
    List<Road> roads;
    
    public Roads(){
        roads = new ArrayList<>();
    }
    
    public void addRoad(Road road){
        roads.add(road);
    }
}

class Road implements Comparable<Road>{
    int end;
    int cost;
    
    public Road(int end, int cost){
        this.end=end;
        this.cost=cost;
    }
    
    public int compareTo(Road r){
        return this.cost - r.cost;
    }
}

class Solution {
    public int solution(int N, int[][] road, int K) {
        Roads[] towns = new Roads[N+1];
        for(int i=0;i<=N;i++){
            towns[i] = new Roads();
        }
        
        for(int[] r:road){
            int start = r[0];
            int end = r[1];
            int cost = r[2];
            
            towns[start].addRoad(new Road(end, cost));
            towns[end].addRoad(new Road(start, cost));
        }
        
        int[] dists = dijkstra(N, towns);
        
        int count=0;
        for(int dist:dists){
            if(K>=dist){
                count++;
            }
        }
        
        return count;
    }
    
    private int[] dijkstra(int n, Roads[] towns){
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1]=0;
        
        PriorityQueue<Road> queue = new PriorityQueue<>();
        
        queue.add(new Road(1, 0));
        while(!queue.isEmpty()){
            Road min = queue.poll();
            int next = min.end;
            
            if(visited[next]){
                continue;
            }
                
            visited[next] = true;
            for(Road road:towns[next].roads){
                int newCost = road.cost + dist[next];
                
                if(dist[road.end]<=newCost){
                    continue;
                }
                
                dist[road.end]=newCost;
                queue.add(new Road(road.end, newCost));
            }
        }
        
        return dist;
    }
}