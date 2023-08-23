import java.util.*;
import java.io.*;

public class Main{
    static boolean[] visit;
    static int[] values;
    
    static int pibo(int n){
        if(n==0 || n==1)
            return n;
        
        if(visit[n])
            return values[n];
        
        int val=pibo(n-2)+pibo(n-1);
        visit[n]=true;
        values[n]=val;
        
        return val;
    }
        
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        visit=new boolean[n+1];
        values=new int[n+1];
        
        System.out.print(pibo(n));
    }
}