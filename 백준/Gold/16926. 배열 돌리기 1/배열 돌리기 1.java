import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder sb = new StringBuilder();

	private static int N;
	private static int M;
	private static int R; // 회전 횟수

	private static int[][] table;
	private static boolean[][] visited;
	private static int[][] deltas = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	private static void solution() {
		visited = new boolean[N][M];

		int r = 0;
		int c = 0;
		int nr = 0;
		int nc = 0;
		int startR = 0;
		int startC = 0;

		int currentValue = 0;
		int nextValue = table[0][0];
		int deltaIndex = 0;

		while (true) {
			r = nr;
			c = nc;
			currentValue = nextValue;

			nr = r + deltas[deltaIndex][0];
			nc = c + deltas[deltaIndex][1];

			// 사이클 범위를 벗어나면
			if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) {
				deltaIndex = (deltaIndex + 1) % 4;
				nr = r + deltas[deltaIndex][0];
				nc = c + deltas[deltaIndex][1];
			}

			// 사이클 시작점으로 돌아오면
			if (nr == startR && nc == startC) {
				table[nr][nc] = currentValue;
				nr += 1;
				nc += 1;
				// 가장 안쪽 사이클이면 중지
				if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) {
					break;
				} else {
					// 사이클 시작점 갱신
					startR = nr;
					startC = nc;
					deltaIndex = 0;
					nextValue = table[nr][nc];
					continue;
				}
			}

			visited[nr][nc] = true; // 방문 처리

			nextValue = table[nr][nc];
			table[nr][nc] = currentValue;
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());

		table = new int[N][M];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
	}

	private static void output() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(table[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 0; i < R; i++) {
			solution();
		}
		output();
		System.out.println(sb);
	}

}