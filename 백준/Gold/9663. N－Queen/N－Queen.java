import java.util.*;

class Main{
    static int n;
    static int ans=0;
    static int[] candidates;
    
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        candidates=new int[n];
        
        backTracking(0);
        
        System.out.print(ans);
    }
    
    private static void backTracking(int row){
        if(row==n){
            ans++;
            return;
        }
        
        for(int j=0;j<n;j++){
            if(isPossible(row, j)){
                candidates[row]=j;
                backTracking(row+1);
                //candidates 재초기화 필요? ㄴㄴ 
            }
        }
    }
    
    private static boolean isPossible(int row, int col){
        for(int i=0;i<row;i++){
            int tempCol=candidates[i];
            
            if (tempCol==col)
                return false;
            
            //대각선 계산
            int dx=row-i; int dy=col-tempCol;
            dx=(dx<0?dx*-1:dx); dy=(dy<0?dy*-1:dy);
            
            if(dx==dy)
                return false;
        }
        
        return true;
    }
}