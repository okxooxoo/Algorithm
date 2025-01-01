import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 자르는 횟수의 목록 길이
    private static int M; // 자를 수 있는 지점의 개수
    private static int L; // 롤케이크의 길이

    private static int[] S; // 자를 수 있는 지점
    private static int[] Q; // 자르는 횟수

    // cutCount 만큼 잘라서 모든 조각이 len 이상이 될 수 있는지 여부 반환
    private static boolean check(int len, int cutCount) {
        int count = 0; // 현재 자른 횟수
        int prevCutPoint = 0; // 마지막으로 자른 지점
        int differ = 0;

        for (int i = 0; i < M; i++) {
            differ = S[i] - prevCutPoint;
            if (differ >= len) {
                count++;
                prevCutPoint = S[i];
            }
        }

        // 마지막 조각 len 이상인지 확인
        differ = L - prevCutPoint;
        if (!(differ >= len)) {
            count--;
        }

        return count >= cutCount;
    }

    // cutCount 만큼 잘랐을 때 가장 작은 조각의 길이의 최댓값 반환
    // 가장 작은 조각의 길이의 최댓값을 이진 탐색으로 찾기
    private static int binarySearch(int cutCount) {
        int answer = 0;
        int left = 1;
        int right = L;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid, cutCount)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            int answer = binarySearch(Q[i]);
            sb.append(answer).append("\n");
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());

        S = new int[M];
        Q = new int[N];

        for (int i = 0; i < M; i++) {
            S[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            Q[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(sb);
    }
}