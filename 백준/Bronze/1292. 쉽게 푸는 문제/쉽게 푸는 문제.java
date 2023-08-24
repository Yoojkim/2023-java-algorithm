import java.util.*;
import java.io.*;

public class Main{
    static int[] getAns(int num){
        int std=1;
        int total=0;
        int[] res=null;
        
        while(true){
            total+=std;
            
            if(total<num){
                std++;
                continue;
            }
            
            int[] newRes={std, getAbs(total-num-std+1)};
            res=newRes;
            break;
        }
       
        return res;
    }
    
    static int getAbs(int num){
        return num<0?num*-1:num;
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        
        int n1=Integer.parseInt(nums[0]);
        int n2=Integer.parseInt(nums[1]);
        
        int[] arr1=getAns(n1);
        int[] arr2=getAns(n2);
        
        int res=0;
        for(int i=arr1[0]; i<=arr2[0]; i++){
            if(arr1[0]==arr2[0]){
                int cnt=arr2[1]-arr1[1]+1;
                res+=i*cnt;
                
                break;
            }
            
            if(i==arr1[0]){
                int cnt=i-arr1[1];
                res+=cnt*i;
            } else if(i==arr2[0]){
                res+=i*(arr2[1]+1);
            } else{
                res+=i*i;
            }
        }
        
        System.out.println(res);
    }
}