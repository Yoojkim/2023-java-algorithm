import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        boolean[] dp = new boolean[10000+1];
        Arrays.fill(dp, true);

        for(int i=2;i<=10000;i++){
            for(int j=2;j<i;j++){
                if(i%j==0){
                    dp[i] = false;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            int num = nums[i];
            
            int min = 2; int max = num-1;
            int resMin=0; int resMax=0;
            while(min<=max){
                if(!dp[min]){
                    min++;
                    continue;
                }
                
                if(!dp[max]){
                    max--;
                    continue;
                }
                
                int sum = min+max;
                
                if(sum == num){
                    resMin = min;
                    resMax = max;
                    
                    max--;
                    min++;
                    continue;
                }
                
                if(sum > num){
                    max--;
                    continue;
                }
                
                if(sum < num){
                    min++;
                    continue;
                }
            }
           
            sb.append(resMin).append(" ").append(resMax).append("\n");
        }

        System.out.print(sb);
    }
}