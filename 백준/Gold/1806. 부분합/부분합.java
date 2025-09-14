import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer tokens;

    private static int N; // 수열의 길이
    private static int S; // 최소 부분합

    private static int[] numbers;
    private static int[] acc; // 부분합

    // 투 포인터
    private static int solution() {
        int left = 1;
        int right = 1;
        int answer = 100_001;

        while (right <= N) {
            int sum = acc[right] - acc[left - 1];

            if (sum >= S) {
                answer = Math.min(answer, right - left + 1);
                left++;
            } else {
                right++;
            }
        }

        if (answer == 100_001) {
            return 0;
        }

        return answer;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        S = Integer.parseInt(tokens.nextToken());

        numbers = new int[N + 1];
        acc = new int[N + 1];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(tokens.nextToken());
            acc[i] = acc[i - 1] + numbers[i];
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        bw.write(solution() + "\n");
        bw.flush();
        bw.close();
    }
}
