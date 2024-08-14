import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int M;
	private static int K;

	private static int[][] board;
	private static int[][] choosed; // 선택된 칸의 좌표
	private static int[][] coords;
	private static final int[][] deltas = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static int maxSum; // 최대 합

	// 인접한 칸인지 확인
	private static boolean isNear() {
		for (int i = 0; i < K - 1; i++) {
			for (int j = i + 1; j < K; j++) {
				for (int[] delta : deltas) {
					// 인접하면
					if (choosed[i][0] + delta[0] == choosed[j][0] && choosed[i][1] + delta[1] == choosed[j][1]) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// 선택한 칸 합 계산
	private static void calcSum() {
		int sum = 0;
		for (int i = 0; i < K; i++) {
			int r = choosed[i][0];
			int c = choosed[i][1];
			sum += board[r][c];
		}

		if (maxSum < sum) {
			maxSum = sum;
		}
	}

	// 중복 없는 조합 생성
	private static void makeCombination(int depth, int start) {
		if (depth == K) {
			if (!isNear()) {
				calcSum();
			}
			return;
		}

		for (int i = start; i < N * M; i++) {
			int r = coords[i][0];
			int c = coords[i][1];
			choosed[depth] = new int[] { r, c };
			makeCombination(depth + 1, i + 1);
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		board = new int[N][M];
		coords = new int[N * M][2];
		choosed = new int[K][2];
		maxSum = Integer.MIN_VALUE;

		int k = 0;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
				coords[k++] = new int[] { i, j };
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		makeCombination(0, 0);
		System.out.println(maxSum);
	}

}