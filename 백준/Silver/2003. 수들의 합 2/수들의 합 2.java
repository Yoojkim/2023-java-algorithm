import java.util.*;
import java.io.*;

public class Main{
    
    static int n, s;
    static int[] fields;
    static int ans=0;
    
    private static void twoPointers(){
        int low=0, high=0; 
        int sum=0;
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
            
            if(sum==s)
                ans++;
        }
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
        
        twoPointers();
        
        System.out.print(ans);
    }
}