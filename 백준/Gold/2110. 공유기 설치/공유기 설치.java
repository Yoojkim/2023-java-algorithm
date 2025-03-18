import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = br.readLine().split(" ");

		int N = Integer.parseInt(inputs[0]);
		int C = Integer.parseInt(inputs[1]);

		int[] homes = new int[N + 1];

		for (int i = 0; i < N; i++) {
			homes[i + 1] = Integer.parseInt(br.readLine());
		}

		// 1. 정렬
		Arrays.sort(homes);

		// 2. 최대 지점 사이의 거리와 최소 지점사이의 거리 찾기
		int right = homes[N];
		int left = 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;

			boolean possible = isPossible(mid, homes, N, C);

			if (possible) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.print(Math.min(left, right));
	}

    private static boolean isPossible(int mid, int[] homes, int N, int C) {
        int count = 1; // 첫 번째 집에 공유기를 설치
        int last = homes[1]; // 마지막으로 공유기를 설치한 집의 위치

        for (int i = 2; i <= N; i++) {
            if (homes[i] - last >= mid) {
                count++;
                last = homes[i];
                if (count >= C) {
                    return true;
                }
            }
        }

        return false;
    }
}
