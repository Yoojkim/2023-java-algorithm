import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
	static int[] dx = { 0, 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 0, 1, -1 };
	static int[] dz = { 1, -1, 0, 0, 0, 0 };

	static class node {
		int x;
		int y;
		int z;

		public node(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][][] box = new int[H][N][M];

		Queue<node> queue = new LinkedList<>();

		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < M; j++) {
					box[h][i][j] = Integer.parseInt(st.nextToken());
					if (box[h][i][j] == 1) {
						queue.add(new node(j, i, h));
					}
				}
			}
		}

		// 모두 다 익어있는 경우
		if (queue.size() == M * N * H) {
			System.out.println(0);
			return;
		}

		// BFS
		while (!queue.isEmpty()) {
			node temp = queue.poll();
			int x = temp.x;
			int y = temp.y;
			int z = temp.z;
			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];

				if (nx < 0 || ny < 0 || nz < 0 || nx >= M || ny >= N || nz >= H) {
					continue;
				}
				
				// 익어있지 않은 경우
				if (box[nz][ny][nx] == 0) {
					box[nz][ny][nx] = box[z][y][x] + 1; // 익어진 날짜를 저장
					queue.add(new node(nx, ny, nz));
				}
			}
		}

		int max = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					// 0인 경우 => 아직 익지않았다?! 말도안됨 불가능
					if (box[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					// 해당 칸들에 적힌 숫자중 가장 큰 숫자가 최소일자
					if (max < box[i][j][k]) {
						max = box[i][j][k];
					}
				}
			}
		}

		System.out.println(max - 1);
	}
}