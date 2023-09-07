import java.util.*;

class Main {
    static int ans = 0;
    static int n;
    static boolean[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        board = new boolean[n][n];
        backTracking(0);
        System.out.print(ans);
    }

    private static void backTracking(int row) {
        if (row == n) {
            ans++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isPossible(row, col)) {
                board[row][col] = true;
                backTracking(row + 1);
                board[row][col] = false;
            }
        }
    }

    private static boolean isPossible(int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }

        return true;
    }
}
