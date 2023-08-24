import java.util.*;
import java.io.*;

public class Main{
    
    public static boolean isPN(int num){
        int cnt=0;
        for(int i=1;i<=num;i++){
            if(num%i==0)
                cnt++;
            
            if(cnt>=3)
                return false;
        }
        
        return cnt!=1?true:false;
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int m=Integer.parseInt(br.readLine());
        int n=Integer.parseInt(br.readLine());
        
        int sum=0;
        int min=Integer.MAX_VALUE;
        for(int i=m;i<=n;i++){
            if(!isPN(i))
                continue;
            
            sum+=i;
            if(min>i)
                min=i;
        }
        
        if(sum==0){
            System.out.println(-1);
        } else{
            System.out.println(sum);
            System.out.println(min);
        }
    }
}