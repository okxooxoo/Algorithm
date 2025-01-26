import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 수열 S의 길이
    private static int K; // 삭제할 수 있는 최대 횟수

    private static int[] S; // 수열
    private static int maxLen; // 출력하고자 하는 답

    // 홀수인지 여부 반환
    private static boolean isOdd(int num) {
        return num % 2 == 1;
    }

    // 투 포인터
    private static void solution() {
        int left = 0;
        int right = 0;
        // 범위 안 홀수의 수
        int count = (isOdd(S[right]) ? 1 : 0);

        while (true) {
            int len = right - left + 1 - count;

            if (maxLen < len) {
                maxLen = len;
            }

            if (count > K) {
                if (isOdd(S[left])) count--;
                left++;
            } else if (right >= N - 1) {
                return;
            } else {
                right++;
                if (isOdd(S[right])) count++;
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        S = new int[N];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(tokens.nextToken());
        }

        maxLen = 0;
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(maxLen);
    }
}