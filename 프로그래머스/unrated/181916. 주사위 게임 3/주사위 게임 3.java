import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
        int[] nums={a, b, c, d};
        Arrays.sort(nums);
        
        int[] compares=new int[3];
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]==nums[i+1])
                compares[i]=1;
            else
                compares[i]=0;
        }
        
        int result=compares[0]+compares[1]+compares[2];
        
        int returnResult=-1;
        switch(result){
            case 0:
                returnResult=nums[0]; break;
            case 3:
                returnResult=1111*nums[0]; break;
                
            case 1:
                //두개만 같고 나머지는 다른 경우
                int sameResultIdx=-1;
                for(int cIdx=0;cIdx<compares.length;cIdx++){
                    if (compares[cIdx]==1)
                        sameResultIdx=cIdx;
                }
                
                switch(sameResultIdx){
                    case 0:
                        returnResult=nums[2]*nums[3]; break;
                    case 1:
                        returnResult=nums[0]*nums[3]; break;
                    case 2:
                        returnResult=nums[0]*nums[1]; break;
                }
                
                break;
                
            case 2:
                int nonSameResultIdx=-1;
                for(int cIdx=0;cIdx<compares.length;cIdx++){
                    if (compares[cIdx]==0)
                        nonSameResultIdx=cIdx;
                }
                
                switch(nonSameResultIdx){
                    case 0:
                        returnResult=(10*nums[1]+nums[0])*(10*nums[1]+nums[0]); break;
                    case 1:
                        returnResult=nums[0]-nums[3]>=0?(nums[0]+nums[3])*(nums[0]-nums[3]):(nums[0]+nums[3])*(nums[0]-nums[3])*-1; break;
                    case 2:
                        returnResult=(10*nums[2]+nums[3])*(10*nums[2]+nums[3]); break;
                }
        }
        
        return returnResult;
    }
}