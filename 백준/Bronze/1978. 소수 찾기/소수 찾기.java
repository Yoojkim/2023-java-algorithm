import java.util.*;
import java.io.*;

public class Main{
    public static boolean isPN(int num){
        int cnt=0;
        
        for(int i=1;i<=num;i++){
            if(num%i==0)
                cnt++;
            
            if(cnt>2) 
                return false;
        }
        
        //num=1 고려
        return cnt==2?true:false;
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String[] nums=br.readLine().split(" ");
        
        int cnt=0;
        for(String num:nums){
            int n=Integer.parseInt(num);
            
            if(isPN(n))
                cnt++;
        }
        
        System.out.println(cnt);
    }
}