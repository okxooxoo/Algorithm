import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 날짜 수
    private static int M; // 디저트 종류의 수

    private static int[][] dp;
    private static int answer;

    private static void solution() {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int today = dp[i][j];
                dp[i][j] = dp[i - 1][j] + today / 2;

                for (int k = 0; k < M; k++) {
                    if (j == k) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + today);
                }
            }
        }

        for (int i = 0; i < M; i++) {
            answer = Math.max(answer, dp[N - 1][i]);
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        dp = new int[N][M];
        answer = 0;

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dp[j][i] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(answer);
    }
}