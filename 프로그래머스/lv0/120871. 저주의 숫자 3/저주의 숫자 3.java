class Solution {
    public int solution(int n) {
        
        int newNum=0;
        for(int i=1;i<=n;i++){
            newNum++;
            
            while(isMultiple(newNum) || haveThree(newNum)){
                newNum++;
            }
        }
        
        return newNum;
    }
    
    private boolean isMultiple(int n){
        if(n%3==0)
            return true;
        return false;
    }
    
    private boolean haveThree(int n){
        int digit=getDigit(n);
        
        while(n>=1){
            int digitVal=n/digit;
            
            if(digitVal==3)
                return true;
            
            n-=digitVal*digit;
            digit/=10;
        }
        
        return false;
    }
    
    private int getDigit(int n){
        
        int start=1;
        
        while(true){
            start*=10;
            
            if(n<start)
                break;
        }
        
        return start/10;
    }
}