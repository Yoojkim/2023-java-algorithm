import java.util.*;
import java.io.*;

public class Main{
    
    static int n, s;
    static int[] fields;
    static int ans=0;
    
    private static void cal(int idx, int sum){
        if (sum==s){
            ans++;
            return;
        }
        
        if (sum>s)
            return;
        
        if (idx==n)
            return;
       
        cal(idx+1, sum+fields[idx]);
    }
    
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
        
        for(int i=0;i<n;i++){
            cal(i, 0);
        }
        
        System.out.print(ans);
    }
}