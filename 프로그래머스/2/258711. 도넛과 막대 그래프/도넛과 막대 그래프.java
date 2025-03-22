import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        //a, b <= 1000000
        List<Integer>[] nexts = new List[1000001];
        int[] in = new int[1000001];
        int[] out = new int[1000001];
        
        for(int[] edge:edges){
            //a->b
            int a = edge[0];
            int b = edge[1];
            
            if(nexts[a] == null){
                nexts[a] = new ArrayList<>();
            }
            
            if(nexts[b] == null){
                nexts[b] = new ArrayList<>();
            }
            
            //양방향으로 설정
            nexts[a].add(b);
            nexts[b].add(a);
            
            out[a]++;
            in[b]++;
        }
        
        boolean[] visited = new boolean[1000001];
        int dn = 0;
        int md = 0;
        int eight = 0;
        int start = 0;
        
        for(int i=1;i<=1000000;i++){
            if(in[i] == 0 && out[i] > 1){
                start = i;
                visited[i] = true;
                
                for(int next:nexts[i]){
                    in[next]--;
                }
                
                break;
            }
        }
        
        for(int i=1;i<=1000000;i++){
            if(nexts[i] == null){
                continue;
            }
            
            if(visited[i]){
                continue;
            }

            //BFS
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(i);
            visited[i] = true;
            
            int max = 0;
            int min = Integer.MAX_VALUE;
            while(!queue.isEmpty()){
                int now = queue.poll();
                
                int add = in[now]+out[now];
                max = Math.max(add, max);
                min = Math.min(add, min);
                
                for(int next:nexts[now]){
                    if(visited[next]){
                        continue;
                    }
                    
                    queue.add(next);
                    visited[next] = true;
                }
            }
            
            if(max <= 1){
                md ++;
            } else if(max == 2){
                if(min <= 1){
                    md++;
                } else {
                    dn ++;
                }
            } else {
                eight++;
            }
        }
        
        return new int[]{start, dn, md, eight};
    }
}