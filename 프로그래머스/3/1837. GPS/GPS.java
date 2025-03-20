import java.util.*;

class Solution {
    static int[][] dp;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {     
        //dp 초기화
        dp = new int[k][n+1];
        for(int i=0;i<k;i++){
            Arrays.fill(dp[i], -1);
        }
        
        List<Integer>[] maps = new List[n+1];
        for(int i=1;i<=n;i++){
            maps[i] = new ArrayList<>();
            maps[i].add(i);
        }
        
        for(int[] edge:edge_list){
            int a = edge[0];
            int b = edge[1];
            
            maps[a].add(b);
            maps[b].add(a);
        }
        
        int res = backTracking(k-1, gps_log, maps, gps_log[k-1]);
        return res == Integer.MAX_VALUE?-1:res;
    }
    
    private int backTracking(int depth, int[] gps_log, List<Integer>[] maps, int now){
        if(dp[depth][now]!=-1){
            return dp[depth][now]; //dp[깊이][현재경로] 의 최단수정 
        }
        
        //종료조건
        if(depth == 0){
            if(now != gps_log[0]){
                //도착할 수 없는 경우
                return Integer.MAX_VALUE;
            } else {
                return 0;
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int conn:maps[now]){
            int res = backTracking(depth-1, gps_log, maps, conn);
            
            min = Math.min(min, res);
        }
        
        if(gps_log[depth] != now && min!=Integer.MAX_VALUE){
            min++;
        }
        
        dp[depth][now] = min;
        return min;
    }
}