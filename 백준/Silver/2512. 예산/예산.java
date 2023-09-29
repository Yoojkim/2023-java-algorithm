import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] ns = new int[n];
        int sum=0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int val=Integer.parseInt(st.nextToken());
            ns[i] = val;
            sum+=val;
        }

        Arrays.sort(ns);

        int budget = Integer.parseInt(br.readLine());

        if(sum<=budget)
            System.out.print(ns[ns.length-1]);
        else{
            int left=1; int right=ns[ns.length-1];
            int max=Integer.MIN_VALUE; int ans=-1;

            while(left<=right){
                int mid=(left+right)/2;
                int spentBudget=getRealBudget(mid, budget, ns);

                if(spentBudget<0){
                    right=mid-1;
                } else {
                    if(spentBudget>max){
                        max=spentBudget;
                        ans=mid;
                    }

                    left=mid+1;
                }
            }

            System.out.print(ans);
        }
    }

    private static int getRealBudget(int highBudget, int allBudget, int[] ns){
        int sum=0;

        for(int i=0;i<ns.length;i++){
            if(ns[i]>=highBudget)
                sum+=highBudget;
            else
                sum+=ns[i];

            if(sum>allBudget)
                return -1;
        }

        return sum;
    }
}