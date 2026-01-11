import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 단원 개수
    private static int T; // 가능한 총 공부 시간
    private static int[][] info; // 단원 별 예상 공부 시간, 배점

    private static int[][] dp;

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        T = Integer.parseInt(tokens.nextToken());

        info = new int[N][2];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(tokens.nextToken());
            info[i][1] = Integer.parseInt(tokens.nextToken());
        }

        dp = new int[N + 1][T + 1];
    }

    private static void solve() {
        for (int i = 1; i <= N; i++) {
            int time = info[i - 1][0];
            int score = info[i - 1][1];

            for (int j = 0; j <= T; j++) {
                // 선택 안함
                dp[i][j] = dp[i - 1][j];

                // 선택함
                if (j >= time) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - time] + score);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(dp[N][T]);
    }

}