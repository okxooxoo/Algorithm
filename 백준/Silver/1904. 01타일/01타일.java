import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[i] = 1;
                continue;
            }

            if (i == 2) {
                dp[i] = 2;
                continue;
            }

            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        System.out.println(dp[N]);
    }
}