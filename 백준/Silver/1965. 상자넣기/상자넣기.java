import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        String[] values = br.readLine().split(" ");
        int[] dp = new int[1000+1];
        
        for(int i=0;i<N;i++){
            int box = Integer.parseInt(values[i]);
            
            int max = 0;
            for(int j=1;j<box;j++){
                if(dp[j] > max){
                    max = dp[j];
                }
            }
            
            dp[box]=max+1;
        }
        
        int max = -1;
        for(int i=1;i<=1000;i++){
            if(dp[i] > max){
                max = dp[i];
            }
        }
        
        System.out.print(max);
    }
}