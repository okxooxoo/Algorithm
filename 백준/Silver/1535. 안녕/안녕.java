import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 사람 수
    private static int[] stamina;
    private static int[] delight;

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        stamina = new int[N + 1];
        delight = new int[N + 1];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stamina[i + 1] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            delight[i + 1] = Integer.parseInt(tokens.nextToken());
        }

        dp = new int[N + 1][100];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < 100; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= stamina[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - stamina[i]] + delight[i]);
                }
            }
        }

        System.out.println(dp[N][99]);
    }
}