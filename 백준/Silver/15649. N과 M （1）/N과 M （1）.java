import java.util.*;

class Main{
    static int n, m;
    static int ans[];
    static boolean[] visited;
    static StringBuilder sb=new StringBuilder();
    
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt(); m=scanner.nextInt();
        
        ans=new int[m];
        visited=new boolean[n+1];
        backTracking(0);
        
        System.out.print(sb);
    }
    
    private static void backTracking(int idx){
        if(idx==m){
            for(int num:ans){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            
            return;
        }
        
        for(int i=1;i<=n;i++){
            if(!visited[i]){
                visited[i]=true;
                ans[idx]=i;
                
                backTracking(idx+1);
                
                visited[i]=false;
            }
        }
    }
}