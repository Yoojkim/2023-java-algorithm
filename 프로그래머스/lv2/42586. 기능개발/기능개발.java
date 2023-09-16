import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int dx=0;
        int n=progresses.length;
        
        List<Integer> ans=new ArrayList();
        while(dx<n){
            for(int i=dx;i<n;i++){
                progresses[i]=progresses[i]+speeds[i];
            }
            
            //배포 가능 확인
            int cnt=0;
            while(dx<n){
                if(progresses[dx]>=100){
                    dx++;
                    cnt++;
                } else{
                    break;
                }
            }
            
            if(cnt>0)
                ans.add(cnt);
            
        }
        
        int size=ans.size();
        int[] arrayAns=new int[size];
        for(int i=0;i<size;i++){
            arrayAns[i]=ans.get(i);
        }
        
        return arrayAns;
    }
}