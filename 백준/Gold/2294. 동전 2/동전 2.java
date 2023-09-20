import java.io.*;
import java.util.*;

class Main{

    static List<Integer> ns=new ArrayList();
    static int n,k;
    static int[] d;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        n=Integer.parseInt(nums[0]); k=Integer.parseInt(nums[1]);

        d=new int[k+1];
        for(int i=0;i<n;i++){
            ns.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(ns);

        dp(k);

        System.out.print(d[k]);
    }

    private static int dp(int num){
        if (num==0)
            return 0;

        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int newNum=num-ns.get(i);

            if(newNum<0)
                break;

            if(d[newNum]==0)
                d[newNum]=dp(newNum);

            if(d[newNum]>=0 && min>d[newNum])
                min=d[newNum];
        }

        if(min==Integer.MAX_VALUE)
            return d[num]=-1;
        else{
            return d[num]=min+1;
        }
    }
}