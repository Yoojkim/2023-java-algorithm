import java.util.*;

class Solution {
    
    List<int[]> log = new ArrayList<>();
    
    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3);
        
        return parseLog();
    }
    
    private void hanoi(int n, int start, int to, int end){
        if(n==1){
            log.add(new int[]{start, end});
            return;
        }
        
        hanoi(n-1, start, end, to); //start->end->to
        
        //n의 경우
        log.add(new int[]{start, end}); //start->end
        
        hanoi(n-1, to, start, end); //to->start->end
        
    }
    
    private int[][] parseLog(){
        int size = log.size();
        
        int[][] res = new int[size][2];
        for(int i=0;i<size;i++){
            res[i] = log.get(i);
        }
        
        return res;
    }
}