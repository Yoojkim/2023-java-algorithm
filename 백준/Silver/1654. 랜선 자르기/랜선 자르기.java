import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nums=br.readLine().split(" ");
        int k=Integer.parseInt(nums[0]); int n=Integer.parseInt(nums[1]);

        long[] ks=new long[k];
        for(int i=0;i<k;i++){
            ks[i]=Long.parseLong(br.readLine());
        }

        Arrays.sort(ks);

        long left=1; long right=(ks[k-1]*k)/n;
        long ans=-1;

        while(left<=right){
            long mid=(left+right)/2;

            if(isPossible(ks, mid, n)){

                if(ans<mid)
                    ans=mid;

                left=mid+1;

            } else {
                right=mid-1;
            }
        }

        System.out.print(ans);
    }

    private static boolean isPossible(long[] ks, long size, int n){
        
        
        for(int i=ks.length-1; i>=0; i--){
            long cnt= ks[i]/size;
            
            if(n<cnt)
                n=0;
            else
                n-=cnt;

            if(n<=0)
                break;
        }

        if(n>0)
            return false;

        return true;
    }
}