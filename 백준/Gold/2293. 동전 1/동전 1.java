import java.util.*;
import java.io.*;

class Main{
    static int n;
    static int[] ns;
    static int[] d;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums= br.readLine().split(" ");
        n=Integer.parseInt(nums[0]); int k=Integer.parseInt(nums[1]);
        ns=new int[n];
        d=new int[k+1];
        d[0]=1;

        for(int i=0;i<n;i++){
            ns[i]=Integer.parseInt(br.readLine());
        }


        for(int i=0;i<n;i++){
            for(int j=ns[i];j<=k;j++)
                d[j]+=d[j-ns[i]];
        }

        System.out.print(d[k]);
    }

}