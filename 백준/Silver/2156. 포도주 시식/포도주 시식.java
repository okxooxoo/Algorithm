import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] podos; // 수열
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        podos = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            podos[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                dp[i] = podos[i];
                continue;
            }
            
            if (i == 1) {
                dp[i] = podos[i] + podos[i - 1];
                continue;
            }
            
            if (i == 2) {
                dp[i] += Math.max(dp[i - 1], Math.max(podos[i] + dp[i - 2], podos[i] + podos[i - 1]));
                continue;
            }
            
            dp[i] += Math.max(dp[i - 1], Math.max(podos[i] + dp[i - 2], podos[i] + podos[i - 1] + dp[i - 3]));
        }

        System.out.println(dp[N - 1]);
    }
}