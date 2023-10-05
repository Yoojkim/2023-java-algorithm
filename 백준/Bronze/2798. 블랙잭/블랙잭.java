//시간 널널해서 완전탐색 ㄱㅊ
import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        int[] ns=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            ns[i]=Integer.parseInt(st.nextToken());
        }

        int ans=backTracking(m, ns, n, 0, 0, 0);
        System.out.print(ans);
    }

    private static int backTracking(int m, int[] ns, int n, int start, int depth, int sum){
        if(depth==3){
            return sum;
        }

        int max=Integer.MIN_VALUE;
        for(int i=start;i<n;i++){
            int res=backTracking(m, ns, n, i+1, depth+1, sum+ns[i]);

            if(res>m)
                continue;

            if(res>max)
                max=res;
        }

        return max;
    }
}