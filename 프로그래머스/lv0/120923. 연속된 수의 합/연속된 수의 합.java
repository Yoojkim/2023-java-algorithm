class Solution {
    public int[] solution(int num, int total) {
        //ax+b
        int b=0;
        for(int i=0;i<num;i++){
            b+=i;
        }
        
        int x=(total-b)/num;
        
        int[] result=new int[num];
        for(int i=0;i<num;i++)
            result[i]=x+i;
        
        return result;
    }
}