import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(
            (a, b)-> b-a
        );
        
        for(int work:works){
            queue.add(work);
        }
        
        for(int i=0;i<n;i++){
            if(queue.isEmpty()){
                break;
            }
            
            int max = queue.poll();
            if(max -1 == 0){
                continue;
            }
            
            queue.add(max-1);
        }
        
        if(queue.isEmpty()){
            return 0;
        }
        
        long res = 0;
        for(int work:queue){
            res += (work*work);
        }
        
        return res;
    }
}