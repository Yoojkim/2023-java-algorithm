import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        
        LinkedList<Integer>[] nodes = new LinkedList[n+1];
        for(int i=1;i<=n;i++){
            nodes[i]=new LinkedList<>();
        }
        
        for(int[] wire:wires){
            int n1 = wire[0];
            int n2 = wire[1];
            
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
        
        int min = Integer.MAX_VALUE;
        for(int[] wire:wires){
            int n1 = wire[0];
            int n2 = wire[1];
            
            int n1Count = countLink(n, n1, n2, nodes);
            int n2Count = n-n1Count;
            
            int gap=n1Count-n2Count;
            gap=(gap<0)?gap*-1:gap;
            
            if(min>gap){
                min=gap;
            }
        }
        
        return min;
    }
    
    private int countLink(int n, int start, int exception, LinkedList<Integer>[] nodes){
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        queue.add(start);
        visited[exception] = true;
        
        int cnt=0;
        
        while(!queue.isEmpty()){
            cnt++;
            int node = queue.poll();
            visited[node]=true;
            
            for(int next:nodes[node]){
                if(visited[next]){
                    continue;
                }
                
                queue.add(next);
            }
        }
        
        return cnt;
    }
}