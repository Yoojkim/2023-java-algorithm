import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        int n=Integer.parseInt(nums[0]); long m=Long.parseLong(nums[1]);
        
        long[] ns=new long[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            ns[i]=Long.parseLong(st.nextToken());
        }
        
        Arrays.sort(ns);
        long left=1; long right=ns[n-1];
        long ans=0;
        
        while(left<=right){
            long mid=(left+right)/2;
            
            if(isPossible(ns, mid, m)){
                if(ans<mid)
                    ans=mid;
                left=mid+1;
            } else{
                right=mid-1;
            }
        }
        
        System.out.print(ans);
    }
    
    private static boolean isPossible(long[] ns, long size, long m){
        
        for(int i=ns.length-1; i>=0; i--){
            long cnt=ns[i]-size;
            
            if(m<cnt)
                m=0;
            else
                m-=cnt;
            
            if(m==0)
                break;
        }
        
        if(m==0)
            return true;
        else
            return false;
    }
}