import java.util.*;
import java.io.*;

public class Main{
    
    public static void getBinary(int num){
        int cnt=0;
        
        while(true){ 
            if(num==1){
                System.out.print(cnt);
                break;
            }
            
            if(num%2==1)
                System.out.print(cnt+" ");
            
            cnt++;
            num/=2;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n=Integer.parseInt(br.readLine());
        
        for(int i=0;i<n;i++){
            int num=Integer.parseInt(br.readLine());
            getBinary(num);
            System.out.println();
        }
        
        
    }
}