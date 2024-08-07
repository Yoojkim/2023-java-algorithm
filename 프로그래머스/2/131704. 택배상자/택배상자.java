import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> queue = new Stack<>();
        int N = order.length;
        
        int box = 1;
        int res = 0;
        
        for(int i=0;i<N;i++){
            int nowBox = order[i];
            
            if(box == nowBox){
                res ++;
                box ++;
                
                continue;
            }
            
            if(box < nowBox){
                for(int j= box; j<nowBox; j++){
                    queue.push(j);
                }
                
                res++;
                box = nowBox+1;
                
                continue;
            }
            
            //box > nowBox
            if(!queue.isEmpty() && queue.peek() == nowBox){
                queue.pop();
                res ++;
                
                continue;
            }
            
            break;
        }
        
        return res;
    }
}