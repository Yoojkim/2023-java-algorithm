import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");

        int n=Integer.parseInt(nums[0]); int k=Integer.parseInt(nums[1]);
        int[] ns=new int[n];
        for(int i=n-1;i>=0;i--){
            ns[i]=Integer.parseInt(br.readLine());
        }

        int cnt=0;
        int nIdx=0;
        while(true){
            if(k==0)
                break;

            for(int i=nIdx;i<n;i++){
                if(ns[i]<=k){
                    nIdx=i;
                    break;
                }
            }

            k-=ns[nIdx];
            cnt++;
        }

        System.out.print(cnt);
    }
}