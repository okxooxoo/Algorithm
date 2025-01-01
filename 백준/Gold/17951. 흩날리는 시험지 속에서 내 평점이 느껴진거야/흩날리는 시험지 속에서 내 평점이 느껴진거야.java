import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 시험지의 개수
    private static int K; // 시험지를 나눌 그룹의 수

    private static int[] scores;
    private static int[] accSum; // 맞은 개수의 누적합

    private static final int MAX_SCORE = 2_000_000;

    // K개 만큼 그룹을 나눴을 때 각 그룹의 점수가 score 이상인지 확인
    private static boolean check(int score) {
        int groupCount = 1; // 현재 그룹 수
        int prevIndex = 0; // 마지막으로 자른 인덱스

        for (int i = 1; i < N; i++) {
            if (accSum[i] - accSum[prevIndex] >= score) {
                groupCount++;
                prevIndex = i;
            }
        }

        // 마지막 그룹이 score 이상인지 확인
        if (!(accSum[N] - accSum[prevIndex] >= score)) {
            groupCount--;
        }

        return groupCount >= K;
    }

    private static int binarySearch() {
        int answer = 0; // 받을 수 있는 최대 점수
        int left = 0;
        int right = MAX_SCORE;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        scores = new int[N];
        accSum = new int[N + 1];
        tokens = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            scores[i - 1] = Integer.parseInt(tokens.nextToken());
            accSum[i] = scores[i - 1] + accSum[i - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(binarySearch());
    }
}