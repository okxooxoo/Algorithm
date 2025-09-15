import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] inputs;
    private static int[][] dp; // 모든 집을 칠하는 비용

    private static int solution() {
        int answer = Integer.MAX_VALUE;

        // 첫 번째 집의 색상을 고정
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                if (k == j) {
                    dp[0][j] = inputs[0][j];
                } else {
                    dp[0][j] = 1000 * 1000;
                }
            }

            for (int i = 1; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    dp[i][j] = inputs[i][j];
                    dp[i][j] += Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
                }
            }

            for (int j = 0; j < 3; j++) {
                // 첫 번째 집과 N번째 집은 같은 색일 수 없음
                if (k == j) continue;
                answer = Math.min(answer, dp[N - 1][j]);
            }
        }

        return answer;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        inputs = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(tokens.nextToken());
            int green = Integer.parseInt(tokens.nextToken());
            int blue = Integer.parseInt(tokens.nextToken());

            inputs[i][0] = red;
            inputs[i][1] = green;
            inputs[i][2] = blue;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        bw.write(solution() + "\n");
        bw.flush();
        bw.close();
    }
}
