import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nums=br.readLine().split(" ");
        int n=Integer.parseInt(nums[0]);
        int m=Integer.parseInt(nums[1]);

        long ans=0;
        long[] ds=new long[n+1];
        int[] cnt=new int[m];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){ //O(n)
            long num=Long.parseLong(st.nextToken());

            ds[i]+=ds[i-1]+num;
            int val=(int)(ds[i]%m);

            cnt[val]++;
        }

        for(int i=0;i<m;i++){ //O(m)
            long temp=(long)cnt[i]*(cnt[i]-1)/2;
            ans+=temp;
        }

        ans+=cnt[0];

        System.out.print(ans);
    }
}