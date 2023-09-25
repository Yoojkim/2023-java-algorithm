import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        int[] happy=new int[n];
        int[] health=new int[n];

        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            health[i]=Integer.parseInt(st.nextToken());
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            happy[i]=Integer.parseInt(st.nextToken());
        }

        System.out.print(backTracking(n, happy, health, 0, 0, 100));
    }

    private static int backTracking(int n, int[] happy, int[] health,
                                    int idx, int happySum, int healthSum){
        if(healthSum<=0)
            return 0;

        if(idx==n)
            return happySum;


        int hello=backTracking(n, happy, health, idx+1, happySum+happy[idx], healthSum-health[idx]);
        int notHello=backTracking(n, happy, health, idx+1, happySum, healthSum);

        return (hello>notHello?hello:notHello);
    }
}