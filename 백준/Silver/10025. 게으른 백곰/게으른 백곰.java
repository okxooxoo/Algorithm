import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 얼음 양동이 개수
    private static int K; // 닿을 수 있는 거리
    private static int[] ground;
    private static int[] accSum;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        ground = new int[1_000_001];
        accSum = new int[1_000_001];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(tokens.nextToken());
            int x = Integer.parseInt(tokens.nextToken());
            ground[x] = g;
        }

        accSum[0] = ground[0];
        // 누적합 구하기
        for (int i = 1; i <= 1_000_000; i++) {
            accSum[i] = accSum[i - 1] + ground[i];
        }

        int answer = 0;

        for (int i = 0; i <= 1_000_000; i++) {
            int left = Math.max(0, i - K);
            int right = Math.min(1_000_000, i + K);
            int sum = accSum[right] - (left > 0 ? accSum[left - 1] : 0);

            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}