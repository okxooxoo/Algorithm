import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int C; // 늘려야 할 고객 수
    private static int N; // 도시의 개수

    private static int[] dp; // dp[i]는 i명의 고객을 늘리기 위한 최소 비용

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        C = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());

        dp = new int[C + 100];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(tokens.nextToken());
            int customer = Integer.parseInt(tokens.nextToken());

            for (int j = customer; j < C + 100; j++) {
                if (dp[j - customer] == Integer.MAX_VALUE) continue;
                dp[j] = Math.min(dp[j], dp[j - customer] + cost);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = C; i < C + 100; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
