import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        int n1=Integer.parseInt(nums[0]);
        int n2=Integer.parseInt(nums[1]);
        
        //최대공약수  
        int max=0; int min=0;
        for(int i=1;i<=n1;i++){
            if(n1%i==0 && n2%i==0){
                max=i;
            }
        }
        
        System.out.println(max);
        
        //최소공배수
        int idx=1;
        while(true){
            //없는 경우 종료가 안 됨 (무조건 있는 상황인가봄?)
            int temp=n1*idx;
            
            if(temp%n2==0){
                min=temp;
                break;
            }
            
            idx++;
        }
        
        System.out.println(min);
    }
}