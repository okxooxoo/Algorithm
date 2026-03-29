import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 지름길의 개수
    private static int D; // 고속도로의 길이

    private static ArrayList<ArrayList<int[]>> graph;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        D = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= D; i++) {
            graph.add(new ArrayList<>());
        }

        dp = new int[D + 1];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            int cost = Integer.parseInt(tokens.nextToken());
            if (x > D || y > D) continue;
            graph.get(x).add(new int[] {y, cost});
            graph.get(y).add(new int[] {x, cost});
        }

        for (int cur = 1; cur <= D; cur++) {
            dp[cur] = dp[cur - 1] + 1;
            for (int[] nxt : graph.get(cur)) {
                int nxtNode = nxt[0];
                int cost = nxt[1];
                if (nxtNode > cur) continue;
                dp[cur] = Math.min(dp[cur], dp[nxtNode] + cost);
            }
        }

        System.out.println(dp[D]);
    }
}