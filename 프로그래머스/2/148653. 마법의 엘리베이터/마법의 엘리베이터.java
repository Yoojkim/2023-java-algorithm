class Solution {
    public int solution(int storey) {
        int cnt = 0;
        while(storey != 0){
            int quotient = storey / 10;
            int remainder = storey % 10;
            
            if(remainder == 0){
                storey = quotient;
                continue;
            }
            
            if(remainder < 5){
                cnt += remainder;
                storey = quotient;
                continue;
            }
            
            if(remainder > 5){
                cnt+= 10-remainder;
                storey = quotient + 1;
                continue;
            }
            
            if(remainder == 5){
                //quotient에 따라 다른 값 
                if((int)quotient%10 >=5){
                    cnt+= remainder;
                    storey = quotient + 1;
                    
                    continue;
                } else {
                    cnt+= remainder;
                    storey = quotient;
                    
                    continue;
                }
            }
        }
        
        return cnt;
    }
}