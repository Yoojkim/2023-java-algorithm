import java.util.*;
import java.io.*;

class Main{
    static boolean[] visited;
    static int[] ans;
    static int n;
    
    public static void main(String[] args) throws Exception{
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        
        visited=new boolean[n+1];
        ans=new int[n];
        
        backTracking(0);
    }
    
    //사전 순으로 출력 (idx: ans에 대한 인덱스)
    private static void backTracking(int idx){
        if(idx==n){
            for(int num:ans){
                System.out.print(num+" ");
            }
            System.out.println();
            
            return;
        }
        
        for(int i=1;i<=n;i++){
            if(!visited[i]){
                visited[i]=true;
                ans[idx]=i;
                
                backTracking(idx+1);
                
                visited[i]=false;
                //ans 넣은 거 안 빼도 됨 
            }
        }
        
    }
}