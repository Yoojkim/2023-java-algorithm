import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        
        int cnt=0;
        for(int i=1;i<=n;i++){
            if (n%i==0)
                ++cnt;
            
            if (cnt==k){
                System.out.println(i);
                break;
            }
        }
        
        if(cnt<k){
            System.out.println(0);
        }
    }
}