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

        for(int i=0;i<n;i++){
            for(int j=ns.get(i);j<=k;j++){
                int newK=j-ns.get(i);

                if((d[newK]==0 && newK==0) || d[newK]!=0){
                    if(d[j]==0)
                        d[j]=d[newK]+1;
                    else{
                        d[j]=(d[j]>d[newK]+1?d[newK]+1:d[j]);
                    }
                }
            }
        }

        int ans=(d[k]==0?-1:d[k]);

        System.out.print(ans);
    }
}