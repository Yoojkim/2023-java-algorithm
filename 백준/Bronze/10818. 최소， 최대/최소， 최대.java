import java.util.*;
import java.io.*;

public class Main{
    static int max=Integer.MIN_VALUE;
    static int min=Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n=Integer.parseInt(br.readLine());
        
        StringTokenizer st= new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            int num=Integer.parseInt(st.nextToken());
            
            if(num<min)
                min=num;
            if(num>max)
                max=num;
        }
        
        System.out.print(min+" "+max);
    }
}