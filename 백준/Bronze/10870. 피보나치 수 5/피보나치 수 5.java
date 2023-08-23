import java.util.*;
import java.io.*;

public class Main{
    
    static int pibo(int n){
        if(n==0 || n==1)
            return n;
        
        return pibo(n-1)+pibo(n-2);
    }
        
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        
        System.out.print(pibo(n));
    }
}