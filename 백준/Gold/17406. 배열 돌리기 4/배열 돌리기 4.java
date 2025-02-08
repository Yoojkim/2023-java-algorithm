import java.io.*;
import java.util.*;

class Move {
    int r, c, s;

    public Move(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}

public class Main {
    static int N, M, K;
    static int[][] fields;
    static Move[] moves;
    static int[] selectedMoves;
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fields = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moves = new Move[K];
        selectedMoves = new int[K];
        visited = new boolean[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            moves[i] = new Move(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        backTracking(0);
        System.out.println(result);
    }

    static void backTracking(int idx) {
        if (idx == K) {
            int[][] tempFields = copyArray(fields);
            for (int moveIdx : selectedMoves) {
                rotate(tempFields, moves[moveIdx]);
            }
            result = Math.min(result, calculateMin(tempFields));
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;

            selectedMoves[idx] = i;
            visited[i] = true;
            backTracking(idx + 1);
            visited[i] = false;
        }
    }

    static void rotate(int[][] arr, Move move) {
        int r = move.r, c = move.c, s = move.s;

        for (int layer = 1; layer <= s; layer++) {
            int top = r - layer, left = c - layer;
            int bottom = r + layer, right = c + layer;

            int prev = arr[top][left];

            // 왼쪽 열 (아래로 이동)
            for (int i = top; i < bottom; i++) arr[i][left] = arr[i + 1][left];

            // 아래 행 (오른쪽으로 이동)
            for (int i = left; i < right; i++) arr[bottom][i] = arr[bottom][i + 1];

            // 오른쪽 열 (위로 이동)
            for (int i = bottom; i > top; i--) arr[i][right] = arr[i - 1][right];

            // 위쪽 행 (왼쪽으로 이동)
            for (int i = right; i > left + 1; i--) arr[top][i] = arr[top][i - 1];

            arr[top][left + 1] = prev;
        }
    }

    static int[][] copyArray(int[][] arr) {
        int[][] temp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++)
            System.arraycopy(arr[i], 1, temp[i], 1, M);
        return temp;
    }

    static int calculateMin(int[][] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += arr[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }
}
