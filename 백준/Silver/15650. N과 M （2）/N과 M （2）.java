import java.util.*;

class Main{
    static int n, m;
    static boolean[] visited;
    static int[] ans;
    static StringBuilder sb=new StringBuilder();
    
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt(); m=scanner.nextInt();
        visited=new boolean[n+1];
        ans=new int[m];
        
        //ans 따로 할 수 있는 방법 없나 
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
        
        int minNum=(idx==0?0:ans[idx-1]);
        
        for(int i=minNum+1;i<=n;i++){
            if(!visited[i]){
                visited[i]=true;
                ans[idx]=i;
                
                backTracking(idx+1);
                
                visited[i]=false;
            }
        }
    }
}