import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long min=1; long max = (long)times[0]*n;
        long res = -1;
        while(min<=max){
            long mid = (min+max)/2;
            
            
            if(!isPossible(mid, n, times)){
                min = mid+1;
                
                continue;
            }
            
            //isPossible
            res = mid;
            max = mid -1;
        }
        
        return res;
    }
    
    private boolean isPossible(long mid, int n, int[] times){
        long count=0;
        
        for(int time:times){
            if(time <= mid){
                count += mid/time;
                
                continue;
            }
            
            break;
        }
        
        return count >= n;
    }
}