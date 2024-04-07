import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        //정렬
        Arrays.sort(rocks);
        
        int min = 1;
        int max = distance;
        
        int res = 1;
        while(min<=max){
            int mid = (min+max)/2;
            
            if(isPossible(mid, distance, rocks ,n)){
                res = mid;
                min = mid+1;
                continue;
            }
            
                max = mid-1;
        }
        
        return res;
    }
    
    private boolean isPossible(int mid, int distance, int[] rocks, int n){
        int start = 0;
        int removeCnt = 0;
        
        for(int rock:rocks){
            int dist = rock - start;
            
            if(dist >= mid){
                start = rock;
                continue;
            }
            
            removeCnt ++;
        }
        
        //todo: 이 부분 구현을 못하겠음 .. 
        
        int distToDest = distance - start;

        if(distToDest < mid){
            removeCnt ++;
        }
        
        if(removeCnt <= n){
            return true;
        }
        
        return false;
    }
}