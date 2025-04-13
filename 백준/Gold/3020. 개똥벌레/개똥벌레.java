import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int H = Integer.parseInt(inputs[1]);

        N /= 2;
        int[] downSum = new int[H + 1];
        int[] upSum = new int[H + 1];
        for (int i = 0; i < N; i++) {
            int down = Integer.parseInt(br.readLine());
            int up = Integer.parseInt(br.readLine());

            downSum[down]++;
            upSum[H-up+1]++;
        }

        //누적합 계산
        for (int i = 1; i < H; i++) {
            upSum[i + 1] += upSum[i];
        }

        for (int i = H; i > 1; i--) {
            downSum[i - 1] += downSum[i];
        }


        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            int sum = downSum[i] + upSum[i];
            if (min > sum) {
                min = sum;
                cnt = 1;
            } else if (min == sum) {
                cnt++;
            }
        }

        System.out.print(min + " " + cnt);
    }
}