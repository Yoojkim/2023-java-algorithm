import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");

        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            int[] files = new int[N];
            int[][] dp = new int[N][N];
            int[] prefixSum = new int[N+1]; // 누적합 배열

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                files[j] = Integer.parseInt(st.nextToken());
            }

            // 누적합 계산
            prefixSum[0] = 0;
            for (int i = 1; i <= N; i++) {
                prefixSum[i] = prefixSum[i - 1] + files[i - 1];
            }

            for(int i=0;i<N;i++){
                Arrays.fill(dp[i], -1);
                dp[i][i] = 0;
            }

            int res = dp(0, N-1, files, dp, prefixSum);
            sj.add(Integer.toString(res));
        }

        System.out.print(sj);
    }

    public static int dp(int start, int end, int[] files, int[][] dp, int[] prefixSum){
        if(start==end){
            return 0;
        }
        
        if(dp[start][end] != -1){
            return dp[start][end];
        }

        int min = Integer.MAX_VALUE;

        for(int i=start;i<end;i++){
            int tempMin = dp(start, i, files, dp, prefixSum) + dp(i+1, end, files, dp, prefixSum) + sum(start, end, prefixSum);

            if(min>tempMin){
                min = tempMin;
            }
        }

        dp[start][end] = min;
        return min;
    }

    // sum을 prefixSum 배열을 이용해서 계산
    public static int sum(int start, int end, int[] prefixSum){
        return prefixSum[end + 1] - prefixSum[start];
    }
}
