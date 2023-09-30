import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken()); int m=Integer.parseInt(st.nextToken());
        int[] sums=new int[n+1];
        
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            int val=Integer.parseInt(st.nextToken());
            sums[i]+=val+sums[i-1];
        }
        
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            
            int ans=getSum(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), sums);
            sb.append(ans).append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static int getSum(int i, int j, int[] sums){
        return sums[j]-sums[i-1];
    }
}