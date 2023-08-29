import java.util.*;
import java.io.*;

public class Main{
    
    static int n, s;
    static int[] fields;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        n=Integer.parseInt(nums[0]);
        s=Integer.parseInt(nums[1]);
        
        fields=new int[n];
        String[] arr=br.readLine().split(" ");
        for(int i=0;i<n;i++){
            fields[i]=Integer.parseInt(arr[i]);
        }
        
        int sum=0;
        int low=0, high=0;
        int ans=Integer.MAX_VALUE;
        while(true){
            if(sum>=s){
                sum-=fields[low];
                low++;
            } else if(high==n){
                break;
            } else{
                sum+=fields[high];
                high++;
            }
              
            if(sum>=s){
                int len=high-1-low+1;
                if(ans>len)
                    ans=len;
            }
        }
        
        if(ans==Integer.MAX_VALUE){
            ans=0;
        }
        
        System.out.print(ans);
    }
}