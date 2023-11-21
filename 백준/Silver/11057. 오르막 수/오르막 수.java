import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        System.out.print(calculateResult(N));
    }

    private static long calculateResult(int N) {
        long[][] dp = new long[N + 1][9 + 1];

        //0, 00, 000, 0000 모두 1
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int digit = 1; digit <= N; digit++) {
            for (int max = 1; max <= 9; max++) {
                dp[digit][max] = (dp[digit - 1][max] + dp[digit][max - 1])%10007;
            }
        }

        long sum = 0;
        for (int max = 0; max <= 9; max++) {
            sum = (sum + dp[N][max])%10007;
        }

        return sum;
    }
}