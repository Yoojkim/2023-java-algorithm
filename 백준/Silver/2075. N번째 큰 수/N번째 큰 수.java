import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        
        int[][] nums=new int[n][n];
        
        for(int i=0;i<n;i++){
            String[] rowNums=br.readLine().split(" ");
            for(int j=0;j<n;j++){
                nums[i][j]=Integer.parseInt(rowNums[j]);
            }
        }
        
        int[] table=new int[n];
        for(int i=0;i<n;i++)
            table[i]=n-1;
        
        int cnt=1;
        while(true){
            //현재 제일 큰 수 찾음 
            int max=Integer.MIN_VALUE; 
            int idx=-1;
            for(int i=0;i<n;i++){
                int row=table[i];
                
                if(row<0)
                    continue;
                
                int val=nums[row][i];
                
                if(max<val){
                    max=val;
                    idx=i;
                }
            }
            
            if(cnt==n){
                System.out.print(max);
                return;
            }
            
            table[idx]=table[idx]-1;
            cnt++;
        }
    }
}