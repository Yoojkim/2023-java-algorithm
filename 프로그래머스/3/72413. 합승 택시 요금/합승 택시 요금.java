import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] roads = new int[n+1][n+1];
        
        for(int[] fare:fares){
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            
            roads[c][d] = f;
            roads[d][c] = f;
        }
        
        int[] sDist = dijkstra(n, roads, s);
        int[] aDist = dijkstra(n, roads, a);
        int[] bDist = dijkstra(n, roads, b);
        
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
           min =  Math.min(sDist[i]+aDist[i]+bDist[i], min);
        }
        
        return min;
    }
    
    public int[] dijkstra(int n, int[][] roads, int s){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[1], b[1]));
        
        queue.add(new int[]{s, 0});
        
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            
            if(dist[point[0]] <= point[1]){
                continue;
            }
            
            dist[point[0]] = point[1];
            
            for(int i=1;i<=n;i++){
                if(roads[point[0]][i] == 0){
                    continue;
                }
                
                if(point[1] + roads[point[0]][i] >= dist[i]){
                    continue;
                }
                queue.add(new int[]{i, point[1] + roads[point[0]][i]});
            }
        }
        return dist;
    }
}