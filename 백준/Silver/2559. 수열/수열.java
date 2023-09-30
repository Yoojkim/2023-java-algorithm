import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        
        int[] sums=new int[n+1];
        int max=Integer.MIN_VALUE;
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            int val=Integer.parseInt(st.nextToken());
            sums[i]+=sums[i-1]+val;
            
            if(i<k)
                continue;
            
            int kVal=sums[i]-sums[i-k];
            if(kVal>max)
                max=kVal;
        }
        
        System.out.print(max);
    }
}