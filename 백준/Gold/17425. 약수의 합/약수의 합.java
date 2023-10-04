import java.util.*;
import java.io.*;

//O(n)
class Main{
    static int n=1000000;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        long[] fAns=new long[n+1];
        long[] dAns=new long[n+1];

        for(int i=1;i<=n;i++){
            for(int j=1;i*j<=n;j++){
                fAns[i*j]+=i;
            }
        }

        for(int i=1;i<=n;i++){
            dAns[i]+=fAns[i]+dAns[i-1];
        }

        int t=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<t;i++){
            int num=Integer.parseInt(br.readLine());

            sb.append(dAns[num]).append("\n");
        }

        System.out.print(sb);
    }

}