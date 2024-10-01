import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int K;

	private static int[][] objects;
	private static int[] dp;

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		objects = new int[N][2];
		dp = new int[K + 1];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			objects[i][0] = Integer.parseInt(tokens.nextToken());
			objects[i][1] = Integer.parseInt(tokens.nextToken());
		}
	}

	private static void solve() {
		for (int[] object : objects) {
			int weight = object[0];
			int value = object[1];

			for (int i = K; i >= weight; i--) {
				dp[i] = Math.max(dp[i], value + dp[i - weight]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(dp[K]);
	}

}