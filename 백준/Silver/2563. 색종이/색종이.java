import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static boolean[][] paper;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        paper = new boolean[100][100];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            for (int m = a; m < a + 10; m++) {
                if (m >= 100) break;
                for (int n = 99 - b; n > 89 - b; n--) {
                    if (n < 0) break;
                    paper[n][m] = true;
                }
            }
        }

        int answer = 0;
        // 검은 영역의 넓이 세기
        for (int m = 0; m < 100; m++) {
            for (int n = 0; n < 100; n++) {
                if (paper[m][n]) answer++;
            }
        }

        System.out.println(answer);
    }
}