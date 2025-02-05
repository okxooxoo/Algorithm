import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] matrixInfo;
    private static int[][] dp;

    private static void solution() {
        for (int k = 1; k < N; k++) { // k는 행렬 곱셈 구간의 길이
            for (int i = 0; i + k < N; i++) { // i는 시작 행렬
                dp[i][i + k] = Integer.MAX_VALUE;

                for (int j = i; j < i + k; j++) { // j는 중간에서 나누는 지점
                    dp[i][i + k] = Math.min(dp[i][i + k], dp[i][j] + dp[j + 1][i + k] + matrixInfo[i][0] * matrixInfo[j][1] * matrixInfo[i + k][1]);
                }
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        matrixInfo = new int[N][2];
        dp= new int[N][N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(tokens.nextToken());
            int col = Integer.parseInt(tokens.nextToken());
            matrixInfo[i][0] = row;
            matrixInfo[i][1] = col;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(dp[0][N-1]);
    }
}