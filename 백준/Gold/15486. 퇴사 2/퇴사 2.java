import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());

        int[] ts=new int[n+1];
        int[] ps=new int[n+1];
        int[] dp=new int[n+2];

        for(int i=1;i<=n;i++){
            String[] nums=br.readLine().split(" ");

            int t=Integer.parseInt(nums[0]);
            int p=Integer.parseInt(nums[1]);

            ts[i]=t;
            ps[i]=p;
        }

        for(int i=1;i<=n;i++){

            //dp[i]와 dp[i-50] 까지의 최대
            int max=-1;
            for(int j=i;j>i-50;j--){
                if(j==0)
                    break;

                if(max<dp[j])
                    max=dp[j];
            }

            int exitDate=i+ts[i];
            int pSum=max+ps[i];

            if(exitDate>n+1)
                continue;

            if(dp[exitDate]<pSum){
                dp[exitDate]=pSum;
            }
        }

        int ans=-1;
        for(int arr:dp){
            if(ans<arr)
                ans=arr;
        }

        System.out.print(ans);
    }
}