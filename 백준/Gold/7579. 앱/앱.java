import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int M;

	private static int[][] apps; // 각 앱의 메모리의 바이트 수, 비용의 쌍
	private static int[] dp;

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		apps = new int[N][2];

		tokens = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			apps[i][0] = Integer.parseInt(tokens.nextToken());
		}

		tokens = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			apps[i][1] = Integer.parseInt(tokens.nextToken());
		}

		dp = new int[M + 1];
		Arrays.fill(dp, 1000000000);
	}

	private static void solve() {
		for (int[] app : apps) {
			int m = app[0]; // 앱의 메모리의 바이트 수
			int c = app[1]; // 앱을 비활성화하는 비용

			for (int i = M; i > 0; i--) {
				if (m >= i) {
					dp[i] = Math.min(dp[i], c);
				} else {
					dp[i] = Math.min(dp[i], c + dp[i - m]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(dp[M]);
	}

}