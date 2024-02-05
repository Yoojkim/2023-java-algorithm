import java.util.*;

class Edge implements Comparable<Edge>{
    int island1;
    int island2;
    int cost;
    
    public Edge(int island1, int island2, int cost){
        this.island1 = island1;
        this.island2 = island2;
        this.cost= cost;
    }
    
    public int compareTo(Edge e){
        return Integer.compare(this.cost, e.cost);
    }
}

class Solution {
    
    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        
        for(int[] cost:costs){
            queue.add(new Edge(cost[0], cost[1], cost[2]));
        }
        
        boolean[] visited = new boolean[n];
        int result=0;
        while(!queue.isEmpty()){
            Edge newEdge = queue.poll();
            
            if(visited[newEdge.island1] && visited[newEdge.island2]){
                continue;
            }
            
            visited[newEdge.island1] = true;
            visited[newEdge.island2] = true;
            result+= newEdge.cost;
        }
        
        return result;
    }
}