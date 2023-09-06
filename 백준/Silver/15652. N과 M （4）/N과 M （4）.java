import java.util.*;

//이거 제대로 풀고 다른 문제 ㄴㄴ.. 

class Main{
    static int n, m;
    static StringBuilder sb=new StringBuilder();
    static int[] ans;
    
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt(); m=scanner.nextInt();
        ans=new int[m];
        
        backTracking(0, 1);
        System.out.print(sb);
    }
    
    private static void backTracking(int ansIdx, int nIdx){
        if(ansIdx==m){
            for(int num:ans){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for(int i=nIdx;i<=n;i++){
            ans[ansIdx]=i;
            backTracking(ansIdx+1, i);
        }
    }
}