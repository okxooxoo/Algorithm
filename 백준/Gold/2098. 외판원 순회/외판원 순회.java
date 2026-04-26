import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] graph;
    private static int[][] dp;

    private static final int INF = 99999999;

    private static int solve(int current, int visited) {
        if (visited == (1 << N) - 1) {
            if (graph[current][0] > 0) {
                return graph[current][0];
            }

            return INF;
        }

        if (dp[current][visited] != -1) {
            return dp[current][visited];
        }

        dp[current][visited] = INF;

        for (int next = 0; next < N; next++) {
            // 이미 방문했거나 갈 수 없는 경우
            if ((visited & (1 << next)) != 0 || graph[current][next] == 0) {
                continue;
            }

            dp[current][visited] = Math.min(dp[current][visited], graph[current][next] + solve(next, visited | (1 << next)));
        }

        return dp[current][visited];
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        dp = new int[N][(1 << N)];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve(0, 1));
    }
}