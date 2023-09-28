import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int l=Integer.parseInt(st.nextToken());

        int[] ns=new int[n+2];
        ns[0]=0; ns[n+1]=l;

        if(n!=0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                ns[i] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(ns);

        int left=1; int right=l-1; int ans=-1;

        //최댓값의 최솟값
        while(left<=right){
            int mid=(left+right)/2;

            int cnt=sectionCnt(mid, ns);

            if(cnt>m)
                left=mid+1;
            else {
                right=mid-1;
                ans=mid;
            }
        }

        System.out.print(ans);
    }

    private static int sectionCnt(int size, int[] ns){
        int cnt=0;

        for(int i=0;i<ns.length-1;i++){
            int len=ns[i+1]-ns[i];
            cnt+=len/size;

            if(len%size==0)
                cnt--;
        }

        return cnt;
    }
}