import java.util.*;

//MST
class Road implements Comparable<Road>{
    int a;
    int b;
    int c;
    
    public Road(int a, int b, int c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    
    public int compareTo(Road r){
        return this.c-r.c;
    }
}

class Solution {
    int[] parents;
    public int solution(int n, int[][] costs) {
        PriorityQueue<Road> queue = new PriorityQueue<>();
        for(int[] cost:costs){
            queue.add(new Road(cost[0], cost[1], cost[2]));
        }
        
        int sum=0;
        parents = new int[n];
        for(int i=0;i<n;i++){
            parents[i] = i;
        }
        
        while(!queue.isEmpty()){
            Road road = queue.poll();
            int parentA = find(road.a);
            int parentB = find(road.b);

            if(parentA == parentB){
                continue;
            } 
            
            sum+=road.c;
            
            //하나의 섬으로 연결
            parents[parentA] = parentB;
           
        }
        
        return sum;
    }
    
    private int find(int node){
        while(node != parents[node]){
            node = parents[node];
        }
        
        return node;
    }
    
}