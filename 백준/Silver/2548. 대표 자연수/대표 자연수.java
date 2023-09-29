import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());
        int[] ns=new int[n];

        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            ns[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ns);

        //1. 중간값 계산
        int mid=(n-1)/2;
        int min=cal(mid, ns); int ans=mid;

        //2. 왼
        int idx=mid-1;
        while(idx>=0){
            int idxCal=cal(idx, ns);

            if(idxCal<=min) {
                min = idxCal;
                ans=idx;
            } else{
                break;
            }

            idx--;
        }

        //3. 오
        idx=mid+1;
        while(idx<n){
            int idxCal=cal(idx, ns);

            if(idxCal<min){
                min = idxCal;
                ans=idx;
            } else{
                break;
            }

            idx++;
        }

        System.out.print(ns[ans]);
    }

    private static int cal(int idx, int[] ns){
        int sum=0;

        for(int n:ns){
            int calRes=n-ns[idx];
            if(calRes<0)
                calRes*=-1;

            sum+=calRes;
        }

        return sum;
    }
}