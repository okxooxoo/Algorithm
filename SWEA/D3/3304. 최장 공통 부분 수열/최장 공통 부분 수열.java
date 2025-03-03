import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int[][] dp;
    private static String[] a;
    private static String[] b;
    private static int aLen;
    private static int bLen;

    private static void solution() {
        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (a[i - 1].equals(b[j - 1])) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            tokens = new StringTokenizer(br.readLine());
            a = tokens.nextToken().split("");
            b = tokens.nextToken().split("");
            aLen = a.length;
            bLen = b.length;

            dp = new int[aLen + 1][bLen + 1];
            solution();

            sb.append("#").append(t).append(" ").append(dp[aLen][bLen]).append("\n");
        }
        System.out.println(sb);
    }
}