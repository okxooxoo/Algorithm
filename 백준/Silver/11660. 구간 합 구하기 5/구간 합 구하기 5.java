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
	private static int[][] table;
	private static int[][] sectionSum;

	// (0, 0)부터 (x, y)까지의 구간 합 계산
	private static void calcSectionSum() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				sectionSum[i][j] += table[i][j];
				sectionSum[i][j] += sectionSum[i][j - 1];
				sectionSum[i][j] += sectionSum[i - 1][j];
				sectionSum[i][j] -= sectionSum[i - 1][j - 1];
			}
		}
	}

	// (x1, y1)부터 (x2, y2)까지의 구간 합 계산
	private static void solution() throws IOException {
		for (int i = 0; i < M; i++) {
			int x1 = 0;
			int y1 = 0;
			int x2 = 0;
			int y2 = 0;

			tokens = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(tokens.nextToken());
			y1 = Integer.parseInt(tokens.nextToken());
			x2 = Integer.parseInt(tokens.nextToken());
			y2 = Integer.parseInt(tokens.nextToken());

			int sum = 0;
			sum += sectionSum[x2][y2];
			sum -= sectionSum[x1 - 1][y2];
			sum -= sectionSum[x2][y1 - 1];
			sum += sectionSum[x1 - 1][y1 - 1];

			sb.append(sum).append("\n");
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		table = new int[N + 1][N + 1];
		sectionSum = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				table[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		calcSectionSum();
		solution();
		System.out.println(sb);
	}

}
