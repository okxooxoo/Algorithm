import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static ArrayList<ArrayList<Integer>> tree;
    private static int[][] dp;

    private static void dfs(int cur, int prev) {
        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for (int next : tree.get(cur)) {
            if (next != prev) {
                dfs(next, cur);
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        // 트리 초기화
        tree = new ArrayList<>();
        dp = new int[N + 1][2];

        // 간선 입력 받기
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            tokens = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(tokens.nextToken());
            int v = Integer.parseInt(tokens.nextToken());

            tree.get(u).add(v);
            tree.get(v).add(u);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dfs(1, -1);
        int answer = Math.min(dp[1][0], dp[1][1]);
        System.out.println(answer);
    }
}