import java.util.*;

class Main{
    static int n, m;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args){
        Scanner scanner =new Scanner(System.in);
        n=scanner.nextInt();
        m=scanner.nextInt();
        
        ans=new int[m];
        
        backTracking(0);
        System.out.print(sb);
    }
    
    public static void backTracking(int idx){
        if(idx==m){
            for(int num:ans){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            
            return;
        }
        
        for(int i=1;i<=n;i++){
            ans[idx]=i;
            backTracking(idx+1);
        }
    }
}