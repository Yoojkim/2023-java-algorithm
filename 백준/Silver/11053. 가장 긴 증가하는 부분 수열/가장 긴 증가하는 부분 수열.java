import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n=Integer.parseInt(br.readLine());
        int[] arr=new int[n+1];
        int[] dp=new int[n+1];
        
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        
        //dp
        for(int i=1;i<=n;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(max<dp[j] && arr[i]>arr[j])
                    max=dp[j];
            }
            
            dp[i]=max+1;
        }
        
        
        int ans=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            if(ans<dp[i])
                ans=dp[i];
        }
        
        System.out.print(ans);
    }
}