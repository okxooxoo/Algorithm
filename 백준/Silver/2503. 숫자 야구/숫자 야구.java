import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] inputs;
    private static int answer;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        inputs = new int[N][3];
        answer = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            inputs[i][0] = Integer.parseInt(tokens.nextToken());
            inputs[i][1] = Integer.parseInt(tokens.nextToken());
            inputs[i][2] = Integer.parseInt(tokens.nextToken());
        }
    }

    private static boolean isValidBaseballNum(int num) {
        char[] target = String.valueOf(num).toCharArray();

        for (int i = 0; i < N; i++) {
            int strike = 0;
            int ball = 0;
            char[] dest = String.valueOf(inputs[i][0]).toCharArray();

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (target[j] == dest[k]) {
                        if (j == k) strike++;
                        else ball++;
                    }
                }
            }

            if (!(strike == inputs[i][1]) || !(ball == inputs[i][2])) {
                return false;
            }
        }

        return true;
    }

    private static void solve() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                for (int k = 1; k < 10; k++) {
                    if (i == j || i == k || j == k) continue;
                    int num = i * 100 + j * 10 + k; // 세자리 수 생성

                    if (isValidBaseballNum(num)) {
                        answer++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(answer);
    }
}