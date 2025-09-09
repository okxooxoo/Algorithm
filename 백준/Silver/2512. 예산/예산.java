import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 지방의 수
    private static int M; // 총 예산
    private static int[] regions; // 각 지방의 예산요청
    private static int maxValue;

    private static boolean isValid(int amount) {
        int total = M; // 총 예산 복제

        for (int i = 0; i < N; i++) {
            total -= Math.min(regions[i], amount);
        }

        return total >= 0;
    }

    private static int solution() {
        int left = 1;
        int right = maxValue;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isValid(mid)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        regions = new int[N];

        maxValue = 0;
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            regions[i] = Integer.parseInt(tokens.nextToken());
            maxValue = Math.max(maxValue, regions[i]);
        }

        M = Integer.parseInt(br.readLine());
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }
}
